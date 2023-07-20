/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2017g1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Coche implements Runnable {
    
    private int id;
    private Parking p;
    private int cual;
    Random num = new Random();
    
    public Coche(int id, Parking p){
    
        this.id = id;
        this.p=p;
    
    
    }
    
    @Override
    public void run(){
        try {
            //Entrta en el parking
            cual = p.EntraCoche(id);
            //Se duerme entre 3 y 5 seg
            Thread.sleep((num.nextInt(3)+3)*1000);
            //Sale del parking
            p.SaleCoche(id,cual);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
