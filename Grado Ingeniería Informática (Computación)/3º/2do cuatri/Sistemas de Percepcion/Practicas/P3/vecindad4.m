%Vemos todos los vecinos de la posicion(i,j)
function [etiqueta, Mb]= vecindad4(Mb, etiqueta, i,j, n)

%MEtiqueta=zeros(f-2,c-2); %Al ser recursiva smpr estaria rellenando de ceros
%Tipo 4
    if Mb(i,j)==1%Pues esta desplazada 1 fila y 1 colummna
        %Modificamos los valores
        etiqueta(i,j)=n;
        Mb(i,j)=0;
        %Vemos sus vecinos
        [etiqueta Mb]=vecindad4(Mb,etiqueta, i-1, j, n);%Arriba
        [etiqueta Mb]=vecindad4(Mb,etiqueta, i+1, j, n);%Abajo
        [etiqueta Mb]=vecindad4(Mb,etiqueta, i, j-1, n);%Izquierda
        [etiqueta Mb]=vecindad4(Mb,etiqueta, i, j+1, n);%Derecha
    end
end


