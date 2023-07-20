%% CIRCULO-TRIANGULO VS CUADRADO

clc, clear, close all

% Carga de la informacion

addpath('Funciones')

% Carga de datos

rutaFich = '../../../../01_GeneracionDatos/DatosGenerados/';
nombreFich = 'conjunto_datos_estandarizado.mat';

load([rutaFich nombreFich]);

nombreFich = 'NombresProblema.mat';
load([rutaFich nombreFich]);

X_MD = Z;

FoI = Y ==3;
Y_MD = Y;
Y_MD(FoI) = 1;
%% GENERACION XoI, YoI, del problema especifico

clasesOI = [1 2];

codifClases = unique(Y_MD);
codifClasesOI = codifClases(clasesOI);

filasOI = false(size(Y_MD));

for i=1:length(clasesOI)
   
    filasOI_i = (Y_MD==codifClasesOI(i));
    filasOI = or(filasOI,filasOI_i);
    
end

numDescriptores = size(X_MD,2);
XoI = X_MD(filasOI,1:numDescriptores-1); %No consideramos Euler
YoI = Y_MD(filasOI);

% Calculamos los tres mejores descriptores conjuntos
numDescriptoresOI = 9;
[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas_3_dim(XoI,YoI,numDescriptoresOI);

%Representamos
nombresProblemaOI = nombresProblema;

nombreClases{1} = 'Circulo-triangulo';
nombreClases{2} = 'Cuadrado';

nombresProblemaOI.clases = nombreClases;

nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

funcion_representa_datos(XoI,YoI,espacioCcas,nombresProblemaOI)

save('./DatosGenerados/espacio_ccas_circtriang_cuad.mat','espacioCcas','XoI','YoI','nombresProblemaOI')