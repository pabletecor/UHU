package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ExpertManager {
    // creating an object of class "OracleConnection"
    OracleConnection conexion = null;
    
     // creating a PreparedStatement as an atribute for ExpertManager to use it in several methods
    PreparedStatement ps = null;
    

    /**
    * Implements operations over Expert table
    * @param c  OracleConnection
    */    
    public ExpertManager(OracleConnection c) {
      conexion = c;
    }
     /**
    * REturnn a list with all the experts with a nationality
    * @param country
    * @throws SQLException 
    * @return ArrayList<expert>
    */
    
    //Hecho por mi
    
    /*
    public ArrayList<Expert> expertListByCountry(String country) throws SQLException {
        // TODO add code

        ArrayList<Expert> expList = new ArrayList();
        //CREAMOS LA SENTENCIA A EJECUTAR
        ResultSet rs = ps.executeQuery("SELECT * FROM EXPERTS WHERE EXPERTS.COUNTRY = '" + country + "'");

        //
        while (rs.next()) {
            Expert exp = new Expert();
            exp.setCodEXPERT("CODEXPERT");
            exp.setCountry("COUNTRY");
            exp.setName("NAME");
            exp.setSex("SEX");
            exp.setSpecialism("SPECIALISM");
            expList.add(exp);

        }
        
        ps.close();
        
        return expList;
    }
    
    */
    
    //Hecho por Jose
    
    public ArrayList<Expert> expertListByCountry(String country) throws SQLException {
        // TODO add code
        ArrayList<Expert> exp = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM EXPERT WHERE EXPERT.COUNTRY = '" + country + "'";
            ps = conexion.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Expert aux = new Expert();
                aux.setName(rs.getString("NAME"));
                aux.setCodEXPERT(rs.getString("CODEXPERT"));
                aux.setSex(rs.getString("SEX"));
                aux.setCountry(rs.getString("COUNTRY"));
                aux.setSpecialism(rs.getString("SPECIALISM"));
                exp.add(aux);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            ps.close();
        }
        return exp;
    }

    
    
    /**
    * Check if the expert exists
    * @param codExpert
     * @return 
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean expertExists (String codExpert) throws SQLException {
        // TODO add code
        boolean existe=false;
        ResultSet result = null;
        //Seleccionamos los codigos de experto de la tabla EXPERT
        String sql = "select CODEXPERT from EXPERT";
        ps = conexion.conn.prepareStatement(sql);
        result=ps.executeQuery();
        
        //Mientras no se acaben los codigos, comparamos con el parametro
        while(result.next() && !existe)
        {
        String codigo = result.getString("CODEXPERT");
        
        if(codigo.equals(codExpert)){
        existe = true;
        
        }
       
        }
        
        return existe;
    }
     /**
    * Insert a new Expert
    * @param exp
     * @return 
    * @throws SQLException 
     */
    public boolean insertaExperto(Expert exp) throws SQLException {
        // TODO add code
        boolean result=false;
        
        if(expertExists(exp.getCodEXPERT()))
        {
            System.out.println("THE EXPERT WITH CODE" + exp.getCodEXPERT() + " DOES ALREADY EXIST");
            
        }
        
        else
        {
            //CREO UN STATEMENT PARA INSERTAR
            
            
            String ins = "INSERT INTO EXPERT (CODEXPERT,NAME,COUNTRY,SEX,SPECIALISM) VALUES (?,?,?,?,?)";
       
            //Insertamos en la tabla
            ps = conexion.conn.prepareStatement(ins);
            ps.setString(1, exp.getCodEXPERT());
            ps.setString(2, exp.getName());
            ps.setString(3, exp.getCountry());
            ps.setString(4, exp.getSex());
            ps.setString(5, exp.getSpecialism());
         
            result= ps.executeUpdate()>0;
            
             
        }
         
       return result;
        
    }
}