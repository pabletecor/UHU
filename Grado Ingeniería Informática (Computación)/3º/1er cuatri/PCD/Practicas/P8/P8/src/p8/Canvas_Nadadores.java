/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p8;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author usuario
 */
public class Canvas_Nadadores extends Canvas {

    int calle;
    int numNadadoresC1; //Ponemos con un for el numero de nadadores por cada calle (como en la pila)
    int numNadadoresC2;
    int tipo;
    private String [] calle1;
    private String [] calle2;
    private String calle3;

    public Canvas_Nadadores(int alto, int ancho) {

        this.setSize(ancho, alto);
        this.setBackground(Color.white);
        this.calle = 0;
        this.calle3 = "";
        this.numNadadoresC1 = 0;
        this.numNadadoresC2 = 0;
        this.tipo = 1;
        

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {

        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.BOLD, 20);

        //Creamos las calles (rectangulos largos)
        //5 rectangulos, primero y ultimo la cola de hilos que quieren entrar, segunda y tercera con 2 nadadores y cuarta con 1 solo
        og.setFont(f1);

        int x = 20;
        int y = 55;
        for (int i = 0; i < 5; i++) {

            og.setColor(Color.LIGHT_GRAY);

            if (i > 0 && i < 4) {
                og.setColor(Color.CYAN);
            }

            og.fillRect(x, y, 580, 50); //drawrect(pos x , pos y , ancho, alto)
            y += 80;

        }

        //Creamos los nadadores y pintarlos en su respectiva piscina
        //Nadadores del club (tipo 1)
       

            //Ponemos tipografia de color NEGRO
            og.setColor(Color.BLACK);

            //og.drawString(id, 500, y);
            
            x=400;  //Primera calle
            
            for (int i = 0; i < numNadadoresC1; i++) {
            
                og.drawString(calle1[i], x, 165);
                x -= 155;
        }
            
             x=400;  //Segunda calle
            
            for (int i = 0; i < numNadadoresC2; i++) {
            
                og.drawString(calle2[i], x, 245);
                x -= 155;
        }
            
            x=300;  //Tercera calle
            
            //Si el nadador es libre pintamos de rojo el numero
            if(tipo==2)
                og.setColor(Color.RED);
            
                og.drawString(calle3, x, 325);         
        

        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);

    }

    public void representa(int tiponad, int calle, int numNadadoresC1 , int numNadadoresC2, String [] c1, String [] c2, String c3 ) {

        
        this.calle = calle;
        this.numNadadoresC1 = numNadadoresC1;
        this.numNadadoresC2 = numNadadoresC2;
        this.tipo = tiponad;
        this.calle1 = c1;
        this.calle2=c2;
        this.calle3 = c3;
        repaint();

    }

}
