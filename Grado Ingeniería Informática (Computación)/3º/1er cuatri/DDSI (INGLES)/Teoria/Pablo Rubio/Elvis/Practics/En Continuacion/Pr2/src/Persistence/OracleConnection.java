package Persistence;
import java.sql.*;

public class OracleConnection {
    Connection conn = null;
    
   
    
    public OracleConnection() throws Exception {
        try {	        
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", "", "");
        }
	catch (Exception e)  {
            throw e;    
	}      
    }    

    public void disconnect() throws Exception {
        try {
            conn.close();        
        } 
        catch (Exception e) {
            throw e;
        }       
    }


    /**  
    *  Starts transaction
    *@throws SQLException 
    */
    public void startTransaction() throws SQLException {
        // TODO add code
    }
    /** 
    *  End the transaction with commit
    *@throws SQLException s
    */			
    public void endTransaccionCommit() throws SQLException {
       // TODO add code
    }
    /**  
    *  End the transaction with rollback
    @throws SQLException 
    */			
    public void endfTransaccionRollback() throws SQLException {
        // TODO add code
    }

    }
