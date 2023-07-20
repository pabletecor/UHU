/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Pablo Cordon
 */
public class Parking {
    
    //private int pCoches []; 
    //private int pBus [];
    private int nCoches;
    private int nCochesBus;
    private int nBuses;
    private int colaBus;
    private int cual;
    private boolean CocheEnBus;
    private ReentrantLock bloqueo;
    private Condition pCoches;
    private Condition pBus;
    
    
    public Parking(){
    
        //pCoches = new int [5];
        //pCoches = new int [2];
        nCoches = 0;
        nBuses = 0;
        colaBus = 0;
        cual = 0;
        CocheEnBus = false;
        bloqueo = new ReentrantLock();
        pCoches = bloqueo.newCondition();
        pBus = bloqueo.newCondition();
    
    }
    
    public int EntraCoche(int id) throws InterruptedException{
    
        
        bloqueo.lock();
        
        
        try {
        
        while(nCoches >= 5 && nBuses >=2){
        
            System.out.println("Todo el parking esta lleno. Coche " + id + " se pone a esperar.");
            pCoches.await();    //Espera en el parking de coches
        
        }
    
        if( nCoches < 5 ){
        
            cual = 1;
            System.out.println("Coche " + id + " aparca en el parking para coches.");
            //pCoches[nCoches] = id;
            nCoches++;

        
        }
        
        else if (nBuses < 2 && colaBus==0) {    //Si hay menos de dos buses y no hay ningun bus esperando, aparco
        
            cual = 2;
            System.out.println("Coche " + id + " aparca en el parking para buses.");
            //pBus[nBuses] = id;
            nBuses++;

        
        }
        
        } finally {
        
            bloqueo.unlock();
            
        }
        
        return cual;
    }
    
    
    public void SaleCoche(int id, int cual){ //1 coche 2 bus
    
        bloqueo.lock();
        
        try{
            
        
        if ( cual == 1) {
        
            System.out.println("Coche " + id + " sale del parking de coches.");
            nCoches--;
            pCoches.signal();

        }
        
        else{
            System.out.println("Coche " + id + " sale del parking de buses.");
            nBuses--;
            //Si hay buses esperando, tienen preferencia.
            if(colaBus >0)
                pBus.signal();
            else
                pCoches.signal();
            
        }
        
        } finally {
            
            bloqueo.unlock();
        }
    }
    
    
    public void EntraBus(int id) throws InterruptedException {
    
        bloqueo.lock();
        
        try{
        
        while (nBuses>=2) {
            
            System.out.println("Todo el parking esta lleno. Bus " + id + " se pone a esperar.");
            colaBus++;
            pBus.await();
        }
        
        
        if(nBuses <2){
            
            if(colaBus>0)
                colaBus--;
            
            System.out.println("Bus " + id + " aparca en el parking para buses.");
            nBuses++;

            
        } 
        
        } finally {
            bloqueo.unlock();
        }
    
    }
    
    public void SaleBus(int id){
    
        bloqueo.lock();
     
        try {
        
        System.out.println("Bus " + id + " sale del parking de buses.");
        nBuses--;
        if(colaBus >0)
                pBus.signal();
            else
                pCoches.signal();
    
        } finally {
            bloqueo.unlock();
        }
    
    }
    

    
    
}
