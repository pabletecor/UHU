TUTORIAL MATLAB

%% ORGANIZACION: CURRENT DIRECTORY, COMMAND WINDOW, WORKSPACE, COMMAND
% HISTORY

% Ejemplo Suma y resta de dos numeros
a =1  
A = 2
suma = a+A, resta = a-A; % Realiza la suma y la resta

% OBSERVACIONES:
% el carácter “,” para separar comandos
% el símbolo “%” para introducir comentarios en el código
% % si no ponemos “;” el resultado se muestra en pantalla


%% Workspace
whos A  % para ver el tipo de variable
% double ; real de punto flotante con 8 bytes
% empleado por Matlab por defecto en la definición de variables

save variables A a suma resta  
% Guarda las variables en fichero matlab variables.mat

clear  % elimina las variables del workspace

load variables.mat  
% carga en el Workspace las variables almacenadas en el fichero .mat

clc % borra pantalla del command window.
% Ver ayuda matlab para todas las posibilidades


%% Ayuda matlab
help save  % te ofrece ayuda respecto la función  por el command window
doc load % abre la ayuda Matlab para dar la información
type imfilter % edita el contenido de la función por command window
edit imfilter % abre fichero .m con la función


%% DEFINICIÓN DE MATRICES
a = 1
whos a

% []: Para concatenar informacion
% Definición con corchetes 
% Los corchetes se usan para concatenar informacion
% Si la información va separada por espacios o comas, se añade horizontalmente
% Si va seperada por "puntos y coma" se añade verticalmente

b = [2 2], whos b
b = [2; 2], whos b
b = [2, 2]

c = [a b 3] 
whos c

d = [2; 2]
whos d

e = [a; d]
whos e

f = [e; d; 3]
whos f

% Operador Transpononer
cTras = c';

% Definicion de matrices
g = [1 2 3; 4 5 6]
whos g

% Añade a g una última columana con valores 7 y 8


h = [g [7;8]]

% Añade a h una última fila con los valores del vector c


i = [h; c]
whos i

% Operador : para la definición de vectores filas con los valores indicados
ValorInicio = 1; ValorFinal = 6; Paso = 1;
A = ValorInicio:Paso:ValorFinal
A = 1:6

B = 1:2:5
C = 5:-1:1

% Inicializacion de matrices
A = []
B = ones(2) %2x2
C = ones(2,3)
D = zeros(3,1)

E = eye(4) % matriz identidad 4x4

F = rand(4,3) % matriz 4x3 de números distribución probabilidad uniforme entre 0-1

G = randn(100,1)  % vector columna de 100 elementos
% generados según distribución gaussiana de media cero y desviación típica
% 1



%% ACCESO A LOS ELEMENTOS DE UNA MATRIZ: con paréntesis
clear
a = [1 4 6 9 -1 ; 2 4 9 11 5 ; 1 3 5 7 2 ; 3 -2 4 7 2]
a11 = a(1,1)
a23 = a(2,3)
UltimoElementoSegundaFila = a(2,end)
UltimoElementoTerceraColumna = a(end,3)

% : hace referencia a todos los elementos (de una fila, de una columna, de
% una matriz)

SegundafilaCompleta = a(2,:);
UltimaColumnaCompleta = a(:,end)

% Para acceder a todos los elementos de una matriz y almacenarlos en un
% vector columna
b = a(:)


%% OPERACIONES CON ELEMENTOS Y MATRICES

clc, clear
A = [1 4 6 9 -1 ; 2 4 9 11 5 ; 1 3 5 7 2 ; 3 -2 4 7 2]

% Cuatro Primeros valores de la segunda fila
f = 2; k = 4; A(2,1:4)

% Intercambiar las dos primeras filas
A = A([2,1,3,4],:)

% Guardar en B, la segunda fila
B = A(2,:)

% Eliminar de A la segunda fila
A(2,:) = []

% Añadir el contenido de B a A
A = [A ; B]

% Añadir una nueva columna a A con valores 5.5, 10.5 y 15.5
B = [5; 10; 15];
A = [A B]


% Poner el contenido de las tres últimas columnas, de abajo de las tres primeras
A1 = A(:,1:3)
A2 = A(:,4:end)
A = [A1; A2]


% Sea a = [1 4 6 9 -1 ; 2 4 9 11 5 ; 1 3 5 7 2 ; 3 -2 4 7 2]
% Accede a los dos primeros elementos de la tercera columna

% Accede a al primer y tercer elemento de la segunda fila    
a(2,[1,3])
% Accede a los elementos 1, 2 y 4 de la última fila      
a(end, [1,2,4])
% Accede a la matriz 3x3 centrada en el elemento de la fila 2, columna 3
f=2,c=3
a(f-1:f+1,c-1:c+1)
% Elimina la segunda columna de a

% Intercambia segunda y tercera columna de a



%% OPERACIONES CON ELEMENTOS Y MATRICES

clear
a = [1 4 6 9 -1 ; 2 4 9 11 5 ; 1 3 5 7 2 ; 3 -2 4 7 2]
f = 2; c = 3
[a(1:3,2:4) a(f-1:f+1,c-1:c+1)] ; [a(1:3,2:4) ; a(f-1:f+1,c-1:c+1)] 

A = rand(2)
B = randn(2)

[A B] ; [A;B], C = [A B; A-1 B+1]

clear, clc
% Operaciones con constantes
A = 3*ones(3);
A = A+1
B = 2*eye(3);
C = A + B
D = C/2
D = (C/2)*3
D = A^2

% Operaciones entre matrices
A = [1 0 5]; B = [2 4 1];

A*B  % PRODUCTO MATRICIAL
A, B'
PMatric = A*B'  
PPunto_a_Punto = A.^B

% Ejemplos
A = [4 2; 8 4] ; B = [1 2; 4 2]; [A B]
P = A*B
P = A.*B % MULTIPLICACION ELEMENTO A ELEMENTO
A^2
A.^B

A+B, A-B, A*B, A.*B, A/B, A*inv(B), A./B, A^2 


%% TIPOS DE DATOS
% ? logical ; un bit
% ? int8 ; entero con signo de 8 bits
% ? int16 ; entero con signo de 16 bits
% ? int32 ; entero con signo de 32 bits
% ? int64 ; entero con signo de 64 bits
% ? uint8 ; entero sin signo de 8 bits
% ? uint16 ; entero sin signo de 16 bits
% ? uint32 ; entero sin signo de 32 bits
% ? uint64 ; entero sin signo de 64 bits
% ? single ; real de punto flotante con 4 bytes
% ? double ; real de punto flotante




%% DEFINICION DE VARIABLES TIPO CARÁCTER Y CADENAS DE TEXTO

A = 'Hola'
PrimerCaracter = A(1);

B = 'Como estas';

C = [A; B]

C = [A ; B(1: length(A))]

% Para crear variables tipo celdas {}
   
C = char([]);
C{1} = A;
C{2} = B;
C  % Crea la variable tipo celda en fila

% Si se quiere tener el contenido en columna
C = char([]);
C{1,1} = A;
C{2,1} = B;

% Acceso al contenido
C(1)  % Variable tipo celda con el contenido de la primera celda
C{1}  % Variable tipo carácter con el contenido de la primera celda

%% DEFINICIÓN VARIABLES TIPO ESTRUCTURAS
Campos = [];
Campos.opciones = [];
Campos.valores = [];
Campos.opciones{1} = 'Escalado';
Campos.opciones{2} = 'Normalizacion';
Campos.valores{1} = true;
Campos.valores{2} = [3 2];

Campo.opciones(1)  % tipo cell - así se declaró opciones. Accede a la primer celda
Campo.opciones{1}  % tipo del contenido de la celda. Accede al contenido
Campos.valores(1)
Campos.valores{1}
%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%% EJEMPLO REPRESENTACIONES CON PLOT
figure
figure('Name', 'Esta es la Figura Numero 2')
figure(4), set(4, 'Name', 'Esta es la figura 4')

close all

% REPRESENTAR UN SENO ENTRE 0 Y 2PI

 x=0:pi/100:2*pi;
 y=sin(x);
 
 figure % crea una ventana gráfica y es la actual
 plot(x,y); % dibuja gráfico en ventana actual (y frente a x)
 
 xlabel('Grados en rad') % texto para el eje x
 ylabel('Seno') % texto para el eje y
 title ('Ejemplo') % texto para el título
 XMIN = 0; XMAX = 7; YMIN = -1.2; YMAX = 1.2;
 axis([XMIN XMAX YMIN YMAX])
 
 
 % REPRESENTAR EN EL MISMO GRÁFICO DOS SENOS DESPLAZADOS DEL ANTERIOR
 
 y1=sin(x-0.25); % otra función
 y2=sin(x-0.5); % otra función
 hold on % mantiene la ventana anterior para que se dibuje en ella el próximo gráfico sin borrar dicha ventana
 plot(x,y1,'.r', x,y2,'g') % dos curvas
 legend('sen(x)','sen(x-0.25)','sen(x-0.5)') % leyendas
 grid on % para dibujar
 
 
 % EJEMPLO DE REPRESENTACIÓN EN 3D
 x = -4*pi:0.1:4*pi;
 figure,
 plot3(sin(x),cos(x),x)
 
 
 %% FAMILIARIZARSE CON ALGUNAS FUNCIONES MATLAB GENÉRICAS

%  addpath  
%  ceil, floor, round
%  sum, max, min, mean, std, median
%  length, size
%  abs -> valor absoluto

% Ejercicio: Crea una matriz 2x3 y calcula la suma, valor maximo, mnimio, media, desviacion típica y mediana con las
% funciones matlab sum, max, min, mean, std, median

 
 % FAMILIARIZARE CON OPERADORES RELACIONALES, LÓGICOS Y SENTENCIAS DE
 % CONTROL DE FLUJO
% eq - Equal = =
% ne - Not equal ~ =
% lt - Less than <
% gt - Greater than >
% le - Less than or equal < =
% ge - Greater than or equal > =
 
 % FAMILIARIZARE CON OPERADORES LÓGICOS 
% and - Logical AND &
% or - Logical OR |
% not - Logical NOT ~
% xor - Logical EXCLUSIVE OR
% any - True if any element of vector is nonzero
% all - True if all elements of vector are nonzero


 % FAMILIARIZARE CON SENTENCIAS DE CONTROL DE FLUJO
% Sentencia if
% if <condición>
% <sentencia>
% elseif <condición>
% <sentencia>
% else <sentencia>
% end


% Sentencia switch
% switch <expresion>
% case 0 …
% case 1 …
% …
% otherwise …
% end



% Sentencia for
% for i = 1 : 2 :10
% …
% end



% Sentencia while
% while <condición>
% …
% end



% Sentencias continue y break
% Para modificar el flujo en bucles
% continue: para saltar al siguiente ciclo del bucle
% break: para salir del bucle
 


%% SCRIPTS Y FUNCIONES

% CREAR FUNCION SUMA DE TRES ELEMENTOS, TIPO UINT8, Y COMPROBAR SU
% FUNCIONAMIENTO. GUARDA LA FUNCION EN UN DIRECTORIO LLAMADO FuncAux, y
% añade el path de este directorio para llamarla

addpath('FuncAux')
A = funcion_suma(2,3,4)

A = funcion_suma(200,300,400)




%%%%%%%%%%%%%%%%%%%% OPERACIONES CON IMAGENES %%%%%%%%%%%%%%%%

%Lee la imagen y la asocia a la variable x
x =  imread('X.jpg');

%Muestra la imagen
imshow(Ietiq);

% Figure abre una nueva ventana para mostrar otra imagen.
%imtool muestra la imagen con mas datos (coordenadas...)
figure, imtool(x);

%subplot representa varias imagenes en la misma "figure"
%subplot(nfilas,ncolumnas,orden)
subplot (1,3,1), imshow(Ir), subplot (1,3,2), imshow(Ig), subplot (1,3,3), imshow(Ib)
close all; 


% Histogramas. Muestra un histograma con los valores de los pixeles de la imagen.
imhist(x);


%bwlabel. Dada una imagenbinaria, etiqueta los diferentes objetos que en esta aparecen.
[Ietiq N]=bwlabel(Ib); %Ib = matriz binaria ;Ietiq = matriz etiquetada


%regionprops. Devuelve información de diferentes atributos de la imagen binaria etiquetada
stats=regionprops(Ietiq,'Area','Centroid');

 % stats.Area % contiene información de las áreas de cada objeto
 % stats.Centroid - contiene información de la coordenada x (columna)
 % y la coordenada y (fila) del centroide de cada objeto 

areas=cat(1,stats.Area); % vector columna con las áreas de cada objeto
centroides=cat(1,stats.Centroid); % matriz de dos columnas (x,y) y
% tantas filas como objetos etiquetados distintos de cero haya


%bwareaopen. se eliminan de la MatrizBinaria todos las agrupaciones de píxeles a “1” compuestas por menos de NumPix pixeles.
MatrizBinaria_ObjetosGrandes=bwareaopen(MatrizBinaria,NumPix); 



%%%%%%%%%%%%%%%%%%% ADQUISICIÓN DE IMAGENES %%%%%%%%%%%%%%%%%



% Función que devuelve una estructura con información del hardware de adquisición de imágenes
% disponible, incluyendo los adaptadores de video instalados
datos = imaqhwinfo

% Función que devuelve una estructura con información del dispositivo de video instalado
datos=imaqhwinfo('winvideo')


% Hay cámaras que no ofrecen modelo RGB de salida, sino que ofrecen modelos
% de color basados en luminancia y dos componentes cromáticas YCbCr,
% YUY,...

video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480

% Otra opción es editar el objeto video y seleccionar el modelo de color de
% salida de la imagen - En ReturnedColorSpace
% De esta forma, se hace la conversión de forma automática, sin necesidad
% de aplicar ninguna función.

video.ReturnedColorSpace = 'rgb' %Puede ser tambien grayspace...


% capturar una imagen independiente (no afecta el número de disparos y
% frames por disparos):

preview(video)
% se abre una pantalla gráfica que muestra lo que visualiza la cámara

I = getsnapshot(video);
% captura la imagen que se está visualizando la cámara
% en el momento de la llamada
% Antes de capturar hay que previsualizar (si no se captura una imagen en
% negro)
imtool(I) % para mostrar la imagen por imtool


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% VIDEO: Adquisición de imágenes, frames, continuada. Parámetros de interés

% SEGUNDA OPCIÓN QUE ES LA QUE SE UTILIZA:
% Se programan infinitos disparos y el video termina cuando se han
% adquirido de la memoria un número determinado de frames

%%COMPROBAMOS FPS DE NUESTRA WEBCAM
%Lo haremos con preview

video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480

video.ReturnedColorSpace = 'rgb';

preview(video)

%Trabaja aproximadamente a 15fps

%%TOMAMOS EL VIDEO

%Queremos un video de 30 seg a 10 fps
video.TriggerRepeat=inf; % disparos continuados
video.FrameGrabInterval=1.5; %15 fps/1.5= 10 fps 
%El FrameGrabInternal se redondea a 2, pero luego el video esta a 10 fps


aviobj = VideoWriter('NombreVideo.avi', 'Uncompressed AVI'); % Crear objeto archivo avi
aviobj.FrameRate = 10; % El video sera a 10 fps

open(aviobj)

start(video)

while (video.FramesAcquired<600) % Video de 30s aprox
    
I=getdata(video,1); % captura un frame guardado en memoria.
imshow(I)
%writeVideo(aviobj,getframe); % Lo que se muestra en la
% ventana de tipo figure, se convierte en frame y se añade al video

%aviobj = addframe(aviobj,I); % Se añade directamente el frame
writeVideo(aviobj,I);

end

stop(video)

close(aviobj);



%%%% PARA LEER ARCHIVOS DE VIDEO YA CREADOS
clear
videoLeido = VideoReader('NombreVideo.avi');
get(videoLeido)
NumeroFrames = videoLeido.NumberOfFrames;
NumFilasFrame = videoLeido.Height;
NumeroColumnasFrame = videoLeido.Width;
FPS = videoLeido.FrameRate;

Numero_de_Frame = 10;
I = read(videoLeido,Numero_de_Frame); % Lectura Décimo Frame


%%%% Generacion de un nuevo video a partir del archivo de video leido
aviobj = VideoWriter('VideoProc.avi', 'Uncompressed AVI');
aviobj.FrameRate = FPS; % El video tendra la misma tasa de frames
open(aviobj)

for i=1:NumeroFrames
I = read(videoLeido,i);
writeVideo(aviobj, 255-I);
end

close(aviobj);


%%Representación de superficie esférica centrada en (Rc, Gc, Bc) de radio Radio
[R,G,B] = sphere(100);
% Matrices de puntos de una esfera centrada en el origen de radio
unidad
x = Radio*R(:)+Rc; y = Radio*G(:)+Gc; z = Radio*B(:)+Bc;
plot3(x,y,z, '.b')


%%Calculo de distancias de un punto a una nube de puntos de forma matricial

%Sea P el centroide y NP los puntos de X de los que queremos calcular la distancia

%IMPORTANTE TRASPONEMOS LOS VALORES

%1 - Usamos la funcion repmat para repetir el vector P tantas veces como muestras 
%haya en la nube de puntos

Pamp = repmat(P,1,size(NP,2))  %Repite P en 1 fila y size(NP,2) (numero columnas NP) columnas

%2 - Calculamos la distancia de forma matricial

vectorDistancia = sqrt( sum((Pamp-NP.^2)))

