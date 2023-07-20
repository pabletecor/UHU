/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Camisa extends Thread {
    
    private int id;
    private Semaphore corte;
    private Semaphore coser;
    
    
    public Camisa(int id, Semaphore corte,Semaphore coser){
    
        this.id = id;
        this.corte = corte;
        this.coser = coser;
    
    
    }
    
    
    @Override
    public void run(){
    
        Random num = new Random();
        
        System.out.println("Hola soy la camisa " + id);
        
        try {
            //Entra en la maquina de corte
            corte.acquire();
            System.out.println("Camisa " + id + " entra en CORTE");
            Thread.sleep(2000);
            //Entra en la maquina de coser
            corte.release();
            coser.acquire();
            System.out.println("Camisa " + id + " entra en COSIDO");
            Thread.sleep(3000);
            //Sale de la maquina de coser
            System.out.println("FIN de camisa " + id);
            corte.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Camisa.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    
    
    }
    
    
}
