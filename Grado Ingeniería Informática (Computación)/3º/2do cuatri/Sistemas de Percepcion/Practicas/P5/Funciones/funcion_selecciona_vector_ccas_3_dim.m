function [espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas_3_dim(XoI,YoI,numDescriptoresOI)

%Datos
[numMuestras, numDescriptores] = size(XoI);
codifClases = unique(YoI);
numClases = length(codifClases);

% 1.- Generar ranking de caracteristicas
outputs = YoI';
valoresJ = zeros(1,numDescriptores);
for j=1:numDescriptores
    inputs = XoI(:,j)';
    valoresJ(1,j) = indiceJ(inputs, outputs);
end

[valoresJord, indices] = sort(valoresJ, 'descend');


% 2.- Preseleccion de caracteristicas: 9 de las 22
%numDescriptoresOI = 9;
Preseleccion = indices(1:numDescriptoresOI);

% 3.- Las 3 mejores
comb = combnk(Preseleccion,3);
numComb = size(comb,1);

outputs = YoI';
valoresJ = zeros(numComb,1);

for i=1:numComb
    columnasOI = comb(i,:);
    inputs = XoI(:,columnasOI)';
    valoresJ(i) = indiceJ(inputs, outputs);
end

[valoresJord, indices] = sort(valoresJ, 'descend');

espacioCcas = comb(indices(1),:); %mejores conjunto de 3 descriptores
JespacioCcas = valoresJord(1:3); %valores de J de esos descriptores




end