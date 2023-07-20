/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class PilaCanvas extends Canvas{
    int cima;
    int capacidad;
    int numelementos;
    Object[] datos;
    String mensaje;
    String aviso;
    public PilaCanvas(int ancho, int alto,int capacidad)
    {
        setBackground(Color.cyan);
        this.setSize(ancho,alto);
        repaint();
        this.capacidad=capacidad;
        this.cima=-1;
        this.numelementos=0;
        datos=new Object[capacidad];
        mensaje="";
        aviso="";
    }
    public void representa(Object[] buf,int cima, int numelementos,String mensaje)
    {
        datos=buf;
        this.cima=cima;
        this.numelementos=numelementos;
        this.mensaje=mensaje;
        this.aviso="";
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        // Se crea un buffer intermedio para que montar la salida completa
        // y luego pintarla de una vez, evitando el parpadeo
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);

        og.setFont(f1);
        og.setColor(Color.black);
        int x=400;
        for (int i = 0; i < capacidad; i++) {
            og.drawRect(100,x,50,40);
            x-=40;
        }
        x=425;
        og.setColor(Color.white);
        for (int i = 0; i < this.numelementos; i++) {
            og.drawString(datos[i].toString(),115, x);
            x-=40;
        }
        og.drawString(mensaje, 170,425);
        og.drawString(aviso, 170, 455);
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }

    /* El update original del canvas, borra el canvas y llama a paint. Si queremos 
     sobreescribir  lo que hay pintado, sobrecargamos update y hacemos que llame 
     paint. Así no borra lo anterior, y no se produce parpadeo
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    public void avisa(String mensaje)
    {
        this.aviso=mensaje;
        repaint();
    }
}
