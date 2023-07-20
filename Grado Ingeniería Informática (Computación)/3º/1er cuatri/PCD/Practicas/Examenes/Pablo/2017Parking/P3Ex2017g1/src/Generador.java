/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Pablo Cordon
 */
public class Generador {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO code application logic here
    
        //Creamos el Threadpool
        ExecutorService thp = Executors.newFixedThreadPool(3);
        ArrayList <Future<Integer>> f = new ArrayList <Future<Integer>>();
        int nbus=0;
        
        Parking p = new Parking();
        Thread Vehiculo [] = new Thread[20];
        Random num = new Random();
        num.setSeed(System.currentTimeMillis());
        
        for (int i = 0; i < 20; i++) {
            
            if(num.nextInt(10) < 7) {   //Coche
                //Vehiculo [i] = new Thread (new Coche(i,p));
                //Añado los coches al arraylist
                f.add(thp.submit(new Coche(i,p)));
            
            } else {
                Vehiculo [nbus] = new Autobus(i,p);
                Vehiculo[nbus].start();
                nbus++;
            }
            
            
            
            Thread.sleep((num.nextInt(3)+1)*1000);
        }
        
//        for (int i = 0; i < nbus; i++) {
//            Vehiculo[nbus].join(); //Cambiar esto por nbus
//        }
        
        thp.shutdown();
        
        int nseg =0;
        
        for (int i = 0; i < f.size(); i++) {
            nseg += f.get(i).get();
           
        }
        
        System.out.println("Los COCHES han estado aparcados un total de " + nseg + "s");
        
        System.exit(0);
    }
    
}
