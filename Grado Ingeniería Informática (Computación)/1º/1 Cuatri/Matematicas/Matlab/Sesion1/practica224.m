%Ejercicio 2.1.2
%Pablo Cordon Hidalgo

%Apartado 2

syms x1

f1 = 1 / (x1+1);
taylor (f1, x1 , 0, 'order' ,5) 
%He tenido que hacerlo de esta manera ya que, al hacerlo 
%Mediante el metodo que dan los apuntes me producía un error.
%He conseguido la solucion en la pag web de matworks.

%Apartado 3

syms x2

f2 = cos (x2);
taylor (f2,x2, pi/2,'order', 5)

