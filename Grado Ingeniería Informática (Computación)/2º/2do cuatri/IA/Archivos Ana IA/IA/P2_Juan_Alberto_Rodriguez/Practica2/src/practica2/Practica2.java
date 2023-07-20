/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;
import java.util.Scanner;
/**
 *
 * @author Juan Alberto
 */
public class Practica2 
{
    	static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola
	static int select = -1; //opción elegida del usuario
	//static int num1 = 0, num2 = 0; //Variables
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		//el 0 para salir
		while(select != 0)
                {
			//try catch para evitar que el programa termine si hay un error
			try{
				System.out.println("Elige opción:\n1.- Algoritmo A*" + "\n2.- Algoritmo Anchura" + "\n3.- Algoritmo Greedy Best First" +"\n4.- Algoritmo Profundidad\n" + "0.- Salir");
				//recoger una variable 
				select = Integer.parseInt(scanner.nextLine()); 
	
                               int [][] Inicial = {{4,1,3},{6,2,5},{7,8,0}}; //ejemplo 
                               int [][] Solucion = {{1,2,3},{4,0,5},{6,7,8}};
                                Estado e = new Estado(Inicial,Solucion );
                                Nodo n;

				switch(select)
                                {
				case 1: 
					e.A_Estrella(2);
					break;
				case 2: 
                                        e.BusquedaAnchura(25, 2);
					break;
                                case 3:
                                        e.Greedy(2);
                                        break;
                                case 4:
                                        e.BusquedaProfundidad(25, 2);
				case 0: 
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Error: introduzca un numero valido");break;
				}
				
				System.out.println("\n"); 
			}catch(Exception e){
				System.out.println("Error: introduzca un numero");
			}
		}

	}

    }
    