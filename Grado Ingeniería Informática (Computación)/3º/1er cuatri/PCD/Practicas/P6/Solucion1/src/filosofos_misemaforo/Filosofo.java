/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos_misemaforo;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class Filosofo extends Thread {

    private int id;
    private SemaforoBinario izq, dch;
    private SemaforoGeneral sent;
    private Canvas_Filosofos canvas;

    /* El constructor inicializa el id, los semáforos binarios y general,
y el canvas */
    public Filosofo(int id, SemaforoBinario izq, SemaforoBinario dch,
            SemaforoGeneral sent, Canvas_Filosofos canvas) {
        this.id = id;
        this.izq = izq;
        this.dch = dch;
        this.sent = sent;
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
                sent.Swait();  //Coge sitio
                izq.Swait(); 
                canvas.ponestado(id, 3); //Coge palillo dch.
                dch.Swait(); 
                //Empieza a comer
                canvas.ponestado(id, 4); //Come 
                System.out.println("El filosofo " + id + " come");
                Thread.sleep(3000);
                //Acaba de comer
                izq.Ssignal(); //Deja el palillo izq
                canvas.ponestado(id, 2); //deja palillo izq
                dch.Ssignal(); //Deja el palillo dch
                System.out.println("El filosofo " + id + " esta saciado");
                //Ha terminado de comer, se levanta
                sent.Ssignal(); //El filósofo deja de estar sentado
            }
        } catch (InterruptedException ex) {
            System.out.println("Fin del Filosofo");
        }
    }
}
