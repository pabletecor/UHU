package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ExpertManager {
    // creating an object of class "OracleConnection"
    OracleConnection conexion = null;
    
     // creating a PreparedStatement as an atribute for ExpertManager to use it in several methods
    PreparedStatement ps = null;
    

    /**
    * Implements operations over Expert table
    * @param c  OracleConnection
    */    
    public ExpertManager(OracleConnection c) {
      conexion = c;
    }
     /**
    * REturnn a list with all the experts with a nationality
    * @param country
    * @throws SQLException 
    * @return ArrayList<expert>
    */
    public ArrayList<Expert> expertListByCountry(String country) throws SQLException {
        // creting result set
        Statement stm=null;
        ResultSet resulSet=stm.executeQuery("select * from COUNTRIES");
        //Iterating throught the result rows
        Expert exp=new Expert();
        ArrayList<Expert> sol=new ArrayList<Expert>();
        while (resulSet.next()) {            
                    
            exp.setCountry(resulSet.getString("POPULATION"));
            exp.setCountry(resulSet.getString("NAME"));
            exp.setCountry(resulSet.getString("SEX"));
            sol.add(exp); 
            String name=resulSet.getString("NAME");
            int population=resulSet.getInt("POPULATION");
            
            System.out.println("NAME" + name);
            System.out.println("POPULATION" + population);
            
            //accesin colums values by index or name
            
            
        }
        return sol;
        
    }    
    /**
    * Check if the expert exists
    * @param codExpert
     * @return 
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean expertExists (String codExpert) throws SQLException 
    {
        // TODO add code
        Statement stm=null;
        ResultSet resulSet=stm.executeQuery("select * from EXPERT where codEpert=:codExpert");
        Expert exp=new Expert();
        //ArrayList<Expert> sol=new ArrayList<Expert>();
        while (resulSet.next())
        {
            //Terminar
        }
        
    }
     /**
    * Insert a new Expert
    * @param exp
    * @throws SQLException 
     */
    public boolean insertaExperto(Expert exp) throws SQLException 
    {
        // TODO add code
        try{
        PreparedStatement ps = conexion.conn.prepareStatement("Insert into expert values (?,?,?,?,?)");
        ps.setString(1, "E111");
        ps.setString(2, "name1");
        ps.setString(3,"country1");
        ps.setString(4,"M");
        ps.setString(5, "computers");
        ps.executeUpdate();
       
       }
       catch (SQLException e1){
           System.out.println(e1.getMessage());
       }
       finally {
           return true;
       }
    }
}