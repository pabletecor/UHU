/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Pablo Cordon
 */
public class Generador {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
    
        Parking p = new Parking();
        Thread Vehiculo [] = new Thread[20];
        Random num = new Random();
        num.setSeed(System.currentTimeMillis());
        
        for (int i = 0; i < 20; i++) {
            if(num.nextInt(10) < 7) {   //Coche
                Vehiculo [i] = new Thread (new Coche(i,p));
            
            } else {
                Vehiculo [i] = new Autobus(i,p);
            }
            
            Vehiculo[i].start();
            
            Thread.sleep((num.nextInt(3)+1)*1000);
        }
        
        for (int i = 0; i < 20; i++) {
            Vehiculo[i].join();
        }
        
    }
    
}
