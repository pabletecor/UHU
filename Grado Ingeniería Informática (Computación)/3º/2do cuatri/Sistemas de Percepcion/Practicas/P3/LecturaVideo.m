% VELOCIDAD DE FRAME DE LA CAMARA
video=videoinput('winvideo',1,'YUY2_320x240'); %
video.TriggerRepeat=inf; % disparos continuados
video.FrameGrabInterval=1;
start(video)
TIEMPO=[];

while (video.FramesAcquired<150)
[I TIME METADATA]=getdata(video,1);
TIEMPO=[TIEMPO ; TIME METADATA.AbsTime];
imshow(I)
end

stop(video)
video
% Camara trabajando a unos 28-30 fps


% EJEMPLO DE ALMACENAR LA SECUENCIA DE VIDEO PROCESADA EN UN ARCHIVO AVI
clear
video=videoinput('winvideo',1,'YUY2_640x480'); %
video.ReturnedColorSpace = 'grayscale';
video.TriggerRepeat=inf; % disparos continuados
video.FrameGrabInterval=3; %30 fps/3= 10 fps

set(video, 'LoggingMode', 'memory') %No hace falta ponerlo, por defecto guarda en memoria

aviobj = VideoWriter('Ejemplo.avi', 'Uncompressed AVI'); % Crear objeto archivo avi
aviobj.FrameRate = 10; % El video sera a 10 fps

aviobj %Vemos todas las propiedades del objeto de video aviobj 

open(aviobj)

start(video)

while (video.FramesAcquired<100) % Video de 10s
    
I=getdata(video,1); % captura un frame guardado en memoria.
imshow(255-I)
%writeVideo(aviobj,getframe); % Lo que se muestra en la
% ventana de tipo figure, se convierte en frame y se añade al video

%aviobj = addframe(aviobj,I); % Se añade directamente el frame
writeVideo(aviobj,255-I);
end

stop(video)

close(aviobj);


% EJEMPLO DE ALMACENAR LA SECUENCIA DE VIDEO GENERADA Y PROCESADA EN ARCHIVOS AVI

clear

video=videoinput('winvideo',1,'YUY2_640x480'); 
video.ReturnedColorSpace = 'grayscale';
video.TriggerRepeat=inf; % disparos continuados
video.FrameGrabInterval=3;

set(video, 'LoggingMode', 'disk&memory')

aviobjI = VideoWriter('SecuenciaEntrada.avi', 'Uncompressed AVI');
aviobjI.FrameRate = 10; % El video sera a 10 fps

video.DiskLogger = aviobjI;

open(aviobjI)

aviobjO = VideoWriter('SecuenciaSalida.avi', 'Uncompressed AVI');
aviobjO.FrameRate = 10; % El video sera a 10 fps
open(aviobjO)


start(video)

while (video.FramesAcquired<50) % Video de 5s
    
I=getdata(video,1); % captura un frame guardado en memoria.

imshow((255-I))

% writeVideo(aviobjO,getframe);

writeVideo(aviobjO,255-I);

end

stop(video)
close(aviobjO);
close(aviobjI);



% EJEMPLO: MOSTRAR UN PUNTO DE FORMA ALEATORIA EN LA IMAGEN
clear;

video=videoinput('winvideo',1,'YUY2_320x240'); %
set(video, 'LoggingMode', 'memory')
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat=inf; % disparos continuados
video.FrameGrabInterval=3;
Resolucion = video.videoResolution;
NumFilas = Resolucion(2);
NumColumnas = Resolucion(1);

aviobj = VideoWriter('Cuadrado.avi', 'Uncompressed AVI');
aviobj.FrameRate = 10; % El video sera a 10 fps
open(aviobj)

Valores=rand(100,1); % Sabemos que se van a analizar 100 frames

start(video)

while (video.FramesAcquired<100) % 10s de video

I=getdata(video,1); % captura un frame guardado en memoria.
Valor = Valores(video.FramesAcquired);
x = round(NumColumnas*Valor); y = round(NumFilas*Valor);

imshow(I), hold on, plot(x,y,'*r');

if (y>2 && y<NumFilas-1) && (x>2 && x<NumColumnas-1)
I(y-2:y+2, x-2:x+2,:) = 0;
end

% writeVideo(aviobjColor,getframe);
writeVideo(aviobj, I);
end

stop(video)
close(aviobj);


%%%% PARA LEER ARCHIVOS DE VIDEO YA CREADOS
clear
videoLeido = VideoReader('Ejemplo.avi');
get(videoLeido)
NumeroFrames = videoLeido.NumberOfFrames;
NumFilasFrame = videoLeido.Height;
NumeroColumnasFrame = videoLeido.Width;
FPS = videoLeido.FrameRate;

Numero_de_Frame = 10;
I = read(videoLeido,Numero_de_Frame); % Lectura Décimo Frame


%%%% Generacion de un nuevo video a partir del archivo de video leido
aviobj = VideoWriter('EjemploProc.avi', 'Uncompressed AVI');
aviobj.FrameRate = FPS; % El video tendra la misma tasa de frames
open(aviobj)

for i=1:NumeroFrames
I = read(videoLeido,i);
writeVideo(aviobj, 255-I);
end

close(aviobj);