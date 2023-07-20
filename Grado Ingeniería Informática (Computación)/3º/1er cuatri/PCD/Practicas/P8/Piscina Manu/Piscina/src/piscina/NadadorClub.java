/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piscina;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class NadadorClub extends Thread{
    private final int id;
    private final Piscina pis;
    public NadadorClub(int id,Piscina pis)
    {
        this.id=id;
        this.pis=pis;
    }
    @Override
    public void run()
    {
        try {
           pis.EntraClub(id);
           Thread.sleep(new Random().nextInt(6)*100);
           pis.SaleClub(id);
        } catch (InterruptedException e) {
        }
    }
}
