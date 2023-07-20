/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseRemota;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pedro
 */
public interface IRemoto extends Remote{
    public void metodoremoto(int n) throws RemoteException;
}
