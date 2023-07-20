/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class PilaLenta {

    private int cima;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private CanvasPila canvas;
    //int contador = 0;

    public PilaLenta(int capacidad, CanvasPila elcanvas) {

        this.capacidad = capacidad;
        this.canvas = elcanvas;
        this.cima = -1;
        this.numelementos = 0;
        this.datos = new Object[this.capacidad];

    }

    public int getNum() {

        return this.numelementos;

    }

   public synchronized void Apila(Object o) throws java.lang.Exception {

        int contador = 0;
        
            while (pilallena() && contador < 3) {
                
                System.out.println("idHilo " + Thread.currentThread().getId() + ". Intento de apilar " + (contador + 1));
                contador++;
                canvas.avisa("RICARDO ES MARICON");
                wait();
                //throw new Exception("¡La pila está llena!");

            } if(!pilallena()) {

                this.cima++;
                Thread.sleep(300);
                this.datos[this.cima] = o;
                Thread.sleep(300);
                this.numelementos++;
                
                canvas.representa(datos, cima, numelementos, ("(" + contador + ")Apilamos el elemento " + datos[this.cima]));
                
                contador = 0;    //He podido apilar, reinicio mi contador

                notifyAll();    //He podido apilar, aviso al resto de los productores.

            }

         else {  //He gastado todos mis intentos, abandono(salta excepcion y mensaje)

            canvas.avisa("Productor cansado, abandona la ejecucion");
            //Lanzo la excepcion para que la recoja el productor y pueda salirse del run (Dentro del run y fuera del bucle haremos un try-catch)
            throw new Exception("Productor " + Thread.currentThread().getId() + " cansado, abandona la ejecucion");


        }

    }

    public synchronized Object Desapila() throws java.lang.Exception {

        Object x = null;
        int contador = 0;


            if (pilavacia() && contador < 3) {
                
                contador++;
                canvas.avisa("La pila esta vacia");
                wait();
                //throw new Exception("¡La pila está vacía!");

            } if(!pilavacia()) {

                x = this.datos[this.cima];
                Thread.sleep(300);
                this.numelementos--;
                Thread.sleep(300);
                this.cima--;
                Thread.sleep(300);

                contador = 0;

                canvas.representa(datos, cima, numelementos, ("Desapilamos el elemento " + datos[cima + 1]));

                notifyAll();    //He conseguido desapilar ¿Aviso?
            }

            else  {
            
           
            canvas.avisa("Consumidor cansado, abandona la ejecucion");
            throw new Exception("Consumidor cansado, abandona la ejecucion");

        }

        return x;

    }

    public Object Primero() throws Exception {

        if (pilavacia()) {
            
            canvas.avisa("¡La pila está vacía!");
            throw new java.lang.Exception("La pila esta vacia");

        } else {

            Object x = datos[cima];
            return x;

        }
    }

    private boolean pilavacia() {
        return this.getNum() == 0;

    }

    private boolean pilallena() {
        return this.getNum() == capacidad;

    }

}
