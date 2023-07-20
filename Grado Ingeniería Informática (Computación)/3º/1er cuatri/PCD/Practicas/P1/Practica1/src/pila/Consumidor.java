/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Consumidor implements Runnable {
    
    private PilaLenta lapila;   
    
    public Consumidor(PilaLenta p){  
    this.lapila = p;
    
    }  
    
    /**
     *
     * @throws Exception
     */
    public synchronized void consumir() throws Exception{
    //Hay que mostrar el numero que se va a desapilar
        Object numDesapila;
        numDesapila = lapila.Desapila();
        System.out.println("Desapilamos el numero " + numDesapila);
      
    }
    
    @Override
    public synchronized void run(){
    
    for (int i = 0; i < 10; i++) {
            
            try {
                consumir();
            } catch (Exception ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
