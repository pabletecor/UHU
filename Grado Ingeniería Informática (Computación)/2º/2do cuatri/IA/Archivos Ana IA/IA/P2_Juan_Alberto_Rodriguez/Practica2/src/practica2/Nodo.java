/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Juan Alberto
 */
public class Nodo
{
    private int [][] puzzle;
    private Nodo padre;
    private int profun;
    private int coste;
    private int heuris;
  
//constructor para puzzle aleatorio
    public Nodo()
    {   
        //cada hueco con un numero aleatorio
        ArrayList<Integer> tabla=new ArrayList<>();
        puzzle=new int[3][3];
        int j=0, k=0, numeros=0, espacio;
        boolean encontrado;

        for(int i=0; i<9; i++) tabla.add(i);
        
        Random rnd=new Random();
        while(numeros !=9)
        {
            espacio=(int)(rnd.nextDouble() * 9.0); 
            encontrado=false;
            for(int i=0; i<tabla.size() && !encontrado; i++){
                if(espacio==tabla.get(i)){
                    puzzle[j][k++]=espacio;
                    if(k==3)
                    {
                        k=0; j++;
                    }
                    tabla.remove(i);
                    numeros++;
                    encontrado=true;
                }
            }
        }
        padre=null;
    }
//constructor para puzzle por parametros
    public Nodo(int [][] estado)
    {   
        //se rellena el puzzle con los numeros introducidos
        this.puzzle=new int[3][3];
        for(int i=0; i<estado.length; i++)
        {
            for(int j=0; j<estado.length; j++)
            {
                this.puzzle[i][j]=estado[i][j]; 
            }
        }
        padre=null;    
    }
    
//para tener la posicion del hueco (el 0)
    public int[] Localizacero()
    { 
        //busca la fila y columna del hueco
        int[] pos=new int[2];
        for(int i=0; i<puzzle.length; i++)
        {
            for(int j=0; j<puzzle.length; j++)
            {
                if(puzzle[i][j]==0)
                {
                    pos[0]=i;
                    pos[1]=j; 
                }
            }
        }
        return pos;
    }
    
//calcular el coste 
    public int Calcularcoste()
    {
        return heuris + profun;
    }
    
//********************** gets **********************
    public int[][] getPuzzle() 
    {
        return puzzle;
    }
    
    public Nodo getPadre() 
    {
        return padre;
    }

    public int getProfundidad() 
    {
        return profun;
    }

    public int getCoste() 
    {
        return coste;
    }
    
    public int getHeuristica() 
    {
        return heuris;
    }
//********************** sets **********************
  
    public void setPuzzle(int[][] puzzle)
    {
        this.puzzle = puzzle;
    }
 
    public void setPadre(Nodo padre)
    {
        this.padre = padre;
    }

    public void setProfundidad(int profun)
    {
        this.profun= profun;
    }

    public void setCoste(int coste)
    {
        this.coste = coste;
    }
      
    public void setHeuristica(int heuris)
    {
        this.heuris= heuris;
    }
}
