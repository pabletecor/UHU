package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * clase para trabajar sobre la tabla CASO
 * @author Ignacio Andreu Orta
 */
public class manejaCaso {
    // Crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    
    /**
    * Implementa operaciones sobre la tabla CASO
    * @param c conexión con Oracle
    */
    public manejaCaso(conexionOracle c) {
        conexion = c;
    }
	 
    /**
    * Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código de caso
    * 
    * @param codCaso código del caso a buscar
    * @throws SQLException si ocurre alguna anomalía
    * @return encontrado booleano que indica si existe o no el caso indicado.
    */
    public boolean existeCaso(String codCaso) throws SQLException {
        boolean encontrado = false;
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from CASO_POLICIAL where codCaso = ?");
            prep.setString(1, codCaso);
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
    *  Inserta caso en la tabla de CASO_POLICIAL
    * @param cs caso a insertar
    * @throws SQLException si ocurre alguna anomalía
    */
    public void insertaCaso (caso cs) throws SQLException  {
        try {
            ps = conexion.conn.prepareStatement("insert into CASO_POLICIAL values (?,?,?,?)");
            ps.setString(1, cs.getCodCaso());
            ps.setString(2, cs.getNombre());
            ps.setString(3, cs.getFechaInicio());
            ps.setString(4, cs.getFechaFin());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }


    public ArrayList<caso> listaCasos() throws SQLException{

        ArrayList<caso> caso = new ArrayList();
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from CASO_POLICIAL ORDER BY CODCASO");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                caso col = new caso(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                caso.add(col);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al leer datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }

        return caso;

    }

}
