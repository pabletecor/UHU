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

    public PilaLenta(int capacidad) {

        this.capacidad = capacidad;
        this.cima = -1;
        this.numelementos = 0;
        this.datos = new Object[this.capacidad];

    }

    public int getNum() {

        return this.numelementos;

    }

    public void Apila(Object o) throws java.lang.Exception {

        if (pilallena()) {
            System.out.println("La pila esta llena");

        } else {
            this.cima++;
            Thread.sleep(100);
            this.datos[this.cima] = o;
            Thread.sleep(100);
            this.numelementos++;

        }

    }

    public Object Desapila() throws java.lang.Exception {

        Object x=null;
        
        if (pilavacia()) {
            try {
                    throw new Exception("¡La pila está vacía!");
                    } catch (Exception ex) {
                    Logger.getLogger(PilaLenta.class.getName()).log(Level.SEVERE,null, ex);
                    }

        } else {
            x = this.datos[this.cima];
            Thread.sleep(100);
            this.numelementos--;
            Thread.sleep(100);
            this.cima--;
            Thread.sleep(100);
            
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
