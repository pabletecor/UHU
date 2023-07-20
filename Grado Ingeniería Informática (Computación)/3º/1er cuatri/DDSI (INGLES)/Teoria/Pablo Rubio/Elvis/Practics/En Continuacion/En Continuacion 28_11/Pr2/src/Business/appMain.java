package Business;
import Persistence.ExpertManager;
import Persistence.OracleConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class appMain {

   static OracleConnection co = null; 
    
    public static void main(String[] args) {    
    
        try {
            co = new OracleConnection();
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
                case "1":   exercise1();
                            break;
                case "2":   exercise2();
                            break;
                case "3":   exercise3();
                            break;
            }
            
            co.disconnect();
        }      
        catch (SQLException e) {
            System.out.println("Connetion error: " + e.getMessage());
        }    
	catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /*
    Show all the experts whose nationality is equal to one requested by the keyboard
    */
    public static void exercise1() {
        // TODO add code to show all the experts whose nationality is equal to one requested by the keyboard
          ExpertManager exp= new ExpertManager(co);
       try {
           exp.expertListByCountry("SPAIN");
       } catch (SQLException ex) {
           Logger.getLogger(appMain.class.getName()).log(Level.SEVERE, null, ex);
       }
          
    }
    /*
    Insert a new expert 
    */
    public static void exercise2() {
        // TODO add code to insert a new expert
    }
    /*
    Insert data in COLLABORATES table
    */
    public static void exercise3() { 
    }// TODO add code to insert a new row in COLLABORATES
}
