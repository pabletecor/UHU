package Business;
import Persistence.CollaboratesManager;
import Persistence.conexionOracle;
import Persistence.Case;
import Persistence.Collaborates;
import Persistence.ExpertManager;
import Persistence.Expert;
import Persistence.CaseManager;
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
            System.out.println("Conection Successfull\n");
            System.out.println("Operations Menu");
            System.out.println("----------------");
            System.out.println("1. Experts by Nationality");
            System.out.println("2. Insert Expert");
            System.out.println("3. Insert Colaboration");
            System.out.println("4. Show number of Men and Woman");
            System.out.println("5. Experts participating in a case");
            System.out.println();
            System.out.print("What will you like to do?  ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":   part1();
                            break;
                case "2":   part2();
                            break;
                case "3":   part3();
                            break;
                case "4": part4();
                break;
                
                case "5": part5();
                
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
    public static void part1() throws SQLException {
        // TODO implementar operaciones
         String pais;
         Scanner sc=new Scanner(System.in);
        
         System.out.println("Insert country to search: ");
         pais=sc.nextLine();
         
         ExpertManager ex=new ExpertManager(co);
         ArrayList<Expert> aux= new ArrayList<Expert>();
         
         aux=ex.expertListByCountry(pais);
         System.out.println("\nExpertos\n************");
         System.out.println(aux.size());//0
         for (int i = 0; i < aux.size(); i++) 
         {
             System.out.println("\nExpert Code: "+aux.get(i).getCodExperto());
             System.out.println("\nExpert Name: "+aux.get(i).getName());
             System.out.println("\nExpert Country: "+aux.get(i).getCountry());
             System.out.println("\nExpert Sex: "+aux.get(i).getSex());
             System.out.println("\nExpert Specialism: "+aux.get(i).getSpecialism());
             System.out.println("\n************************************");
         }
         
    }
    /*
    Insert data on table EXPERT    
    */
    public static void part2() throws SQLException {
        // TODO implementar operaciones
        ExpertManager ex=new ExpertManager(co);
        Expert exp=new Expert();
        Scanner sc=new Scanner(System.in);
        System.out.println("*****Introducción de Datos de un experto********\n");
        System.out.println("Nombre: ");
        exp.setName(sc.nextLine());
        System.out.println("Codigo de Experto: ");
        exp.setCodExpert(sc.nextLine());
        System.out.println("Pais: ");
        exp.setCountry(sc.nextLine());
        System.out.println("Sexo: ");
        exp.setSex(sc.nextLine());
        System.out.println("Especialidad: ");
        exp.setSpecialism(sc.nextLine());

        if(ex.insertaExperto(exp)==true)
        {
            System.out.println("Expert was inserted successfully.");
        }
        else
        {
            System.out.println("Expert was not inserted .");
        }
         
    }
    /*
    Insertar datos en la tabla COLABORA
    */
    public static void part3() throws SQLException, ParseException { 
        
        CollaboratesManager mcol=new CollaboratesManager(co);
        Scanner sc=new Scanner(System.in);
        Collaborates col=new Collaborates();
        ExpertManager mex=new ExpertManager(co);
        Expert exp=new Expert();
        CaseManager mca=new CaseManager(co);
        Case ca=new Case();
        String codEx;
        String codCa;
        
        System.out.println("\n***** Introductión of Data of Colaboration *******\n");
        System.out.println("Code of Expert: ");
        codEx=sc.nextLine();
        System.out.println("Code of Case: ");
        codCa=sc.nextLine();

        if(mex.expertExists(codEx)==true)
        {
            System.out.println("\nEl Expert already exist. \n");
            col.setCodEXPERT(codEx);
            
            if(mca.existeCaso(codCa)==true)
            {
                System.out.println("\nThe code of the case already exist.\n");
                System.out.println("\nThe colaboration already exist\n");
                col.setCodCase(codCa);
            }
            else
            {
                System.out.println("The Code of the Case does not exist. Inset the data to register the case: ");
                ca.setCodCase(codCa);
                System.out.println("Name: ");
                ca.setName(sc.nextLine());
                System.out.println("Start Date: ");
                ca.setStartDate(sc.nextLine());  
                mca.insertaCaso(ca);
                
                col.setCodCase(codCa);
                System.out.println("Date: ");
                col.setDate(sc.nextLine());
                System.out.println("Description of Colaboratión: ");
                col.setDescriptionColaboration(sc.nextLine());
                mcol.insertaColaboracion(col);
            }
        }
        else
        {
            System.out.println("The Expert does not exist. Insert the data: \n");
            
            if(mca.existeCaso(codCa)==true)
            {
                System.out.println("\nThe code of the case alreay exist. Insert the data: \n");
                col.setCodCase(codCa);
                
                //existe el caso y no el experto introduzco experto y colabora
                
                System.out.println("Name: ");
                exp.setName(sc.nextLine());
                System.out.println("Conuntry: ");
                exp.setCountry(sc.nextLine());
                System.out.println("Sex: ");
                exp.setSex(sc.nextLine());
                System.out.println("Specialism: ");
                exp.setSpecialism(sc.nextLine());
                mex.insertaExperto(exp);
                System.out.println("\n**Expert has been Inserted. Provide data in order to register a colaboration: **\n");
                col.setCodEXPERT(codEx);
                col.setCodCase(codCa);
                System.out.println("Date: ");
                col.setDate(sc.nextLine());
                System.out.println("Description of Colaboratión: ");
                col.setDescriptionColaboration(sc.nextLine());
                mcol.insertaColaboracion(col);
            }
            else
            {
                System.out.println("The Code of the case does not exist. Provide data in order to register a case: ");
                ca.setCodCase(codCa);
                System.out.println("Name: ");
                ca.setName(sc.nextLine());
                System.out.println("Star tDate: ");
                ca.setStartDate(sc.nextLine());
                System.out.println("End Date: ");
                ca.setEndDate(sc.nextLine());  
                mca.insertaCaso(ca);
                exp.setName(codEx);
                System.out.println("Name: ");
                exp.setName(sc.nextLine());
                System.out.println("Country: ");
                exp.setCountry(sc.nextLine());
                System.out.println("Sex : ");
                exp.setSex(sc.nextLine());
                System.out.println("Specialism: ");
                exp.setSpecialism(sc.nextLine());
                mex.insertaExperto(exp);
                System.out.println("\n**Expert has been Inserted. Provide data in order to register a colaboration: **\n");
                col.setCodEXPERT(codEx);
                col.setCodCase(codCa);
                System.out.println("Date: ");
                col.setDate(sc.nextLine());
                System.out.println("Description of Colaboratión: ");
                col.setDescriptionColaboration(sc.nextLine());
                mcol.insertaColaboracion(col);

            }            
            
            
        }
        
    }
    
    
    public static void part4() throws SQLException
    {
        ExpertManager me=new ExpertManager(co);
        
        System.out.println("amaunt of men: "+me.sexoExperto('M'));
        System.out.println("amaunt of women: "+me.sexoExperto('F'));
        
    }
    
    public static void part5() throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        CollaboratesManager mc=new CollaboratesManager(co);
        ResultSet rs;
        String Cas;
        System.out.println("Insert the name of a case: ");
        Cas=sc.nextLine();
        System.out.println("List Colaborators acording to case "+Cas+" is: \n");
        
        rs=mc.listaColaboradoresPorCaso(Cas);
        while(rs.next())
        {
            String codEx=rs.getString("CODEXPERT");
            String name=rs.getString("NAME");
            String specialism=rs.getString("SPECIALISM");
            String description=rs.getString("COLABORATION_DESCRIPTION");
            System.out.println("Code of Expert: "+codEx);
            System.out.println("Name: "+name);
            System.out.println("Specialism: "+specialism);
            System.out.println("Description of the Colaboracion: "+description+"\n"+"*****\n");
        }
    }
    
}
