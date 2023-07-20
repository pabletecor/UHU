%Numero de pixeles que componen el circulo negro

circulo = imread('Circulo1.JPG');

figure, imshow(circulo);

imhist(circulo);

ROI = circulo <120;

npix = sum( ROI(:) )

%numero de pixeles que componen la primera X

x =  imread('X.jpg');
Ib = imread('ImagenBinaria.tif');
ROI = x <120;


Ietiq = funcion_etiquetar(Ib); %matriz de tamaño el numero de objetos +1
%para etiquetar las x tenemos en cuenta la vecindad
imshow(Ib);
figure, imshow(Ietiq);