/* suma(?N1, ?N2, ?R)
cierto si R unifica con la suma de N1 y N2 en aritmética de Peano
*/

suma(0,N2,N2).

suma(s(N1), N2,s(R)):- suma(N1,N2,R).


/*p2d(P,D)
convierte peano a decimal*/
p2d(0,0).
p2d(s(N),R2):- p2d(N,R), R2 is R+1.


/* resta(?N1,?N2,?R).
es cierto si R unifica con la resta de N1 menos N2 en aritmética de Peano.
*/

resta(N1,0,N1).
resta(N1,s(N2),R):-resta(N1,N2,s(R)).

/*mult(?N1, ?N2, ?R)
es cierto si R unifica con la suma múltiple de N1, N2 veces.
*/
mult(_,0,0).
mult(N1,s(N2),R2) :- mult(N1,N2,R),suma(N1,R,R2).