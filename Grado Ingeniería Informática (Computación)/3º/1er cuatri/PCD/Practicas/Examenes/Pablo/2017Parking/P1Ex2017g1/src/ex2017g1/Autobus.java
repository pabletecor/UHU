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
public class Autobus extends Thread {
    
    int id;
    Parking p;
    Random num = new Random();
    
    public Autobus(int id, Parking p){
    
        this.id = id;
        this.p=p;
    
    
    }
    
    @Override
    public void run(){
        try {
            //Entrta en el parking
            p.EntraBus(id);
            //Se duerme entre 3 y 5 seg
            Thread.sleep((num.nextInt(3)+3)*1000);
            //Sale del parking
            p.SaleBus(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


    
}
