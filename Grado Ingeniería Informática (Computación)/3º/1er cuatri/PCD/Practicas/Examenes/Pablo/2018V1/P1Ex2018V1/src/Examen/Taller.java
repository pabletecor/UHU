/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

/**
 *
 * @author Pablo Cordon
 */
public class Taller {
    
    int nOperarios,nCoches,nCamiones;
    
    public Taller(){
    
        this.nOperarios = 4;
        this.nCoches = 0;
        this.nCamiones = 0;
    
    }
    
    public synchronized void EntraCamion() throws InterruptedException{
    
        nCamiones++;
        //No hay suficientes operarios
        while (nOperarios < 2){
            wait();
        
        }
        
        nCamiones--;
        nOperarios -= 2;
        
    
    }
    
    
    public synchronized void SaleCamion() {
    
        nOperarios +=2;
        notifyAll();
    
    }
    
    public synchronized void EntraCoche() throws InterruptedException{
    
        while(nOperarios<1 || nCamiones !=0){
            wait();
        
        }
        
        nOperarios--;
    
    }
    
    public synchronized void SaleCoche(){
    
        nOperarios++;
        notifyAll();
        
    }
    
    
}
