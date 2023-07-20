/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class UsaPila {
     
    public static void main(String[] args) throws Exception{
     
        
        PilaLenta p = new PilaLenta(5);
        
        Productor prod1= new Productor(p,1);
        Productor prod2= new Productor(p,2);
        Thread c1 = new Thread (new Consumidor (p));
        Thread c2 = new Thread (new Consumidor (p));
        //Ponemos a correr los hilos
          System.out.println("Comienza la main");
          
          prod1.start();
          prod2.start();
          c1.start();
          c2.start();
          
          prod1.join();
          prod2.join();
          c1.join();
          c2.join();
     
     }
}
