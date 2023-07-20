%%PRACTICA 2

% 3. Genera una imagen en color, donde se visualice con un color diferente los objetos
% presentes en la imagen (al haber 6 objetos, pueden utilizarse los colores primarios secundarios).
% Para ello, se debe implementar la siguiente función (ver documentación adjunta):
% [Matriz_Etiquetada N] = Funcion_Etiquetar (Matriz_Binaria)

%Probamos el funcionamiento de la funcion etiquetar recursiva.


Ib = imread('ImagenBinaria.tif');

Prueba = bwlabel(Ib);
imtool(Prueba)

%Probamos la funcion recursiva ((i,j) = 255 )
[Ietiq_rec, Nr] = funcion_etiquetar_Recursiva(Ib); %matriz de tamaño el numero de objetos +1
%para etiquetar las x tenemos en cuenta la vecindad.

%Mostramos el valor de N (nº de objetos de la imagen)
Nr

imtool(Ietiq_rec);


%Probamos la segunda version de la funcion recursiva
Ib = Ib==255; 
imtool(Ib);


[Ietiq_rec, Nr] = Funcion_Etiquetar_v3(Ib,8); %matriz de tamaño el numero de objetos +1
%para etiquetar las x tenemos en cuenta la vecindad.

%Mostramos el valor de N (nº de objetos de la imagen)
Nr

imtool(Ietiq_rec);


%probamos la funcion iterativa.
[Ietiq, N] = Funcion_Etiquetar(Ib); %matriz de tamaño el numero de objetos +1

N

imtool(Ietiq);

%Probamos la funcion v2 (exploracion 1 filas 2 columnas)
[Ietiq, N] = Funcion_Etiquetar_v2(Ib,8,1); %matriz de tamaño el numero de objetos +1

N

imtool(Ietiq);

 
% 4. Genera una imagen donde se localicen, a través de su centroide, los objetos de mayor
% y menor área (ver documentación para la definición de área y centroide).
% Para ello, se deben implementar las siguientes funciones:
% Areas = Calcula_Areas (Matriz_Etiquetada)

Areas = Calcula_Areas (Ietiq);

% Centroides = Calcula_Centroides (Matriz_Etiquetada)

Centroides = Calcula_Centroides (Ietiq);

% 5. Genera una imagen binaria donde sólo se visualicen los dos objetos de área mayor
%Matriz_Binaria_Filtrada = Filtra_Objetos (Matriz_Binaria , NumPix)

%Calculamos el segundo mayor area para filtrar

nObjeto = 2;
NumPix=0;
CopiAreas = Areas;
for i =1 : nObjeto
    
    NumPix = max(CopiAreas);
    CopiAreas(CopiAreas==NumPix) = [];
    
end
 

Matriz_Binaria_Filtrada = Filtra_Objetos (Ietiq , NumPix);


%Probando
X = [18 3 4 11 0]
sincero = find(X>0)
sol = min(X(sincero))

Y = magic(5)
