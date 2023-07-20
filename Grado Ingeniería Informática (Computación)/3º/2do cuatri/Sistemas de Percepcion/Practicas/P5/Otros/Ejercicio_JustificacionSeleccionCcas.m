%% PROBLEMA DE CLASIFICACI�N:
% UN EXPERTO RECOMIENDA A CLIENTES SOBRE SI PARA ELLOS ES MEJOR ALQUILAR
% O COMPRAR UNA VIVIENDA. PARA EFECTUAR ESTA RECOMENDACI�N SE BASA EN 
% INFORMACI�N DE LOS CLIENTES RELATIVA A SUS INGRESOS, GASTOS, N�MERO DE HIJOS...

clear, clc, close all

%addpath('datosEjemploSeleccionAtributos')
addpath('Funciones')

%% CARGAMOS CONJUNTO DE DATOS, DEFINIMOS VARIABLES DEL PROBLEMA
load datosEjemploSeleccionAtributos.mat

[numMuestras, numDescriptores] = size(X);
codifClases = unique(Y);
numClases = length(codifClases);

%% REPRESENTACION DESCRIPTORES PARA LAS DOS CLASES

% DIAGRAMAS DE CAJA DE CADA DESCRIPTOR

nombreDescriptores = nombresProblema.descriptores;
nombreClases = nombresProblema.clases;
close all

for j=1:numDescriptores-1

    bpFigure = figure; hold on
    
    for i=1:numClases
    
        Xij = X(Y==codifClases(i),j); % datos clase i del descriptor j
    
        figure(bpFigure)
        subplot(1,numClases,i), boxplot(Xij)
        xlabel('Diagrama de Caja')
        ylabel([nombreDescriptores{j} ' - ' nombreClases{i}])
        axis([ 0 2 -3 3 ]) % manejamos datos estandarizados

    end
    
    inputs = X(:,j)';
    outputs = Y';
    indiceJdescriptor = indiceJ(inputs,outputs);
    sgtitle(['Separabilidad ' nombreDescriptores{j} ': ' num2str(indiceJdescriptor)])
    
end


% EN ESPACIOS DE CARACTERISTICAS DOS A DOS
close all
for i=1:2:numDescriptores-1 % para que sean pares
    
    espacioCcas = [i i+1];
    funcion_representa_datos(X,Y, espacioCcas, nombresProblema)
    % la funcion abre una ventana tipo figure
    
    inputs = X(:,espacioCcas)';
    outputs = Y';
    indiceJEspacioCca = indiceJ(inputs,outputs);
    title(['Separabilidad Espacio CCas dado por  '...
        nombreDescriptores{espacioCcas} ': ' num2str(indiceJEspacioCca)])
    
end


%% EJERCICIO:

%% 1- DETERMINA LOS TRES MEJORES DESCRIPTORES INDIVUDIALES

outputs = Y';
valoresJ = zeros(1,numDescriptores);

for j=1:numDescriptores
    inputs = X(:,j)';
    valoresJ(1,j) = indiceJ(inputs,outputs);
end

[valoresJord, indices] = sort(valoresJ,'descend');

mejoresDInd = indices(1:3); %Mejores descriptores individualmente
JmejoresDInd = valoresJord(1:3); %Valores de J de esos descriptores

%% 2.- REPRESENTA SUS DIAGRAMAS DE CAJA Y A�ADE EN EL T�TULO EL INDICE DE 
% SEPARABILIDAD DE CADA DESCRIPTOR

%Diagramas de cajas de cada descriptor

nombreDescriptores = nombresProblema.descriptores;
nombresClases = nombresProblema.clases;
close all

for j=1:length(mejoresDInd)
    
    d = mejoresDInd(j);
    bpFigure = figure;hold on
    
    for i = 1:numClases
        
        Xij = X(Y==codifClases(i),d); % datos clase i del descriptor J
        
        
        figure(bpFigure);
        subplot(1,numClases,i), boxplot(Xij)
        xlabel('Diagrama de Caja')
        ylabel([nombreDescriptores{d} ' - ' nombresClases{i}])
        axis([0 2 -3 3]) % Manejamos datos estandarizados
    end
    
    sgtitle(['Separabilidad ' nombreDescriptores{d} ': ' num2str(JmejoresDInd(j))])
    
end


%% 3.- REPRESENTA LAS MUESTRAS EN EL ESPACIO DE CARACTER�STICAS DEFINIDO POR
% ESOS TRES DESCRIPTORES

espacioCcas = mejoresDInd;
funcion_representa_datos(X,Y,espacioCcas, nombresProblema)
% La funcion abre una ventana de tipo figure

%% 4.- A�ADE AL T�TULO DE LA GR�FICA ANTERIOR EL VALOR DE SEPARABILIDAD DE 
% ESE ESPACIO  DE CARACTER�STICAS

inputs = X(:,espacioCcas)';
outputs = Y';
indiceJEspacioCca = indiceJ(inputs,outputs);
title(['Separabilidad Espacio Ccas dado por ' nombreDescriptores{espacioCcas} ': ' num2str(inidiceJEspacioCca)])


%% 5.- UTILIZA LA FUNCI�N DE MATLAB combnk PARA ENCONTRAR LA COMBINACI�N DE 
% 3 DESCRIPTORES QUE PRODUCE LA MAYOR SEPARABILIDAD

comb = combnk(1:numDescriptores,3);

numComb = size(comb,1);

outputs = Y';
valoresJ = zeros(numComb,1);

for i =1:numComb
    columnasOI = comb(i,:);
    inputs = X(:,columnasOI);
    valoresJ(i) = indicesJ(inputs,outputs);
end
[valoresJord, indices] = sort(valoresJ,'descend');

mejoresD = comb(indices(1),:); % Mejores descriptores de forma conjunta
JmejoresD = valoresJord(1); % valores de J de ese espacio formado por esos descriptores

%% 6.- REPRESENTA LAS MUESTRAS DEL ESPACIO DE CARACTER�STICAS DEFINIDO POR
% ESOS TRES DESCRIPTORES Y A�ADA EL �NDICE DE SEPARABILIDAD EN EL T�TULO

espacioCcas = mejoresD;
funcion_representa_datos(X,Y,espacioCcas,nombresProblema)

inputs = X(:,espacioCcas);
outputs = Y';
indiceJEspacioCca = indiceJ(inputs,outputs);
title(['Separabilidad Espacio Ccas dado por ' nombreDescriptores{espacioCcas} ': ' num2str(indiceJEspacioCcA)])

%% 7.- �QU� CONCLUSIONES EXTRAES?

%El proceso de la seleccion de atributos es MUY importante, y lo que a
%priori puede parecer un atributo bastante regular, combinado con otros
%puede ser maginifico.