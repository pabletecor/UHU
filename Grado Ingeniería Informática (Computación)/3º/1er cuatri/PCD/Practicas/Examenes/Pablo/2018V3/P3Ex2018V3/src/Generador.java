/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pablo Cordon
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Thread Ropa [] =  new Thread [10];
        Semaphore corte = new Semaphore(2);
        Semaphore coser = new Semaphore (1);
        Random num = new Random();
        
        for (int i = 0; i < 10; i++) {
            num.setSeed(System.currentTimeMillis());
            if(num.nextInt(10) < 7){
                Ropa [i] = new Camisa(i,corte,coser);
                Ropa [i].start();
            }
            else{
                Ropa [i] = new Thread ( new Pantalon(i,corte,coser));
                Ropa [i].start();
            }
            Thread.sleep((num.nextInt(3)+1) * 1000);
            
        }
        
        for (int i = 0; i < 10; i++) {
            Ropa[i].join();
        }
        
        System.exit(0);
    }
    
}
