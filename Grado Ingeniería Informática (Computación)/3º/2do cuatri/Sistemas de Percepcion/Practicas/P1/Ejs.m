Imagen1 = imread('p1_1.jpg');
imfinfo(Imagen1)

%%7

Im = uint8(255*rand(2,4))

max (Im)

%Elige el maximo de las filas 
max(Im,[],2)

max(max(max(I)));

%%8 (Imagen negativo)
imshow(Imagen1)
Imagen2 = 255-Imagen1;
figure, imshow(Imagen2)

%Otra manera de hacerlo

%Imagen21 = uint8([]); Tambien puedo declarar asi la imagen21
Imagen21 = uint8(zeros(size(Imagen1)));  %Declaro imagen21 en memoria (aunque sea rellenando con ceros)


Imagen21R=Imagen1(:,:,1);
Imagen21G=Imagen1(:,:,2);
Imagen21B=Imagen1(:,:,3);

Imagen21(:,:,1) = 255 - Imagen21R;
Imagen21(:,:,2) = 255 - Imagen21G;
Imagen21(:,:,3) = 255 - Imagen21B;

%Imagen21 = cat(3,Imagen21R,Imagen21G,Imagen21B) -> Concatenar

imshow(Imagen21);

%%9

Imagen3 = Imagen1(:,:,1);
%Para guardar usamos imwrite


%%10

Imagen4 = imadjust(Imagen3,[],[],0.5);
Imagen5 = imadjust(Imagen3,[],[],1.5);
imshow(Imagen3), figure, imshow(Imagen4),figure, imshow(Imagen5)
figure,imhist(Imagen3),figure,imhist(Imagen4),figure,imhist(Imagen5)

close all

subplot(2,3,1) ,imshow(Imagen3),
subplot(2,3,2) ,imshow(Imagen4),
subplot(2,3,3) ,imshow(Imagen5)

subplot(2,3,4) ,imhist(Imagen3)
subplot(2,3,5) ,imhist(Imagen4)
subplot(2,3,6) ,imhist(Imagen5)

%%11

Imagen6 = imabsdiff(Imagen4,Imagen5);


subplot(1,3,1) ,imshow(Imagen4)
subplot(1,3,2) ,imshow(Imagen5)
subplot(1,3,3) ,imshow(Imagen6)

%%EJERCICIOS CASA
%Funcion compara_matrices(A,B)
%devuelve un display que dice si las matrices son iguales o distintas.
%Resta las dos matrices y si da todo cero son iguales.


%Funcion_imabsdif(A,B)
%Devuelve una matriz que hace la diferencia de A y B, su valor abs y la
%devuelve.

Imagen62 = funcion_imabsdiff(Imagen4,Imagen5);

funcion_compara_matrices(Imagen6,Imagen62);


%

Ic = imread('p1_1.jpg');

I = Ic(:,:,2);

imhist(I)

%x =107 y =1056

h = imhist(I);
h(107)
h(108) %%valor de x+1 

figure,stem(0:255,h,'.r')
figure,imhist(I),hold on,plot(0:255,h,'.r')

hNv1 = funcion_imhist_v1(I);
hNv2 = funcion_imhist_v2(I);

funcion_compara_matrices(h,hNv1);
