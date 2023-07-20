package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
         boolean existe=false;
        //Seleccionamos los codigos de experto de la tabla EXPERT
        ResultSet result = ps.executeQuery("select CODCASO from POLICIALCASE");
        
        //Mientras no se acaben los codigos, comparamos con el parametro
        while(result.next() && !existe)
        {
        String codigo = result.getString("CODCASO");
        
        if(codigo.equals(codCase)){
        existe = true;
        
        }
       
        }
        
        return existe;
    	  }
     /**
    *  insert a Policialcase into PolicialCase table
    * @param pc Policialcase to insertar
    * @throws SQLException 
    */
    public boolean insertCase (Policialcase pc) throws SQLException  {
        // TODO add code
        if(!existeCaso(pc.getCodCaso()))
        {
            System.out.println("EL CASO CON CODIGO" + pc.getCodCaso() + " no existe");
            return false;
        }
        
        else
        {
            //CREO UN STATEMENT PARA INSERTAR
            Statement insert = conexion.conn.createStatement();
            
            String ins = "INSERT INTO EXPERT (CODCASO,NAME,STARTDATE,ENDDATE) VALUES (' " + pc.getCodCaso() + " ' , ' " + pc.getNombre()+ " ' , ' " + pc.getFechaInicio()+ " ' , ' " 
                         + pc.getFechaFin() + "' )";
       
            //Insertamos en la tabla
            insert.executeUpdate(ins);
        
            return true;
        }
         
        
    }
}

	 


