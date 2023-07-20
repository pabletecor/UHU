/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i.a;

/**
 *
 * @author Rafa
 */
public class Nodo {
    
    private Integer x;
    private Integer y;
    private Integer profundidad;
  
    private Nodo padre;
    
    
    
    Nodo(Integer nx, Integer ny, Nodo nPadre, Integer nProfundidad){
        x = nx;
        y = ny;
        padre = nPadre;
        profundidad = nProfundidad;
    }
    
    Nodo(Nodo a){
        x = a.getX();
        y = a.getY();
        padre = a.getPadre();
        profundidad = a.getProfundidad();
    }
    
    
    public Nodo getPadre(){
        return padre;
    }
    public Integer getX(){
        return x;
    }
    public Integer getY(){
        return y;
    }
    public Integer getProfundidad(){
        return profundidad;
    }
    Nodo(){
        x = 0;
        y = 0;
        padre = null;
        profundidad = 0;
    }
    
    public Boolean igual(Nodo N){
        return(N.x==this.x && N.y == this.y);
    }
    
    public String toString(){
        return getX()+"," + getY();
    }
    
}