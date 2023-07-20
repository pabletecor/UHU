package Persistencia;

import java.sql.*;

public class conexionOracle {

    public Connection conn = null;

    /**
     * Establece la conexión con el servidor
     *@param usu usuario para entrar en la BD
     * @param cont contraseña para entrar en la BD
     * @throws Exception si ocurre cualquier anormalidad
     */
    public conexionOracle(String usu, String cont) throws Exception {
//        Class.forName("oracle.jdbc.OracleDriver");
//        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", usu, cont);
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/rabida", "ISDD_002", "ISDD_002");
    }
    
    /**
     * Implementa la desconexión con el servidor
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void desconexion() throws SQLException {
        conn.close();
    }

    /**
     * Implementa la desconexión con el servidor
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void inicioTransaccion() throws SQLException {
        conn.setAutoCommit(false);
    }
    
    /**
     * Inicia una transacción
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    
    /**
     * Finaliza una transacción que se ha realizado correctamente
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionCommit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    /**
     * Aborta una transacción mediante un rollback
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionRollback() throws SQLException {
        conn.rollback();
    }

}
