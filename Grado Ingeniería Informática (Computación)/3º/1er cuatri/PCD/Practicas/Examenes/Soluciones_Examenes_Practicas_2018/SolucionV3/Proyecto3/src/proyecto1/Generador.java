/*PEDRO
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
        Thread[] prenda = new Thread[10];
        Semaphore corte = new Semaphore(2);
        Semaphore cose = new Semaphore(1);
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {

            for (int i = 0; i < 10; i++) {
                if (rnd.nextInt(10) < 3) {
                    prenda[i] = new Thread(new Pantalon(corte, cose));
                    prenda[i].start();
                } else {                    
                    prenda[i] =  new Camisa(corte, cose);
                    prenda[i].start();
                }
                Thread.sleep((rnd.nextInt(3) + 1) * 1000);                
            }
            for (int i = 0; i < 10; i++) {
                prenda[i].join();
            }            
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
}
