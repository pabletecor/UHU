/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos_misemaforo;

/**
 *
 * @author usuario
 */
public class SemaforoBinario {

    int contador;

    public SemaforoBinario(int inicial) throws Exception {
        /* Si el valor no es 0 ni 1, no es un semaforo binario */
        if (inicial != 0 && inicial != 1) {
            throw (new Exception("Imposible inicializar el semaforo binario."));
        }
        contador = inicial;
    }

    public synchronized void Swait() throws InterruptedException {
        /* Mientras que no haya palillo, se espera */
        while (contador == 0) {
            wait();
        }
        contador = 0;
    }

    

    public synchronized void Ssignal() {
        /* Hay palillo disponible, coge palillo */
        contador = 1;
        notify();
    }

}
