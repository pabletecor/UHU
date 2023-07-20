%%Previa Practica 3

% Funci�n que devuelve una estructura con informaci�n del hardware de adquisici�n de im�genes
% disponible, incluyendo los adaptadores de video instalados
datos = imaqhwinfo

% Funci�n que devuelve una estructura con informaci�n del dispositivo de video instalado
datos=imaqhwinfo('winvideo')

% Funci�n para crear el objeto de video que contiene la configuraci�n del
% dispositivo de adquisici�n de im�genes (WebCam, c�mara...) y
% con el que Matlab se comunicar� con
% el dispositivo de adquisici�n de im�genes (Webcam, c�mara,...)

%video=videoinput('winvideo',1,'RGB24_352x288'); 

video=videoinput('winvideo',1);

% Hay c�maras que no ofrecen modelo RGB de salida, sino que ofrecen modelos
% de color basados en luminancia y dos componentes crom�ticas YCbCr,
% YUY,...
% Hay que aplicar alguna funci�n MATLAB que transforme el modelo de color a RGB
% Esta funci�n es: ycbcr2rgb.m para modelos YCbCr.

video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480
preview(video)
I = getsnapshot(video);
image(I)
Imod=ycbcr2rgb(I);
imshow(Imod)

% Otra opci�n es editar el objeto video y seleccionar el modelo de color de
% salida de la imagen - En ReturnedColorSpace
% De esta forma, se hace la conversi�n de forma autom�tica, sin necesidad
% de aplicar ninguna funci�n.

video.ReturnedColorSpace = 'rgb'

% Para acceder a la informaci�n de este objeto Matlab:

get(video)

% Para capturar una imagen independiente (no afecta el n�mero de disparos y
% frames por disparos):

preview(video)
% se abre una pantalla gr�fica que muestra lo que visualiza la c�mara

I = getsnapshot(video);
% captura la imagen que se est� visualizando la c�mara
% en el momento de la llamada
% Antes de capturar hay que previsualizar (si no se captura una imagen en
% negro)
imtool(I) % para mostrar la imagen por imtool


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% VIDEO: Adquisici�n de im�genes, frames, continuada. Par�metros de inter�s
video.TriggerRepeat=3; % set(video,'TriggerRepeat',Inf);
% n�mero de disparos adicionales programados para el dispositivo.
% si tiene un valor 3, se ejecutan 4 disparos
% video.TriggerRepeat=inf; % set(video,'TriggerRepeat',Inf); Con esta
% configuraci�n, infinitos disparos

video.FramesPerTrigger=3;
% N�mero de im�genes o frames que se capturan por disparo

video.FrameGrabInterval=3;
% Respecto a los frames que la camara puede adquirir a su m�xima velocidad
% de captura (t�picamente 30 fps), se almacenan en memoria el primero de cada tres hasta
% un total de (N�mero de disparos * Frames por Disparos).
% Este param�tro es importante porque determina los frames por segundo fps
% a la que se graba en memoria (para que un video se pueda ver de forma
% aceptable se deben mostrar al menos 1 fps).
% Si por ejemplo el dispositivo de video es capaz de capturar 4 fps
% y se fija el FrameGrabInterval a 2,
% las im�genenes se han grabado en memoria con una tasa de 2fps.

% LoggingMode = memory;
% el almacenamiento de los frames es en memoria -tambi�n puede ser en disco

% TriggerType = immediate
% El n�mero de disparos programado es inmediato, uno detr�s de otro
% La otra opci�n es disparar de forma manual, si lo permite el dispositivo.

% Video.FramesAcquired
% En esta variable se almacena el n�mero de Frames que se han adquirido
% con getdata. La instrucci�n getdata permite guardar como variable matlab
% uno o varios frames guardados en memoria.

% Para cambiar estos par�metros, puede hacerse como se ha mostrado o
% haciendo doble click en el objeto video en el Workspace y modificar las
% opciones.
% Para comenzar a capturar una secuencia de frames:

start(video) % el dispositivo de video empieza a funcionar con la
% configuraci�n almacenada en el objeto.
% Si el disparo es inmediato y el n�mero de disparos infinito, est�
% continuamente capturando fotos hasta que se llame la funci�n stop(video).
% No todos los frames se guardan en memoria
% - s�lo los que indica video.FrameGrabInterval

stop(video) % para detener una adquisici�n de video

% CREAR UNA SECUENCIA DE 50 FRAMES Y MOSTRARLA:

% SEGUNDA OPCI�N QUE ES LA QUE SE UTILIZA:
% Se programan infinitos disparos y el video termina cuando se han
% adquirido de la memoria un n�mero determinado de frames
video.TriggerRepeat=inf; % disparos continuados
video.FramesPerTrigger=1;
% N�mero de im�genes o frames que se capturan por disparo

video.FrameGrabInterval=5; % Hacer para un valor 10 y 20;

start(video)
numero=0;
contador=[];

while (video.FramesAcquired<50)
    
I=getdata(video,1); % captura un frame guardado en memoria. A medida que se va llamando
% a esta funci�n se van capturando los frames en el mismo orden cronol�gico en que fueron guardados

% Ver la ayuda de esta funci�n: admite guardar simult�nemente un n�mero mayor de frames, en cuyo
% caso se almacena en I un vector de frames.

numero = numero+1;
contador= [contador; video.FramesAcquired video.FramesAvailable numero ];

imshow(255-I) % para ir mostrando la secuencia de frames - en este caso se muestra la imagen complemtaria

end

stop(video)

% Para ver el reporte de los frames que se han capturado con getdata
% y los que quedan por capturar guardados en memoria:

video

% SELECCI�N DE video.FrameGrabInterval
% La funci�n getdata permite guardar informaci�n temporal
% de cuando se han tomado los frames. Esto es importante porque, fijando
% video.FrameGrabInterval a 1 (es decir se guardan los frames a la m�xima
% velocidad de captura en memoria), permite tener una idea de los fps
% que nuestro dispositivo de video es capaz de capturar.
% En base a ello podemos fijar
% el n�mero de frames por segundo que queremos que
% se graben en memoria a trav�s del par�metro video.FrameGrabInterval, para
% que la secuencia de video registrada se visualice con un m�nimo de 1fps.

% Un Ejemplo ser�a:

video.FrameGrabInterval=1; %Captura 15 fps
video.FrameGrabInterval=3; %Captura 5 fps (15/3 = 5)

start(video)
TIEMPO=[];

while (video.FramesAcquired<100)
 % Como ahora se graban todos los frames a la velocidad de captura de la
 % c�mara, varios frames por segundo,
 % para que la secuencia dure un poco m�s de tiempo hay que programar un
 % mayor n�mero de frames adquiridos con getdata
[I TIME]=getdata(video,1);
TIEMPO=[TIEMPO ; TIME];
gamma=1.5;
I=imadjust(I,[],[],gamma);
imshow(I) % para ir mostrando la secuencia de frames - en este caso se muestra una secuencia m�s "clara"
end

stop(video)

video

% En este ejemplo se ha guardado en la variable TIEMPO los instantes de
% tiempo, contados desde el primer disparo, en los que se
% capturan los frames que se graban