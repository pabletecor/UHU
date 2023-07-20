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
public class NadaClub extends Thread {
    
    private Piscina piwi;
    int id;
    
    public NadaClub (Piscina p, int id)
    {
        this.piwi = p;
        this.id = id;
    
    }
    
    @Override
    public void run()
    {
        int calle;
        
        Random aleatorio = new Random(System.currentTimeMillis());
        
        try {
            
            calle = piwi.entraclub();
            System.out.println("Nadador Club " + Thread.currentThread().getId() + " entra en la calle " + calle);
            System.out.println("Nadador club " + Thread.currentThread().getId() + " está nadando");
            Thread.sleep(aleatorio.nextInt(((5)+2)*1000));  //Esta nadando
            piwi.saleclub(calle); 
            System.out.println("Nadador Club " + Thread.currentThread().getId() + " sale de la calle " + calle);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NadaClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}