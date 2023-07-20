package Aplicacion;
import Persistencia.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class aplicacionCliente {

   static conexionOracle co = null; 
    
    public static void main(String[] args) {    
    
        try {
            co = new conexionOracle();
            System.out.println("Conexion realizada con éxito\n");
            System.out.println("Menú de Opciones");
            System.out.println("----------------");
            System.out.println("1. Expertos por nacionalidad");
            System.out.println("2. Insertar un experto");
            System.out.println("3. Insertar una colaboración");
            System.out.println("4. Mostrar cantidad de Hombres y Mujeres");
            System.out.println("5. Expertos que participan en un Caso");
            System.out.println();
            System.out.print("¿Qué quieres hacer?  ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":   ejercicio1();
                            break;
                case "2":   ejercicio2();
                            break;
                case "3":   ejercicio3();
                            break;
                case "4": ejercicio4();
                break;
                
                case "5": ejercicio5();
                
                break;
                
            }
            
            co.desconexion();
        }      
        catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }    
        catch (Exception e) {
            System.out.println("Error en el programa principal: " + e.getMessage());
        }
    }
    
    /*
    Mostrar por pantalla todos los expertos cuya nacionalidad sea igual a una solicitada por teclado
    */
    public static void ejercicio1() throws SQLException {
        // TODO implementar operaciones
         String pais;
         Scanner sc=new Scanner(System.in);
        
         System.out.println("Introduzca el pais a buscar: ");
         pais=sc.nextLine();
         
         manejaExperto ex=new manejaExperto(co);
         ArrayList<experto> aux= new ArrayList<experto>();
         
         aux=ex.listaExpertosPorPais(pais);
         System.out.println("\nExpertos\n************");
         System.out.println(aux.size());//0
         for (int i = 0; i < aux.size(); i++) 
         {
             System.out.println("\nCodigo del Experto: "+aux.get(i).getCodExperto());
             System.out.println("\nNombre del Experto: "+aux.get(i).getNombre());
             System.out.println("\nPais del Experto: "+aux.get(i).getPais());
             System.out.println("\nSexo del Experto: "+aux.get(i).getSexo());
             System.out.println("\nEspecialidad del Experto: "+aux.get(i).getEspecialidad());
             System.out.println("\n************************************");
         }
         
    }
    /*
    Insertar datos en la tabla EXPERTO    
    */
    public static void ejercicio2() throws SQLException {
        // TODO implementar operaciones
        manejaExperto ex=new manejaExperto(co);
        experto exp=new experto();
        Scanner sc=new Scanner(System.in);
        System.out.println("*****Introducción de Datos de un experto********\n");
        System.out.println("Nombre: ");
        exp.setNombre(sc.nextLine());
        System.out.println("Codigo de Experto: ");
        exp.setCodExperto(sc.nextLine());
        System.out.println("Pais: ");
        exp.setPais(sc.nextLine());
        System.out.println("Sexo: ");
        exp.setSexo(sc.nextLine());
        System.out.println("Especialidad: ");
        exp.setEspecialidad(sc.nextLine());

        if(ex.insertaExperto(exp)==true)
        {
            System.out.println("Experto insertado correctamente.");
        }
        else
        {
            System.out.println("Experto no insertado .");
        }
         
    }
    /*
    Insertar datos en la tabla COLABORA
    */
    public static void ejercicio3() throws SQLException, ParseException { 
        
        manejaColabora mcol=new manejaColabora(co);
        Scanner sc=new Scanner(System.in);
        colabora col=new colabora();
        manejaExperto mex=new manejaExperto(co);
        experto exp=new experto();
        manejaCaso mca=new manejaCaso(co);
        caso ca=new caso();
        String codEx;
        String codCa;
        
        System.out.println("\n***** Introducción de Datos de Colaboracion *******\n");
        System.out.println("Codigo de Experto: ");
        codEx=sc.nextLine();
        System.out.println("Codigo de Caso: ");
        codCa=sc.nextLine();

        if(mex.existeExperto(codEx)==true)
        {
            System.out.println("\nEl Experto ya existe. \n");
            col.setCodExperto(codEx);
            
            if(mca.existeCaso(codCa)==true)
            {
                System.out.println("\nEl Codigo del Caso ya existe.\n");
                System.out.println("\nLa colaboracion ya existe\n");
                col.setCodcaso(codCa);
            }
            else
            {
                System.out.println("El Codigo del Caso no existe. Introduzca los siguientes datos para registrar el caso: ");
                ca.setCodCaso(codCa);
                System.out.println("Nombre: ");
                ca.setNombre(sc.nextLine());
                System.out.println("Fecha Inicio: ");
                ca.setFechaFin(sc.nextLine());  
                mca.insertaCaso(ca);
                
                col.setCodcaso(codCa);
                System.out.println("Fecha: ");
                col.setFecha(sc.nextLine());
                System.out.println("Descripcion de Colaboración: ");
                col.setDescripcionColaboracion(sc.nextLine());
                mcol.insertaColaboracion(col);
            }
        }
        else
        {
            System.out.println("El Experto no existe. Introduzca los siguientes datos: \n");
            
            if(mca.existeCaso(codCa)==true)
            {
                System.out.println("\nEl Codigo del Caso ya existe. Introduzca los siguientes datos: \n");
                col.setCodcaso(codCa);
                
                //existe el caso y no el experto introduzco experto y colabora
                
                System.out.println("Nombre: ");
                exp.setNombre(sc.nextLine());
                System.out.println("Pais: ");
                exp.setPais(sc.nextLine());
                System.out.println("Sexo: ");
                exp.setSexo(sc.nextLine());
                System.out.println("Especialidad: ");
                exp.setEspecialidad(sc.nextLine());
                mex.insertaExperto(exp);
                System.out.println("\n**Experto Insertado. Introduzca los siguientes datos para registrar la colaboracion: **\n");
                col.setCodExperto(codEx);
                col.setCodcaso(codCa);
                System.out.println("Fecha: ");
                col.setFecha(sc.nextLine());
                System.out.println("Descripcion de Colaboración: ");
                col.setDescripcionColaboracion(sc.nextLine());
                mcol.insertaColaboracion(col);
            }
            else
            {
                System.out.println("El Codigo del Caso no existe. Introduzca los siguientes datos para registrar el caso: ");
                ca.setCodCaso(codCa);
                System.out.println("Nombre: ");
                ca.setNombre(sc.nextLine());
                System.out.println("Fecha Inicio: ");
                ca.setFechaInicio(sc.nextLine());
                System.out.println("Fecha Fin: ");
                ca.setFechaFin(sc.nextLine());  
                mca.insertaCaso(ca);
                exp.setNombre(codEx);
                System.out.println("Nombre: ");
                exp.setNombre(sc.nextLine());
                System.out.println("Pais: ");
                exp.setPais(sc.nextLine());
                System.out.println("Sexo: ");
                exp.setSexo(sc.nextLine());
                System.out.println("Especialidad: ");
                exp.setEspecialidad(sc.nextLine());
                mex.insertaExperto(exp);
                System.out.println("\n**Experto Insertado. Introduzca los siguientes datos para registrar la colaboracion: **\n");
                col.setCodExperto(codEx);
                col.setCodcaso(codCa);
                System.out.println("Fecha: ");
                col.setFecha(sc.nextLine());
                System.out.println("Descripcion de Colaboración: ");
                col.setDescripcionColaboracion(sc.nextLine());
                mcol.insertaColaboracion(col);

            }            
            
            
        }
        
    }
    
    
    public static void ejercicio4() throws SQLException
    {
        manejaExperto me=new manejaExperto(co);
        
        System.out.println("cantidad de hombres: "+me.sexoExperto('M'));
        System.out.println("cantidad de mujeres: "+me.sexoExperto('F'));
        
    }
    
    public static void ejercicio5() throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        manejaColabora mc=new manejaColabora(co);
        ResultSet rs;
        String Caso;
        System.out.println("Introduzca el nombre de un caso: ");
        Caso=sc.nextLine();
        System.out.println("Lista de Colaboradores segun el caso "+Caso+" es: \n");
        
        rs=mc.listaColaboradoresPorCaso(Caso);
        while(rs.next())
        {
            String codEx=rs.getString("CODEXPERTO");
            String nombre=rs.getString("NOMBRE");
            String especialidad=rs.getString("ESPECIALIDAD");
            String descripcion=rs.getString("DESCRIPCION_COLABORACION");
            System.out.println("Codigo del Experto: "+codEx);
            System.out.println("Nombre: "+nombre);
            System.out.println("Especialiad: "+especialidad);
            System.out.println("Descripcion de la Colaboracion: "+descripcion+"\n"+"*****\n");
        }
    }
    
}
