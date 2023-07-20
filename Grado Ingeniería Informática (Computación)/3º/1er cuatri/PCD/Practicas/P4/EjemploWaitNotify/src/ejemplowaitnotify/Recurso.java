/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplowaitnotify;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Recurso {
    private int turno=0;
    
    public Recurso(int inicial){
        this.turno=inicial;
    
    
    }
    
    public synchronized void cogeturno(int id){
        if(turno!=id){
            try {
                wait();
                System.out.println("                    Se despierta el hilo" + id);
            } catch (InterruptedException ex) {}
        
        }
    
    }
    
    public synchronized void pasaturno(){
        turno=(turno+1)%5;
        notifyAll();
    
    
    }
    
}
