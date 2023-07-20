/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 * @author Manu Reyes
 * Clase recurso de nuestro proyecto
 * Almacena objetos en una pila y permite que se apilen o se desapilen
 * Implementa la interfaz IPila
 */
public class PilaLenta extends Exception implements IPila {

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

    @Override
    public int getNum() {

        return this.numelementos;

    }

    /**
     * Apila un objeto en la pila. Si el objeto que llama a la clase no puede
     * apilar, se espera e incrementa el número de intentos
     * si llega a 3 intentos, lanza una excepción en el objeto que llama a este método
     * @param o
     * @throws Exception
     */
    @Override
    public synchronized void Apila(Object o) throws java.lang.Exception {
        int intentos=0;
        while (pilallena()&&intentos<3) {
            intentos++;
            canvas.avisa("La pila esta llena");
            System.out.println(Thread.currentThread().getId()+" productor intento "+intentos);
            wait();

        } 

        if(!pilallena()) {
            this.cima++;
            this.datos[this.cima] = o;
            this.numelementos++;
            canvas.representa(datos, cima, numelementos,("Apilamos el elemento "+datos[this.cima]));
            notifyAll();
        }else{
            canvas.avisa("La pila esta llena!");
            throw new Exception("Pila llena");
        }

    }

    /**
     * Desapila un objeto de la pila y notifica al resto de procesos.
     * Si no puede, espera e incrementa el numero
     * de intentos. Si tras 3 intentos la pila sigue vacía, lanza una excepción
     * al objeto que lo llama.
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object Desapila() throws java.lang.Exception {
        int intentos=0;
        Object x = null;
        while(pilavacia()&&intentos<3)
        {
            intentos++;
            canvas.avisa("Pila vaciándose");
            System.out.println("Consumidor "+Thread.currentThread().getId() + " con Intento " + intentos);
            wait();
        }
        if (!pilavacia()) {
            x = this.datos[this.cima];
            this.numelementos--;
            this.cima--;
            canvas.representa(datos, cima, numelementos,("Desapilamos el elemento "+datos[cima+1]));
            notifyAll();

        } else {
            
            canvas.avisa("La pila esta vacia");
            throw new Exception("Pila vacia");
        }
        return x;
    }

    @Override
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
