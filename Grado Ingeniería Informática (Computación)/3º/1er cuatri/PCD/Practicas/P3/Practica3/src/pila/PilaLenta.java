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

        if (pilallena()) {
            canvas.avisa("¡La pila está llena!");
            throw new Exception("¡La pila está llena!");

        } else {
      
            this.cima++;
            Thread.sleep(300);
            this.datos[this.cima] = o;
            Thread.sleep(300);
            this.numelementos++;
            Thread.sleep(300);
            canvas.representa(datos, cima, numelementos,("Apilamos el numero " + o));

        }

    }

    public synchronized Object Desapila() throws java.lang.Exception {

        Object x = null;

        if (pilavacia()) {
            
            canvas.avisa("¡La pila está vacía!");
            throw new Exception("¡La pila está vacía!");

        } else {
            x = this.datos[this.cima];
            Thread.sleep(300);
            this.numelementos--;
            Thread.sleep(300);
            this.cima--;
            Thread.sleep(300);
            canvas.representa(datos, cima, numelementos,("Desapilamos el numero " + x ));

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
