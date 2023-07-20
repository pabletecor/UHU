/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2017g1;

import java.util.ArrayList;

/**
 *
 * @author Pablo Cordon
 */
public class Parking {
    
    private int pCoches []; 
    private int pBus [];
    private int nCoches;
    private int nCochesBus;
    private int nBuses;
    private int colaBus;
    private int cual;
    private boolean CocheEnBus;
    
    
    public Parking(){
    
        pCoches = new int [5];
        pCoches = new int [2];
        nCoches = 0;
        nBuses = 0;
        colaBus = 0;
        cual = 0;
        CocheEnBus = false;
    
    }
    
    public synchronized int EntraCoche(int id) throws InterruptedException{
    
        while(nCoches >= 5 && nBuses >=2){
        
            System.out.println("Todo el parking esta lleno. Coche " + id + " se pone a esperar.");
            wait();
        
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
    
        return cual;
        
    }
    
    
    public synchronized void SaleCoche(int id, int cual){ //1 coche 2 bus
    
        if ( cual == 1) {
        
            System.out.println("Coche " + id + " sale del parking de coches.");
            nCoches--;
            notifyAll();

        }
        
        else{
            System.out.println("Coche " + id + " sale del parking de buses.");
            nBuses--;
            notifyAll();
        }
    }
    
    
    public synchronized void EntraBus(int id) throws InterruptedException{
    
        while (nBuses>=2) {
            
            System.out.println("Todo el parking esta lleno. Bus " + id + " se pone a esperar.");
            colaBus++;
            wait();
        }
        
        
        if(nBuses <2){
            
            if(colaBus>0)
                colaBus--;
            
            System.out.println("Bus " + id + " aparca en el parking para buses.");
            nBuses++;

            
        } 
        
    
    }
    
    public synchronized void SaleBus(int id){
    
        System.out.println("Bus " + id + " sale del parking de buses.");
        nBuses--;
        notifyAll();
    
    
    }
    

    
    
}
