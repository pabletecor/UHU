%% CIRCULO VS TRIANGULO

clc, clear, close all

% Carga de la informacion

addpath('Funciones')
addpath('../../../../01_GeneracionDatos/DatosGenerados/')
load 'conjunto_datos_estandarizado.mat'

% Carga de datos

rutaFich = '../../../../01_GeneracionDatos/DatosGenerados/';
nombreFich = 'conjunto_datos_estandarizado.mat';

load([rutaFich nombreFich]);

nombreFich = 'NombresProblema.mat';
load([rutaFich nombreFich]);

clear nombreFich rutaFich

X = Z;
%% GENERACION XoI, YoI, del problema especifico

clasesOI = [1 3];

codifClases = unique(Y);
codifClasesOI = codifClases(clasesOI);

filasOI = false(size(Y));

for i=1:length(clasesOI)
   
    filasOI_i = (Y==codifClasesOI(i));
    filasOI = or(filasOI,filasOI_i);
    
end

numDescriptores = size(X,2);
XoI = X(filasOI,1:numDescriptores-1); %No consideramos Euler
YoI = Y(filasOI);

% Calculamos los tres mejores descriptores conjuntos
numDescriptoresOI = 9;
[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas_3_dim(XoI,YoI,numDescriptoresOI);

%Representamos
nombresProblemaOI = nombresProblema;

nombresProblemaOI.clases = nombresProblema.clases(clasesOI);

nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

funcion_representa_datos(XoI,YoI,espacioCcas,nombresProblemaOI)

save('./DatosGenerados/espacio_ccas_circ_triang.mat','espacioCcas','XoI','YoI','nombresProblemaOI')
