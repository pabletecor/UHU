/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplowaitnotify;

/**
 *
 * @author usuario
 */
public class EjemploWaitNotify {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Thread[] h = new Thread[5];
        Recurso r = new Recurso(0);
        
        for (int i = 0; i < 5; i++) {
            h[i]= new Thread(new Hilos(i,r));
            
        }
        
        for (int i = 0; i < 5; i++) {
            h[i].start();
        }
        
        for (int i = 0; i < 5; i++) {
            h[i].join();
            System.out.println("Finaliza el hilo "+i);
        }
        
        System.out.println("Hilos 0 a 3 finalizados");
       
        Thread.sleep(1000);
        synchronized(r){
            r.notify();     
        }
        
        Thread.sleep(1000);
        synchronized(r){
            r.notify();     
        }
        
        
    }
    
}
