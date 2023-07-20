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
public interface IPila {
    
    public int getNum();
    public void Apila(Object o);
    public Object Desapila();
    public Object Primero();
}
