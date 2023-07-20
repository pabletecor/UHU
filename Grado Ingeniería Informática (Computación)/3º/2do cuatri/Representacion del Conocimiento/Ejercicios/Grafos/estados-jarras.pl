

/*
  Problemas de estado
  
  - Ajedrez
  - 9 reinas
  - Jarras
    ...

   Problema de las jarras	
	|     |
	|     |    |   | 
	|     |    |   |
    -------    -----
	  G5         G3
	  
	           |---|T
	|     |    |   |_ 
	|     |    |   | 
	|     |    |   |
    -------    -----
	  G5         G3
   
    Pasar de G5 a G3
    1. T is G5 + G3, T =< 3
       G5 -> 0
       G3 -> T
    2. T > 3
	   G3 -> 3
       G5 -> T - 3	
	  
	  
	           
	|     |    
	|     |    |   | 
	|     |    |   |
    -------    -----
	  G5         G3
	  
	Pasar de G3 a G5
    1. T =< 5
	   G3 -> 0
	   G5 -> T
	5. T > 5
       G5 -> 5
       G3 -> T - 5	   
    	
	  
   1. Definir el estado
   
      estado(G5, G3)
	    representa un instante del problema de las 
		jarras. G5 indica en número de galones
		en la garrafa de 5 galones y G3 indica
		el número de galones en la garrafa de 
		3 galones.
      
	  estado( .... )
	  
      Ajedrez
      estado([[t,c,a,k,q,a,c,t],
	          [p,p,p,..],
			  [x,x,x,x,x,x,x,x ],
              ...			  
			  ])	
    2. Estados inicial y final
       estado inicial = estado(0,0).
       estado final   = estado(4,_).	   
	  
    3. Definir los movimientos
        1. llenar G5
        2. llenar G3
        3. vaciar G5
        4. vaciar G3
        5. pasar de G3 a G5
        6. pasar de G5 a G3 		
		
mov(Nombre, EstadoAntes, EstadoDespués)

*/

 /* 1. llenar G5 */
 mov(llenarG5, estado(_, G3), estado(5, G3)).
 
 /* 2. llenar G3 */
 mov(llenarG3, estado(G5, _), estado(G5, 3)).
 
 
/* 3. vaciar G5 */
mov(vaciarG5, estado(_, G3), estado(0, G3)).
 
/* 4. vaciar G3 */
mov(vaciarG3, estado(G5, _), estado(G5, 0)).
  
/* 5. pasar de G3 a G5 */
mov(deG3aG5, estado(G5, G3), estado(T, 0)):-
  T is G3 + G5, T =< 5.
mov(deG3aG5, estado(G5, G3), estado(5, G32)):-
  T is G3 + G5, T > 5,
  G32 is T - 5.

/* 6. pasar de G5 a G3 */
mov(deG5aG3, estado(G5, G3), estado(0, T)):-
  T is G5 + G3,
  T =< 3.
  
mov(deG5aG3, estado(G5, G3), estado(G52, 3)):-
  T is G5 + G3,
  T > 3,
  G52 is T - 3.
  
/* camino(+EstadoInicial, +EstadoFinal, +Visitados, -R).
   es cierto si R unifica con una lista de movimientos
   para pasar del EstadoInicial al EstadoFinal sin
   repetir los estados de la lista de estados
   Visitados.
   
   Inicial -> Mov -> EstadoTMP              Final
                        --------------------->
						         N-1 
*/

camino(Ini, Ini, _, []).

camino(Ini, Fin, Visitados, [Mov|Camino]):-
  mov(Mov, Ini, Tmp),
  \+ member(Tmp, Visitados),
  camino(Tmp, Fin, [Tmp|Visitados], Camino).
  

  