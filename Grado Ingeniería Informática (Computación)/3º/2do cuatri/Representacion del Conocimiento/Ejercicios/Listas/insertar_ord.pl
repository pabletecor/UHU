
/* ord_inser(Lista, R)
   es cierto si R unifica con una lista
   que contiene los elementos de Lista
   ordenados de menor a mayor.
   
   [8,4,6,2,7,1]
   [8]
   
   [8,4,6,2,7,1]
   [4,8]
   
   [8,4,6,2,7,1]
   [4,6,8]
   
   [8,4,6,2,7,1]
   [2,4,6,8]
   ...
   
*/

ord_inser([], []).

ord_inser([Cab|Resto], R2):-
  ord_inser(Resto, R),
  inser_lista_ord(Cab, R, R2).
  
  
/* insertar_lista_ord(E, Lista, R)
  Es cierto si R unifica con una lista
  que contiene los elementos de Lista
  (lista ordenada) con E insertado en
  su posición correcta.
 
*/