%Pablo Cordón Hidalgo
%Ejercicio 3.3.4
%Apartado 1

syms x 
f = (1-x^2)/(1+x^2);

diff (f,x)
pretty (ans)

%Apartado 2

g = log ( 1 + x^2);
diff (g,3)
pretty (ans)