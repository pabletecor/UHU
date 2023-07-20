package Persistencia;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;


public class manejaExperto {
    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    

    /**
    * Implementa operaciones sobre la tabla EXPERTO
    * @param c conexión con Oracle
    */    
    public manejaExperto(conexionOracle c) {
      conexion = c;
    }

     /**
    * Devuelve una lista con todos los expertos cuyo país se pase por parámetro
    * @param pais
    * @throws SQLException si ocurre alguna anomalía
    * @return ArrayList<experto>
    */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {
        // TODO implementar operaciones
        
        ArrayList<experto> aux= new ArrayList<experto>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM EXPERTO WHERE pais=?");
            ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                experto exp=new experto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                aux.add(exp);
            }
          //  aux=(ArrayList<experto>) ps;
            
        }
        catch(SQLException e)
        {
            System.out.println("Error en lista de Expertos por Pais");
        }
        finally
        {
            ps.close();
            return aux;
        }
    }    
    /**
    * Comprueba si existe un experto
    * @param codExperto
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeExperto(String codExperto) throws SQLException {
        // TODO implementar operaciones
        boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT codExperto FROM EXPERTO WHERE codExperto=?");
            ps.setString(1, codExperto);
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                existe=true;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error en lista de Expertos por Pais");
        }
        finally
        {
            ps.close();
            return existe;
        }
    }
     /**
    * inserta un experto
    * @param exp
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean insertaExperto(experto exp) throws SQLException {
        
        // TODO implementar operaciones
        
        boolean insertado=false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("cexp: "+exp.getCodExperto());
        if(existeExperto(exp.getCodExperto())!=true)
        {
            
            if(exp.getCodExperto()!=null)
            {
                System.out.println("El experto no existe. Se introducirá uno nuevo.\n");
                ps=conexion.conn.prepareStatement("INSERT INTO EXPERTO (codExperto, nombre, pais, sexo, especialidad) values (?,?,?,?,?) ");
                ps.setString(1, exp.getCodExperto());
                ps.setString(2, exp.getNombre());
                ps.setString(3, exp.getPais());
                ps.setString(4, exp.getSexo());
                ps.setString(5, exp.getEspecialidad());
                ps.executeUpdate();

                insertado=true;
                ps.close();
            }
            else
                System.out.println("No se ha introducido un codigo de experto.");
            
        }
        else if(existeExperto(exp.getCodExperto())==true)
        {
            System.out.println("El Codigo de experto ya existe. NO se pudo introducir.\n");
            
        }

        return insertado; 
    }
    
     public boolean eliminarExperto(String codExperto) throws SQLException
    {
        boolean eliminado=false;
        if(existeExperto(codExperto)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM EXPERTO WHERE codExperto=?");
                ps.setString(1,codExperto);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("El codigo del Experto no es correcto. ");
        }
        return eliminado;
    }
            

    public ArrayList<experto> listaExpertos() throws SQLException {
        
        
        ArrayList<experto> aux= new ArrayList<experto>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM EXPERTO");
            //ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                experto exp=new experto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                aux.add(exp);
            }
          //  aux=(ArrayList<experto>) ps;
            
        }
        catch(SQLException e)
        {
            System.out.println("Error en lista de Expertos por Pais");
        }
        finally
        {
            ps.close();
            return aux;
        }
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int sexoExperto(char caracter) throws SQLException{ 
    
        String fSexoExperto = "{?=call fSexoExperto(?)}";
        String caract = ""+caracter;
        int nsexo=0;
        
        
        try {
            CallableStatement stmt= conexion.conn.prepareCall(fSexoExperto);
            stmt.setString(2, caract);
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.executeUpdate();
            nsexo=stmt.getInt(1);
        } catch (SQLException esql) {
            
            System.out.println("Excepcion capturada en sexoExperto. "+esql.getLocalizedMessage()+"\n"+esql.getMessage());
        }
        
        return nsexo;
    }    

}