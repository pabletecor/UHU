%Ejercicio 2.1.2
%Pablo Cordon Hidalgo

%Determino el rango de x

x = linspace (-10,10,200);

f = x.^2;
g = exp (x.^2);
h = sin (x.^2);
t = log (1 + x.^2) / log (1); %Esta función no se representa correctamente

subplot (2,2,1); plot (x,f);
subplot (2,2,2); plot (x,g);
subplot (2,2,3); plot (x,h);
subplot (2,2,4); plot (x,t);