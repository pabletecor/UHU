/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Pablo Cordon
 */
/*

Una piscina dispone de dos calles para usuarios de un club de natación y una calle
para nado libre. En cada calle del club puede haber un máximo de dos usuarios, y en
la calle libre un solo usuario.

A la piscina acceden usuarios del club, y otros para nadar por libre. Los usuarios por
libre siempre usarán su calle para nadar, pero los usuarios del club podrá usar la calle
libre si está vacía y no hay usuarios por libre esperando. 


 */
public class Piscina {

    private int callelibre;
    private int nclub1;
    private int nclub2;
    private int cual; //Ponemos la calle 1 por defecto
    private int esperalibre; //Contador de todos los nadadores libres que estan esperando
    private String [] calle1;
    private String [] calle2;
    private String calle3;
    
    //Creamos el ReentrantLock
    private ReentrantLock bloqueo;
    private Condition Colalibre;
    private Condition Colaclub;

    //Creamos el canvas
    private Canvas_Nadadores canvas;
    
    public Piscina(Canvas_Nadadores elcanvas){
        
        this.callelibre = 0;
        this.nclub1 = 0;
        this.nclub2 = 0;
        this.calle1= new String [2];
        this.calle2= new String [2];
        this.calle3="";
        this.cual=1; //Ponemos la calle 1 por defecto
        this.esperalibre = 0; //Contador de todos los nadadores libres que estan esperando
        this.bloqueo = new ReentrantLock();
        this.Colalibre = bloqueo.newCondition();
        this.Colaclub = bloqueo.newCondition();
        this.canvas = elcanvas;
    
    }
    
    
    public int entraclub() throws InterruptedException {   //Tipo 1

        
         
        bloqueo.lock();

        String idHilo = Thread.currentThread().getId() + "";    //Id del hilo que esta usando la piscina
        
        try {

            while ((nclub1 + nclub2) == 4 && (callelibre == 1 || esperalibre > 0  )) {

                //Si todas las calles están ocupadas, me espero en la cola del club
                Colaclub.await();

            }

            if (nclub1 < 2) {   //Calle 1
                
                calle1[nclub1] = idHilo;    //Nadador idHilo esta nadando en la calle 1 en la pos nclub1
                nclub1++;
                canvas.representa(1,1,nclub1,nclub2,calle1,calle2,calle3);
                
                cual = 1;
                
            } else if (nclub2 < 2) {    //Calle 2
                
                calle2[nclub2] = idHilo;    //Nadador idHilo esta nadando den la calle 2 en la pos nclub2
                nclub2++;
                canvas.representa(1,2,nclub1,nclub2,calle1,calle2,calle3);
                
                cual = 2;
                
            }
            
            else {  //Calle 3
                
                calle3=idHilo;
                callelibre++;
                canvas.representa(1,3,nclub1,nclub2,calle1,calle2,calle3);
                
                cual = 3;

            }

        } finally {
            
            bloqueo.unlock();
            
        }
        
        return cual;
        
    }

    public void saleclub (int cual) {

        
        
        bloqueo.lock();
        
        String idHilo = Thread.currentThread().getId() + "";    //Id del hilo que esta usando la piscina
        
        try {
        
        switch (cual) {
            
            case 1:
                nclub1--;
                canvas.representa(1,1,nclub1,nclub2,calle1,calle2,calle3);
                break;
                
            case 2:
                nclub2--;
                canvas.representa(1,2,nclub1,nclub2,calle1,calle2,calle3);
                break;
                
            default:
                callelibre--;
                canvas.representa(1,3,nclub1,nclub2,calle1,calle2,"");
                Colalibre.signal();
                break;
        }

        //Hago signal a las dos conditions (No se si asi está bien)
        Colaclub.signal();
        
        
        } finally {
        
        bloqueo.unlock();
        
        }
    }

    public void entralibre() throws InterruptedException{  //Tipo 2
        
        esperalibre ++; //Antes del bloqueo cuento los libres que quieren nadar
        
        bloqueo.lock();
        
        calle3 = Thread.currentThread().getId() + "";    //Id del hilo que esta usando la piscina
        
        try{
        
            while (callelibre == 1) {
                
                Colalibre.await();
            }
            
            if (callelibre<1) {
                
                esperalibre--; //Si entran a nadar, ya no estan esperando.
                callelibre++;
                canvas.representa(2,3,nclub1,nclub2,calle1,calle2,calle3);
                
            }
            
        } finally {
        
            bloqueo.unlock();
        }
        
    
    
    }
    
    
    public void salelibre(){
        
        bloqueo.lock();
        
        calle3="";    //Id del hilo que esta usando la piscina
        
        try {
        
        canvas.representa(2,3,nclub1,nclub2,calle1,calle2,calle3);
        callelibre--;
        //Hago signal a las dos conditions (No se si asi está bien)
        Colalibre.signal();
        Colaclub.signal();
        
    
        } finally {
        
        bloqueo.unlock();
        }

    }
    
    
}
