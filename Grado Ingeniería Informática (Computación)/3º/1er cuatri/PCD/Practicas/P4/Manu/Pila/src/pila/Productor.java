/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 * @author Manu Reyes
 * Clase Hilo: encargada de producir elementos de la pila
 * Si al producir la pila esta llena, espera
 * Si le notifican que puede producir pero la pila vuelve a estar llena,
 * aumenta en 1 intento.
 * A los 3 intentos, el proceso termina.
 */
public class Productor extends Thread {

    private final PilaLenta lapila;
    private final int id;

    public Productor(PilaLenta p, int id) {
        this.lapila = p;
        this.id=id;
    }

    /**
     *Produce 15 veces en un periodo de entre 1 y 3 segundos
     * Si no puede, es decir, que ocurren 3 intentos y no ha apilado;
     * termina el proceso
     */
    public void produce()
    {
        try {
            for (int i = 0; i < 15; i++) {
                int rand = (int) (Math.random() * 100); //numero aleatorio entre 0 y 99
                lapila.Apila(rand); //producir=apilar en la pila
                System.out.println("Introduzco: " + rand);
                sleep((int) (((Math.random()*10)%3+1)*1000)); //numero aleatorio
            }
        } catch (Exception e) {
            System.out.println("Productor " + Thread.currentThread().getId()+" fuera");
        }
        System.out.println("Productor "+ Thread.currentThread().getId()+" termina de producir " );
        
    }
    //Método que se llama al ejecutarse el proceso
    @Override
    public void run() {  //En el run ponemos lo que queremos que se ejecute concurrentemente (el for con producir)
            produce();
    }
}
