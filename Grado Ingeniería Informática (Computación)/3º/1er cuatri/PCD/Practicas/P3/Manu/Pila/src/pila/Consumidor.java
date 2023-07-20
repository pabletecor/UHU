/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Consumidor implements Runnable {

    private final PilaLenta lapila;

    public Consumidor(PilaLenta p) {
        this.lapila = p;

    }

    /**
     *
     */
    public synchronized void consumir() {
        //Hay que mostrar el numero que se va a desapilar
        Object numDesapila;
        try {
            numDesapila = lapila.Desapila();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                consumir();
                Thread.sleep(200);
            } catch (Exception ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
