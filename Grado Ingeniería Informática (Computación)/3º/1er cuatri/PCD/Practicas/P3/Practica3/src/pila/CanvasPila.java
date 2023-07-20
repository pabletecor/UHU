/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Pablo Cordon
 */
public class CanvasPila extends Canvas {
    
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private String mensaje;
    private String aviso;
    
    
    public CanvasPila(int ancho, int alto, int capacidad){
        this.setSize(ancho,alto);
        this.setBackground(Color.WHITE);
        repaint();
        this.capacidad=capacidad;
        this.cima=-1;
        this.numelementos=0;
        datos=new Object[capacidad];
        mensaje="";
        aviso="";
    
    }
    
    @Override
    public void paint ( Graphics g){
         
         // Se crea un buffer intermedio para que montar la salida completa
        // y luego pintarla de una vez, evitando el parpadeo
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.ITALIC , 20);
        Font f2 = new Font("Monospace", Font.BOLD , 20);

        og.setFont(f1);
        og.setColor(Color.red);
        //Falta por crear los rectangulos y rellenarlos.
        
        //Creamos los rectangulos
        og.setColor(Color.BLACK);
        og.setFont(f1);
        
        int y = 375;
        for (int i = 0; i < capacidad; i++) {
       
            og.drawRect(40, y, 50, 30); //drawrect(pos x , pos y , ancho, alto)
            y += 30;
        }
        //Ponemos los datos dentro de los rectangulos
        
        og.setColor(Color.BLACK);
        og.setFont(f1);
        
        y = 550;
        for (int i = 0; i < this.numelementos; i++) {
            
            og.drawString(datos[i].toString(), 45, y);
            y -= 30;
            
        }
        
        og.setColor(Color.darkGray);
        og.setFont(f2);
        og.drawString(mensaje, 110, 530);
        
        og.setColor(Color.red);
        og.setFont(f2);
        og.drawString(aviso, 110, 370);
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    
    }
    
    @Override
    public void update (Graphics g) {
           paint(g);
        
    }
    
    public void avisa (String mensaje) {
        this.aviso=mensaje;
        repaint();
    
    }
    
    public void representa (Object[] buf, int cima, int numele, String mensaje ) {
        datos=buf;
        this.cima=cima;
        this.numelementos=numele;
        this.mensaje=mensaje;
        this.aviso="";
        repaint();
    
    }
}
