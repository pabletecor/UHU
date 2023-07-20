/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Coche implements Callable < Integer > {
   
    int id;
    Taller t;
    
    public Coche( int id , Taller t){
    
        this.id = id;
        this.t = t;
    
    
    }
    
    @Override
    public Integer call(){
     Random rnd = new Random();
     Integer num=0;
        rnd.setSeed(System.currentTimeMillis());
        try {

            System.out.println("Soy el coche " + id);
            t.EntraCoche();
            System.out.println("      ----> COCHE " + id + " EN TALLER");
            num = rnd.nextInt(3) + 1;
            Thread.sleep( num * 3000);
            System.out.println("      <---- FIN COCHE " + id);
            t.SaleCoche();

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    
    
    }
    

