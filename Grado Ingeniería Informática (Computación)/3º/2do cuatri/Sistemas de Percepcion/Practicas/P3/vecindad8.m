%Vemos todos los vecinos de la posicion(i,j)
function [etiqueta, Mb]= vecindad8(Mb, etiqueta, i,j, n)
%Tipo 8
    if Mb(i,j)==1%Pues esta desplazada 1 fila y 1 colummna
        %Modificamos los valores
        etiqueta(i,j)=n;
        Mb(i,j)=0;
        %Vemos sus vecinos
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i-1, j-1, n);%Diagonal Arriba Izq
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i, j-1, n);%Izquierda
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i+1, j-1, n);%Diagonal Abajo Izq
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i-1, j, n);%Arriba
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i+1, j, n);%Abajo
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i-1, j+1, n);%Diagonal Arriba Der
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i, j+1, n);%Derecha
        [etiqueta Mb]=vecindad8(Mb,etiqueta, i+1, j+1, n);%Diagonal Abajo Der
    end
end

%Intendo de hacer bucle....
%        a=i+2;
%        b=j+2;
%        %Queremos recorrer un 3x3 de su posicion, por lo tanto nos posicionamos
%        %en la diogonal derecha abajo fuera del array(3x3) que buscamos
%        for k=1:3
%            [etiqueta Mb]=vecindad8(Mb,etiqueta, a-k, b-k, n);%Arriba
%        end