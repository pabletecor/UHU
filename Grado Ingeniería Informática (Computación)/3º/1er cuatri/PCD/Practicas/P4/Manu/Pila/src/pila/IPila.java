/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author Manu Reyes
 */
public interface IPila {
    
    public int getNum();
    public void Apila(Object o) throws Exception;
    public Object Desapila() throws Exception;
    public Object Primero() throws Exception;
}
