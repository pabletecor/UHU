/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1ex2018v3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Pantalon implements Runnable {
    
    int id;
    Linea l;
    Random num = new Random();
    
    public Pantalon(int id, Linea l){
    
        this.id = id;
        this.l = l;
    
    
    }
    
    
    @Override
    public void run(){
    
                Random num = new Random();
        
        System.out.println("Hola soy la camisa " + id);
        
        try {
            //Entra en la maquina de corte
            l.EntraCorte(1);
            System.out.println("Pantalon " + id + " entra en CORTE");
            Thread.sleep(2000);
            //Entra en la maquina de coser
            l.CoserPatalon();
            System.out.println("Pantalon " + id + " entra en COSIDO");
            Thread.sleep(3000);
            //Sale de la maquina de coser
            System.out.println("FIN de pantalon " + id);
            l.SaleCoser();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Camisa.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    
    
    
    }
    
}
