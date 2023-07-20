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
public class SemaforoGeneral {
    
    int contador;

    public SemaforoGeneral(int inicial) throws Exception {
        /* Si el valor es < que 0, no puede ser un semaforo */
        if (inicial < 0) {
            throw (new Exception("Imposible inicializar el semaforo. Valor < 0"));
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
