package Business;


import Persistence.Collaborates;
import Persistence.CollaboratesManager;
import Persistence.ExpertManager;
import Persistence.Expert;
import Persistence.OracleConnection;
import Persistence.Policialcase;
import Persistence.PolicialcaseManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class appMain {

    static OracleConnection co = null;
    static ExpertManager expertManager = null;

    public static void main(String[] args) throws Exception {

        try {
            co = new OracleConnection();
            expertManager = new ExpertManager(co);
            System.out.println("Conexion completed successfully\n");
            System.out.println("Menu");
            System.out.println("----------------");
            System.out.println("1. Experts by nationality");
            System.out.println("2. Insert an expert");
            System.out.println("3. Insert a colaboration");
            System.out.println();
            System.out.print("What do you want to do?  ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    exercise1();
                    break;
                case "2":
                    exercise2();
                    break;
                case "3":
                    exercise3();
                    break;
            }

            co.disconnect();
        } catch (SQLException e) {
            System.out.println("Connetion error: " + e.getMessage());
} //        catch (Exception e) {
//            System.out.println("Error: " + e.getMessage() );
//        }

    }

    /*
    Show all the experts whose nationality is equal to one requested by the keyboard
     */
    
    //Hecho por mi 
    
    public static void exercise1() throws SQLException {
        // TODO add code to show all the experts whose nationality is equal to one requested by the keyboard
        Scanner sc = new Scanner(System.in);
        

        System.out.println("Insert the nationality you want to search: ");
        String nat = sc.nextLine();

        ArrayList<Expert> expList = expertManager.expertListByCountry(nat);

        //Declaramos el iterador
        Iterator<Expert> it;
        it = expList.iterator();

        //Hacemos un if para ver el numero de expertos que se han pasado a la lista. Si es 0 damos error
        if (expList.isEmpty()) {

            System.out.println("ERROR!!!! There are not Experts with this nationality!!!");
        } else {

            while (it.hasNext()) {
                Expert exp = it.next();
                System.out.print(exp.getCodEXPERT() + " / " + exp.getName() + "/"
                        + exp.getCountry() + "/" + exp.getSex()
                        + "/" + exp.getSpecialism() + "\n");
            }

        }

    }

    
    
    
    
//    //HECHO POR JOSE
//    public static void exercise1() throws SQLException {
//        // TODO add code to show all the experts whose nationality is equal to one requested by the keyboard
//        System.out.println("Insert country: ");
//        Scanner sc = new Scanner(System.in);
//        String country = sc.nextLine();
//        ArrayList<Expert> ex = expertManager.expertListByCountry(country);
//        
//        if (ex.isEmpty()) {
//            System.out.println("There is no one from " + country);
//        } else {
//            for (int i = 0; i < ex.size(); i++) {
//                System.out.println("Experto #" + (i + 1) + ": ");
//                System.out.println("Name: " + ex.get(i).getName());
//                System.out.println("Code: " + ex.get(i).getCodEXPERT());
//                System.out.println("Sex: " + ex.get(i).getSex());
//                System.out.println("Country: " + ex.get(i).getCountry());
//                System.out.println("Specialism: " + ex.get(i).getSpecialism());
//                //seguir
//            }
//        }
//
//    }

    
    
    /*
    Insert a new expert 
     */
    /**
     *
     * @throws SQLException
     */
    public static void exercise2() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert a new expert: ");

        System.out.println("Cod: ");
        String cod = sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Country: ");
        String country = sc.nextLine();
        System.out.println("Sex: ");
        String sex = sc.nextLine();
        System.out.println("Specialism: ");
        String specialism = sc.nextLine();
        Expert exp = new Expert(cod, name, country, sex, specialism);

        boolean created = expertManager.insertaExperto(exp);
        
        if(created){
            System.out.println("EVERYTHING GOOD");
        }
        else System.out.println("EVERYTHING BAD");
    }

    /*
    Insert data in COLLABORATES table
     */
    public static void exercise3() throws SQLException {
        // TODO add code to insert a new row in COLLABORATES
        ExpertManager expMan = new ExpertManager(co);
        PolicialcaseManager pc = new PolicialcaseManager(co);
        CollaboratesManager cm = new CollaboratesManager(co);
        Scanner sc = new Scanner(System.in);

        //We insert the EXPERT code by hand
        System.out.println("Insert the EXPERT code below:\n");
        String codEXP = sc.nextLine();
        //Check if the expert exists

        if (!expMan.expertExists(codEXP)) {
            //Si el experto no existe, seguimos pidiendo datos y padentro
            
            System.out.println("The expert with code" + codEXP + " does not exist. Update the EXPERT table:\n");
            
        System.out.println("Expert name: ");
        String name = sc.nextLine();
        System.out.println("Expert country: ");
        String country = sc.nextLine();
        System.out.println("Expert sex: ");
        String sex = sc.nextLine();
        System.out.println("Expert specialism: ");
        String specialism = sc.nextLine();
        Expert exp = new Expert(codEXP, name, country, sex, specialism);

        expMan.insertaExperto(exp);
            
        }
            //The expert exists. We keep going!
        
            System.out.println("Insert the POLICIALCASE code below:\n");
            String codCASE = sc.nextLine();
            
            if(!pc.existeCaso(codCASE)){
            //Si no existe creamos un nuevo caso
            System.out.println("The case with code" + codCASE + " does not exist. Update the POLICIALCASE table:\n");
            
            System.out.println("Case name: ");
            String name = sc.nextLine();
            System.out.println("Case start date: ");
            String startDate = sc.nextLine();
            System.out.println("Case end date: ");
            String endDate = sc.nextLine();
            
            Policialcase p = new Policialcase(codCASE,name,startDate,endDate);

            pc.insertCase(p);
            
            } 
            //Terminamos de perdir los datos para COLLABORATES
                System.out.println("/nInsert the Collaborates date: ");
                String dateC = sc.nextLine();
                System.out.println("Insert the Cllaborates description:");
                String desc = sc.nextLine();
                
                Collaborates cob = new Collaborates(codEXP,codCASE,dateC,desc);
                cm.insertaColaboracion(cob);
                
            
        
        

    }

}
