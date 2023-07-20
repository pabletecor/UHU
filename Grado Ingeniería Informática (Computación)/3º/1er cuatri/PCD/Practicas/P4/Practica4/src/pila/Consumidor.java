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
public class Consumidor extends Thread {

    private PilaLenta lapila;

    public Consumidor(PilaLenta p) {
        this.lapila = p;

    }

    /**
     *
     * @throws Exception
     */
    public void consumir() {
        //Hay que mostrar el numero que se va a desapilar
        Object numDesapila;
        try {
            numDesapila = lapila.Desapila();
            System.out.println("Desapilamos el numero " + numDesapila);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

     @Override
    public void run() {

        Random rand=new Random();
        try{
            
        for (int i = 0; i < 15; i++) {

            
                Thread.sleep(rand.nextInt((3+1)*1000));
                consumir();
                Thread.sleep(200);
            
        }
        } catch(Exception ex){
         System.out.println("Consumidor expulsado");
        }
    //Termina la ejecucion de consumir, hago un notifyAll sobre la pila 3 veces para asegurarme que todos los hilos se "rompan"
    
    synchronized(this.lapila){
        
        int cuenta=3;
        try{
        for (int i = 0; i < 3; i++) {
           
                Thread.sleep(2000);
                System.out.println("Fin de consumidor. Matando hilos en " + (cuenta-i));
                lapila.notifyAll();
                
            } 
        
        } catch(InterruptedException e){}
    
    }
        try {
            Thread.sleep(4000);
            System.out.println("He matado a todos los hilos.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    }

}
