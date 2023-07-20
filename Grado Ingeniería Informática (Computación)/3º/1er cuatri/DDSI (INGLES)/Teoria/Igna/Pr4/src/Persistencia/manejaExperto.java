package Persistencia;

import java.sql.*;
import java.util.ArrayList;
/**
 * clase para trabajar sobre la tabla EXPERTO
 * @author Ignacio Andreu Orta
 */
public class manejaExperto {

    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla EXPERTO
     *
     * @param c conexión con Oracle
     */
    public manejaExperto(conexionOracle c) {
        conexion = c;
    }

    /**
     * Devuelve una lista con todos los expertos cuyo país se pase por parámetro
     *
     * @param pais pais por el que filtrar
     * @throws SQLException si ocurre alguna anomalía
     * @return expertos ArrayList de expertos ya filtrados
     */   
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {

        ArrayList<experto> expertos = new ArrayList();
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from EXPERTO where pais = ?");
            prep.setString(1, pais);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                experto exp = new experto(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                expertos.add(exp);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al leer datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }

        return expertos;

    }
    /**
     * Devuelve una lista con todos los expertos de la BD
     * 
     * @return expertos ArrayList con todos los expertos de la tabla
     * @throws SQLException si ocurre alguna anomalía
     */
    
    public ArrayList<experto> listaExpertos() throws SQLException{

        ArrayList<experto> expertos = new ArrayList();
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from EXPERTO ORDER BY CODEXPERTO");
            //prep.setString(1, pais);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                experto exp = new experto(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                expertos.add(exp);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al leer datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }

        return expertos;

    }
    /**
     * Comprueba si existe un experto
     *
     * @param codExperto codigo del experto a buscar
     * @throws SQLException si ocurre alguna anomalía
     * @return encontrado un booleano que indica si se ha encontrado el experto
     */
    public boolean existeExperto(String codExperto) throws SQLException {
        boolean encontrado = false;
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from EXPERTO where codExperto = ?");
            prep.setString(1, codExperto);
            ResultSet rs = prep.executeQuery();
            if (rs.next()==true) {
                encontrado = true;
            }
        st.close();    
        }catch (SQLException e){
            System.out.println("Error al insertar datos de la tabla");
        }
        return encontrado;
    }

    /**
     * inserta un experto
     *
     * @param exp experto a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaExperto(experto exp) throws SQLException {
        try {
           
            ps = conexion.conn.prepareStatement("insert into EXPERTO values (?,?,?,?,?)");
            ps.setString(1, exp.getCodExperto());
            ps.setString(2, exp.getNombre());
            ps.setString(3, exp.getPais());
            ps.setString(4, exp.getSexo());
            ps.setString(5, exp.getEspecialidad());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }
    
    /**
     * Cuenta el numero de expertos de la BD que pertenecan al sexo indicado
     * @param sexo sexo quese quiere contar
     * @return resultado entero con el numero de expertos de dicho sexo.
     * @throws SQLException si ocurre alguna anomalía
     */
    public int sexoExperto(String sexo) throws SQLException{
        String caract = sexo;
        String FEXPERTSEX = "{call DDSI_002.fsexoexperto1(?,?)}";

        int nsexo= 0;
        try{
            CallableStatement stmt= conexion.conn.prepareCall(FEXPERTSEX);
            stmt.setString(1, caract);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            nsexo=stmt.getInt(2);
            System.out.println("nsexo es " + nsexo );
        } catch (SQLException esql) {
            
            System.out.println("Excepcion de sexoExperto. "+esql.getLocalizedMessage()+"\n"+esql.getMessage());
        }
        
        return nsexo;
    }
}


/*
    public int contarPorSexo(String s) throws SQLException
    {
        CallableStatement cs = null;
        String sql = "{ ? = call fExpertoPorSexo(?)";
        cs = con.co.prepareCall(sql);
        cs.setString(2, s);
        cs.registerOutParameter(1, Types.INTEGER); //devuelve el entero
        cs.executeUpdate();
        return cs.getInt(1);
    }
*/