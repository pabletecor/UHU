%% PROGRAMACIÓN GENERACIÓN CONJUNTO DE DATOS X-Y

%% LECTURA AUTOMATIZADA DE LAS IMÁGENES DE ENTRENAMIENTO DISPONIBLES

clear all,clc

% LECTURA AUTOMATIZADA DE IMAGENES
addpath('../Imagenes/Entrenamiento')
addpath('../Funciones')

nombres{1}='Circulo';
nombres{2} = 'Cuadrado';
nombres{3} = 'Triangulo';

numClases = 3;
numImagenesPorClase = 2;

for i=1:numClases
    for j=1:numImagenesPorClase
        
        nombreImagen = [nombres{i} num2str(j,'%02d') '.jpg'];
        I = imread(nombreImagen);
        
    end
end


%% --------------------------------
%% 1.- GENERACIÓN CONJUNTO DE DATOS X-Y
%% --------------------------------

X = []; 
Y = [];

%% PARA CADA IMAGEN:


for i=1:numClases
    for j=1:numImagenesPorClase
        
        %nombreImagen = [nombres{i} num2str(j,'%02d') '.jpg'];
        I = imread(nombreImagen);
        

%% 1.1- BINARIZAR CON METODOLOGÍA DE SELECCIÓN AUTOMÁTICA DE UMBRAL (OTSU)

% Genera: Ibin
        
   
    Ibin = I < 255*graythresh(I);

        if sum(Ibin(:)) > 0
            
%% 1.2.- ELIMINAR POSIBLES COMPONENTES CONECTADAS RUIDOSAS: 

% COMPONENTE RUIDOSA:
% COMPONENTES DE MENOS DEL 0.1% DEL NÚMERO TOTAL DE PÍXELES DE LA IMAGEN
% O NÚMERO DE PÍXELES MENOR AL AREA DEL OBJETO MAYOR /5
% SE DEBE CUMPLIR CUALQUIERA DE LAS DOS CONDICIONES

% Genera IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);

    IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);

%% 1.3.- ETIQUETAR.

% Genera matriz etiquetada Ietiq y número N de agrupaciones conexas 
    [Ietiq , N] = bwlabel(IbinFilt);
    

%% 1.4.- CALCULAR TODOS LOS DESCRIPORES DE CADA AGRUPACIÓN CONEXA

% Genera Ximagen - matriz de N filas y 23 columnas (los 23 descriptores
% generados en el orden indicado en la práctica)

    XImagen = descriptores_ej1(Ietiq,N);
    
    YImagen = i*ones(N,1);
    
        else
            XImagen = [];
            YImagen = [];
        end
        
    X = [X;XImagen];
    Y = [Y;YImagen];
    
    end
end

%% 1.5.- GENERAR Yimagen

% Genera Yimagen -  matriz de N filas y 1 columna con la codificación
% empleada para la clase a la que pertenecen los objetos de la imagen




%% --------------------------------
%% 2.- GENERACIÓN VARIABLE TIPO STRUCT nombresProblema
%% --------------------------------

nombreDescriptores = {'Compacticidad', 'Excentricidad', 'Solidez', 'Extension','ExtensionRotacion','Hu1','Hu2','Hu3', 'Hu4', 'Hu5', 'Hu6', 'Hu7','Fourier1','Fourier2','Fourier3','Fourier4','Fourier5','Fourier6','Fourier7','Fourier8','Fourier9','Fourier10','Euler'};

% nombreClases:
nombreClases{1} = 'Circulo';
nombreClases{2} = 'Cuadrado';
nombreClases{3} = 'Triangulo';


% simboloClases: simbolos y colores para representar las muestras de cada clase
simbolosClases{1} = '*r';
simbolosClases{2} = '+g';
simbolosClases{3} = '.b';


% ------------------------------------
nombresProblema = [];
nombresProblema.descriptores = nombreDescriptores;
nombresProblema.clases = nombreClases;
nombresProblema.simbolos = simbolosClases;


%% --------------------------------
%% 3.- GUARDAR CONJUNTO DE DATOS Y NOMBRES DEL PROBLEMA
% (EN DIRECTORIO DATOSGENERADOS)
%% --------------------------------

save('./DatosGenerados/conjunto_datos','X','Y')
save('./DatosGenerados/NombresProblema','nombresProblema')
