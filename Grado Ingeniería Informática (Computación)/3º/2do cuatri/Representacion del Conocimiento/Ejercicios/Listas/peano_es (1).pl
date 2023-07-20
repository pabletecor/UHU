
/* suma(?N1, ?N2, ?R)
   cierto si R unifica con la suma
   de N1 y N2 en aritmética de Peano

   1. P(n0)
   2. P(n-1) -> P(n), Para n > n0
      P(N):- N2 is N-1, P(N2).
*/

suma(0, N2, N2).
suma(s(N1), N2, s(R)):- suma(N1, N2, R).

/*
p2d(+P, -D)
es cierto si D unifica con el decimal
equivalente a número P en aritmética de Peano.

*/

p2d(0,0).
p2d(s(N), R2):-  p2d(N, R), R2 is R+1.


/* resta(?N1, ?N2, ?R).
  es cierto si R unifica con la resta de N1 menos
  N2 en aritmética de Peano.
*/

resta(N1, 0, N1).
resta(N1, s(N2), R):-  resta(N1, N2, s(R)).


/* mult(?N1, ?N2, ?R)
 es cierto si R unifica con la suma múltiple
 de N1, N2 veces.
 4 * 2 = 4 + 4 = 8 = 2 + 2 + 2 + 2 
*/

mult(_, 0, 0).
mult(N1, s(N2), R2):- mult(N1, N2, R), 
  suma(N1, R, R2).
  
/* peano_div(?N1, ?N2, ?R, ?Resto)
   es cierto si R unifica con el número de
   veces que puede restarse N2 a N1. 
   Resto unificará con el resto de la división
   entera.   
  
   peano_div(s(s(s(0))), s(s(0)), R, Resto).
   
   R=s(0)
   Resto=s(0)  
   
   peano_div(s(s(s(s(0)))), s(s(0)), R, Resto).
   R=s(s(0))
   Resto=0
   
*/

peano_div(N1, N2, 0, N1):- menor(N1, N2).
peano_div(N1, N2, s(R), Resto):-
  resta(N1, N2, N12),
  peano_div(N12, N2 , R, Resto).
  
/*
Ejemplos:
2 5 -> R=0, Resto=2  
  
5 2 -> R=2, Resto=1

3 2 -> R=1, Resto=1
*/

/* menor(?N1, ?N2)
   es cierto si N1 es menor que N2 en
   aritmética de Peano.
*/

menor(0, s(_)).

menor(s(N1), s(N2)):-
  menor(N1, N2).
  

/* Ejemplo:
menor(s(s(0)), s(s(s(s(0)))) ).

menor(s(0), s(s(s(0)))).

menor(0, s(s(0)) ).

*/