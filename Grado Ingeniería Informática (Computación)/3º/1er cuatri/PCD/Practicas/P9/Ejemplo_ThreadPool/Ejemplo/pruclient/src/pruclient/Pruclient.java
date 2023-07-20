/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruclient;

import ClaseRemota.IRemoto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pedro
 */
public class Pruclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            IRemoto cl =  (IRemoto) Naming.lookup("rmi://192.168.0.12:2014/acceso");
            
            cl.metodoremoto(15);
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Pruclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
