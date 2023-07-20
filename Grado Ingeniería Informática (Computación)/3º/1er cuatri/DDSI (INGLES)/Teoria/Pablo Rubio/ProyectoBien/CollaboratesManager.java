package Persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

public class CollaboratesManager {
    // creating an objecto of class "OracleConnection"
    OracleConnection conexion = null;
    
    //creating a PreparedStatement as an atribute for ExpertManager to use it in several methods
    
    PreparedStatement ps = null;
    
     /**
    * Implements operations over the table PolicialCase
    * @param c conexión con Oracle
    */
    public CollaboratesManager(OracleConnection c) {
        conexion = c;
    }
     /**
    * Checks if the collaboration exists in table COLLABORATES
    * @param codExpert, codCase case
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existeColaboracion(String codExpert, String codCase) throws SQLException {
        boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM COLLABORATES WHERE codExpert=? AND codCaso=?");
            ps.setString(1, codExpert);
            ps.setString(2, codCase);
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
            System.out.println("Error on list of Colaboration");
        }
        finally
        {
            ps.close();
        }
            return existe;
    }
    
     /**
    * Insert a new Collaboration
    * @param col
    * @throws SQLException si ocurre alguna anomalía
    */
       public boolean insertaColaboracion(Collaborates col) throws SQLException {
           boolean insertado=false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        if(existeColaboracion(col.getCodEXPERT(),col.getCodCase())==false)
        {
            System.out.println("The colaboration does not exist. A new one willbe introduced.\n");
            ps=conexion.conn.prepareStatement("INSERT INTO COLLABORATES"
                    + " (codExpert, codCaso, dateC, DESCRIPCION) values (?,?,?,?) ");
            ps.setString(1, col.getCodEXPERT());
            ps.setString(2, col.getCodCase());
            ps.setString(3, col.getDate());
            ps.setString(4, col.getDescriptionColaboration());
            ps.executeUpdate();
            
            insertado=true;
            ps.close();
        }
        else if(existeColaboracion(col.getCodEXPERT(),col.getCodCase())==true)
        {
            System.out.println("The code of expert already exist. Unable to introduce.\n");
        }

        return insertado;
        
    } 
       
       
       //////////////////////////////////////
       
    public ResultSet listaColaboradoresPorCaso(String codCase)
    {
        String pCollabCase = "{call PCOLABORATESCASE(?,?)}";
        ResultSet rs=null;
        try {
            
            CallableStatement stmt=conexion.conn.prepareCall(pCollabCase);
            stmt.setString(1, codCase);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.executeUpdate();
            
            rs= stmt.getResultSet();
            rs=(ResultSet) stmt.getObject(2);
            
          
        } catch (SQLException ex) {
           // Logger.getLogger(manejaColabora.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Exception on list ListaColaboradoresPorCaso. "+ex.getMessage());
        }finally{
            return rs;
        }
    }
    
    
     public ArrayList<Collaborates> listaColaboraciones() throws SQLException 
     {
        
        
        ArrayList<Collaborates> aux= new ArrayList<Collaborates>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM COLLABORATES");
            //ps.setString(1, pais);
//            System.out.println("se selecciono  todo de collaborates");
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                Collaborates co=new Collaborates(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                aux.add(co);
            }
          //  aux=(ArrayList<experto>) ps;
            
        }
        catch(SQLException e)
        {
            System.out.println("Error on list of collaborates");
        }
        finally
        {
            ps.close();
            return aux;
        }
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    public boolean eliminarColaboracion(String codEXPERT,String codCase) throws SQLException
    {
        boolean eliminado=false;
        if(existeColaboracion(codEXPERT,codCase)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM COLLABORATES WHERE codExpert=? AND codCaso=?");
                ps.setString(1,codEXPERT);
                ps.setString(2,codCase);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("the code of Expert or Case is not correct. ");
        }
        return eliminado;
    }
     
    
}
