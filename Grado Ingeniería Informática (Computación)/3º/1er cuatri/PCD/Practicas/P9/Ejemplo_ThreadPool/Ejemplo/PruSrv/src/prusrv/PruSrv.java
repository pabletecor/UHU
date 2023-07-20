/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prusrv;

import ClaseRemota.LaClaseRemota;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class PruSrv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        try {
            // TODO code application logic here
            Registry elreg = LocateRegistry.createRegistry(2014);
            
            LaClaseRemota r = new LaClaseRemota();
            
            elreg.rebind("acceso", r);
            System.out.println("Servidor Runnig.... pulsa tecla para fin");
            System.in.read();
            System.exit(0);
            
            
        } catch (IOException ex) {
            Logger.getLogger(PruSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
