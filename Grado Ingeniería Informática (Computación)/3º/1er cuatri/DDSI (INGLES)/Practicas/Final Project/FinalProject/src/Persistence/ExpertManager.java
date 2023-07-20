package Persistence;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
  
  
    
    public ArrayList<Expert> expertListByCountry(String country) throws SQLException {
        // TODO add code
        ArrayList<Expert> exp = new ArrayList();
        ResultSet rs = null;
        
            String sql = "SELECT * FROM EXPERT WHERE COUNTRY = '" + country + "'";
            ps = conexion.conn.prepareStatement(sql);
            rs = ps.executeQuery();//Revisar el argumento
            
            while (rs.next()) {
                Expert aux = new Expert();
                aux.setCodEXPERT(rs.getString("CODEXPERT"));
                aux.setCountry(rs.getString("COUNTRY"));
                aux.setName(rs.getString("NAME"));
                aux.setSex(rs.getString("SEX"));
                aux.setSpecialism(rs.getString("SPECIALISM"));
                exp.add(aux);
            }
         ps.close();
        
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
           // System.out.println("\n\n" + codigo);
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
    public boolean insertExpert(Expert exp) throws SQLException {
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
    
     /**
     * Lists all the experts in an Arraylist of experts
     * @return An arrayList with all the experts
     * @throws SQLException if something weird happens
     */
    
    public ArrayList<Expert> ExpertList() throws SQLException {
        
        
        ArrayList<Expert> aux= new ArrayList();
        
        try
        {
            //Statement st = conexion.conn.createStatement();
            ps=conexion.conn.prepareStatement("SELECT * FROM EXPERT ORDER BY CODEXPERT");
            //ps.setString(1, pais);
            
            //ps.execute();
            //ps.executeQuery();
            ResultSet rs= ps.executeQuery();
            
            while(rs.next())
            {
                Expert exp=new Expert(rs.getString("CODEXPERT"),rs.getString("NAME"),rs.getString("COUNTRY"),rs.getString("SEX"),rs.getString("SPECIALISM"));
                aux.add(exp);
            }
          
            //st.close();
        }
        
        catch(SQLException e)
        {
            System.out.println("Error on the EXPERTS list");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
        
            //ps.close();
            return aux;
        
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /**
     * Uses the procedure stored in the database
     * @param sex
     * @return Number of experts of each sex
     */
    public int ExpSex(String sex) {                     //We have to do the statement in the sql database
        
        int nsex=0;
        
//        if (!sex.equals('F') || !sex.equals('M') ) {
//            
//            System.out.println("Incorrect caracter");
//            
//        }
        
//        else {
        String FEXPSEX = "{call FEXPSEX(?,?)}";
        String charact = ""+sex;
        
        
        
        try {
            CallableStatement stmt= conexion.conn.prepareCall(FEXPSEX);//fExpSex
            stmt.setString(1, charact);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            nsex=stmt.getInt(2);
            System.out.println("nsex is " + nsex );
        } catch (SQLException esql) {
            
            System.out.println("Exception in ExpSex. "+esql.getLocalizedMessage()+"\n"+esql.getMessage());
        }
        
   //     }
        
        return nsex;
    }    

    }
    
