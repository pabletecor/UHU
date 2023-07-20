function [Matriz_Etiquetada, N] = Funcion_Etiquetar (Matriz_Binaria) 

V=unique(Matriz_Binaria);
N=length(V);

%Ponemos a 0 el fondo
I=double(zeros(size(Matriz_Binaria,1),size(Matriz_Binaria,2)));
valor=0;
%Inicializamos los puntos de la matriz binaria que valen 255 con diferentes
%valores
for i=1 : size(Matriz_Binaria,1)
    for j=1 : size(Matriz_Binaria,2)
        if(Matriz_Binaria(i,j)==255)
            valor=valor+1;
            I(i,j)=valor;
        end
    end
end

%Arriba-abajo

%Ponemos un marco de ceros
IX=zeros(size(I,1)+2,size(I,2)+2);
IX(2:end-1,2:end-1)=I;

cambio=true;
    
while(cambio==true)
cambio=false;
    for i=2 : size(IX,1)-1
        for j=2 : size(IX,2)-1

            if(IX(i,j)~=0) 
                %Vecindad 4
                vec = [IX(i,j) IX(i-1,j) IX(i+1,j) IX(i,j-1) IX(i,j+1)];

                M=find(vec > 0);
                m=min(vec(M));
                if(IX(i,j)~=m) 
                cambio=true;
                IX(i,j)=m;
                end

            end
        end
    end

    %Abajo-Arriba

    for i= (size(IX,1)-1) : -1 : 2
        for j= (size(IX,2)-1) : -1 : 2

            if(IX(i,j)~=0)
                %Vecindad 4
                vec = [IX(i,j) IX(i-1,j) IX(i+1,j) IX(i,j-1) IX(i,j+1)];

                M=find(vec >0);
                m=min(vec(M));
                if(IX(i,j)~=m) 
                cambio=true;
                IX(i,j)=m;
                end
            end
        end
    end
end

unicos = unique(IX);

for i=1 : length(unicos)
    %Para los valores de IX unicos, le damos valor del 0(fondo) al unicos-1
    IX(IX == unicos(i)) = i-1;
    
end

Matriz_Etiquetada=IX;
N=length(unique(IX))-1; %No consideramos el fondo como un objeto
 
            
end

% function n = vecinos(i,j,I)
% 
% n=[I(i,j) I(i-1,j) I(i+1,j) I(i,j-1) I(i,j+1) ];
%          
% 
% end