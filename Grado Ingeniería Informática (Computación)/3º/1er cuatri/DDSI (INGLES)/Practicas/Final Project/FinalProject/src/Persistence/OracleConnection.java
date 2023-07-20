package Persistence;
import java.sql.*;

public class OracleConnection {
    Connection conn = null;
    
   
    
    public OracleConnection() throws Exception {
        try {	        
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", "ISDD_002", "ISDD_002");
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/rabida", "ISDD_002", "ISDD_002");

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
        conn.setAutoCommit(false);
    }
    /** 
    *  End the transaction with commit
    *@throws SQLException s
    */			
    public void endTransaccionCommit() throws SQLException {
       // TODO add code
       conn.commit();
        conn.setAutoCommit(true);
    }
    /**  
    *  End the transaction with rollback
    @throws SQLException 
    */			
    public void endfTransaccionRollback() throws SQLException {
        // TODO add code
        conn.rollback();
    }

    }
