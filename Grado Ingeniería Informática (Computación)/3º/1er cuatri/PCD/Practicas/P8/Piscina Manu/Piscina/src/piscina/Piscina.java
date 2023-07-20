/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piscina;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class Piscina {
    ReentrantLock mutex;
    Condition colalibre;
    Condition colaclub;
    ArrayList<Integer> ClubEsp;
    ArrayList<Integer> LibreEsp;
    int[] Club;
    int Libre;
    int ClubC=0;
    int LibreC=0;
    int LibreEsperando=0;
    boolean EstoyEnLibre;
    PiscinaCanvas piscan;
    public Piscina(PiscinaCanvas piscan)
    {
       mutex=new ReentrantLock();
       colalibre=mutex.newCondition();
       colaclub=mutex.newCondition();
       ClubC=0;
       LibreC=0;
       LibreEsperando=0;
       EstoyEnLibre=false;
       this.piscan=piscan;
       ClubEsp=new ArrayList<>();
       LibreEsp=new ArrayList<>();
       Club=new int[4];
       for(int i=0;i<4;i++)
           Club[i]=-1;
       Libre=-1;
    }
    public synchronized void EntraClub(int id) throws InterruptedException
    {
        mutex.lock();
        try {
            if(ClubC>4&&LibreC>1&&LibreEsperando>0)
            {
                ClubEsp.add(id);
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
                colaclub.await();
                ClubEsp.remove((Object)id);
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
            }
            if(ClubC<4)
            {
                int i=0;boolean enc=false;
                while(i<4&&!enc)
                {
                    if(Club[i]==-1){
                        enc=true;
                        Club[i]=id;
                        ClubC++;
                        piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);

                    }
                }
            }else if(LibreC<1)
            {
                if(Libre==-1)
                {
                    EstoyEnLibre=true;
                    Libre=id;
                    LibreC++;
                    piscan.representa(Libre, Club, ClubEsp, LibreEsp, 0);
                }
                
            }
        } finally {
            mutex.unlock();
        }
        
    }
    public synchronized void SaleClub(int id)
    {
        mutex.lock();
        try {
                if(EstoyEnLibre)
                {
                    Libre=-1;
                    EstoyEnLibre=false;
                    piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
                    LibreC--;
                    if(LibreEsperando>0)
                        colalibre.signal();
                    else
                        colaclub.signal();
                }
                else
                {
                    for(int i=0;i<4;i++)
                        if(Club[i]==id)
                        {
                            Club[i]=-1;
                            piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
                            ClubC--;
                            colaclub.signal(); 
                        }
                    
                }
                
        } finally {
            mutex.unlock();
        }
    }
    public synchronized void EntraLibre(int id) throws InterruptedException
    {
        mutex.lock();
        try {
            if(LibreC>1)
            {
                LibreEsp.add(id);
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
                colalibre.await();
                LibreEsp.remove((Object)id);
                LibreEsperando++;
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
            }
                Libre=id;
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, 1);
                LibreC++;
        } finally {
            mutex.unlock();
        }
    }
    public synchronized void SaleLibre(int id)
    {
        mutex.lock();
        try {
                Libre=-1;
                piscan.representa(Libre, Club, ClubEsp, LibreEsp, -1);
                LibreC--;
                if(LibreEsperando>0)
                    colalibre.signal();
                else
                    colaclub.signal();
        } finally {
            mutex.unlock();
        }
    }
}
