package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class PolicialcaseManager {
    // creating an object of class "OracleConnection"
    OracleConnection conexion = null;
    
    // creating a PreparedStatement as an atribute for ExpertManager to use it in several methods

    PreparedStatement ps = null;

    
    /**
    * Implemements operations over the table PolicialCASE
    * @param c connection with Oracle
    */
    public PolicialcaseManager(OracleConnection c) {
        conexion = c;
	    }
	 
     /**
    *  Check if the Case exists in the POLICIALCASE table
    * @param codCase id  of Policialcase to find
    * @throws SQLException 
    */
    public boolean existeCaso(String codCase) throws SQLException {
         // TODO add code
        boolean existe=false;
        ResultSet result = null;
        //Seleccionamos los codigos de experto de la tabla EXPERT
        String sql = "select CODCASO from POLICIALCASE";
        ps = conexion.conn.prepareStatement(sql);
        result=ps.executeQuery();
        
        //Mientras no se acaben los codigos, comparamos con el parametro
        while(result.next() && !existe)
        {
        String codigo = result.getString("CODCASO");
        
        if(codigo.equals(codCase)){
        existe = true;
        
        }
       
        }
        
        return existe;
        
    	  }
     /**
    *  insert a Policialcase into PolicialCase table
     * @param cs
     * @return 
    * @throws SQLException 
     * @throws java.text.ParseException 
    */
  
    
       public boolean insertaCaso (Policialcase cs) throws SQLException, ParseException  {
        boolean insertado = false;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Date Start: "+cs.getFechaInicio()+", Date End: "+cs.getFechaFin());

        if(existeCaso(cs.getCodCaso())==false)
        {
            System.out.println("The case does not exist. A new one will be introduce.\n");
            ps=conexion.conn.prepareStatement("INSERT INTO POLICIALCASE (codCaso, name, startDate, endDate) values (?,?,?,?) ");    
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
            System.out.println("The Expertt Code already exist. Unable to introduce .\n");
          
        }

        return insertado;
    }
    
       /**/

    /**
     * Deletes a case of the PolicialCase table
     * @param codCas
     * @return 
     * @throws java.sql.SQLException
     */

    public boolean eliminarCaso(String codCas) throws SQLException
    {
        boolean eliminado=false;
        if(existeCaso(codCas)==true)
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
    
    /**/

    /**
     * Lists all the policialCases in an Arraylist of policialCases
     * @return 
     * @throws java.sql.SQLException
     */

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
            return aux;
        }
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

	 


