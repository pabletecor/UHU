/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holamundo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Holamundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Hola q ase"); 

        

        

        try {
            Saluda s = new Saluda("Welcome cari");
            s.saludo(null, 0);
            s.saludo ("Hola",1);
        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }
           

    }

   
    
    }
   
