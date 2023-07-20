/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExecutionException {
        // TODO code application logic here
        Thread[] vehiculo = new Thread[10];
        Taller repara = new Taller();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        //Declaramos el ThreadPool
        ExecutorService thp = Executors.newFixedThreadPool(3);
        //Declaramos el ArrayList
        ArrayList < Future < Integer > > f = new ArrayList < Future < Integer > > ();
        int nCamion = 0;
        
        try {

            for (int i = 0; i < 10; i++) {
                
                if (rnd.nextInt(10) >2) {
                    
                    f.add(thp.submit(new Coche(i,repara)));
                    
                } else {                    
                    
                    vehiculo[nCamion] =  new Camion(i,repara);
                    vehiculo[nCamion].start();
                    nCamion++;
                }
                Thread.sleep((rnd.nextInt(3) + 1) * 1000);                
            }
            
            Thread.sleep(3 * 1000);
            
            for (int i = 0; i < nCamion; i++) {
                
                vehiculo[i].join();
            }            
            
            int nSeg = 0;
            
            for (int i = 0; i < f.size(); i++) {
                
                nSeg += f.get(i).get();
            }
            
            System.out.println("\n\nLos coches han estado en el taller un total de " + nSeg + "s");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);

            System.exit(0);

        }
    }
    
}
