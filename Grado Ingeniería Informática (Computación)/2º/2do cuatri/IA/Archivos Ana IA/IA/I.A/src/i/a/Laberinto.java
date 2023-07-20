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

/**
 *
 * @author Rafa
 */
public class Laberinto implements Cloneable{
    private Integer altura;    
    private Integer anchura;
    private char [][] matriz;
    private Nodo actual;
    private Nodo inicio;
    private Nodo fin;
    
    Laberinto(){
        altura = 0;
        anchura = 0;
    }
    
    Laberinto(Integer nAltura, Integer nAnchura){
        altura = nAltura;
        anchura = nAnchura;
        matriz = new char [nAltura][nAnchura];
    }
    
    Laberinto(Laberinto l){
        anchura = l.anchura;
        altura = l.altura;
        for(int i= 0; i< altura; i++){
           for(int j= 0; j<anchura; j++){
               insertar(i,j,l.getCaracter(i, j));
           }
        }
    }
    
    public Integer getAltura(){
        return altura;
    }
    
    public Integer getAnchura(){
        return anchura;
    }
    
    public char getCaracter(Integer nx, Integer ny){
        return matriz[nx][ny];
    }
        
    public void insertar(Integer ix, Integer iy, char c){
        matriz[ix][iy]=c;
    }
    
    public Nodo getActual(){
        return actual;
    }

    public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
        }
        b.close();
    }
    
    public void mostrarLaberinto(){
        for(int i = 0; i < altura ; i++){
            for(int j= 0; j< anchura; j++){
                System.out.print(matriz[i][j]);
            }
            System.out.println(" ");
        }
    }
    
    public void pintarCamino(){
        
    }
    
    public Object clone(){
        return new Laberinto(this);
    }
    
}

    
