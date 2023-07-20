%% ClasificadorMDE_2clases

clear, clc, close all

addpath('funciones')

% Cargamos los datos de interes

rutaFich = '../01_SeleccionDescriptores/DatosGenerados/';
nombreFich = 'espacio_ccas_cuad_triang.mat';

load([rutaFich nombreFich]);

clear nombreFichero rutaFicheros

% Preparamos la informacion leida

XoIRed = XoI(:,espacioCcas);
YoIRed = YoI;

% Clasificador MDE

 [d1,d2,d12,coeficientes_d12] = funcion_calcula_funciones_decision_MDM_clasificacion_binaria(XoIRed,YoIRed);
 
 % Representamos muestras y plano del clasificador
 nombresProblemaOIRed = [];
 nombresProblemaOIRed.descriptores = nombresProblemaOI.descriptores(espacioCcas);
 nombresProblemaOIRed.clases = nombresProblemaOI.clases;
 nombresProblemaOIRed.simbolos = nombresProblemaOI.simbolos;

 %Si a esta funcion se le pasa como ultimo argumento los coeficientes,
 %representa la frontera, si no solo representa las muestras
 
 funcion_representa_muestras_clasificacion_binaria_con_frontera(XoIRed,YoIRed,nombresProblemaOIRed,coeficientes_d12)
 
 
save('./DatosGenerados/MDM_cuad_triang.mat','espacioCcas','d12','coeficientes_d12','YoIRed','XoIRed','nombresProblemaOIRed')
