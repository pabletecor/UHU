/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos_misemaforo;

import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 *
 * @author usuario
 */
public class Filosofo extends Thread {

    private int id;
    private Semaphore izq, dch;
    private Canvas_Filosofos canvas;

    /* El constructor inicializa el id, los semáforos binarios y general,
y el canvas */
    public Filosofo(int id, Semaphore izq, Semaphore dch,
             Canvas_Filosofos canvas) {
        this.id = id;
        this.izq = izq;
        this.dch = dch;
     
        this.canvas = canvas;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                //Llega el filosofo
                canvas.ponestado(id, 0); //Piensa
                System.out.println("El filosofo " + id + " piensa.");
                //Tiene hambre y se sienta
                Thread.sleep(3000);
                canvas.ponestado(id, 1); //Tiene hambre
                System.out.println("El filosofo " + id + " tiene hambre");
                
                if(id % 2 ==0){ //Filosofo PAR
                    
                izq.acquire();
                canvas.ponestado(id, 2); //Coge palillo izq
                dch.acquire();
                
                }
                
                else {  //Filosofo IMPAR
                
                dch.acquire();
                canvas.ponestado(id, 3); //Coge palillo dch.
                izq.acquire();
                
                }

                //Empieza a comer
                canvas.ponestado(id, 4); //Come 
                System.out.println("El filosofo " + id + " come");
                Thread.sleep(3000);
                //Acaba de comer
                izq.release(); //Deja el palillo izq
                canvas.ponestado(id, 2); //deja palillo izq
                dch.release(); //Deja el palillo dch
                System.out.println("El filosofo " + id + " esta saciado");
                //Ha terminado de comer, se levanta
                canvas.ponestado(id, 0); //Piensa
            }
            
        } catch (InterruptedException ex) {
            System.out.println("Fin del Filosofo");
        }
    }
}
