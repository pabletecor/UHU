%% REPRESENTACI�N Y AN�LISIS CUALITATIVO INICIAL DEL CONJUNTO DE DATOS X-Y
%% ------------------------------------------------------------------------

%% --------------------------------------------------------------
%% CARGAR CONJUNTO DE DATOS Y VARIABLES DEL PROBLEMA
%% --------------------------------------------------------------
addpath('./DatosGenerados')

load conjunto_datos.mat
load nombresProblema.mat

%Variables del problema
[numMuestras,numDescriptores] = size(X);
codifClases = unique(Y);
numClases = length(codifClases);

%% --------------------------------------------------------------
%% REPRESENTAR LOS DATOS EN ESPACIOS DE CARACTERISTICAS DOS A DOS
%% --------------------------------------------------------------

% Cada gr�fica en una ventana tipo figure. Utilizar la funci�n:
for i =1:2:numDescriptores-1
    
    espacioCcas = [i i+1];
    funcion_representa_datos(X,Y, espacioCcas, nombresProblema)
    %La propia funcion abre una ventana tipo figure
end


%% ---------------------------------------------------------------
%% REPRESENTACI�N HISTOGRAMA Y DIAGRAMA DE CAJAS DE CADA DESCRIPTOR
%% ---------------------------------------------------------------

% Para cada descriptor, abrir dos ventanas tipo figure
% una para representar histogramas y otra para diagramas de caja

% En cada una de ellas se representan los datos del descriptor para las 
% distintas clases del problema en gr�ficas independientes

% - Histogramas: tantas filas de gr�ficas como clases -
% subplot(numClases,1,i)
% - Diagramas de caja: tantas columnas de gr�ficas como clases -
% subplot(1,numClases,i)

% Ejemplo de programaci�n

for j=1:numDescriptores
    
    % Valores m�ximo y m�nimos para representar en la misma escala
    vMin = min(X(:,j)); 
    vMax = max(X(:,j));
    
    hFigure = figure; hold on
    bpFigure = figure; hold on
    
    for i=1:numClases
    
        Xij = X(Y==codifClases(i),j); % datos de la clase i del descriptor j 
        numDatosClase = size(Xij,1);

        %Algunos valores presentativos de la muestra
        valor_medio = mean(Xij);
        desv_tipica = std(Xij);
        mediana = median(Xij);
        Xij_ord = sort(Xij);
        Q1 = Xij_ord(round(0.25*numDatosClase));
        Q3 = Xij_ord(round(0.75*numDatosClase));
        
        
        figure(hFigure)
        subplot(numClases,1,i), hist(Xij),
        xlabel(nombreDescriptores{j})
        ylabel('Histograma')
        axis([ vMin vMax 0 numMuestras/4]) % inf para escala autom�tica del eje y
        title(nombreClases{i})
        
        figure(bpFigure)
        subplot(1,numClases,i), boxplot(Xij)
        xlabel('Diagrama de Caja')
        ylabel(nombreDescriptores{j})
        axis([ 0 2 vMin vMax ])
        title(nombreClases{i})
    end
end


%% ---------------------------------------------------------------
%% OBTENER CONCLUSIONES DE LA EFICIENCIA DE CADA DESCRIPTOR - AN�LISIS CUALITATIVO
%% ---------------------------------------------------------------

% Por cada descriptor, asignar una categor�a seg�n la siguiente escala:

% Escala de adecuaci�n del descriptor: no adecuado, adecuado, muy adecuado 



