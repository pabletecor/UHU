/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Camion extends Thread{
    
    int id;
    Taller t;
    
    public Camion( int id , Taller t){
    
        this.id = id;
        this.t = t;
    
    
    }
    
    
    @Override
    public void run(){
    
         Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            
            System.out.println("Soy el CAMION "+id);
            t.EntraCamion();
            System.out.println("                           ----> CAMION "+id+" EN TALLER");
            
            Thread.sleep((rnd.nextInt(5)+3)*3000);
            System.out.println("                           <---- FIN CAMION "+id);
            t.SaleCamion();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
