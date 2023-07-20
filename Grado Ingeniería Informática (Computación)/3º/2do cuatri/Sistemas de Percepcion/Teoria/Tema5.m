%%EJEMPLOS TEORIA TEMA 5

%Clasificador Bayesiano N-dimensional


datos = [1,3;1,5;3,3;3,7;2,2];

x1 = datos(:,1);
x2 = datos(:,2);

%Vector de medias 
m_filas=mean(datos);

%Matriz de varianza
V1 = var(datos(:,1),1);
V2 = var(datos(:,2),1);
V12 = var(datos(:,1),1); %(covarianza)

Mcovarianza = cov(datos,1);

%Planteamiento teorico distancia euclidea del centro a cualquier punto dado

x1 = sym('x1','real');
x2 = sym('x2','real');
X = [x1;x2];

M=m_filas';

d_2 = expand((X-M)'*(X-M));

x1 = 1; x2=2; eval(d_2);







%% MINIMA DISTANCIA EUCLIDEA PARA DOS DIMENSIONES
addpath("Datos")
load datos_MDE_2dimensiones.mat

valoresClases = unique(Y);
numClases = length(valoresClases);
[numDatos,numAtributos] = size(X);

% Representacion de los datos
funcion_representa_muestras_clasificacion_binaria(X,Y);

hold on;
%Calculo de las matrices de covarianzas de cada clase objetivo. Medir
%correlacion entre los datos y analizar las suposiciones que se tienen que
%cumplir para aplicar el clasificador MDE

% Aprovechamos para calcular el vector de medias de cada clase

M = zeros(numClases,numAtributos);
mCov = zeros(numAtributos, numAtributos,numClases);

for i=1:numClases
    
    FoI= Y==valoresClases(i);
    XClase = X(FoI,:);
    M(i,:) = mean(XClase);
    mCov(:,:,i) = cov(XClase,1);
    
end

plot(M(:,1),M(:,2),'ko-')

mCov %Podemos observar que las varianzas son practicamente iguales

%Variables no correladas, covarianza de las variables aproximadamente 0
mCov_clase1 = mCov(:,:,1);
coef_corr = funcion_calcula_coeficiente_correlacion_lineal_2variables(mCov_clase1)

mCov_clase2 = mCov(:,:,2);
coef_corr = funcion_calcula_coeficiente_correlacion_lineal_2variables(mCov_clase2)

% Probabilidad a priori de las clases
numDatosClase1 = sum(Y==valoresClases(1));
numDatosClase2 = sum(Y==valoresClases(2));
numDatos

%DISEÑO DEL CLASIFICADOR MDE

[d1,d2,d12, coeficientes_d12] = funcion_calcula_funciones_decision_MDE_clasificacion_binaria(X,Y);

%REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE DOS CLASES: LINEA RECTA
%d12 = 0
x1min = min(X(:,1)); x1max = max(X(:,1));
x2min = min(X(:,2)); x2max = max(X(:,2));
axis([x1min x1max x2min x2max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3);
x1Recta = x1min:0.01:x1max;
x2Recta = -(A*x1Recta+C)/(B+eps); %A*x1 + B*x2 + C = 0

plot(x1Recta,x2Recta,'g')

%APLICACION DEL CLASIFICADOR: OPCION CUADRÁTICA - TANTAS FUNCIONES DE
%DECISION COMO CLASES

Y_Clasificador1 = zeros(size(Y));

for i =1:numDatos
    XoI = X(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    
    valor_d1 = eval(d1);
    valor_d2 = eval(d2);
    
    if valor_d1 > valor_d2
        Y_Clasificador1(i) = valoresClases(1);
    else
        Y_Clasificador1(i) = valoresClases(2);
    end

end


%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES

Y_clasificador2 = zeros(size(Y));

for i = numDatos
    XoI = X(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    
    d12_manual = A*x1 + B*x2 + C;
    eval(d12);
    
    if d12_manual > 0
        
        Y_clasificador2(i) = valoresClases(1);
    
    else
        
        Y_clasificador2(i) = valoresClases(2);
        
    end

    
end


% Evaluamos la precision

Y_modelo = Y_clasificador1;

error = Y_modelo-Y;

num_aciertos = sum(error==0);

Acc = num_aciertos/numDatos;

%% MINIMA DISTANCIA DE MAHALANOBIS
clear, close all
addpath("Datos")
load datos_MDM_2dimensiones.mat

valoresClases = unique(Y);
numClases = length(valoresClases);
[numDatos,numAtributos] = size(X);

%% Representacion de los datos
funcion_representa_muestras_clasificacion_binaria(X,Y);

hold on;
%Calculo de las matrices de covarianzas de cada clase objetivo. Medir
%correlacion entre los datos y analizar las suposiciones que se tienen que
%cumplir para aplicar el clasificador MDE

% Aprovechamos para calcular el vector de medias de cada clase

M = zeros(numClases,numAtributos);
mCov = zeros(numAtributos, numAtributos,numClases);

for i=1:numClases
    
    FoI= Y==valoresClases(i);
    XClase = X(FoI,:);
    M(i,:) = mean(XClase);
    mCov(:,:,i) = cov(XClase,1);
    
end

plot(M(:,1),M(:,2),'ko-')

mCov %Podemos observar que las varianzas son practicamente iguales

%Variables correladas, covarianza de las variables aproximadamente 1
mCov_clase1 = mCov(:,:,1);
coef_corr = funcion_calcula_coeficiente_correlacion_lineal_2variables(mCov_clase1)

mCov_clase2 = mCov(:,:,2);
coef_corr = funcion_calcula_coeficiente_correlacion_lineal_2variables(mCov_clase2)

% Probabilidad a priori de las clases
numDatosClase1 = sum(Y==valoresClases(1));
numDatosClase2 = sum(Y==valoresClases(2));
numDatos

% Diseño de clasificador Minima Distancia Mahalanobis

[d1,d2,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(X,Y);


%REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE DOS CLASES: LINEA RECTA
%d12 = 0
x1min = min(X(:,1)); x1max = max(X(:,1));
x2min = min(X(:,2)); x2max = max(X(:,2));
axis([x1min x1max x2min x2max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3);
x1Recta = x1min:0.01:x1max;
x2Recta = -(A*x1Recta+C)/(B+eps); %A*x1 + B*x2 + C = 0

plot(x1Recta,x2Recta,'g')


%APLICACION DEL CLASIFICADOR: OPCION CUADRATICA - TANTAS FUNCIONES DE
%DECISION COMO CLASES

Y_Clasificador1 = zeros(size(Y));

for i =1:numDatos
    XoI = X(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    
    valor_d1 = eval(d1);
    valor_d2 = eval(d2);
    
    if valor_d1 > valor_d2
        Y_Clasificador1(i) = valoresClases(1);
    else
        Y_Clasificador1(i) = valoresClases(2);
    end

end


%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES
Y_clasificador2 = zeros(size(Y));

for i = 1:numDatos
    XoI = X(i,:);
    x1 = XoI(1);
    x2 = XoI(2);
    
    d12_manual = A*x1 + B*x2 + C;
    eval(d12);
    
    if d12_manual > 0
        
        Y_clasificador2(i) = valoresClases(1);
    
    else
        
        Y_clasificador2(i) = valoresClases(2);
        
    end

    
end

% Evaluamos la precision

Y_modelo = Y_clasificador1;

error = Y_modelo-Y;

num_aciertos = sum(error==0);

Acc = num_aciertos/numDatos;

%% MINIMA DISTANCIA MAHALANOBIS 3 DIMENSIONES

clear, close all
addpath("Datos")
load datos_MDM_3dimensiones.mat

valoresClases = unique(Y);
numClases = length(valoresClases);
[numDatos,numAtributos] = size(X);

%% Representacion de los datos
funcion_representa_muestras_clasificacion_binaria(X,Y);

hold on;

% Diseño de clasificador Minima Distancia Mahalanobis

[d1,d2,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(X,Y);

%% REPRESENTACION DE LA FRONTERA DE SEPARACION ENTRE LAS DOS CLASES (Plano)

x1min = min(X(:,1)); x1max = max(X(:,1));
x2min = min(X(:,2)); x2max = max(X(:,2));
x3min = min(X(:,3)); x3max = max(X(:,3));
axis([x1min x1max x2min x2max x3min x3max])

A = coeficientes_d12(1); B = coeficientes_d12(2); C = coeficientes_d12(3); D = coeficientes_d12(4);

Xmin = min(X(:));
Xmax = max(X(:));
paso = (Xmax-Xmin)/100;

[x1Plano, x2Plano] = meshgrid(Xmin:paso:Xmax);
x3Plano = -(A*x1Plano + B*x2Plano +D)/(C+eps);
surf(x1Plano,x2Plano,x3Plano);

%APLICACION DEL CLASIFICADOR: OPCION CUADRATICA - TANTAS FUNCIONES DE
%DECISION COMO CLASES

Y_Clasificador1 = zeros(size(Y));

for i =1:numDatos
    
    XoI = X(i,:);
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


%APLICACION DEL CLASIFICADOR: OPCION LINEAL - 1 FUNCION PARA SEPARAR DOS
%CLASES
Y_clasificador2 = zeros(size(Y));

for i = 1:numDatos
    XoI = X(i,:);
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

%% EJERCICIOS DE EJEMPLO EXAMEN


