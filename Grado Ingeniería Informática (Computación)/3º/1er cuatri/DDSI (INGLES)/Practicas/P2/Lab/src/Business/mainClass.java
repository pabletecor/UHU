package Business;
import Persistence.oracleConnection;

    public class mainClass {

    public static void main(String[] args) {
        oracleConnection  co = null;
        
        // connection to de DB
        try {	        
            co = new oracleConnection ();
            System.out.println("Connected");
        }
	catch (Exception e)  {
            System.out.println("Error: " + e.getMessage());    
	}
        
        // desconnecting from the DB
        if (co != null) {	        
            try {	
                co.desconexion();
                System.out.println("Desconnected");
            }
            catch (Exception e)  {
                System.out.println("Error: " + e.getMessage());    
            }      
        }
    }
    
}
