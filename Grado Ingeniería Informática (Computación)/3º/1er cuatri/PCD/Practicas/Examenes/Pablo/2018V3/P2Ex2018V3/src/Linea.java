
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pablo Cordon
 */
public class Linea {
    
    private int nPantalon, nCamisa,nCorte,esperapantalon;
    private boolean cosiendo;
    //Declaramos los ReentrantLock y Conditions
    private ReentrantLock bloqueo;
    private Condition corte;
    private Condition coseP;
    private Condition coseC;
    
    
    public Linea(){
    
        this.nCorte = 0;
        this.nPantalon = 0;
        this.nCamisa = 0;
        this.esperapantalon=0;
        this.cosiendo = false;
        this.bloqueo =  new ReentrantLock();
        this.corte = bloqueo.newCondition();
        this.coseP = bloqueo.newCondition();
        this.coseC = bloqueo.newCondition();
    
    }
    
    public void EntraCorte(int tipo) throws InterruptedException{  //Tipo 1 pantalon tipo 2 camisa
        
        bloqueo.lock();
        
        try{
        
        if (nCorte >= 2) {  //Maquina de corte llena
            
            corte.await();
        
        }
        
        else {
            
            if (tipo ==1)
                nPantalon ++;
            else
                nCamisa++;
                
            nCorte++;
        
        }
        
        } finally {
            
        bloqueo.unlock();
        
        }
        
        }
        
        
    public void CoserPatalon() throws InterruptedException{
        
        bloqueo.lock();
        
        try{
        esperapantalon++;
        if (cosiendo)   //La maquina de coser esta ocupada
            
            coseP.await();
        
          //La maquina de coser no esta ocupada
          esperapantalon--;
            cosiendo = true;
            nCorte--;
            nPantalon--;
        
          
            corte.signal();
        
        } finally {
        bloqueo.unlock();
        }
        
        }
        
    public void CoserCamisa() throws InterruptedException{
         
        bloqueo.lock();
        
        try {
        
        if (cosiendo)   //La maquina de coser esta ocupada
            coseC.await();  
        
       // else {  //La maquina de coser no esta ocupada
            cosiendo = true;
            nCorte--;
            nCamisa--;
        
        //}
            corte.signal();
        
        } finally {
        bloqueo.unlock();
        }
    
        }
        
    public void SaleCoser(){
        
        bloqueo.lock();
        
        try {
        
        cosiendo = false;
        
        if (esperapantalon !=0)
            coseP.signal();
        else
            coseC.signal();
        
        } finally {
        bloqueo.unlock();
        }
        }
        
    
}
