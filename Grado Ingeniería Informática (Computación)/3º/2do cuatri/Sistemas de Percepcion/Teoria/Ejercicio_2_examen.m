%% EJERCICIO 2 EXAMEN TEORICO - PABLO CORDON HIDALGO - 49085903Q

clear,close all

addpath("Examen")

%Guardamos los datos de la primera clase
load datosEj2.mat

%% 2.1 - CLASIFICADOR MDM (Explicacion dentro de la funcion)

[d1,d2,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(XTrain,YTrain);

%% 2.2 - REPRESENTACION DE LAS INSTANCIAS DE TEST Y EL PLANO MDM

% REPRESENTACION DE LAS MUESTRAS EN EL ESPACIO TRIDIMENSIONAL
funcion_representa_muestras_clasificacion_binaria(XTest,YTest);

hold on;

% REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE LAS DOS CLASES (Plano)

x1min = min(XTest(:,1)); x1max = max(XTest(:,1));
x2min = min(XTest(:,2)); x2max = max(XTest(:,2));
x3min = min(XTest(:,3)); x3max = max(XTest(:,3));
axis([x1min x1max x2min x2max x3min x3max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3); D = coeficientes_d12(4);

Xmin = min(XTest(:));
Xmax = max(XTest(:));
paso = (Xmax-Xmin)/100;

[x1Plano, x2Plano] = meshgrid(Xmin:paso:Xmax);
x3Plano = -(A*x1Plano + B*x2Plano +D)/(C+eps);
surf(x1Plano,x2Plano,x3Plano);

%% 2.3 APLICAR VERSION CUADRATICA Y LINEAL DEL CLASIFICADOR PARA CALCULAR SU ACIERTO

valoresClases = unique(YTest);
numClases = length(valoresClases);
[numDatos,numAtributos] = size(XTest);

%APLICACION DEL CLASIFICADOR: OPCION CUADRATICA - TANTAS FUNCIONES DE
%DECISION COMO CLASES

Y_Clasificador1 = zeros(size(YTest));

for i =1:numDatos
    
    XoI = XTest(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    x3 = XoI(3);
    
    valor_d1 = eval(d1);
    valor_d2 = eval(d2);
    
    if valor_d1 > valor_d2
        Y_Clasificador1(i) = valoresClases(1);
    else
        Y_Clasificador1(i) = valoresClases(2);
    end

end

% Evaluamos la precision

Y_modelo = Y_Clasificador1;

error = Y_modelo-YTest;

num_aciertos = sum(error==0);

Acc_cuad = num_aciertos/numDatos


%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES
Y_clasificador2 = zeros(size(YTest));

for i = 1:numDatos
    XoI = XTest(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    x3 = XoI(3);
    
    d12_manual = A*x1 + B*x2 + C*x3 + D;
    eval(d12);
    
    if d12_manual > 0
        
        Y_clasificador2(i) = valoresClases(1);
    
    else
        
        Y_clasificador2(i) = valoresClases(2);
        
    end

    
end


% Evaluamos la precision

Y_modelo = Y_clasificador2;

error = Y_modelo-YTest;

num_aciertos = sum(error==0);

Acc_lin = num_aciertos/numDatos



%% 2.4 ¿POR QUE NO ES UNA BUENA ESTRATEGIA DE CLASIFICACION UTILIZAR UN CLASIFICADOR DE MDE?

%Representar el espacio de caracteristicas de instacias de test junto con
%el plano que usaria el clasificador MDE


% REPRESENTACION DE LOS DATOS

funcion_representa_muestras_clasificacion_binaria(XTest,YTest);

hold on

%DISEÑO DEL CLASIFICADOR MDE

[d1,d2,d12, coeficientes_d12] = funcion_calcula_funciones_decision_MDE_clasificacion_binaria(XTest,YTest);


% REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE LAS DOS CLASES (Plano)

x1min = min(XTest(:,1)); x1max = max(XTest(:,1));
x2min = min(XTest(:,2)); x2max = max(XTest(:,2));
x3min = min(XTest(:,3)); x3max = max(XTest(:,3));
axis([x1min x1max x2min x2max x3min x3max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3); D = coeficientes_d12(4);

Xmin = min(XTest(:));
Xmax = max(XTest(:));
paso = (Xmax-Xmin)/100;

[x1Plano, x2Plano] = meshgrid(Xmin:paso:Xmax);
x3Plano = -(A*x1Plano + B*x2Plano +D)/(C+eps);
surf(x1Plano,x2Plano,x3Plano);


% EL CLASIFICADOR DE MINIMA DISTANCIA EUCLIDEA FALLA, YA QUE ESTE TIENE EN
% CUENTA QUE LAS VARIANZAS DE LAS CLASES SON IGUALES, CASO QUE NO SE CUMPLE
% EN NUESTROS DATOS DE EJEMPLO, POR LO QUE AL OPERAR SOLO CON LAS MEDIAS SE
% PRODUCE UN FALLO EN LA CLASIFICACIÓN
