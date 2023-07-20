/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author usuario
 */
public class PilaLenta {

    private int cima;
    private final int capacidad;
    private int numelementos;
    private final Object[] datos;
    PilaCanvas canvas;

    public PilaLenta(int capacidad,PilaCanvas canvas) {

        this.capacidad = capacidad;
        this.cima = -1;
        this.numelementos = 0;
        this.datos = new Object[this.capacidad];
        this.canvas=canvas;
    }

    public int getNum() {

        return this.numelementos;

    }

    public synchronized void Apila(Object o) throws java.lang.Exception {

        if (pilallena()) {
            canvas.avisa("La pila esta llena");
            throw new Exception("¡La pila está llena!");

        } else {
            this.cima++;
            Thread.sleep(300);
            this.datos[this.cima] = o;
            Thread.sleep(300);
            this.numelementos++;
            canvas.representa(datos, cima, numelementos,("Apilamos el elemento "+datos[this.cima]));

        }

    }

    public synchronized Object Desapila() throws java.lang.Exception {

        Object x = null;

        if (pilavacia()) {
            canvas.avisa("La pila esta vacia");
            throw new Exception("¡La pila está vacía!");

        } else {
            x = this.datos[this.cima];
            Thread.sleep(300);
            this.numelementos--;
            Thread.sleep(300);
            this.cima--;
            Thread.sleep(300);
            canvas.representa(datos, cima, numelementos,("Desapilamos el elemento "+datos[cima+1]));
        }
        return x;
    }

    public Object Primero() throws Exception {

        if (pilavacia()) {
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
