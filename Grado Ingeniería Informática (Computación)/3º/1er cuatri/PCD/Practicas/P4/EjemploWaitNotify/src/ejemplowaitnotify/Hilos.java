/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplowaitnotify;

/**
 *
 * @author usuario
 */
public class Hilos implements Runnable {
    
    private int id;
    private Recurso r;
    
    public Hilos(int id, Recurso r){
    
        this.id=id;
        this.r=r;
    
    }
    
    
    @Override
    public void run(){
    
        for (int i = 0; i < 5; i++) {
            r.cogeturno(id);
            System.out.println("Soy el hilo "+id);
            r.pasaturno();
        }
    
        if(id==4){
            r.cogeturno(id);
            System.out.println("Soy el hilo "+id);
            r.pasaturno();
        
        }
        
    }
    
}
