package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    	  }
     /**
    *  insert a Policialcase into PolicialCase table
    * @param pc Policialcase to insertar
    * @throws SQLException 
    */
    public boolean insertCase (Policialcase pc) throws SQLException  {
        // TODO add code
    }
}

	 


