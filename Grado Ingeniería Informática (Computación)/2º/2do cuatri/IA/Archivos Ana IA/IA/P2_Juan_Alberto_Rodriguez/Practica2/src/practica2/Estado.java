/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 *
 * @author Juan Alberto
 */
public class Estado 
{    
    Nodo Estadoinicial;
    Nodo Estadofinal;
    public String NombreAlgoritmo;
    public int MaximoAbiertos;
    public int MaximoCerrados;
    public int Visitados;
    public long milisegundos;
    
//Construye un estado inicial aleatorio 
    public Estado()
    {
        int [][] Solucion={{1,2,3},{4,0,5},{6,7,8}}; 
        Estadofinal=new Nodo(Solucion);
        Estadoinicial=new Nodo();
    }

//Construye un estado inicial por parametro (usar combinacion correo)   
    public Estado(int [][] Inicio, int[][] Solucion) 
    { 
        this.Estadoinicial=new Nodo(Inicio);
        this.Estadofinal=new Nodo(Solucion);
    }

    public void setSolucion(Nodo Solucion) 
    {
        this.Estadofinal = Solucion;
    }
   
//*********************************** empieza busqueda A* ******************
    
    public void A_Estrella(int modo)
    {
         long TiempoEmpieza = System.currentTimeMillis(); //comienza
         Visitados = 0;
         Queue<Nodo> Abiertos = new LinkedList<>();
         ArrayList<Nodo> Cerrados = new ArrayList<>();
         ArrayList<Nodo> hijos = new ArrayList<>();
         Abiertos.add(Estadoinicial);
         Nodo Actual = Abiertos.peek();
         Visitados++;          
         
         while(!Arrays.deepEquals(Actual.getPuzzle(), Estadofinal.getPuzzle()) && !Abiertos.isEmpty()){
             Visitados++;
             Abiertos.poll();
             Cerrados.add(Actual);
             hijos = GenerarSucesores(Actual,modo);
             hijos = GestionRepetidosA_Estrella(hijos, Cerrados, Abiertos, modo);
             //los hijos se guardan en abierto
             while(!hijos.isEmpty()){
                  int pos = 0, costeMenor = hijos.get(0).Calcularcoste(), indice = 0;
                  //el hijo que tenga menor coste tiene prioridad en la cola (se pone primero)
                  while(pos < hijos.size())
                  {
                      if(costeMenor > hijos.get(pos).Calcularcoste())
                      {
                          costeMenor = hijos.get(pos).Calcularcoste();
                          indice = pos;
                      }
                      pos++;
                  }
                  Abiertos.add(hijos.remove(indice));
              }
              if(!Abiertos.isEmpty())
                    Actual = Abiertos.peek();
         }        
         MaximoAbiertos = Abiertos.size();
         MaximoCerrados = Cerrados.size();
         NombreAlgoritmo = "A*";
         long TiempoTermina = System.currentTimeMillis(); //termina  
         milisegundos = TiempoTermina - TiempoEmpieza;  
         
         if(Abiertos.isEmpty()) 
              System.out.println("\n ** cola abiertos vacia **\n\n");
         else 
         {
           MostrarSolucion(Actual,Actual.getProfundidad(),Visitados,MaximoAbiertos,MaximoCerrados, milisegundos, modo);           
           System.out.println("Solucion. \n");
         }
    }
//******************************* tratar repetidos A* *************************
    public ArrayList<Nodo> GestionRepetidosA_Estrella(ArrayList<Nodo> hijos, ArrayList<Nodo> Cerrados, Queue<Nodo> Abiertos, int modo) 
    {
        ArrayList<Nodo> Aux =new ArrayList<>();
        boolean cerrado, abierto, borrado;
        int i=0;
        while( i < hijos.size())
        {
            //busco en cerrados
            cerrado = abierto = borrado = false;
            for(int j=0; j<Cerrados.size() && !cerrado; j++)
            {
                if(Arrays.deepEquals(Cerrados.get(j).getPuzzle(), hijos.get(i).getPuzzle()))
                {
                    cerrado = true;
                    //si encuentra un coste menos, se vuelve a abrir pero con el coste nuevo
                    if(Cerrados.get(j).Calcularcoste() <= hijos.get(i).Calcularcoste())
                    {
                        borrado = true;
                    }
                    else
                    {
                        //si no, lo elimina de cerrado y pasa a abierto
                        Cerrados.remove(j); 
                    }
                }
            }       
            //busco en abierto
               while(!Abiertos.isEmpty())
               {
                   //guardo el contenido de abierto en una lista (para no perder nada)
                   if(!Abiertos.isEmpty()) Aux.add(Abiertos.poll()); 
               }
               int j = 0; boolean elimina;
               while(j < Aux.size()){
                    elimina = false;
                    if(Arrays.deepEquals(Aux.get(j).getPuzzle(), hijos.get(i).getPuzzle()))
                    {
                        abierto = true; borrado = false; 
                        //Si el coste es menor o igual se sustituye el nodo de abierto con el actual
                        if(Aux.get(0).Calcularcoste() <= hijos.get(i).Calcularcoste())
                        {
                            borrado = true; 
                        }
                        else
                        {
                            Aux.remove(j); //Elimino el Abierto
                            elimina = true;
                        }           
                    }
                    if(!elimina) j++;
                }
                while(!Aux.isEmpty())
                {
                    if(!Aux.isEmpty())
                         Abiertos.add(Aux.remove(0));
                }
            if(borrado)
            {
                if(modo == 2)
                   System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido se borra **");
                hijos.remove(i);
            }
            else
            {
                if((abierto || cerrado) && modo == 2)
                   System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido NO se borra **");
                i++;
            }
        }
        return hijos;
    }
//*********************************** termina busqueda A* ******************
 
//******************************** empieza busqueda en anchura*************************   
    public void BusquedaAnchura(int limite, int modo)
    {
        long TiempoEmpieza = System.currentTimeMillis(); //comienza
        Visitados=0;
        Queue<Nodo> Abiertos = new LinkedList<>();
        ArrayList<Nodo> Cerrados = new ArrayList<>();
        ArrayList<Nodo> hijos =new ArrayList<>();
        Abiertos.add(Estadoinicial);
        Nodo Actual= Abiertos.peek();
        Visitados++;
        
        while(!Abiertos.isEmpty() && !Arrays.deepEquals(Actual.getPuzzle(), Estadofinal.getPuzzle()))
        {
             Visitados++;
             Abiertos.poll();
             Cerrados.add(Actual);
             if(Actual.getProfundidad() <= limite)
             {
                hijos = GenerarSucesores(Actual, modo);
                hijos = GestionRepetidosAnchura(hijos, Cerrados, Abiertos, modo);
                while(!hijos.isEmpty())
                {
                  Abiertos.add(hijos.remove(0));
                }  
             }
             if(!Abiertos.isEmpty())
                   Actual = Abiertos.peek();  
        }
        MaximoAbiertos = Abiertos.size();
        MaximoCerrados = Cerrados.size();
        NombreAlgoritmo = "Anchura";
        long TiempoTermina = System.currentTimeMillis(); //termina  
        milisegundos = TiempoTermina - TiempoEmpieza;
        
        if(Abiertos.isEmpty()) 
              System.out.println("\n ** cola abiertos vacia **\n\n");
        else 
        {
           MostrarSolucion(Actual,Actual.getProfundidad(),Visitados,MaximoAbiertos,MaximoCerrados,milisegundos, modo);
           System.out.println("Solucion. \n");
        }
    } 
//************************ tratar repetidos en anchura **************************  
    public ArrayList<Nodo> GestionRepetidosAnchura(ArrayList<Nodo> hijos, ArrayList<Nodo> Cerrados, Queue<Nodo> Abiertos, int modo) 
    {
        ArrayList<Nodo> Aux =new ArrayList<>();
        boolean Cerrado, Abierto;
        int i = 0;
        while(i < hijos.size())
        {    
            Cerrado = Abierto = false;
            //un repetido en cerrado
            for(int j = 0; j < Cerrados.size() && !Cerrado; j++)
            {
                if(Arrays.deepEquals(Cerrados.get(j).getPuzzle(), hijos.get(i).getPuzzle())){
                    Cerrado = true;
                }
            }
            //un repetido en abierto
            while(!Abiertos.isEmpty())
            {
                    if(Arrays.deepEquals(Abiertos.peek().getPuzzle(), hijos.get(i).getPuzzle())){
                        Abierto = true;
                    }
                    if(!Abiertos.isEmpty())
                            Aux.add(Abiertos.poll());
            }
            while(!Aux.isEmpty())
            {
                    Abiertos.add(Aux.remove(0));
            }
            if(Abierto || Cerrado){
               //se borra el sucesor repetido
               if(modo == 2)
                  System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido se borra **");
               hijos.remove(i);
            }
            else{
                i++;
            }
        }
        return hijos;
    }
//******************************** termina busqueda en anchura *************************

//******************************** empieza greedy **************************************
    
    public void Greedy(int modo)
    {
         long TiempoEmpieza = System.currentTimeMillis(); //empieza
         Visitados = 0;
         Queue<Nodo> Abiertos = new LinkedList<>();
         ArrayList<Nodo> Cerrados = new ArrayList<>();
         ArrayList<Nodo> hijos = new ArrayList<>();
         Abiertos.add(Estadoinicial);
         Nodo Actual= Abiertos.peek();
         Visitados++;
         
         while(!Arrays.deepEquals(Actual.getPuzzle(), Estadofinal.getPuzzle()) && !Abiertos.isEmpty())
         {
             Visitados++;
             Abiertos.poll();
             Cerrados.add(Actual);
             //los hijos los metemos en abiertos
             hijos = GenerarSucesores(Actual, modo);
                          
             while(!hijos.isEmpty())
             {
                 //los hijos con menor heuristica tendran prioridad
                 int pos=0, costeMenor = hijos.get(0).getHeuristica(), indice = 0;    
                 while(pos < hijos.size())
                 {
                     if(costeMenor > hijos.get(pos).getHeuristica())
                     {
                         costeMenor = hijos.get(pos).getHeuristica();
                         indice = pos;
                     }
                     pos++;
                 }
                 Abiertos.add(hijos.remove(indice));
             }
             if(!Abiertos.isEmpty()) Actual = Abiertos.peek();
         }         
         //------------------
        MaximoAbiertos = Abiertos.size();
        MaximoCerrados = Cerrados.size();
        NombreAlgoritmo = "Greedy Best First";
        long TiempoTermina = System.currentTimeMillis(); //termina 
        milisegundos = TiempoTermina - TiempoEmpieza;
        
        if(Abiertos.isEmpty()) 
              System.out.println("\n ** cola abiertos vacia **\n\n");
        else {
           MostrarSolucion(Actual,Actual.getProfundidad(),Visitados,MaximoAbiertos,MaximoCerrados,milisegundos, modo);
           System.out.println("Solucion. \n");
        }
    }
    //******************************** tratar repetidos greedy **************************************
        
     public ArrayList<Nodo> GestionRepetidosGreedy(ArrayList<Nodo> hijos, ArrayList<Nodo> Cerrados, Queue<Nodo> Abiertos, int modo) 
     {
        ArrayList<Nodo> Aux = new ArrayList<>();
        boolean Cerrado, Abierto, borrado;
        int i=0;
        while( i < hijos.size())
        {
            Cerrado = Abierto = borrado = false;
            //miro en abiertos
            for(int j=0; j<Cerrados.size() && !Cerrado; j++)
            {
                if(Arrays.deepEquals(Cerrados.get(j).getPuzzle(), hijos.get(i).getPuzzle()))
                {
                    Cerrado = true;
                    if(Cerrados.get(j).getHeuristica() <= hijos.get(i).getHeuristica())
                    {
                        borrado = true;
                    }
                    else
                    {
                        //sale de cerrado y entra a abierto
                        Cerrados.remove(j); 
                    }
                }
            }
            //miro en cerrados 
               while(!Abiertos.isEmpty())
               {
                   Aux.add(Abiertos.poll()); 
               }
               int j = 0; boolean elimina;
               while(j < Aux.size())
               {
                    elimina = false;
                    if(Arrays.deepEquals(Aux.get(j).getPuzzle(), hijos.get(i).getPuzzle())){
                        Abierto = true; borrado = false; 
                        //comparo costes
                        if(Aux.get(0).getHeuristica() <= hijos.get(i).getHeuristica()){
                            borrado = true; 
                        }
                        else
                        {
                            Aux.remove(j);
                            elimina=true;
                        }
                    }
                    if(!elimina) j++;
                }
                while(!Aux.isEmpty()){
                    Abiertos.add(Aux.remove(0));
                }
            if(borrado)
            {
                if(modo == 2)
                   System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido se borra **");
                hijos.remove(i);
            }
            else 
            {
                if((Abierto || Cerrado) && modo == 2)
                      System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido NO se borra **");
                i++;
            } 
        }
        return hijos;
    }
    //******************************** termina greedy **************************************
          
    //*************************************** empieza busqueda profundidad ***********************************
     public void BusquedaProfundidad(int limite, int modo)
     {
      long TiempoEmpieza = System.currentTimeMillis(); //empieza
      Visitados = 0;
      Stack<Nodo> Abiertos = new Stack<>();
      ArrayList<Nodo> Cerrados = new ArrayList<>();
      ArrayList<Nodo> hijos = new ArrayList<>();
      Abiertos.add(Estadoinicial);
      Nodo Actual= Abiertos.peek();
      Visitados++;
     
      while(!Abiertos.isEmpty() && !Arrays.deepEquals(Actual.getPuzzle(), Estadofinal.getPuzzle()))
      {
             Visitados++;
             Abiertos.pop();
             Cerrados.add(Actual);
             if(Actual.getProfundidad() <= limite)
             {
                hijos = GenerarSucesores(Actual, modo);
                hijos = GestionRepetidosProfundidad(hijos,Cerrados, Abiertos, modo);
                while(!hijos.isEmpty())
                {
                  Abiertos.add(hijos.remove(0));
                }  
             }
             if(!Abiertos.isEmpty())
                    Actual = Abiertos.peek();  
        }
        MaximoAbiertos = Abiertos.size();
        MaximoCerrados = Cerrados.size();
        NombreAlgoritmo = "Profundidad";
        long TiempoTermina = System.currentTimeMillis(); //termina  
        milisegundos = TiempoTermina - TiempoEmpieza;
        if(Abiertos.isEmpty()) 
              System.out.println("\n ** pila abiertos vacia **\n\n");
        else 
        {
           MostrarSolucion(Actual,Actual.getProfundidad(),Visitados,MaximoAbiertos,MaximoCerrados,milisegundos, modo);         
           System.out.println("Solucion. \n");
        }
    }
    //***************************************
    public ArrayList<Nodo> GestionRepetidosProfundidad(ArrayList<Nodo> hijos, ArrayList<Nodo> Cerrados, Stack<Nodo> Abiertos,int modo)
    {
        ArrayList<Nodo> Aux =new ArrayList<>();
        boolean Cerrado, Abierto, borrado;
        int i=0;
        while( i < hijos.size())
        {
            Abierto = Cerrado = borrado = false;
            for(int j=0; j<Cerrados.size() && !Cerrado; j++){
                if(Arrays.deepEquals(Cerrados.get(j).getPuzzle(), hijos.get(i).getPuzzle())){
                    Cerrado = true; 
                    if(Cerrados.get(j).getProfundidad() <= hijos.get(i).getProfundidad()){
                        borrado = true;
                    }
                }
            }
            if(!borrado)
            {
                while(!Abiertos.isEmpty())
                {
                    if(Arrays.deepEquals(Abiertos.peek().getPuzzle(), hijos.get(i).getPuzzle()))
                    {
                       borrado = Abierto = true;
                    }
                    Aux.add(Abiertos.pop());
                }
                while(!Aux.isEmpty()){
                    Abiertos.add(Aux.remove(0));
                }
            }
            if(borrado)
            {
                //Eliminamos el hijo repetido
                if(modo == 2)
                   System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido se borra **");
                hijos.remove(i);
            }
            else
            {
                if((Abierto || Cerrado) && modo == 2)
                    System.out.println("** nodo " + MostrarEstadoLineal(hijos.get(i).getPuzzle()) + " repetido NO se borra**");
                i++;
            }    
        } 
        return hijos;
    }
    //*************************************** termina busqueda profundidad *****************************
    public void MostrarEstado(int [][] estado)
    {
        
        for(int i=0; i<estado.length; i++){
            for(int j=0; j<estado.length; j++){
                System.out.print("[" + estado[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println("---------");
            
    }
    
    public String MostrarEstadoLineal(int [][] estado)
    { 
        
        String cadena = "";
        for(int i=0; i<estado.length; i++){
            for(int j=0; j<estado.length; j++){
                cadena += "[" + estado[i][j] + "]";
            }
        }
        return cadena;  
    }

//generar hijos
    public ArrayList<Nodo> GenerarSucesores(Nodo Actual, int modo) 
    {
                
        ArrayList<Nodo> HijosGenerados = new ArrayList<>();
        int [] PCero = Actual.Localizacero();
        
        if(modo == 2)
            System.out.println("\n\n **** Se expande el nodo: " + MostrarEstadoLineal(Actual.getPuzzle()) + " *****\n");
        
        if(PCero[0]!=0){
            HijosGenerados.add(MoverArriba(PCero,Actual,modo));
        }
        
        if(PCero[0]!=2){
            HijosGenerados.add(MoverAbajo(PCero,Actual,modo));
        }
        
        if(PCero[1]!=0){
            HijosGenerados.add(MoverIzquierda(PCero,Actual,modo));
        }
        
        if(PCero[1]!=2){  
            HijosGenerados.add(MoverDerecha(PCero,Actual,modo));
        }
        System.out.println();
        return HijosGenerados;
        
    }
    
//*************************** movimientos *****************************************
    
    public Nodo MoverArriba(int[] PCero, Nodo Actual, int modo)
    {
        Nodo hijo = new Nodo(Actual.getPuzzle());
        hijo.setPadre(Actual);
        hijo.setProfundidad(1 + Actual.getProfundidad());
        int Arriba = hijo.getPuzzle()[PCero[0]-1][PCero[1]];
        hijo.getPuzzle()[PCero[0]-1][PCero[1]] = 0;
        hijo.getPuzzle()[PCero[0]][PCero[1]] = Arriba;
        CalculoHeuristica(hijo);
        if(modo == 2) System.out.println("MoverArriba -> " + MostrarEstadoLineal(hijo.getPuzzle()));
      
        return hijo;
    }
    
    public Nodo MoverAbajo(int []PCero, Nodo Actual, int modo)
    {
        Nodo hijo = new Nodo(Actual.getPuzzle());
        hijo.setPadre(Actual);
        hijo.setProfundidad(1 + Actual.getProfundidad());
        int Abajo = hijo.getPuzzle()[PCero[0]+1][PCero[1]];
        hijo.getPuzzle()[PCero[0]+1][PCero[1]] = 0;
        hijo.getPuzzle()[PCero[0]][PCero[1]] = Abajo;
        CalculoHeuristica(hijo);
        if(modo == 2) System.out.println("MoverAbajo -> " + MostrarEstadoLineal(hijo.getPuzzle()));
        
        return hijo;
    }
    
    public Nodo MoverIzquierda(int[]PCero, Nodo Actual, int modo)
    {
         Nodo hijo = new Nodo(Actual.getPuzzle());
         hijo.setPadre(Actual);
         hijo.setProfundidad(1 + Actual.getProfundidad());
         int Izquierda = hijo.getPuzzle()[PCero[0]][PCero[1]-1];
         hijo.getPuzzle()[PCero[0]][PCero[1]-1] = 0;
         hijo.getPuzzle()[PCero[0]][PCero[1]] = Izquierda;
         CalculoHeuristica(hijo);       
         if(modo == 2) System.out.println("MoverIzquierda -> " + MostrarEstadoLineal(hijo.getPuzzle()));
         
         return hijo;
    }
    
    public Nodo MoverDerecha(int []PCero, Nodo Actual, int modo)
    {     
         Nodo hijo = new Nodo(Actual.getPuzzle());
         hijo.setPadre(Actual);
         hijo.setProfundidad(1 + Actual.getProfundidad());
         int Derecha = hijo.getPuzzle()[PCero[0]][PCero[1]+1];
         hijo.getPuzzle()[PCero[0]][PCero[1]+1] = 0;
         hijo.getPuzzle()[PCero[0]][PCero[1]] = Derecha;
         CalculoHeuristica(hijo); 
         if(modo == 2) System.out.println("MoverDerecha -> " + MostrarEstadoLineal(hijo.getPuzzle()));
         
         return hijo;
    }
//*******************************************************************************************
//calcular lo cerca que esta el nodo actual con el nodo final
    public void CalculoHeuristica(Nodo Actual)
    {
        int entropia = 0;
        if(!Arrays.deepEquals(Actual.getPuzzle(), Estadofinal.getPuzzle()))
        {
            for(int i=0; i<Actual.getPuzzle().length; i++)
            {
                for(int j=0; j<Actual.getPuzzle().length; j++)
                {
                    if(Actual.getPuzzle()[i][j] != Estadofinal.getPuzzle()[i][j])
                    {
                        entropia++;
                    }
                }
            }
        }
        Actual.setHeuristica(entropia);
    }
    
//*********************************** visualizacion ************************************
    
    //limpia la pantalla
    public void limpiapantalla()
    {     
        for(int i=0; i<25; i++) System.out.println();
    }
    
    public void MostrarSolucion(Nodo Actual, int CamSolucion, int Visitados, int MaxC, int MaxA, long tiempo, int Modo)
    {    
        switch(Modo)
        {       
            case 0:  limpiapantalla();
                     Modo0(Actual, CamSolucion, Visitados, MaxC, MaxA, tiempo, Modo);
                     break;            
            case 1: limpiapantalla();
                    Modo1(Actual, CamSolucion, Visitados, MaxC, MaxA, tiempo, Modo);
                    break;
            case 2: Modo_Dos(Actual, CamSolucion, Visitados, MaxC, MaxA, tiempo, Modo);  
                    break;
            default: System.out.println("Error");
        }
    }
    
//muestra solucion en modo 0
    public void Modo0(Nodo Actual, int CamSolucion, int Visitados, int MaxC, int MaxA,long tiempo, int Modo){
            
        System.out.println("\n***** "+NombreAlgoritmo + "," + MostrarEstadoLineal(Estadoinicial.getPuzzle()) 
                         + "," + CamSolucion + "," + Visitados + "," + MaxC + "," + MaxA + "," + tiempo +" *****\n");
    }
    
//muestra solucion en modo 0 y modo 1
    public void Modo1(Nodo Actual, int CamSolucion, int Visitados, int MaxC, int MaxA,long tiempo, int Modo)
    {
        Modo0(Actual,CamSolucion,Visitados,MaxC,MaxA,tiempo,Modo);
        while(Actual != null)
        {
            MostrarEstado(Actual.getPuzzle());
                  Actual = Actual.getPadre();
        } 
        System.out.println();
        System.out.println(CamSolucion + " Pasos\n" + Visitados + " Visitados\n" + MaxC + " Cerrados\n" + MaxA + " Estados Abiertos\n" + tiempo + " Milisegundos\n\n");        
    }
    
//muestra solucion en modo 0, modo 1 y modo 2
    public void Modo_Dos(Nodo Actual, int CamSolucion, int Visitados, int MaxC, int MaxA,long tiempo, int Modo)
    {    
        Modo1(Actual,CamSolucion,Visitados,MaxC,MaxA,tiempo,Modo); 
    }
    

}
