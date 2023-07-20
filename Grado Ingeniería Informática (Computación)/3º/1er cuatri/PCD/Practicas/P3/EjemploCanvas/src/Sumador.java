/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Sumador extends Thread {
    
    private Recurso r;
    private int cual;
    
    
    public Sumador(Recurso r, int cual){
        this.r=r;
        this.cual=cual;
    }
    
    public void run(){
        for (int i = 0; i < 10000; i++) {
            r.incrementa(cual);
        }
    }
}
