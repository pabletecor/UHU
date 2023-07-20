%Pablo Cordón Hidalgo
%Apartado 1

syms x1

f= x1/exp(x1^2);
limit (f,x1,inf)

syms x2

g = log ((1+x2) - sin (x2)) / (x2-sin(x2));
limit (g,x2,0)

syms x3

h = sin(x3^2 - 1)/ABS(x3-1);
limit (h,x3,1,'right')