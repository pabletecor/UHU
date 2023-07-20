/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploreentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class EjemploReentrantLock {

    /**
     * @param args the command line arguments
     */
    ReentrantLock mutex = new ReentrantLock();
    Condition c1 = mutex.newCondition();
    Condition c2 = mutex.newCondition();
    
                
                
                
                
     public void m1() throws InterruptedException {
        
        mutex.lock();
        try{
        
            c1.await();
            c2.signal();
            
        
        } finally {
        
        mutex.unlock();
        
        
        }
        
        
        
        
        
        }
     
     
     public void m2() throws InterruptedException {
        
        mutex.lock();
        try{
        
            c1.await();
            c2.signal();
            
        
        } finally {
        
        mutex.unlock();
        
        
        }
     
     }
     
     
     
    public static void main(String[] args) {
        // TODO code application logic here
        ;
        
        
        
    }
   
    
    
    
}
