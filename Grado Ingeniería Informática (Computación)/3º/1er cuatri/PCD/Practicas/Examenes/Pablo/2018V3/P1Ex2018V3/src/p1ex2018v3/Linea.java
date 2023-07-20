/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1ex2018v3;

/**
 *
 * @author Pablo Cordon
 */
public class Linea {
    
    private int nPantalon, nCamisa,nCorte;
    private boolean cosiendo;
    
    
    public Linea(){
    
        this.nCorte = 0;
        this.nPantalon = 0;
        this.nCamisa = 0;
        this.cosiendo = false;
    
    }
    
    public synchronized void EntraCorte(int tipo) throws InterruptedException{  //Tipo 1 pantalon tipo 2 camisa
        
        while (nCorte >= 2) {  //Maquina de corte llena
            
            wait();
        
        }
            
            if (tipo ==1)
                nPantalon ++;
            else
                nCamisa++;
                
            nCorte++;
        
        
        
        }
        
        
    public synchronized void CoserPatalon() throws InterruptedException{
        
        while (cosiendo)   //La maquina de coser esta ocupada
            wait();
        
      //  if (!cosiendo) {  //La maquina de coser no esta ocupada
            cosiendo = true;
            nCorte--;
            nPantalon--;
        
        //}
            notifyAll();
            
        }
        
    public synchronized void CoserCamisa() throws InterruptedException{
         
        while (cosiendo || nPantalon != 0)   //La maquina de coser esta ocupada
            wait();
        
       // else {  //La maquina de coser no esta ocupada
            cosiendo = true;
            nCorte--;
            nCamisa--;
        
        //}
            notifyAll();
        
        }
        
    public synchronized void SaleCoser(){
        
        cosiendo = false;
        notifyAll();
        
        
        }
        
    
}
