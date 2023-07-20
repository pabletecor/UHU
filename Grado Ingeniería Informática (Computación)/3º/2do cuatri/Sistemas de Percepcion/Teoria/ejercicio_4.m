% EJERCICIO 4 BOLETIN

clear,close all

addpath("Datos")

%Guardamos los datos de la primera clase
load datos_biomarcadores_exp1.mat
datos1 = datos;
clases1 = clases;

%Guardamos los datos de la segunda clase
load datos_biomarcadores_exp2.mat
datos2 = datos;
clases2 = clases;

valoresClases1 = unique(clases1);
numClases1 = length(valoresClases1);
[numDatos1,numAtributos1] = size(datos1);

valoresClases2 = unique(clases2);
numClases2 = length(valoresClases2);
[numDatos2,numAtributos2] = size(datos2);

% a) Representa para cada experimento los datos de los diferentes
% biomarcadores

% Representacion de los datos del primer experimento

funcion_representa_muestras_clasificacion_binaria(datos1,clases1);


% Representacion de los datos del segundo experimento

funcion_representa_muestras_clasificacion_binaria(datos2,clases2);




% b) Diseña un clasificador lineal que permita predecir la enfermedad a
% partir de los biomarcadores utilizados en los exp 1 y 2

%Exp1 - MDE

%DISEÑO DEL CLASIFICADOR MDE

[d1,d2,d12, coeficientes_d12] = funcion_calcula_funciones_decision_MDE_clasificacion_binaria(datos1,clases1);

%REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE DOS CLASES: LINEA RECTA
%d12 = 0
hold on

x1min = min(datos1(:,1)); x1max = max(datos1(:,1));
x2min = min(datos1(:,2)); x2max = max(datos1(:,2));
axis([x1min x1max x2min x2max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3);
x1Recta = x1min:0.01:x1max;
x2Recta = -(A*x1Recta+C)/(B+eps); %A*x1 + B*x2 + C = 0

plot(x1Recta,x2Recta,'g')


%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES

Y_clasificador2 = zeros(size(clases1));

for i =1:numDatos1
    XoI = datos1(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    
    d12_manual = A*x1 + B*x2 + C;
    eval(d12);
    
    if d12_manual > 0
        
        Y_clasificador2(i) = valoresClases1(1);
    
    else
        
        Y_clasificador2(i) = valoresClases1(2);
        
    end

    
end


% Evaluamos la precision

Y_modelo = Y_clasificador2;

error = Y_modelo-clases1;

num_aciertos = sum(error==0);

Acc = num_aciertos/numDatos1;


%Exp2 - MDE

%DISEÑO DEL CLASIFICADOR MDE

[d1,d2,d12, coeficientes_d12] = funcion_calcula_funciones_decision_MDE_clasificacion_binaria(datos2,clases2);

%% REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE LAS DOS CLASES (Plano)

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3); D = coeficientes_d12(4);



%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES

Y_clasificador2 = zeros(size(clases2));

for i =1:numDatos2
    
    XoI = datos2(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    x3 = XoI(3);
    
    d12_manual = A*x1 + B*x2 + C*x3 + D;
    eval(d12);
    
    if d12_manual > 0
        
        Y_clasificador2(i) = valoresClases2(1);
    
    else
        
        Y_clasificador2(i) = valoresClases2(2);
        
    end

    
end


% Evaluamos la precision

Y_modelo = Y_clasificador2;

error = Y_modelo-clases2;

num_aciertos = sum(error==0);

Acc = num_aciertos/numDatos2;


%% c) Diseñar un clasificador MDM

[d1,d2,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(datos2,clases2);

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3); D = coeficientes_d12(4);

%APLICACION DEL CLASIFICADOR: OPCION CUADRATICA - TANTAS FUNCIONES DE
%DECISION COMO CLASES

Y_Clasificador1 = zeros(size(clases2));

for i =1:numDatos2
    
    XoI = datos2(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    x3 = XoI(3);
    
    valor_d1 = eval(d1);
    valor_d2 = eval(d2);
    
    if valor_d1 > valor_d2
        Y_Clasificador1(i) = valoresClases2(1);
    else
        Y_Clasificador1(i) = valoresClases2(2);
    end

end


% Evaluamos la precision

Y_modelo = Y_Clasificador1;

error = Y_modelo-clases2;

num_aciertos = sum(error==0);

Acc = num_aciertos/numDatos2;
