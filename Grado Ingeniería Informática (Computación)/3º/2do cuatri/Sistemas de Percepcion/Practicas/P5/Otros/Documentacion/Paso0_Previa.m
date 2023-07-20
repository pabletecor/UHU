%RECONOCIMIENTO DE OBJETOS POR COMPACTICIDAD (P^2/A)

addpath("ImagenesPractica5/Entrenamiento")

nombres{1} = 'Circulo01.JPG';
nombres{2} = 'Cuadrado01.JPG';
nombres{3} = 'Triangulo01.JPG';

I =imread(nombres{1});


Ib = I < 255*graythresh(I); %Calculamos otsu

[Ietiq,N] = bwlabel(Ib);

stats = regionprops(Ietiq,'Area','Perimeter','Eccentricity');
areas = cat(1,stats.Area);
perimetros = cat(1,stats.Perimeter);
c = (perimetros.^2)./areas;


valorMedio = mean(c);

exc = cat(1,stats.Eccentricity);


%%CALCULO DE COMPACTICIDAD/EXCENTRICIDAD DE TODOS LOS OBJETOS
nombres{1} = 'Circulo01.JPG';
nombres{2} = 'Cuadrado01.JPG';
nombres{3} = 'Triangulo01.JPG';
numClases = 3; %SOLO TENEOMS UNA IMAGEN POR CLASE - NO ES LO NORMAL
X=[];
y=[];

for i=1:numClases
    
    I = imread(nombres{i});
    Ib = I < 255*graythresh(I);
    
    [Ietiq,N] = bwlabel(Ib);
    

    stats = regionprops(Ietiq,'Area','Perimeter','Eccentricity');
    areas = cat(1,stats.Area);
    perimetros = cat(1,stats.Perimeter);
    comp = (perimetros.^2)./areas;
    ecc = cat(1,stats.Eccentricity);
    
    datos = [comp ecc];
    X = [X;datos];
    Y = [Y; i*ones(N,1)];


end

%%REPRESENTACION DE DATOS EN ESPACIO DE CCAS: COMPACTICIDAD-EXCETRICIDAD

%Partimos de X e Y

%Respecto a las clases
simbolos = {'*r','*b','*k'};
nombres{1} = 'Circulo01.JPG';
nombres{2} = 'Cuadrado01.JPG';
nombres{3} = 'Triangulo01.JPG';
codifClases = unique(Y);
numClases = length(codifClases);

%Respecto a los descriptores
nombreDescriptores = {'compact','excentr','d3','d4','d5','d6'};
espacioCcas = [1 2];    %Podemo elegir varios descriptores si hubiera mas de 2
nombreDescriptores{espacioCcas(1)} = 'compacticidad';
nombreDescriptores{espacioCcas(2)} = 'excentricidad';

figure, hold on
for i =1:numClases

    datosClase = X(Y==codifClases(i),espacioCcas);
    plot(datosClase(:,1),datosClase(:,2),simbolos{i})
    
end

legend(nombreclases)
xlabel(nombreDescriptores{espacioCcas(1)})
xlabel(nombreDescriptores{espacioCcas(2)})


%%EJERCICIO CALCULO HU
%Los 7 valores HU son 

clear, clc, close all

addpath("Funciones");
addpath("ImagenesEjemploLetrasXY");

%Calcular momentos de HU de la primera letra X
I = imread("X.jpg");
Ib = I<255*graythresh(I);
[Ietiq ,N]= bwlabel(Ib);
Ib1 = Ietiq ==1;

hu = Funcion_Calcula_Hu(Ib1);

%Calcular en X los momentos de HU de todas las letras X 
I = imread("X.jpg");
Ib = I<255*graythresh(I);
[Ietiq ,N]= bwlabel(Ib);

X = [];

for i=1:N
    
    Ibi = Ietiq==i;
    hu = funcion_Calcula_Hu(Ibi);
    
    X = [X ; hu'];
    
end

%Calcular en X los momentos de HU de todas las letras X y letras Y
nombreClases = [];
nombreClases{1} = 'X';
nombreClases{2} = 'Y';
extension = '.jpg';

X=[];
numClases = length(nombreClases);

for i =1:length(nombreClases)
    
   nombreImagen = [nombreClases{i} extension];
   I = imread(nombreImagen);
   umbral = graythresh(I); %Obtiene umbral en rango 0-1
   Ibin = I < 255 *umbral;
   % figure, imshow(Ibin)
   [Ietiq,N] = bwlabel(Ibin);
   
   for j =1:N
       
       Iobjeto=Ietiq==j;
       m = Funcion_Calcula_Hu(Iobjeto);
       X = [X;m'];
   end
   
end

%Calcular en Y matriz de codificacion de salida de los datos de X

nombreClases = [];
nombreClases{1} = 'X';
nombreClases{2} = 'Y';
extension = '.jpg';

X=[];
Y=[];
numClases = length(nombreClases);
codifClases = 1:numClases;

for i =1:length(nombreClases)
    
   nombreImagen = [nombreClases{i} extension];
   I = imread(nombreImagen);
   umbral = graythresh(I); %Obtiene umbral en rango 0-1
   Ibin = I < 255 *umbral;
   % figure, imshow(Ibin)
   [Ietiq,N] = bwlabel(Ibin);
   
   for j =1:N
       
       Iobjeto=Ietiq==j;
       m = Funcion_Calcula_Hu(Iobjeto);
       X = [X;m'];
   end
   
   Y = [Y; codifClases(i)*ones(N,1)];
   
end

%Guardar informacion en directorio DatosGenerados

save('./DatosGenerados/conjunto_datos','X','Y')

%Representar en el espacio por dos-tres descriptores las muestras de letras
%X y letras Y
%espacioCcas = espacio de caracteristicas (Qué descriptores quiero usar)

espacioCcas = [1 3];
espacioCcas = [1 5 7];

nombreDescriptores = {'Hu1','Hu2','Hu3','Hu4','Hu5','Hu6','Hu7'};
nombreClases{1} = 'Letras X';
nombreClases{2} = 'Letras Y';
simbolosClases{1} = '*r';
simbolosClases{2} = '*b';

%funcion_representa_datos_sinEstructura(X,Y,espacioCcas,nombreDescriptores,nombreClases,simbolosClases)

nombresProblema = [];
nombresProblema.descriptores = nombreDescriptores;
nombresProblema.clases = nombreClases;
nombresProblema.simbolos = simbolosClases;

funcion_representa_datos(X,Y,espacioCcas,nombresProblema)


% CALCULAR CONJUNTO DE DATOS X-Y INCORPORANDO A LOS 7 MOMENTOS DE HU
% EL DESCRIPTOR EXTENT DE REGIONPROPS


nombres{1} = 'Circulo01.JPG';
nombres{2} = 'Cuadrado01.JPG';
nombres{3} = 'Triangulo01.JPG';

I =imread(nombres{1});


Ib = I < 255*graythresh(I); %Calculamos otsu

[Ietiq,N] = bwlabel(Ib);

stats = regionprops(Ietiq,'Extent');
ext = cat(1,stats.Extent);

X = [X;ext];

%%DEFINICION DE EXTENT

%Boundin Box: rectángulo más pequeño que contiene la región.
%Extension: relación de píxeles del objeto en su bounding-box

%Extent = Area/BoundingBoxArea

%%Calculo manual de BBox
IbinObjeto = Ietiq==1;
[f,c] = find(IbinObjeto);
fmin=min(f); fmax=max(f);
cmin=min(c); cmax=max(c);

BBoxObjeto = IbinObjeto(fmin:fmax,cmin:cmax);

%Representamos con line el BBox sobre la imagen binaria original

figure,imshow(IbinObjeto), hold on
line([cmin,cmin],[fmin,fmax],'Color','r')
line([cmax,cmax],[fmin,fmax],'Color','r')
line([cmin,cmax],[fmin,fmin],'Color','r')
line([cmin,cmax],[fmax,fmax],'Color','r')

%Calculo con RegionProps 
stats = regionprops(IbinObjeto,'BoundingBox');
bb = cat(1,stats.BoundingBox);
%columna-fila:Esquina superior izquierda
%ancho-alto

fminM=bb(2);
cminM=bb(1);
fmaxM=fminM+bb(4);
cmaxM=cminM+bb(3);

%Calculo manual del descriptor
numPixBB = (cmax-cmin)*(fmax-fmin);
numPixObjt = sum(IbinObjeto(:));
extentObjeto = numPixObjt/numPixBB;

%%DEFINICION DE SOLIDITY

%Convex-Hull: Polígono convexo más pequeño que puede contener el objeto
%(Apartado 4.2.2 tema 4 Descripción de objetos - Formas de obtenerlo)

%Solidez: proporción de los píxeles del objeto en su convex-hull

%Solidity: Area/ConvexArea

