/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Recurso {
    
    private int[] contadores = {0,0};
    private EjemploCanvas cv;
    
    public Recurso(EjemploCanvas cv){
        this.cv = cv;
    }
    
    public synchronized void incrementa(int cual){
        contadores[cual]++;
        verContadores();
        cv.representa(contadores);
    }
    
    public int[] getContadores(){
        
        return contadores;
    
    }
    
    public void verContadores(){
        System.out.println("Contadores 1 vale" + contadores[0]);
        System.out.println("Contadores 2 vale" + contadores[1]);
    
    }
}
