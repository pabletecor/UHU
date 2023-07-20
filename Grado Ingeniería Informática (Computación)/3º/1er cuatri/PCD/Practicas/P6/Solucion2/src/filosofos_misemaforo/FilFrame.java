/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos_misemaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pablo Cordon
 */
public class FilFrame extends javax.swing.JFrame {

    /**
     * Creates new form FilFram
     */
    public FilFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String args[]) throws InterruptedException, Exception {
        int alto = 800;
        int ancho = 600;
        FilFrame f =  new FilFrame();
        //Tamaño de la ventana
        f.setSize(alto, ancho);
    
        Canvas_Filosofos cf = new  Canvas_Filosofos(ancho,alto);
    
        f.add(cf);
        f.setVisible(true);
        
        //SemaforoGeneral silla = new SemaforoGeneral(4);
        //SemaforoBinario [] palillos = new SemaforoBinario[5];
        
        Filosofo [] fil = new Filosofo [5];
        Semaphore [] palillos = new Semaphore [5];
       
        //inicializamos los palillos y Filosofos
        for (int i = 0; i < 5; i++) {
             
          palillos[i] = new Semaphore(1);
            
        }
            
        for (int i = 0; i < 5; i++) {
            
            //Mod 4 porque filosofos del 0 al 4
            fil[i]= new Filosofo(i,palillos[i], palillos[(i%4)+1], cf);
            
        }
           
        //Ponemos a correr los filósofos
        for (int i = 0; i < 5; i++) {
            
            fil[i].start();
            
        }

        //Sincronizamos a los filosofos
        for (int i = 0; i < 5; i++) {
            
            fil[i].join();
        }

          Thread.sleep(20000);
          System.exit(0);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
