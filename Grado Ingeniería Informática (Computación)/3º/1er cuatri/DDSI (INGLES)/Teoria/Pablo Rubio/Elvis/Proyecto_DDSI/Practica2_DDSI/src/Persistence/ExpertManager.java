package Persistence;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;


public class ExpertManager {
    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    

    /**
    * Operations on table EXPERTO
    * @param c conection with Oracle
    */    
    public ExpertManager(conexionOracle c) {
      conexion = c;
    }

     /**
    * Returns a list with all experts wich country is pass as parameter
    * @param pais
    * @throws SQLException si ocurre alguna anomalía
    * @return ArrayList<experto>
    */
    public ArrayList<Expert> expertListByCountry(String country) throws SQLException {
        
        
        ArrayList<Expert> aux= new ArrayList<Expert>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM EXPERT WHERE country=?");
            ps.setString(1, country);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                Expert exp=new Expert(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                aux.add(exp);
            }
          //  aux=(ArrayList<experto>) ps;
            
        }
        catch(SQLException e)
        {
            System.out.println("Error on expert list by Country");
        }
        finally
        {
            ps.close();
            return aux;
        }
    }    
    /**
    * Check if exist an Expert
    * @param codExperto
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean expertExists(String codExpert) throws SQLException {
        // TODO implementar operaciones
        boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT codExpert FROM EXPERT WHERE codExpert=?");
            ps.setString(1, codExpert);
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
            System.out.println("Error on list of Expert by Country");
        }
        finally
        {
            ps.close();
            return existe;
        }
    }
     /**
    * Insert Expert
    * @param exp
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean insertaExperto(Expert exp) throws SQLException {
        
        // TODO 
        
        boolean insertado=false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("cexp: "+exp.getCodExperto());
        if(expertExists(exp.getCodExperto())!=true)
        {
            
            if(exp.getCodExperto()!=null)
            {
                System.out.println("Expert does not exist. A new Expert will be insert.\n");
                ps=conexion.conn.prepareStatement("INSERT INTO EXPERT (codExpert, name, country, sex, specialism) values (?,?,?,?,?) ");
                ps.setString(1, exp.getCodExperto());
                ps.setString(2, exp.getName());
                ps.setString(3, exp.getCountry());
                ps.setString(4, exp.getSex());
                ps.setString(5, exp.getSpecialism());
                ps.executeUpdate();

                insertado=true;
                ps.close();
            }
            else
                System.out.println("An expert code has'nt been introduce.");
            
        }
        else if(expertExists(exp.getCodExperto())==true)
        {
            System.out.println("El Codigo de experto ya existe. NO se pudo introducir.\n");
            
        }

        return insertado; 
    }
    
     public boolean eliminarExperto(String codExpert) throws SQLException
    {
        boolean eliminado=false;
        if(expertExists(codExpert)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM EXPERT WHERE codExpert=?");
                ps.setString(1,codExpert);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("The Expert cod is incorrect. ");
        }
        return eliminado;
    }
            

    public ArrayList<Expert> listaExpertos() throws SQLException {
        
        
        ArrayList<Expert> aux= new ArrayList<Expert>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM EXPERTO");
            //ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                Expert exp=new Expert(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                aux.add(exp);
            }
          //  aux=(ArrayList<experto>) ps;
            
        }
        catch(SQLException e)
        {
            System.out.println("Error on the list Experts by Country");
        }
        finally
        {
            ps.close();
            return aux;
        }
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int sexoExperto(char caracter) throws SQLException{ 
    
        String fExpertSex = "{?=call fExpertSex(?)}";
        String caract = ""+caracter;
        int nsexo=0;
        
        
        try {
            CallableStatement stmt= conexion.conn.prepareCall(fExpertSex);
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