/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Cordon
 */
public class NadaLibre extends Thread{
    
    private Piscina piwi;
    int id;
    
    public NadaLibre (Piscina p, int id)
    {
        this.piwi = p;
        this.id = id;
    
    }
    
    @Override
    public void run()
    {
        
        Random aleatorio = new Random(System.currentTimeMillis());
        
        try {
            
            piwi.entralibre();
            System.out.println("Nadador libre " + Thread.currentThread().getId() + " entra en la calle 3"); //La calle 3 es la libre
            System.out.println("Nadador libre " + Thread.currentThread().getId() + " está nadando");
            Thread.sleep(aleatorio.nextInt(((5)+2)*1000));  //Esta nadando
            piwi.salelibre(); 
            System.out.println("Nadador libre " + Thread.currentThread().getId() + " sale de la calle 3");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NadaClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
}
