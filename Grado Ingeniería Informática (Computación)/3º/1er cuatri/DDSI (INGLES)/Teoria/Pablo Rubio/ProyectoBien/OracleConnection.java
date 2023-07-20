package Persistence;
import java.sql.*;
import javax.swing.JOptionPane;

public class OracleConnection {
    Connection conn = null;
    
    /** 
      Makes connection with the server
    @throws Exception in case of error
    */
    public OracleConnection() throws Exception {
        try
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
            }
            catch(Exception ex)
            {
                System.out.println("\nNo can't find the class of the driver");
            }
            
    
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@172.17.20.75:1521:rabida",
                "ISDD_008","ISDD_008");
            
            System.out.println("Conection successfull");
            JOptionPane.showMessageDialog(null, "Conection completed ");
        }  
        catch (SQLException sqle) 
        {
            String mensaje = "";
            System.out.println ("A problem has apear on");
            mensaje =(" Text :"+sqle.getMessage()+"\nUnable to complete conection.");
            JOptionPane.showMessageDialog(null, "Unable to complete conection. ");
            System.out.println (mensaje);
        }
        
    }
      
    /** 
      Disconnection with the server
    @throws SQLException in case of error disconnecting
    */
    public void desconexion() throws SQLException {
        try 
        {
            conn.close();
            System.out.println("Successfull conection");
        } 
        catch (SQLException sqle) 
        {
            
            String mensaje = "";
            System.out.println ("A problem has occur on");
            mensaje =("ErrorCode: "+sqle.getErrorCode()+
            " SQL: "+sqle.getSQLState()+
            " Text :"+sqle.getMessage());
            System.out.println (mensaje);  
        }
    }
    /**  
    *  Starts a transaction
    *@throws SQLException in case of error
    */
    public void startTransaction() throws SQLException {
        // TODO implementar operaciones
        try
        {
            conn.setAutoCommit(true);
        }
        catch(SQLException sqle)
        {
            System.out.println("ERROR in startTransaction() + "+sqle.getMessage());
        }
        
    }
    /** 
    *  End transaction with commint
    *@throws SQLException in case of error
    */			
    public void finTransaccionCommit() throws SQLException {
       // TODO 
       
        try
        {
            conn.commit();
        }
        catch(SQLException sqle)
        {
            System.out.println("Error in finTransaccionCommit() "+sqle.getMessage());
        }
    }
    /**  
    *  End transaction with rollback
    @throws SQLException in case of error
    */			
    public void finTransaccionRollback() throws SQLException {
        // TODO 
        
         try
        {
            conn.rollback();
        }
        catch(SQLException sqle)
        {
            System.out.println("Error in finTransaccionRollback() "+sqle.getMessage());
        }
    }

}
