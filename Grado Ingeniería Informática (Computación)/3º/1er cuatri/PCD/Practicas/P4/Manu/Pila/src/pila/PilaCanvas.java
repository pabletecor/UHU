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
 * Clase que crea el lienzo sobre el cual se visualiza el proyecto.
 * Inicializamos el lienzo y lo actualizamos cada vez que se llama a esta clase
 * se actualiza dibujando el dibujo de la pila e insertando en el todos los
 * datos de la pila.
 * También muestra si estamos apilando o desapilando y si la pila esta vacía
 * o llena.
 * @author Manu Reyes
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
        setBackground(Color.DARK_GRAY);
        this.setSize(ancho,alto);
        this.capacidad=capacidad;
        this.cima=-1;
        this.numelementos=0;
        datos=new Object[capacidad];
        mensaje="";
        aviso="";
        repaint();
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

        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.BOLD, 20);
        
        og.setFont(f1);
        og.setColor(Color.WHITE);
        og.fillRect(97, 97, 56, 606);
        og.setColor(Color.LIGHT_GRAY);
        og.fillRect(100, 100, 50, 600);
        og.setColor(Color.black);
        int x=660;
        for (int i = 0; i < capacidad; i++) {
            og.drawRect(100,x,50,40);
            x-=40;
        }
        x=685;
        og.setColor(Color.white);
        for (int i = 0; i < this.numelementos; i++) {
            og.drawString(datos[i].toString(),115, x);
            x-=40;
        }
        og.setColor(Color.RED);
        og.fillOval(170, x+20, 20, 20);
        og.setColor(Color.WHITE);
        og.drawString(mensaje, 100,750);
        og.drawString(aviso, 100, 780);
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }

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
