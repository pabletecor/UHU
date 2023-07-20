/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i.a;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.*;


/**
 *
 * @author Rafa
 */
public class Busqueda {
    
    
    private Nodo inicial; //nodo donde empezamos, inicio del laberinto
    private Nodo actual; //nodo donde nos movemos
    private Nodo objetivo; //nodo al que queremos llegar, final del laberinto
    private Laberinto L = new Laberinto(10,10); //Nuestro laberinto 
    
    public Nodo getObjetivo(){ 
        return objetivo;
    }
    
    public Nodo getInicial(){
        return inicial;
    }
    
    public Nodo getActual(){
        return actual;
    }
    
    
    
    Busqueda() throws FileNotFoundException, IOException{ //Constructor, nos carga el mapa y asigna las variables
        String cadena;
        FileReader f = new FileReader("C:\\Users\\Rafa\\Desktop\\Segundo cuatri\\IA\\laberinto.txt");
        BufferedReader b = new BufferedReader(f);
        int i = 0;
        char auxiliar;
        while((cadena = b.readLine())!=null) { //mientras que toda linea de nuestro fichero no este vacia...
            for(int j= 0; j<cadena.length() ;j++){ //recorremos en base a j acotada a las dimensiones de nuestro laberinto
                auxiliar = cadena.charAt(j);
                System.out.print(auxiliar);
                L.insertar(i, j, auxiliar); 
                if(auxiliar == '>'){ //si la variable auxiliar coincide con el inicio del laberinto
                    inicial = new Nodo(i,j,null,0); //asignamos los valores al nodo inicial
                }else if(auxiliar == '*'){ //si la variable auxiliar coincide con el final
                    objetivo = new Nodo(i,j,null,0); //asignamos los valores al nodo final o nodo objetivo
                }
            }
            i++;
            System.out.println();
        }
        b.close();
    }
    
    public int solucion(){
        int camino = 0;
        if(actual.igual(objetivo)){
            while(actual.getPadre()!=null){
                L.insertar(actual.getX(), actual.getY(), '.');
                actual = actual.getPadre();
                camino++;
            }
            return camino;
        }
        else return 0;
    }
    
    
    
    public Queue<Nodo> generaHijosAnchura(Nodo a){ //visitamos los hijos
            Nodo aux;
            Queue<Nodo> colaAuxiliar = new LinkedList<Nodo>();
                if(L.getCaracter(actual.getX()+1,actual.getY()) != '#'){ //si el caracter del laberinto hacia abajo no es una pared
                     a = new Nodo(actual.getX()+1,actual.getY(), actual, actual.getProfundidad()+1);//creamos un nodo ahi
                        colaAuxiliar.add(a);//y lo metemos en la cola
                }
                
                if(L.getCaracter(actual.getX()-1,actual.getY()) != '#'){//hacia arriba
                     a = new Nodo(actual.getX()-1,actual.getY(), actual, actual.getProfundidad()+1);
                        colaAuxiliar.add(a);
                }
                
                if(L.getCaracter(actual.getX(),actual.getY()+1) != '#'){//derecha
                     a = new Nodo(actual.getX(),actual.getY()+1, actual, actual.getProfundidad()+1);
                        colaAuxiliar.add(a);
                }
                
                if(L.getCaracter(actual.getX(),actual.getY()-1) != '#'){//izquierda
                     a = new Nodo(actual.getX(),actual.getY()-1, actual, actual.getProfundidad()+1);
                        colaAuxiliar.add(a);
                }
                System.out.println("Hijos:");
                for(int i = 0; i < colaAuxiliar.size(); i++){
                    aux = colaAuxiliar.peek();
                    System.out.println(aux);
                    colaAuxiliar.add(colaAuxiliar.poll());
                }
                return colaAuxiliar; //devolvemos la cola
    }
    
    public Stack<Nodo> generaHijosProfundidad(Nodo a){ //visitamos los hijos
            Nodo aux;
            Stack<Nodo> pilaAuxiliar = new Stack<Nodo>();
            Stack<Nodo> pilaAuxiliar2 = new Stack<Nodo>();
                if(L.getCaracter(actual.getX()+1,actual.getY()) != '#'){ //si el caracter del laberinto hacia abajo no es una pared
                     a = new Nodo(actual.getX()+1,actual.getY(), actual, actual.getProfundidad()+1);//creamos un nodo ahi
                        pilaAuxiliar.add(a);//y lo metemos en la cola
                }
                
                if(L.getCaracter(actual.getX()-1,actual.getY()) != '#'){//hacia arriba
                     a = new Nodo(actual.getX()-1,actual.getY(), actual, actual.getProfundidad()+1);
                        pilaAuxiliar.add(a);
                }
                
                if(L.getCaracter(actual.getX(),actual.getY()+1) != '#'){//derecha
                     a = new Nodo(actual.getX(),actual.getY()+1, actual, actual.getProfundidad()+1);
                        pilaAuxiliar.add(a);
                }
                
                if(L.getCaracter(actual.getX(),actual.getY()-1) != '#'){//izquierda
                     a = new Nodo(actual.getX(),actual.getY()-1, actual, actual.getProfundidad()+1);
                        pilaAuxiliar.add(a);
                }
                
                while(!pilaAuxiliar.isEmpty()){
                    aux = pilaAuxiliar.peek();
                    System.out.println("Hijos = " + aux);
                    pilaAuxiliar2.add(pilaAuxiliar.pop());
                }
                while(!pilaAuxiliar2.isEmpty()){
                    pilaAuxiliar.add(pilaAuxiliar2.pop());
                }
                return pilaAuxiliar; //devolvemos la cola
    }
    
    public Queue<Nodo> procesaHijosAnchura(Queue<Nodo> colaAbierta, Queue<Nodo> colaCerrada, Queue<Nodo> colaHijos){ //Comprobaremos los hijos repetidos
 
       Queue<Nodo> colaAuxiliar = new LinkedList<Nodo>();
       Nodo aux;
       for(int j = 0; j < colaHijos.size(); j++)
       {
      
        boolean res = false;
        for(int i = 0; i <colaAbierta.size(); i++ ){
            if(colaAbierta.element().igual(colaHijos.peek())){
                res=true;
            }
        }
        if(res == false){
            for(int i= 0; i< colaCerrada.size(); i++){
                if(colaCerrada.element().igual(colaHijos.peek())){
                    res=true;
                }
            }
        }
        if(res==false){
            colaAuxiliar.add(colaHijos.peek());
        }
        colaHijos.add(colaHijos.poll());
       }
       return colaAuxiliar;
        
    }
    
    public Stack<Nodo> procesaHijosProfundidad(Stack<Nodo> pilaAbierta, Stack<Nodo> pilaCerrada, Stack<Nodo> pilaHijos){ //Comprobaremos los hijos repetidos
      Stack<Nodo> pilaAuxiliar = new Stack<Nodo>();
       for(int j = 0; j < pilaHijos.size(); j++)
       {
      
        boolean res = false;
        for(int i = 0; i <pilaAbierta.size(); i++ ){
            if(pilaAbierta.peek().igual(pilaHijos.peek())){
                res=true;
            }
        }
        if(res == false){
            for(int i= 0; i< pilaCerrada.size(); i++){
              if(pilaCerrada.peek().igual(pilaHijos.peek()) ){
                if(pilaCerrada.peek().getProfundidad()<=pilaHijos.peek().getProfundidad()){
                  res = true;
                }else{
                    pilaCerrada.remove(i);
                }
              }
              
            }
        }
        if(res==false){
            pilaAuxiliar.add(pilaHijos.peek());
        }
        pilaHijos.pop();
       }
       
       return pilaAuxiliar; 
    }
    
    public void busquedaAnchura(){
        //Creamos tres colas
        Queue<Nodo> colaHijos = new LinkedList<Nodo>();
        Queue<Nodo> colaAbierto = new LinkedList<Nodo>();
        Queue<Nodo> colaCerrado = new LinkedList<Nodo>();
        int camino;
        Nodo hijo = new Nodo();
        Nodo aux = new Nodo();
        
        colaAbierto.add(inicial);
        actual = colaAbierto.peek();
        
        while(!actual.igual(objetivo) && !colaAbierto.isEmpty()){
            colaAbierto.remove();
            colaCerrado.add(actual);
            System.out.println("Generando hijos de " + actual);
            colaHijos = generaHijosAnchura(actual);
            System.out.println("Tratando hijos de: " + actual);
            colaHijos = procesaHijosAnchura(colaAbierto, colaCerrado, colaHijos);
            
            for(int i= 0; i<=colaHijos.size(); i++){
                hijo = colaHijos.poll();
                colaAbierto.add(hijo);
            }
            colaHijos.clear();
            
            actual = colaAbierto.peek();
            System.out.println("Siguiente nodo " + actual);
        }   
        System.out.println("Mostrando solucion");
        camino = solucion();
        System.out.println("Numero de pasos: " + camino);
        System.out.println("Nodos visitados: " );
        L.mostrarLaberinto(); 
    }
    
    public void busquedaProfundidad(int limite){
        Stack<Nodo> pilaAbierta = new Stack<Nodo>();
        Stack<Nodo> pilaCerrada = new Stack<Nodo>();
        Stack<Nodo> pilaHijos = new Stack<Nodo>();
        int camino;
        Nodo hijo = new Nodo();
        
        pilaAbierta.add(inicial);
        actual = pilaAbierta.peek();
        
        while(!actual.igual(objetivo) && !pilaAbierta.isEmpty()){
            pilaAbierta.pop();
            pilaCerrada.add(actual);
            
            if(actual.getProfundidad()<= limite){
                System.out.println("generando hijos de " + actual);
                pilaHijos = generaHijosProfundidad(actual);
                System.out.println("visitando hijos de " + actual);
                pilaHijos = procesaHijosProfundidad(pilaAbierta, pilaCerrada, pilaHijos);
                
                 for(int i= 0; i<pilaHijos.size(); i++){
                    hijo = pilaHijos.pop();
                    pilaAbierta.add(hijo);
                 }
                 pilaHijos.clear();
                 
                 actual = pilaAbierta.peek();
                 System.out.println("Siguiente nodo " + actual);
            }    
        }
        System.out.println("Mostrando solucion");
        camino = solucion();
        System.out.println("Numero de pasos: " + camino);
        System.out.println("Nodos visitados: " );
        L.mostrarLaberinto();
    }
    
}
