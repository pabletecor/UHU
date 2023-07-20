package Persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

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
     * @param codCaso
     * @return 
     * @throws SQLException SQLException if something weird happens
     */
    public boolean existeColaboracion(String codExpert, String codCaso) throws SQLException {
        // TODO add code
        
        boolean existe = false;
        ResultSet resultExp = null;
        ResultSet resultCase = null;
        //Seleccionamos los codigos de experto de la tabla EXPERT
        String sql = "select CODEXPERT from EXPERT";
        ps = conexion.conn.prepareStatement(sql);
        resultExp = ps.executeQuery();
        //Seleccionamos los codigos de experto de la tabla POLICIALCASE
        sql = "select CODCASO from POLICIALCASE";
        ps = conexion.conn.prepareStatement(sql);
        resultCase = ps.executeQuery();

        //Mientras no se acaben los codigos, comparamos con el parametro
        while (resultExp.next() && !existe) {
            String codigoExp = resultExp.getString("CODEXPERT");

            while (resultCase.next() && !existe) {

                String codigoCase = resultCase.getString("CODCASO");
                
                if (codigoExp.equals(codExpert) && codigoCase.equals(codCaso)) {
                    
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
     * @throws SQLException SQLException if something weird happens
     */
    public boolean insertaColaboracion(Collaborates col) throws SQLException {
        // TODO add code
       if (existeColaboracion(col.getCodExperto(), col.getCodCaso())) {
           System.out.println("THE COLAB WITH EXPERT CODE" + col.getCodExperto() + " AND "
                   + "CASE CODE " + col.getCodCaso() + " ALREADY EXISTS");
           return false;
       
        } else {
            //CREO UN STATEMENT PARA INSERTAR
            

            String ins = "INSERT INTO COLLABORATES (CODEXPERT,CODCASO,DATEC,DESCRIPCION) VALUES ('" + col.getCodExperto() + "' , '" + col.getCodCaso() + "' , '" + col.getFecha() + "' , '"
                    + col.getDescripcionColaboracion() + "' )";

            //Insertamos en la tabla
            ps = conexion.conn.prepareStatement(ins);
            
            //Insertamos en la tabla
            ps.executeUpdate();

            return true;
        }

    }
    
        public ResultSet CollaboratesbyCase (String codCase)    
    {
        String pCollabCase = "{call PCOLABORATESCASE(?,?)}";
        ResultSet rs=null;
        try {
            
            CallableStatement stmt=conexion.conn.prepareCall(pCollabCase);
            stmt.setString(1, codCase);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.executeUpdate();
            
            rs= stmt.getResultSet();
            rs=(ResultSet) stmt.getObject(2);
            
          
        } catch (SQLException ex) {
           // Logger.getLogger(manejaColabora.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Exception on list ListaColaboradoresPorCaso. "+ex.getMessage());
        }finally{
            return rs;
        }
    }
    
     /**
     * Lists all the collaborates in an Arraylist of collaborates
     * @return An arrayList with all the collaborates
     * @throws SQLException if something weird happens
     */
    
     public ArrayList<Collaborates> CollaboratesList() throws SQLException 
     {
        
        
        ArrayList<Collaborates> aux= new ArrayList<Collaborates>();
        try
        {
            ps=conexion.conn.prepareStatement("SELECT * FROM COLLABORATES");
 
            ps.execute();
            ps.executeQuery();
            ResultSet rs= ps.getResultSet();
            while(rs.next())
            {
                Collaborates co=new Collaborates(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                aux.add(co);
            }
     
            
        }
        catch(SQLException e)
        {
            System.out.println("Error on list COLLABORATES  ");
        }
        finally
        {
            ps.close();
            
        }
        return aux;
        
    }
     
    /**
     * Delete the fist collaborate that is found with those parameters as values
     * @return true if the delete is correct, false in the other case
     * @throws SQLException if something weird happens
     */ 
    
    public boolean deleteCollaborate(String codEXPERT,String codCase) throws SQLException
    {
        boolean delete=false;
        if(existeColaboracion(codEXPERT,codCase)==true)
        {
                ps=conexion.conn.prepareStatement("DELETE FROM COLLABORATES WHERE CODEXPERT=? AND CODCASO=?");
                ps.setString(1,codEXPERT);
                ps.setString(2,codCase);
                ps.executeUpdate();
                delete=true;
                ps.close();
        }
        else
        {
            System.out.println("the code of Expert or Case is not correct. ");
        }
        return delete;
    }
     
    
}
