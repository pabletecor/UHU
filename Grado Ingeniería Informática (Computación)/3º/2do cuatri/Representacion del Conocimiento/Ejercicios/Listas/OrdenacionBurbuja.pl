%Ordenación por burbuja

/*
burbuja(Lista,R)

Es cierto si R unifica con una lista que contiene los elementos de lista ordenados 
de menor a mayor utilizando el metodo de burbuja

En este caso, contruimos la induccion no por el tamaño de la lista, sino ordenando
en cada paso la lista un poco mas.
*/

%La lista está ordenada
burbuja(Lista,Lista) :- ordenada(Lista).

%El par esta desordenado
burbuja(Lista, R2 ) :-
	append(Lista1,[E1,E2|Lista2],Lista),
	E1 > E2, 
	append(Lista1,[E2,E1|Lista2],R),
	burbuja(R,R2).
	

/*
ordenada(+Lista)
cierto si los elemetnos de Lista estan ordenados de menor a mayor
*/


ordenada([]). %Lista vacia esta ordenada

ordenada([_]). %Lista de un elemento esta ordenada

ordenada([E1,E2| Resto]) :- %Lista de más de un elemento
	E1 =< E2, ordenada([E2|Resto]).
