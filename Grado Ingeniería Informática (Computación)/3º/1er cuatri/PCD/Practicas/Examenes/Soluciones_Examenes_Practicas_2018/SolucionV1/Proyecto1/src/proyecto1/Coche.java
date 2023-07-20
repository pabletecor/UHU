/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Coche implements Runnable {

    Taller t;

    public Coche(Taller tl) {
        t = tl;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {

            System.out.println("Soy el coche " + Thread.currentThread().getName());
            t.entracoche();
            System.out.println("      ----> COCHE " + Thread.currentThread().getName() + " EN TALLER");
            Thread.sleep((rnd.nextInt(3) + 1) * 3000);
            System.out.println("      <---- FIN COCHE " + Thread.currentThread().getName());
            t.salecoche();

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
