function Funcion_Reconoce_Formas(Nombre)
    
%clear, clc, close all

%Nombre = 'Test1.jpg'; %Quitar, es para pruebas

%% 1 - AÑADIMOS PATHS

addpath('../../Imagenes/Test')
addpath('../../Imagenes/Entrenamiento')
addpath('../../Funciones') %Funciones:Paso 2 y 3
addpath('../../01_GeneracionDatos/DatosGenerados') %Datos:Paso 3
%Paso 4
%addpath('../../02_FaseEntrenamiento/CasoA_MDE_clases2a2/01_CirculoCuadrado/02_DisenoClasificador/DatosGenerados')
%addpath('../../02_FaseEntrenamiento/CasoA_MDE_clases2a2/02_CirculoTriangulo/02_DisenoClasificador/DatosGenerados')
%addpath('../../02_FaseEntrenamiento/CasoA_MDE_clases2a2/03_CuadradoTriangulo/02_DisenoClasificador/DatosGenerados')
addpath('../../02_FaseEntrenamiento/CasoB_MDM_clases2a2/01_CirculoCuadrado/02_DisenoClasificador/DatosGenerados')
addpath('../../02_FaseEntrenamiento/CasoB_MDM_clases2a2/02_CirculoTriangulo/02_DisenoClasificador/DatosGenerados')
addpath('../../02_FaseEntrenamiento/CasoB_MDM_clases2a2/03_CuadradoTriangulo/02_DisenoClasificador/DatosGenerados')
%addpath('../../02_FaseEntrenamiento/CasoC_knn_3clases/02_DisenoClasificador/DatosGenerados')
%addpath('../../02_FaseEntrenamiento/CasoC_knn_3clases/02_DisenoClasificador/Funciones')



%% 2.- GENERAMOS CONJUNTO DE DESCRIPTORES DE LOS OBJETOS DE LA IMAGEN

    XTest = [];

    I = imread(Nombre);
    
    % Binarizamos con OTSU
    umbral = graythresh(I); %Obtiene rango 0-1
    Ibin = I < 255*umbral;
        
    % Eliminamos posibles regiones ruidosas
    IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);
    
    if sum(IbinFilt(:)) > 0
        
        % Etiquetamos la imagen
        [Ietiq, N] = bwlabel(IbinFilt);
            
        % Calculamos los descriptores de las agrupaciones de pixeles
        % conexas de la imagen
        
        XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);
        %XImagen = funcion_calcula_descriptores_extentInvRot_hu_imagen(Ietiq, N);
           
    else
            
         XImagen = [];
            
    end
        
    XTest = [XTest; XImagen];

    
    
%% 3 - ESTANDARIZAMOS LOS DATOS

    %Variables del problema
    %load conjunto_datos_estandarizado.mat
    load medias_desviaciones_estandarizacion.mat
    
    numDescriptores = size(XTest,2);
    
%     medias = mean(XTest);
%     desviaciones = std(XTest);
%     medias(end) = 0; %No tocamos el numero de euler(ultimo descriptor)
%     desviaciones(end) = 1;%El numero de euler es 1, no se modifica
%     %Si la media es 0 y la desviacion 1, no se modifica, por eso damos esos
%     %valores a la ultima posicion(NO TOCAR EULER)

    ZTest = XTest;
    for i=1:numDescriptores-1

        xi = XTest(:,i);
        datos = (xi-medias(i))/desviaciones(i);
        ZTest(:,i) = datos;

    end

    %ignoramos euler
    ZTest = ZTest(:,1:22);

    
    
%% 4 - CARGAMOS LA INFORMACION PARA APLICAR LOS DIFERENTES CLASIFICADORES

    %4.1.- Minima Distancia Euclidea
    opcion = 1;
        %4.1.A.-
        load MDE_circ_cuad
        coef_circ_cuad = coeficientes_d12;
        d12_circ_cuad = d12;
        espCcas_circ_cuad = espacioCcas;
        nombresProblema_circ_cuad = nombresProblemaOIRed;
        X_circ_cuad = XoIRed;
        Y_circ_cuad = YoIRed;
        
        %4.1.B.-
        load MDE_circ_triang
        coef_circ_triang = coeficientes_d12;
        d12_circ_triang = d12;
        espCcas_circ_triang = espacioCcas;
        nombresProblema_circ_triang = nombresProblemaOIRed;
        X_circ_triang = XoIRed;
        Y_circ_triang = YoIRed;
        
        %4.1.C.-
        load MDE_cuad_triang
        coef_cuad_triang = coeficientes_d12;
        d12_cuad_triang = d12;
        espCcas_cuad_triang = espacioCcas;
        nombresProblema_cuad_triang = nombresProblemaOIRed;
        X_cuad_triang = XoIRed;
        Y_cuad_triang = YoIRed;
    
     %4.2.- Minima Distancia Mahalanobis
%     opcion = 2;
%         %4.1.A.-
%         load MDM_circ_cuad
%         coef_circ_cuad = coeficiente_d12;
%         d12_circ_cuad = d12;
%         espCcas_circ_cuad = espacioCcas;
%         nombresProblema_circ_cuad = nombresProblemaOIRed;
%         X_circ_cuad = XoIRed;
%         Y_circ_cuad = YoIRed;
%         
%         %4.1.B.-
%         load MDM_circ_triang
%         coef_circ_triang = coeficiente_d12;
%         d12_circ_triang = d12;
%         espCcas_circ_triang = espacioCcas;
%         nombresProblema_circ_triang = nombresProblemaOIRed;
%         X_circ_triang = XoIRed;
%         Y_circ_triang = YoIRed;
%         
%         %4.1.C.-
%         load MDM_cuad_triang
%         coef_cuad_triang = coeficiente_d12;
%         d12_cuad_triang = d12;
%         espCcas_cuad_triang = espacioCcas;
%         nombresProblema_cuad_triang = nombresProblemaOIRed;
%         X_cuad_triang = XoIRed;
%         Y_cuad_triang = YoIRed;
        
    %4.3.-
%     opcion = 3;
%         load decision_knn.mat
%         espCcas_knn = espacioCcas;
%         nombresProblema_knn = nombresProblemaOIRed;
%         X_knn = XoIRed;
%         Y_knn = YoIRed;

%    %4.4.-
%    opcion = 4;
%         load decision_2et
%         coef_2et = coeficiente_d12;
%         d12_2et = d12;
%         espCcas_2et = espacioCcas;
%         nombresProblema_2et = nombresProblemaOIRed;
%         X_2et = XoIRed;
%         Y_2et = YoIRed;
    
%% 5 - USAMOS LOS CLASIFICADORES PARA RECONOCER CADA OBJETO

    nombres{1} = 'Circulo';
    nombres{2} = 'Cuadrado';
    nombres{3} = 'Triangulo';
    
    for i=1:N
       
        figure,
        if opcion==1 || opcion ==2 %MDE o MDM
            
            %% 5.1.- Evaluamos Clasificadores
            %Comparamos CirculosCuadrados
            XTestoI = ZTest(i, espCcas_circ_cuad);
            A = coef_circ_cuad(1); B = coef_circ_cuad(2); C = coef_circ_cuad(3); D = coef_circ_cuad(4);
            x1 = XTestoI(1);
            x2 = XTestoI(2);
            x3 = XTestoI(3);
            d12_manual_1 = A*x1 + B*x2 + C*x3 + D;
            XTestoI_circ_cuad = XTestoI;
            
            %Comparamos CirculosTriangulos
            XTestoI = ZTest(i, espCcas_circ_triang);
            A = coef_circ_triang(1); B = coef_circ_triang(2); C = coef_circ_triang(3); D = coef_circ_triang(4);
            x1 = XTestoI(1);
            x2 = XTestoI(2);
            x3 = XTestoI(3);
            d12_manual_2 = A*x1 + B*x2 + C*x3 + D;
            XTestoI_circ_triang = XTestoI;
            
            %Comparamos CuadradosTriangulos
            XTestoI = ZTest(i, espCcas_cuad_triang);
            A = coef_cuad_triang(1); B = coef_cuad_triang(2); C = coef_cuad_triang(3); D = coef_cuad_triang(4);
            x1 = XTestoI(1);
            x2 = XTestoI(2);
            x3 = XTestoI(3);
            d12_manual_3 = A*x1 + B*x2 + C*x3 + D;
            XTestoI_cuad_triang = XTestoI;
            
            
            %% 5.2.- Regla de Decision
            if d12_manual_1 > 0 & d12_manual_2 > 0     %Circulo
                op = 1;
            elseif d12_manual_1 < 0 & d12_manual_3 > 0 %Cuadrado
                op = 2;
            elseif d12_manual_2 < 0 & d12_manual_3 < 0 %Triangulo
                op = 3;
            end
            
            %% 6.-
            %% 6.1.-
            %figure,
            subplot(2,4, 1:2),
            Funcion_visualiza(I, Ietiq==i, [255 255 0]);
            title(['Reconocimiento Objeto: ', nombres{op}])
            hold on,
            %% 6.2.- 
            subplot(2,4, 3:4), hold on, 
            funcion_representa_muestras_clasificacion_binaria_con_frontera...
                (X_circ_cuad,Y_circ_cuad, nombresProblema_circ_cuad, coef_circ_cuad); hold on
            plot3(XTestoI_circ_cuad(:,1), XTestoI_circ_cuad(:,2), XTestoI_circ_cuad(:,3), 'ok')
            subplot(2,4, 5:6), hold on,
            funcion_representa_muestras_clasificacion_binaria_con_frontera...
                (X_circ_triang,Y_circ_triang, nombresProblema_circ_triang, coef_circ_triang); hold on
            plot3(XTestoI_circ_triang(:,1), XTestoI_circ_triang(:,2), XTestoI_circ_triang(:,3), 'ok')
            subplot(2,4, 7:8), hold on,
            funcion_representa_muestras_clasificacion_binaria_con_frontera...
                (X_cuad_triang,Y_cuad_triang, nombresProblema_cuad_triang, coef_cuad_triang); hold on
            plot3(XTestoI_cuad_triang(:,1), XTestoI_cuad_triang(:,2), XTestoI_cuad_triang(:,3), 'ok')

               
            
            
        else % knn y 2etapas
            %knn
            if opcion == 3
                
                XTestoI = ZTest(i, espCcas_knn);
                k = 5;
                Distancia = 'Mahalanobis';
                %Distancia = 'Euclidea';
                YTestoI = funcion_knn(XTestoI, XoIRed, YoIRed, k, Distancia);
                
                op = 1;
                cantidad = 0;
                clases = unique(YTestoI);
                for m=1: length(clases)
                    x = sum (YTestoI==clases(m));
                    if x>cantidad
                        cantidad = x;
                        op = clases(m);
                    end
                end
                subplot(1,2, 1),
                funcion_visualiza(I, Ietiq==i, [255 255 0]);
                title(['Reconocimiento Objeto: ', nombres{op}])
                hold on,
                subplot(1,2, 2), hold on,
                funcion_representa_Muestras_clasificacion_binaria_con_frontera...
                    (X_knn,Y_knn, nombresProblema_knn);
                plot3(XTestoI(:,1), XTestoI(:,2), XTestoI(:,3), 'ok')
                
            else %2 etapas
                
            end
            
        end
        
        %Pulsar ENTER para continuar
%         condicion=true;
%         while condicion
%             continuar = input(' ');
%             break;
%         end
%        pause;
            
    end
    
end