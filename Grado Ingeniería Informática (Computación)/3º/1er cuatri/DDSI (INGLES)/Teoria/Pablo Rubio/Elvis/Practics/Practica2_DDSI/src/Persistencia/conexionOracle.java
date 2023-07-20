package Persistencia;
import java.sql.*;
import javax.swing.JOptionPane;

public class conexionOracle {
    Connection conn = null;
    
    /** 
      Establece la conexión con el servidor
    @throws Exception si ocurre cualquier anormalidad
    */
    public conexionOracle() throws Exception {
        try
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
            }
            catch(Exception ex)
            {
                System.out.println("\nNo se puede encontrar la clase del driver");
            }
            
    
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@172.17.20.75:1521:rabida",
                "DDSI_046","35AS5R");
            
            System.out.println("Conexion realizada");
            JOptionPane.showMessageDialog(null, "Conexión realizada correctamente. ");
        }  
        catch (SQLException sqle) 
        {
            String mensaje = "";
            System.out.println ("Atención,se ha producido un problema en");
            mensaje =(" Texto :"+sqle.getMessage()+"\nNo se ha podido realizar la conexion.");
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la conexion. ");
            System.out.println (mensaje);
        }
        
    }
      
    /** 
      Implementa la desconexión con el servidor
    @throws SQLException si ocurre cualquier anormalidad
    */
    public void desconexion() throws SQLException {
        try 
        {
            conn.close();
            System.out.println("Desconexion realizada");
        } 
        catch (SQLException sqle) 
        {
            
            String mensaje = "";
            System.out.println ("Atención,se ha producido un problema en");
            mensaje =("codigo: "+sqle.getErrorCode()+
            " SQL: "+sqle.getSQLState()+
            " Texto :"+sqle.getMessage());
            System.out.println (mensaje);  
        }
    }
    /**  
    *  Inicia una transacción
    *@throws SQLException si ocurre cualquier anormalidad
    */
    public void inicioTransaccion() throws SQLException {
        // TODO implementar operaciones
        try
        {
            conn.setAutoCommit(true);
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en inicio de transaccion."+sqle.getMessage());
        }
        
    }
    /** 
    *  Finaliza una transacción con commint
    *@throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionCommit() throws SQLException {
       // TODO implementar operaciones
       
        try
        {
            conn.commit();
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en fin de transaccion (COMMIT)."+sqle.getMessage());
        }
    }
    /**  
    *  Finaliza una transacción con rollback
    @throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionRollback() throws SQLException {
        // TODO implementar operaciones
        
         try
        {
            conn.rollback();
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en fin de transaccion (ROLLBACK)."+sqle.getMessage());
        }
    }

}
