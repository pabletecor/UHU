package Persistence;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CollaboratesManager {
    // creating an object of class "OracleConnection"

    OracleConnection conexion = null;

    // creating a PreparedStatement as an atribute for ExpertManager to use it in several methods
    PreparedStatement ps = null;

    /**
     * Implemements operations over the table PolicialCASE
     *
     * @param c conexión con Oracle
     */
    public CollaboratesManager(OracleConnection c) {
        conexion = c;
    }

    /**
     * Check if the collaboration exists in table COLLABORATES
     *
     * @param codExpert, codCase
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeColaboracion(String codExpert, String codCaso) throws SQLException {
        // TODO add code
        boolean existe = false;
        //Seleccionamos los codigos de experto de la tabla EXPERT
        ResultSet resultEXPERT = ps.executeQuery("select CODEXPERT from EXPERT");
        ResultSet resultCASE = ps.executeQuery("select CODCASO from POLICIALCASE");

        //Mientras no se acaben los codigos, comparamos con el parametro
        while (resultEXPERT.next() && !existe) {
             String codigoEXP = resultEXPERT.getString("CODEXPERT");
            while (resultEXPERT.next() && !existe) {
               String codigoCASO = resultCASE.getString("CODCASE");

                if (codigoEXP.equals(codExpert) && codigoCASO.equals(codCaso) ) {

                    existe = true;

                }

            }
        }

        return existe;
    }

    /**
     * Insert a new Collaboration
     *
     * @param col
     * @return 
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean insertaColaboracion(Collaborates col) throws SQLException {
        // TODO add code
        if(!existeColaboracion(col.getCodExperto(),col.getCodCaso()))
        {
            System.out.println("LA COLABORACION CON CODIGO DE EXPERTO" + col.getCodExperto() + " Y "
                    + "CODIGO DE CASO" + col.getCodCaso()+ " NO EXISTE");
            return false;
        }
        
        else
        {
            //CREO UN STATEMENT PARA INSERTAR
            Statement insert = conexion.conn.createStatement();
            
            String ins = "INSERT INTO COLLABORATES (CODEXPERT,CODCASO,DATEC,DESCRIPTION) VALUES (' " + col.getCodExperto() + " ' , ' " + col.getCodCaso() + " ' , ' " + col.getFecha() + " ' , ' " 
                         + col.getDescripcionColaboracion() + "' )";
       
            //Insertamos en la tabla
            insert.executeUpdate(ins);
        
            return true;
        }
         
        
        
    }
}
