%DISEÑO DEL CLASIFICADOR DE MINIMA DISTANCIA DE MAHALANOBIS

function [d1_mCov_de_Cada_Clase,d2_mCov_de_Cada_Clase,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(X,Y)

    datos = X; %Vamos a dejar x para la notacion del vector de atributos genérico
    
    valoresClases = unique(Y);          %Valores que se asignan a las clases
    numClases = length(valoresClases);  %Numero de clases que  hay
    [numDatos, numAtributos] = size(datos);
    
    %Inicializacion del vector de características X 
    x1 = sym('x1','real');
    x2 = sym('x2','real');

    X = [x1;x2];
    
    %Si tenemos 3 dimensiones añadiremos x3 al vector
    if numAtributos==3
       
        x3 = sym('x3','real');
        X = [X;x3];
        
    end
    
    %Calculo del vector de medias y matrices de covarianza de cada clase


        M = zeros(numClases,numAtributos);
        mCov = zeros(numAtributos, numAtributos,numClases);

        for i=1:numClases %Para cada clase...
            
            %Separamos las Filas de Interes
            FoI = Y==valoresClases(i);
            %Guardamos estas filas en una variable aparte
            XClase = datos(FoI,:);
            %Calculamos su media para el vector de medias de la clase
            M(i,:) = mean(XClase);
            %Calculamos tambien la mariz de covarianzas de la clase
            mCov(:,:,i) = cov(XClase,1);

        end

    
    %Matrices de covarianza de cada clase (separadas):
    mCov1=mCov(:,:,1);
    mCov2=mCov(:,:,2);

    
    %Matriz de covarianza que se utiliza para el calculo de función lineal
    %SE DEBE CONSIDERAR UNA UNICA MATRIZ DE COVARIANZA PARA LAS DOS CLASES
    numDatosClase1 = sum(Y==valoresClases(1));
    numDatosClase2 = sum(Y==valoresClases(2));
    mCov = (numDatosClase1*mCov1 + numDatosClase2*mCov2)/(numDatosClase1+numDatosClase2);
    
    %Calculo de las funciones de decision de cada clase: menos Distancia
    %Euclidea al cuadrado entre X y el Vector prototipo de la clase
    
    M1 = M(1,:)'; % Vector columna - hacemos la traspuesta
    d1_mCov_de_Cada_Clase = expand(-(X-M1)'*pinv(mCov1)*(X-M1));
    d1_mCov_comun = expand(-(X-M1)'*pinv(mCov)*(X-M1));
    
    M2 = M(2,:)'; % Vector columna - hacemos la traspuesta
    d2_mCov_de_Cada_Clase = expand(-(X-M2)'*pinv(mCov2)*(X-M2));
    d2_mCov_comun = expand(-(X-M2)'*pinv(mCov)*(X-M2));
    
    %Funcion de decision que separa las muestras de las lases d12 = d1 -d2
    %Frontera de decision: d12 = d1 - d2 = 0;
    
    d12 = d1_mCov_comun - d2_mCov_comun; %forma lineal SOLO SI CONSIDERAMOS LA MISMA COV ¿?
    
    
    %Calculo de coeficientes
    
    if numAtributos == 2
       %Si dim = 2: d12 = A*x1+B*x2+C - forma lineal
       x1 = 0; x2=0; C = eval(d12);
       x1 = 1; x2=0; A = eval(d12)-C;
       x1 = 0; x2=1; B = eval(d12)-C;
       
       coeficientes_d12 = [A B C];
       
    else
       %Si dim = 3: d12 = A*x1+B*x2+C*x3 + D 
       x1 = 0; x2=0; x3 =0; D = eval(d12);
       x1 = 1; x2=0; x3 =0; A = eval(d12)-D;
       x1 = 0; x2=1; x3 =0; B = eval(d12)-D;
       x1 = 0; x2=0; x3 =1; C = eval(d12)-D;
        
       coeficientes_d12 = [A B C D];
    end
    
    
end