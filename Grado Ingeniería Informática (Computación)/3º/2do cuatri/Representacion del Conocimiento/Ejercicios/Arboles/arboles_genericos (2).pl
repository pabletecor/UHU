
/*

  Árboles genéricos
  
      1    arbol1
	/ | \ 
   3  4  5
 
   Árbol más pequeño (n0): a(E, [])
   
   a(E, ListaHijos)
  
   ListaHijo es una lista de árboles

             1        arbol2
		   / | \ 
          2  3  4
		 /|\    |
        5 6 7   8
*/
arbol1(a(1, [a(3,[]), a(4, []), a(5, [])])).
arbol2(
   a(1,[a(2,[a(5,[]),a(6,[]),a(7,[])]),a(3,[]),a(4,[a(8,[])])])
).

/*
pertenece(E, Arbol)
 es cierto si el elemento E pertenece al 
 árbol genérico Arbol.
*/

/* predicado para árboles */

pertenece(E, a(E, _)).
pertenece(E, a(Et, ListaHijos)):-
  E \= Et, 
  pertenece(E, ListaHijos).

/* predicado para lista de árboles */

pertenece(E, [Cab|_]):-
  pertenece(E, Cab).
pertenece(E, [_|Resto]):-
  pertenece(E, Resto).  
  
  
/* cuenta_nodos(+Arbol, -N)
   es cierto si N unifica con el número de
   nodos del árbol genérico Arbol
*/

/*  predicado para árboles */

cuentaNodosArbol(a(_,Lista),N2) :- cuentaNodosListaArboles(Lista,N),N2 is N+1.

/* predicado para lista de árboles */

cuentaNodosListaArboles([],0).

cuentaNodosListaArboles([Cabeza|Resto], N3 ) :- 
   cuentaNodosArbol(Cabeza,N) , cuentaNodosListaArboles(Resto,N2), N3 is N + N2.


/* hojas(+)*/


