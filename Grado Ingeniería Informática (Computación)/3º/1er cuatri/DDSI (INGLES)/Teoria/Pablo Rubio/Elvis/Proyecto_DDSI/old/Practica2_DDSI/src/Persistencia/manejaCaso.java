package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
//import java.sql.Date;
import java.util.Scanner;


public class manejaCaso {
    // Crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    
    /**
    * Implementa operaciones sobre la tabla CASO
    * @param c conexión con Oracle
    */
    public manejaCaso(conexionOracle c) {
        conexion = c;
	    }

	 
     /**
    *  Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código de caso
    * @param codCaso código del caso a buscar
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existeCaso(String codCaso) throws SQLException {
       boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM CASO_POLICIAL WHERE codCaso=?");
            ps.setString(1, codCaso);
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                existe=true;
            }
            
           /* if(codExperto==ps.executeQuery().getNString(1));
            {
                System.out.println("El experto con ese codigo ya existe");
                existe=true;
            }*/
                
        }
        catch(SQLException e)
        {
            System.out.println("Error en lista de Casos ");
        }
        finally
        {
            ps.close();
            return existe;
        }
    }
     /**
    *  Inserta caso en la tabla de CASO_POLICIAL
    * @param cs caso a insertar
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean insertaCaso (caso cs) throws SQLException, ParseException  {
        boolean insertado = false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Fecha Ini: "+cs.getFechaInicio()+", Fecha Fin: "+cs.getFechaFin());

        if(existeCaso(cs.getCodCaso())==false)
        {
            System.out.println("El caso no existe. Se introducirá uno nuevo.\n");
            ps=conexion.conn.prepareStatement("INSERT INTO CASO_POLICIAL (codCaso, nombre, fecha_inicio, fecha_fin) values (?,?,?,?) ");    
            ps.setString(1, cs.getCodCaso());
            ps.setString(2, cs.getNombre());
            ps.setString(3, cs.getFechaInicio());
            ps.setString(4, cs.getFechaFin());
            ps.executeUpdate();
            
            insertado=true;
            ps.close();
        }
        else if(existeCaso(cs.getCodCaso())==true)
        {
            System.out.println("El Codigo de experto ya existe. NO se pudo introducir.\n");
          
        }

        return insertado;
    }
    
    public boolean eliminarCaso(String codCas) throws SQLException
    {
        boolean eliminado=false;
        if(existeCaso(codCas)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM CASO_POLICIAL WHERE codCaso=?");
                ps.setString(1,codCas);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("El codigo del caso no es corecto. ");
        }
        return eliminado;
    }
    
    
     public ArrayList<caso> listaCasos() throws SQLException {
        
        
        ArrayList<caso> aux= new ArrayList<caso>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM CASO_POLICIAL");
            //ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                caso ca=new caso(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                aux.add(ca);
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("Error en lista de Casos");
        }
        finally
        {
            ps.close();
            return aux;
        }
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

	 


