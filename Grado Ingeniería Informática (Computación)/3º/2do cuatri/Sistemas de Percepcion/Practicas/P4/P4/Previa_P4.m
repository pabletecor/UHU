%%PREVIA P4

addpath("Funciones")
addpath("Imagenes")
addpath("Videos")


%Funcion MinEntreMax
I = imread("Matric.tif");
imshow(I);
Id = double(I);
[h,nivelesdegris] =  imhist(I); %OJO uint8 para imhist
vectorPesos = [ 1 2 4 8 4 2 1];
[g_MinEntreMax, gmax1,gmax2] = Funcion_MinEntreMax(Id,vectorPesos);

Ib = I<g_MinEntreMax;

%Funcion suaviza_vector_medias_moviles

hSuav =  funcion_suaviza_vector_medias_moviles(h,vectorPesos);
figure,plot(0:255,hSuav,'b');

%%RIDLER AND CALVARD - ISODATA

clear ,clc, close all
addpath("Funciones")
addpath("Imagenes")
addpath("Videos")

I = imread("Matric.tif");
Id = double(I);
[h,nivelesdegris] =  imhist(I);

%Programacion funcion de interes y comprobacion
gIni = 50; gFin = 125;
[gMean,numPix] = calcula_valor_medio_region_histograma(h,gIni,gFin);

Ib = I>=gIni-1 & I<=gFin-1;
sum(Ib(:))
mean(I(Ib))

%Programacion ISODATA

umbralParada = 0;
T = funcion_isodata(h,umbralParada);


%Programacion OTSU

clear,clc,close all
addpath("Funciones")
addpath("Imagenes")
addpath("Videos")

I = imread("Matric.tif");
imshow(I), figure, imhist(I)

g_otsu = funcion_otsu(I);
255*graythresh(I);   %Funcion otsu implementada en matlab (normalizada)



