/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Coche implements Callable <Integer> {
    
    private int id;
    private Parking p;
    private int cual;
    Random num = new Random();
    int numDormido;
    
    public Coche(int id, Parking p){
    
        this.id = id;
        this.p=p;
    
    
    }
    
   
    @Override
    public Integer call(){
        try {
            //Entrta en el parking
            cual = p.EntraCoche(id);
            //Se duerme entre 3 y 5 seg
            numDormido = num.nextInt(3)+3;
            System.out.println("Coche" + id + " duerme " + numDormido + "s");
            Thread.sleep((numDormido)*1000);
            //Sale del parking
            p.SaleCoche(id,cual);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return numDormido;
    }
    
}
