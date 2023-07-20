/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseRemota;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author pedro
 */
public class LaClaseRemota extends UnicastRemoteObject implements IRemoto {

    private int acumulado=0;
    public LaClaseRemota() throws RemoteException{
        super();
    }
    
    @Override
    public synchronized void metodoremoto(int n) throws RemoteException {
        System.out.println("Metodo invocado con el numero "+n+" El acumulado es "+acumulado);
    }
    
}
