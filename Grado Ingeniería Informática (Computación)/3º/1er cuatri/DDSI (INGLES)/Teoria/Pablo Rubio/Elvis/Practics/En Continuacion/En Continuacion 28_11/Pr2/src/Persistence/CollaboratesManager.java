package Persistence;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CollaboratesManager {
   // creating an object of class "OracleConnection"
    OracleConnection conexion = null;
    
    // creating a PreparedStatement as an atribute for ExpertManager to use it in several methods
    PreparedStatement ps = null;
    
     /**
    * Implemements operations over the table PolicialCASE
    * @param c conexión con Oracle
    */
    public CollaboratesManager(OracleConnection c) {
        conexion = c;
    }
     /**
    * Check if the collaboration exists in table COLLABORATES
    * @param codExpert, codCase
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existeColaboracion(String codExpert, String codCaso) throws SQLException {
        
        // TODO add code
    }
    
     /**
    * Insert a new Collaboration
    * @param col 
    * @throws SQLException si ocurre alguna anomalía
    */
       public boolean insertaColaboracion(Collaborates col) throws SQLException {
           // TODO add code
        
    } 
}
