/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piscina;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class PiscinaCanvas extends Canvas{

    
    private int callelibre;
    private int[] callesclub=new int[4];
    private ArrayList<Integer> ColaClub;
    private ArrayList<Integer> ColaLibre;
    private int soyclub;
    //soyclub: -1 nadie 0 club 1 libre
    public PiscinaCanvas(int ancho, int alto)
    {
        this.setSize(ancho, alto);
        this.setBackground(Color.LIGHT_GRAY);
        soyclub=-1;
    }
    public void representa(int callelibre,int[] callesclub,ArrayList<Integer> ColaClub,ArrayList<Integer> ColaLibre,int soyclub)
    {
        this.callelibre=callelibre;
        this.callesclub=callesclub;
        this.ColaClub=ColaClub;
        this.ColaLibre=ColaLibre;
        this.soyclub=soyclub;
        repaint();
    }
    @Override
    public void paint(Graphics g)
    {
        Image offscreen = createImage(getWidth(),getHeight());
        Graphics og = offscreen.getGraphics();
        og.setFont(new Font("Helvetica",Font.BOLD,20));
        og.setColor(Color.BLACK);
        og.drawRect(50, 50, 200, 250);
        og.drawLine(149, 149, 2, 2);
        og.drawLine(199,199,2,2);
        og.setColor(Color.BLUE);
        for(int i=0;i<ColaClub.size();i++)
        {
            og.fillOval(50+(i*5),39, 5, 5);
            og.drawString(Integer.toString(ColaClub.get(i)),50+(i*5),45);
        }
        for(int i=0;i<4;i++)
        {
            if(i>1)
            {
                if(callesclub[i]!=-1)
                {
                    og.drawString(Integer.toString(callesclub[i]),(i%2)*100+60,240);
                    og.fillOval((i%2)*100+60,200,20,20);
                }
                
            }
            else
            {
                if(callesclub[i]!=-1)
                {
                    og.drawString(Integer.toString(callesclub[i]),i*100+60,140);
                    og.fillOval(i*100+60,100,20,20);
                }               
            }
        }
        if(soyclub==0)
        {
            og.drawString(Integer.toString(callelibre),240,100);
            og.fillOval(210,100,20,20);
        }
        else if(soyclub==1)
        {
            og.setColor(Color.RED);
            og.drawString(Integer.toString(callelibre),240,100);
            og.fillOval(210,100,20,20);
        }
        og.setColor(Color.red);
        for(int i=0;i<ColaLibre.size();i++)
        {
            og.fillOval(50+(i*5),260, 5, 5);
            og.drawString(Integer.toString(ColaLibre.get(i)),50+(i*5),270);
        }
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
        
    }
    @Override
    public void update(Graphics g)
    {
        paint(g);
    }
    
}
