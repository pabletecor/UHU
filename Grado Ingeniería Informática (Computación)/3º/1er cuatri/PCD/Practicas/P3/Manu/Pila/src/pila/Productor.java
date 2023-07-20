/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Productor extends Thread {

    private final PilaLenta lapila;
    private final int id;

    public Productor(PilaLenta p, int id) {
        this.lapila = p;
        this.id=id;
    }

    /**
     *
     */
    public void produce() throws Exception
    {
        Random rand=new Random();
        for(int i=0;i<10;i++)
        { 
            int aleatorio=rand.nextInt(100);
            lapila.Apila(aleatorio);
        }
    }

    @Override
    public void run() {  //En el run ponemos lo que queremos que se ejecute concurrentemente (el for con producir)
            try {
                produce();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
}
