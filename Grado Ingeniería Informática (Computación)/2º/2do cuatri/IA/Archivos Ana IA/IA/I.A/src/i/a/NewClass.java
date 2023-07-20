/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i.a;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Rafa
 */
public class NewClass {
    

public static void main (String [ ] args) throws IOException{
 
    
            System.out.println("Selecciona una opcion: ");
            System.out.println("Opcion 1: Busqueda en anchura ");
            System.out.println("Opcion 2: Busqueda en profundidad ");
            Scanner f = new Scanner(System.in);
            int opcion = f.nextInt();
            
            if(opcion == 1){
                Busqueda b = new Busqueda();
                b.busquedaAnchura();
            }
            if(opcion == 2){
                Busqueda b = new Busqueda();
                b.busquedaProfundidad(200);
            }
        }
}
