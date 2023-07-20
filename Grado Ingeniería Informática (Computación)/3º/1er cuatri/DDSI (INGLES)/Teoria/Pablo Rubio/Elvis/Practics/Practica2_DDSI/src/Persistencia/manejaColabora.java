package Persistencia;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

public class manejaColabora {
    // Creamos un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    
     /**
    * Implementa operaciones sobre la tabla COLABORA
    * @param c conexión con Oracle
    */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }
     /**
    * Comprueba si existe una colaboración en la tabla de COLABORA dado su código
    * @param codExperto, codCaso caso
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existeColaboracion(String codExperto, String codCaso) throws SQLException {
        boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM COLABORA WHERE codExperto=? AND codCaso=?");
            ps.setString(1, codExperto);
            ps.setString(2, codCaso);
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
            System.out.println("Error en lista de Colaboracion");
        }
        finally
        {
            ps.close();
            return existe;
        }
    }
    
     /**
    * Inserta una colaboración en la tabla COLABORA
    * @param codExperto, codCaso caso
    * @throws SQLException si ocurre alguna anomalía
    */
       public boolean insertaColaboracion(colabora col) throws SQLException {
           boolean insertado=false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        if(existeColaboracion(col.getCodExperto(),col.getCodCaso())==false)
        {
            System.out.println("La colaboracion no existe. Se introducirá uno nuevo.\n");
            ps=conexion.conn.prepareStatement("INSERT INTO COLABORA (codExperto, codCaso, fecha, descripcion_colaboracion) values (?,?,?,?) ");
            ps.setString(1, col.getCodExperto());
            ps.setString(2, col.getCodCaso());
            ps.setString(3, col.getFecha());
            ps.setString(4, col.getDescripcionColaboracion());
            ps.executeUpdate();
            
            insertado=true;
            ps.close();
        }
        else if(existeColaboracion(col.getCodExperto(),col.getCodCaso())==true)
        {
            System.out.println("El Codigo de experto ya existe. NO se pudo introducir.\n");
        }

        return insertado;
        
    } 
       
    public ResultSet listaColaboradoresPorCaso(String codCaso)
    {
        String pColaboradoresCaso = "{call pColaboradoresCaso(?,?)}";
        ResultSet rs=null;
        try {
            
            CallableStatement stmt=conexion.conn.prepareCall(pColaboradoresCaso);
            stmt.setString(1, codCaso);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.executeUpdate();
            
            rs= stmt.getResultSet();
            rs=(ResultSet) stmt.getObject(2);
            
          
        } catch (SQLException ex) {
           // Logger.getLogger(manejaColabora.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Excepcion en lista ListaColaboradoresPorCaso. "+ex.getMessage());
        }finally{
            return rs;
        }
    }
    
    
     public ArrayList<colabora> listaColaboraciones() throws SQLException {
        
        
        ArrayList<colabora> aux= new ArrayList<colabora>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM COLABORA");
            //ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                colabora co=new colabora(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                aux.add(co);
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
     
    public boolean eliminarColaboracion(String codExperto,String codCaso) throws SQLException
    {
        boolean eliminado=false;
        if(existeColaboracion(codExperto,codCaso)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM COLABORA WHERE codExperto=? AND codCaso=?");
                ps.setString(1,codExperto);
                ps.setString(2,codCaso);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("El codigo del Experto o el del Caso no es correcto. ");
        }
        return eliminado;
    }
     
    
}
