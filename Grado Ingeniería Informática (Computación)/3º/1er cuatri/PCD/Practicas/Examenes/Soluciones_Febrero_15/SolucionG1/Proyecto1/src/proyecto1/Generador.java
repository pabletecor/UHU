/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        Thread cl[] = new Thread[10];
        System.out.println("Lanzando clientes .....");
        for (int i = 0; i < 10; i++) {
            if(rnd.nextInt(2)%2==1){
                cl[i] = new Cliente(i);
            }
            else{
                cl[i] = new Thread(new Proveedor(i));                
            }
            cl[i].start();
            Thread.sleep((rnd.nextInt(2)+1)*1000);
        }
        Thread.sleep(2000);
        for (int i = 0; i < 10; i++) {
            cl[i].interrupt();
        }
        System.out.println("Todos los clientes finalizados");
    }
}
