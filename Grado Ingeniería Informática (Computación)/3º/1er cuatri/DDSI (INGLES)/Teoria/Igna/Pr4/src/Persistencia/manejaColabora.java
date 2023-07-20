package Persistencia;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import oracle.jdbc.OracleTypes;

/**
 * clase para trabajar sobre la tabla COLABORA
 * @author Ignacio Andreu Orta
 */

public class manejaColabora {
    // Creamos un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    
     /**
    * Implementa operaciones sobre la tabla COLABORA
    * @param c conexión con Oracle
    */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }
    
     /**
    * Comprueba si existe una colaboración en la tabla de COLABORA dado su código
    * @param codExperto codigo del experto  a comprobar
    * @param codCaso codigo del caso a comporbar
    * @throws SQLException si ocurre alguna anomalía
    * @return encontrado booleano que indica si existe o no la colaboración.
    */
    public boolean existeColaboracion(String codExperto, String codCaso) throws SQLException {
    boolean encontrado = false;
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from COLABORA where codExperto = ? and codCaso = ?");
            prep.setString(1, codExperto);
            prep.setString(2, codCaso);
            ResultSet rs = prep.executeQuery();
            if (rs.next()==true) {
                encontrado = true;
            }
            st.close();    
        }catch (SQLException e){
            System.out.println("Error al insertar datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
        return encontrado;
    }
    
    /**
     * Devuelve una lista con todos los expertos de la BD
     * 
     * @return colabora ArrayList de todas las colaboraciones
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<colabora> listaColabora() throws SQLException{

        ArrayList<colabora> colabora = new ArrayList();
        try {
            Statement st = conexion.conn.createStatement();
            PreparedStatement prep = null;
            prep = conexion.conn.prepareStatement("select * from COLABORA ORDER BY CODCASO");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                colabora col = new colabora(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                colabora.add(col);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al leer datos de la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }

        return colabora;

    }
            
     /**
    * Inserta una colaboración en la tabla COLABORA
    * @param col colaboración a insertar
    * @throws SQLException si ocurre alguna anomalía
    */
    public void insertaColaboracion(colabora col) throws SQLException {
        conexion.inicioTransaccion();
        if(!existeColaboracion(col.getCodExperto(), col.getCodCaso())){
            manejaCaso Caso = new manejaCaso(conexion);
            manejaExperto Experto = new manejaExperto(conexion);
            Scanner sc = new Scanner(System.in);
            if(!Experto.existeExperto(col.getCodExperto())){
                String nom, pais, sex, esp;
                System.out.print("Datos del Experto");
                System.out.println("Nombre: ");
                nom = sc.nextLine();
                System.out.println("Pais: ");
                pais = sc.nextLine();
                System.out.println("Sexo: ");
                sex = sc.nextLine();
                System.out.println("Especialidad: ");
                esp = sc.nextLine();
                experto exp = new experto(col.getCodExperto(), nom, pais, sex, esp);
                Experto.insertaExperto(exp);
            }
            if(!Caso.existeCaso(col.getCodCaso())){
                String nomb, fini, ffin;
                System.out.println("Nombre del Caso: ");
                nomb = sc.nextLine();
                System.out.println("Fecha Inicio: ");
                fini = sc.nextLine();
                System.out.println("Fecha Fin: ");
                ffin = sc.nextLine();
                caso Caso2 = new caso(col.getCodCaso(), nomb, fini, ffin);
                Caso.insertaCaso(Caso2);
            }
            
        
            try{
                System.out.println("He llegado aqui 1");
                ps = conexion.conn.prepareStatement("insert into COLABORA values (?,?,?,?)");
                ps.setString(1, col.getCodExperto());
                ps.setString(2, col.getCodCaso());
                ps.setString(3, col.getFecha());
                ps.setString(4, col.getDescripcionColaboracion());
                ps.executeUpdate();
                conexion.finTransaccionCommit();
                ps.close();
            }catch (SQLException e) {
                conexion.finTransaccionRollback();
                System.out.println("Error al insertar datos de la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getSQLState());
                System.out.println(e.getErrorCode());
            }
        }
    }
    
    public ResultSet buscacaso(String caso) throws SQLException
    {
        String sql = "{call PCOLABORASCASO(?, ?)";
        CallableStatement cs = null;
        ResultSet rs = null;
        cs = conexion.conn.prepareCall(sql);
        cs.setString(1, caso);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.executeUpdate();
        rs = (ResultSet)cs.getObject(2);
        return rs;
    }
        
}
