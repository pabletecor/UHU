/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import static java.lang.Thread.sleep;
/**
 *Clase Hilo: encargada de consumir de la pila
 * @author Manu Reyes
 */
public class Consumidor implements Runnable {

    private final PilaLenta lapila;

    public Consumidor(PilaLenta p) {
        this.lapila = p;

    }

    /**
     *Consume 15 veces de la pila en un periodo de entre 1 y 3 segundos
     * Cuando termina de consumir notifica al resto de procesos que ha terminado
     * para que dejen de producir
     */
    public void consumir() {

        Object numDesapila;
        try {
            for (int i = 0; i < 15; i++) {
                //consume: desapila de la pila
                numDesapila = (int)lapila.Desapila();
                System.out.println("Consumo: "+numDesapila);
                //aleatoriza el tiempo de ejecución de cada iteración
                sleep((int) (((Math.random() * 10) % 3 + 1) * 1000));
            }

        } catch (Exception ex) {
            System.out.println("Consumidor fuera");
        }
        System.out.println("Termino de consumir");
        try {
            Thread.sleep(3000);
            //notifica a los productores 3 veces, que es numero maximo de intentos
            for (int i = 3; i > 0; i--) {
                System.out.println("Cerrando en "+i);
                synchronized(this.lapila){
                    lapila.notifyAll();
                }
                sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    //método que se ejecuta cuando el proceso comienza, mientras este activo
    public void run() {
        consumir();
    }

}
