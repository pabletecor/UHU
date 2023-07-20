/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Productor extends Thread{
    
    private PilaLenta lapila;   
    
    public Productor(PilaLenta p){  
    this.lapila = p;
    }
    
    /**
     *
     * @throws Exception
     */
    public synchronized void producir() throws Exception{
        
        //No usamos aquí el for, lo pondremos despues    
        
        //Generamos un numero aleatorio entre 0 y 99
        Random aleatorio = new Random(System.currentTimeMillis());
        
        //Hacemos que el numero aleatorio seleccionado este entre 0 y 99 y ademas sea entero
        int numAleatorio = aleatorio.nextInt(100);
        
        lapila.Apila(numAleatorio);
        
        //Hay que mostrar el numero que se va a apilar
        System.out.println("Apilo el numero " + numAleatorio);
        
        aleatorio.setSeed(System.currentTimeMillis());
        
        
    
    }
    
    @Override
    public synchronized void run(){  //En el run ponemos lo que queremos que se ejecute concurrentemente (el for con producir)
    
        for (int i = 0; i < 10; i++) {
            
            try {
                producir();
            } catch (Exception ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    
    
    
}
