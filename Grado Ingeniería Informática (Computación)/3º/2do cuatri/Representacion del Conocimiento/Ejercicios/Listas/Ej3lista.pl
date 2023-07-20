% comprime(+Lista, -Resultado)
% es cierto cuando Resultado unifica con una lista
% en el siguiente formato:
%
% comprime([1,1,1,2,2,3,4,4,4], R).
% R= [(1,3), (2,2), (3,1), (4,3)]
%
%
% Tupla
% ('Jose', 21, '1,60') 
%

comprime([], []).
comprime([E], [(E,1)]).
comprime([H, H|Tail], [(E,N2)|R]):- comprime([H|Tail], [(E,N)|R]), N2 is N+1.
comprime([H1, H2|Tail], [(H1,1)|R]):- H1 \= H2, comprime([H2|Tail], R).

% [1,1,1,2,2,3,4,4] -> [(1,3), (2,2), (3,1), (4,2)]
% [1,1,2,2,3,4,4] -> [(1,2), (2,2), (3,1), (4,2)]




/*

comprime(Lista, R).

comprime([1,1,1,2,2,3,3,3],R).
R = [(1,3), (2,2), (3,3)]


*/

comprime([],[]).

comprime( [Cabeza|Resto], R  ) :- comprime([])


