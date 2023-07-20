package Persistence;
import java.sql.*;

public class oracleConnection {
    public Connection conn = null;
    
    public oracleConnection() throws Exception {
        try {	        
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", "DDSI_002", "DDSI_002");
        }
	catch (Exception e)  {
            throw e;    
	}      
    }    

    public void desconexion() throws Exception {
        try {
            conn.close();        
        } 
        catch (Exception e) {
            throw e;
        }       
    }

}
