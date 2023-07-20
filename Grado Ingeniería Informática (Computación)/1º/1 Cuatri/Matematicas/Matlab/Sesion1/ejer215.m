% Ejercicio 2.1.5
%Pablo Cordón Hidalgo

x = linspace (-2*pi, 2*pi, 100);

f = sin(x);
g = cos(x);
h = tan(x);

plot (x, f,'r', x, g, 'b', x, h, 'g')
