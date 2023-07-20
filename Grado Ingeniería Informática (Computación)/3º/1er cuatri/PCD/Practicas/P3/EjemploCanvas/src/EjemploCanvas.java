
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class EjemploCanvas extends Canvas {
    
    private int [] contadores;
    
    public EjemploCanvas(int ancho, int alto){
        this.setSize(ancho,alto);
        this.setBackground(Color.cyan);
        repaint();
    
    }
    
    public void representa(int [] contadores){
        this.contadores = contadores;
        repaint();
    
    }
    
    @Override
    public void update (Graphics g){
        paint (g);
    }
    
    @Override
    public void paint (Graphics g){
        
        Font f1 = new Font ("Arial", Font.ITALIC+Font.BOLD,20);
        Font f2 = new Font ("Courier", Font.ITALIC+Font.BOLD,20);
        
        
        Image img = createImage (getWidth(), getHeight());
        Graphics og = img.getGraphics();
        
        og.setColor(Color.blue);
        og.setFont(f2);
        og.fillOval(20, 20, 20, 20);
        og.drawString("Contador 1: " + contadores[0], 50, 40);
    
        
        og.setColor(Color.red);
        og.setFont(f2);
        og.fillOval(20, 60, 20, 20);
        og.drawString("Contador 2: " + contadores [1], 50, 80);
        
        g.drawImage(img, 0, 0, null);
    }
    
    
}
