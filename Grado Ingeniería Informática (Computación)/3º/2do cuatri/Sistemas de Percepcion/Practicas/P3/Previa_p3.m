%%Previa Practica 3

% Función que devuelve una estructura con información del hardware de adquisición de imágenes
% disponible, incluyendo los adaptadores de video instalados
datos = imaqhwinfo

% Función que devuelve una estructura con información del dispositivo de video instalado
datos=imaqhwinfo('winvideo')

% Función para crear el objeto de video que contiene la configuración del
% dispositivo de adquisición de imágenes (WebCam, cámara...) y
% con el que Matlab se comunicará con
% el dispositivo de adquisición de imágenes (Webcam, cámara,...)

%video=videoinput('winvideo',1,'RGB24_352x288'); 

video=videoinput('winvideo',1);

% Hay cámaras que no ofrecen modelo RGB de salida, sino que ofrecen modelos
% de color basados en luminancia y dos componentes cromáticas YCbCr,
% YUY,...
% Hay que aplicar alguna función MATLAB que transforme el modelo de color a RGB
% Esta función es: ycbcr2rgb.m para modelos YCbCr.

video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480
preview(video)
I = getsnapshot(video);
image(I)
Imod=ycbcr2rgb(I);
imshow(Imod)

% Otra opción es editar el objeto video y seleccionar el modelo de color de
% salida de la imagen - En ReturnedColorSpace
% De esta forma, se hace la conversión de forma automática, sin necesidad
% de aplicar ninguna función.

video.ReturnedColorSpace = 'rgb'

% Para acceder a la información de este objeto Matlab:

get(video)

% Para capturar una imagen independiente (no afecta el número de disparos y
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
video.TriggerRepeat=3; % set(video,'TriggerRepeat',Inf);
% número de disparos adicionales programados para el dispositivo.
% si tiene un valor 3, se ejecutan 4 disparos
% video.TriggerRepeat=inf; % set(video,'TriggerRepeat',Inf); Con esta
% configuración, infinitos disparos

video.FramesPerTrigger=3;
% Número de imágenes o frames que se capturan por disparo

video.FrameGrabInterval=3;
% Respecto a los frames que la camara puede adquirir a su máxima velocidad
% de captura (típicamente 30 fps), se almacenan en memoria el primero de cada tres hasta
% un total de (Número de disparos * Frames por Disparos).
% Este paramétro es importante porque determina los frames por segundo fps
% a la que se graba en memoria (para que un video se pueda ver de forma
% aceptable se deben mostrar al menos 1 fps).
% Si por ejemplo el dispositivo de video es capaz de capturar 4 fps
% y se fija el FrameGrabInterval a 2,
% las imágenenes se han grabado en memoria con una tasa de 2fps.

% LoggingMode = memory;
% el almacenamiento de los frames es en memoria -también puede ser en disco

% TriggerType = immediate
% El número de disparos programado es inmediato, uno detrás de otro
% La otra opción es disparar de forma manual, si lo permite el dispositivo.

% Video.FramesAcquired
% En esta variable se almacena el número de Frames que se han adquirido
% con getdata. La instrucción getdata permite guardar como variable matlab
% uno o varios frames guardados en memoria.

% Para cambiar estos parámetros, puede hacerse como se ha mostrado o
% haciendo doble click en el objeto video en el Workspace y modificar las
% opciones.
% Para comenzar a capturar una secuencia de frames:

start(video) % el dispositivo de video empieza a funcionar con la
% configuración almacenada en el objeto.
% Si el disparo es inmediato y el número de disparos infinito, está
% continuamente capturando fotos hasta que se llame la función stop(video).
% No todos los frames se guardan en memoria
% - sólo los que indica video.FrameGrabInterval

stop(video) % para detener una adquisición de video

% CREAR UNA SECUENCIA DE 50 FRAMES Y MOSTRARLA:

% SEGUNDA OPCIÓN QUE ES LA QUE SE UTILIZA:
% Se programan infinitos disparos y el video termina cuando se han
% adquirido de la memoria un número determinado de frames
video.TriggerRepeat=inf; % disparos continuados
video.FramesPerTrigger=1;
% Número de imágenes o frames que se capturan por disparo

video.FrameGrabInterval=5; % Hacer para un valor 10 y 20;

start(video)
numero=0;
contador=[];

while (video.FramesAcquired<50)
    
I=getdata(video,1); % captura un frame guardado en memoria. A medida que se va llamando
% a esta función se van capturando los frames en el mismo orden cronológico en que fueron guardados

% Ver la ayuda de esta función: admite guardar simultánemente un número mayor de frames, en cuyo
% caso se almacena en I un vector de frames.

numero = numero+1;
contador= [contador; video.FramesAcquired video.FramesAvailable numero ];

imshow(255-I) % para ir mostrando la secuencia de frames - en este caso se muestra la imagen complemtaria

end

stop(video)

% Para ver el reporte de los frames que se han capturado con getdata
% y los que quedan por capturar guardados en memoria:

video

% SELECCIÓN DE video.FrameGrabInterval
% La función getdata permite guardar información temporal
% de cuando se han tomado los frames. Esto es importante porque, fijando
% video.FrameGrabInterval a 1 (es decir se guardan los frames a la máxima
% velocidad de captura en memoria), permite tener una idea de los fps
% que nuestro dispositivo de video es capaz de capturar.
% En base a ello podemos fijar
% el número de frames por segundo que queremos que
% se graben en memoria a través del parámetro video.FrameGrabInterval, para
% que la secuencia de video registrada se visualice con un mínimo de 1fps.

% Un Ejemplo sería:

video.FrameGrabInterval=1; %Captura 15 fps
video.FrameGrabInterval=3; %Captura 5 fps (15/3 = 5)

start(video)
TIEMPO=[];

while (video.FramesAcquired<100)
 % Como ahora se graban todos los frames a la velocidad de captura de la
 % cámara, varios frames por segundo,
 % para que la secuencia dure un poco más de tiempo hay que programar un
 % mayor número de frames adquiridos con getdata
[I TIME]=getdata(video,1);
TIEMPO=[TIEMPO ; TIME];
gamma=1.5;
I=imadjust(I,[],[],gamma);
imshow(I) % para ir mostrando la secuencia de frames - en este caso se muestra una secuencia más "clara"
end

stop(video)

video

% En este ejemplo se ha guardado en la variable TIEMPO los instantes de
% tiempo, contados desde el primer disparo, en los que se
% capturan los frames que se graban