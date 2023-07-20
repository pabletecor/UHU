function [Etiqueta, N]=Funcion_Etiquetar_v3(Binaria,vecindad)

    %Soluciones
    [f,c]=size(Binaria);
    Etiqueta=zeros(f+2,c+2); %Matriz ampliada de etiquetas
    MAmpliada=zeros(f+2,c+2);%Matriz Ampliada con Ib
    MAmpliada(2:f+1,2:c+1)=Binaria;%1fila y 1columna a cada extremo
    N=0;
    

    for i=2:f+1     %Filas
        for j=2:c+1 %Columnas
            
            if MAmpliada(i,j)==1
                
                N=N+1;%Incrementamos etiqueta
        
                if vecindad ==4
                
                %vecindad tipo 4
                [Etiqueta, MAmpliada]=vecindad4(MAmpliada, Etiqueta, i,j, N);
                
                else
                
                %Vecindad tipo 8
                [Etiqueta, MAmpliada]=vecindad8(MAmpliada, Etiqueta, i,j, N);
                
                end
            end
        end
    end
    
    %Eliminamos las filas y columnas extras
    Etiqueta(f+2,:)=[];%Ultima fila
    Etiqueta(1,:)=[];%Primera fila
    Etiqueta(:,c+2)=[];%Ultima columna
    Etiqueta(:,1)=[];%Primera columna
    %Mostrar los cambios
    %figure,
    %subplot(2,1,1), imshow(Binaria), title('Inicio')
    %subplot(2,1,2),imshow(uint8(255*Etiqueta)), title('Final')
end


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


%Vemos todos los vecinos de la posicion(i,j)
function [etiqueta, Mb]= vecindad8(Mb, etiqueta, i,j, n)
%Tipo 8
    if Mb(i,j)==1%Pues esta desplazada 1 fila y 1 colummna
        %Modificamos los valores
        etiqueta(i,j)=n;
        Mb(i,j)=0;
        %Vemos sus vecinos
        [etiqueta, Mb]=vecindad8(Mb,etiqueta, i-1, j-1, n);%Diagonal Arriba Izq
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