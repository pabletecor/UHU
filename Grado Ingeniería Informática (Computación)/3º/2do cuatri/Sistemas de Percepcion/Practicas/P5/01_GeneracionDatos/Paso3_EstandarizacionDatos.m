%% Paso 3 - Estadarizacion de los Datos

%Cargamos los datos
clc,clear all 

addpath('./DatosGenerados')
addpath('../Funciones')

load 'conjunto_datos.mat'
load NombresProblema.mat

%Variables del problema
[numMuestras,numDescriptores] = size(X);
codifClases = unique(Y);
numClases = length(codifClases);

%Estandarizacion

medias = mean(X);
desviaciones = std(X);
medias(end) = 0;    %El desriptor Euler no se toca
desviaciones(end) = 1;

Z = X;

for i = 1:numDescriptores - 1
    
    Descriptor = X(:,i);
    media = mean(Descriptor);
    desviacion = std(Descriptor);

    %medias_desviaciones(:,i) = [media, desviacion];
    Z(:,i) = (Descriptor - media)./desviacion;
    
end

%Guardamos los datos

save('./DatosGenerados/conjunto_datos_estandarizado','Z','Y')
save('./DatosGenerados/medias_desviaciones_estandarizacion.mat','medias','desviaciones')