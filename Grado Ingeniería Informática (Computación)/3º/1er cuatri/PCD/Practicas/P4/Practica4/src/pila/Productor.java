/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class Productor extends Thread {

    private PilaLenta lapila;
    int id;
    

    public Productor(PilaLenta p, int id) {
        this.lapila = p;
        this.id=id;
    }

    /**
     *
     * @throws Exception
     */
    

    @Override
    public void run() {  //En el run ponemos lo que queremos que se ejecute concurrentemente (el for con producir)
        Random aleatorio = new Random(System.currentTimeMillis());

         try {  //Si captura una excepción, sale del bucle porque hemos hecho 3 intentos.
             
            for (int i = 0; i < 15; i++) {

            //Hacemos que el numero aleatorio seleccionado este entre 0 y 99 y ademas sea entero
            int numAleatorio = aleatorio.nextInt(100);
            
           
                Thread.sleep(aleatorio.nextInt((3+1)*1000));
                lapila.Apila(numAleatorio);
                System.out.println("Hilo " + Thread.currentThread().getId() + ". Apilo el numero " + numAleatorio);
                //Configuro la semilla para que no vuelva a salir el mismo num.
                aleatorio.setSeed(System.currentTimeMillis());
            }
            
            } catch (Exception ex) {
                System.out.println("Hilo " + Thread.currentThread().getId()+" expulsado");
            }

        }
    }

