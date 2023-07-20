clear, clc, close all

addpath('funciones')

% Cargamos los datos de interes

rutaFich = '../01_SeleccionDescriptores/DatosGenerados/';
nombreFich = 'espacio_ccas_circtriang_cuad.mat';

load([rutaFich nombreFich]);

clear nombreFichero rutaFicheros

% Preparamos la informacion leida

XoIRed = XoI(:,espacioCcas);
YoIRed = YoI;

% Clasificador knn

% k=5;
% XTest = [0,0];
% 
% YTest = funcion_knn(XTest,XoIRed,YoIRed,k,'euclidea');
 
% Representamos muestras y plano del clasificador
nombresProblemaOIRed = [];
nombresProblemaOIRed.descriptores = nombresProblemaOI.descriptores(espacioCcas);
nombresProblemaOIRed.clases = nombresProblemaOI.clases;
nombresProblemaOIRed.simbolos = nombresProblemaOI.simbolos;
 
save('./DatosGenerados/Knn_circtriang_cuad.mat','espacioCcas','YoIRed','XoIRed','nombresProblemaOIRed')
