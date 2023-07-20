package Persistence;

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


public class PolicialcaseManager {
    // Crea un objeto de tipo "OracleConnection"
    OracleConnection conexion = null;
    
    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    
    /**
    * Implementa operaciones sobre la tabla CASO
    * @param c conexión con Oracle
    */
    public PolicialcaseManager(OracleConnection c) {
        conexion = c;
	    }

	 
     /**
    *  Checks if exist a case on the table POLICIAL_CASE given a codCase
    * @param codCase code of the case wanted
     * @return 
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existsCase(String codCase) throws SQLException {
       boolean existe=false;
        try
        {
            ps=conexion.conn.prepareStatement("SELECT codCaso FROM POLICIALCASE WHERE codCaso=?");
            ps.setString(1, codCase);
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
            System.out.println("Error on list of Case ");
        }
        finally
        {
            ps.close();
        }
            return existe;
    }
     /**
    *  Insert a case on the table POLICIAL_CASE
    * @param cs case to be insert
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean insertCase (Policialcase cs) throws SQLException, ParseException  {
        boolean insertado = false;
        int contador=0;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Date Start: "+cs.getStartDate()+", Date End: "+cs.getEndDate());

        if(existsCase(cs.getCodCase())==false)
        {
            System.out.println("The case does not exist. A new one will be introduce.\n");
            ps=conexion.conn.prepareStatement("INSERT INTO POLICIALCASE (codCaso, name, startDate, endDate) values (?,?,?,?) ");    
            ps.setString(1, cs.getCodCase());
            ps.setString(2, cs.getName());
            ps.setString(3, cs.getStartDate());
            ps.setString(4, cs.getEndDate());
            ps.executeUpdate();
            
            insertado=true;
            ps.close();
        }
        else if(existsCase(cs.getCodCase())==true)
        {
            System.out.println("The Expertt Code already exist. Unable to introduce .\n");
          
        }

        return insertado;
    }
    
    public boolean eliminarCaso(String codCas) throws SQLException
    {
        boolean eliminado=false;
        if(existsCase(codCas)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM POLICIALCASE WHERE codCaso=?");
                ps.setString(1,codCas);
                ps.executeUpdate();
                eliminado=true;
                ps.close();
        }
        else
        {
            System.out.println("The code of case its incorrect. ");
        }
        return eliminado;
    }
    
    
     public ArrayList<Policialcase> listaCasos() throws SQLException 
     {
        
        
        ArrayList<Policialcase> aux= new ArrayList<Policialcase>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM POLICIALCASE");
            //ps.setString(1, pais);
            
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                Policialcase ca=new Policialcase(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                aux.add(ca);
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("Error on list of cases");
        }
        finally
        {
            ps.close();
        }
            return aux;
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

	 


