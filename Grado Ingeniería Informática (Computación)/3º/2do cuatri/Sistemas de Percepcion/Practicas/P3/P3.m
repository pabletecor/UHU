%%PRACTICA 3
% 
% 1.- Utilizando la función de Matlab subplot, muestre en una misma ventana tipo figure la
% imagen capturada y distintas imágenes que resalten, sobre la imagen original, aquellos píxeles
% cuya intensidad sea mayor que un determinado umbral (asigne distintos valores de umbral
% para generar las distintas imágenes). La intensidad de un píxel se calculará como la media de
% los niveles de gris de las componentes roja, verde y azul.


umbrales = [50 130 210];
color = [255,0,0];

I=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480

I.ReturnedColorSpace = 'rgb';

% CAPTURAMOS UNA IMAGEN 

preview(I)

Ic = getsnapshot(I); % captura la imagen que se está visualizando la cámara en el momento de la llamada

imshow(Ic);

R = double(Ic(:,:,1));
G = double(Ic(:,:,2));
B = double(Ic(:,:,3));

I = ( R+G+B ) /3 ; %Imagen intensidad

imshow(uint8(I));
%subplot representa varias imagenes en la misma "figure"
%subplot(nfilas,ncolumnas,orden)
close all;
subplot(2,2,1), imshow(uint8(I)), title('Imagen original');

for i = 1:length(umbrales)

    ROI = (I>umbrales(i));
    
    I2 = Funcion_visualiza(I,ROI,color);
    
    subplot(2,2,i+1), imshow(uint8(I2)), title(['Imagen con int > ' num2str(umbrales(i)) ]);
    
end

% 2.- Para cada una de las imágenes generadas en el apartado anterior, localice a través de su
% centroide los distintos “objetos” (agrupaciones de píxeles conexos) detectados. Visualice el
% centroide del objeto de mayor área en otro color para distinguirlo.

close all;
subplot(2,2,1), imshow(uint8(I)), title('Imagen original');

for i = 1:length(umbrales)

    ROI = (I>umbrales(i));
    
    %Localizamos los distintos objetos (agrupaciones conexas) de la M
    %binaria
    

    [Ietiq, N]=bwlabel(ROI);
    
    
    I2 = Funcion_visualiza(I,ROI,color);
    
    stats=regionprops(Ietiq,'Area','Centroid');

    areas=cat(1,stats.Area); % vector columna con las áreas de cada objeto
    centroides=cat(1,stats.Centroid); 
    
    [areas_ord, indices] = sort(areas,'descend');
    
    subplot(2,2,i+1), imshow(uint8(I2)), title(['Imagen con int > ' num2str(umbrales(i)) ])
    hold on, plot(centroides(:,1),centroides(:,2), '.g') , plot(centroides(indices(1),1), centroides(indices(1),2),'*b')
    hold off
    
end
 
% 3.- La escena inicialmente oscurecida y aclarándose progresivamente (utilizar la instrucción
% imadjust).


clear 
clc

gamma = 0:0.05:4;

video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480

video.ReturnedColorSpace = 'rgb';

% Para capturar una secuencia de frames:
video.TriggerRepeat=inf; % set(video,'TriggerRepeat',Inf);

video.FramesPerTrigger=1;

% número de disparos programados para el dispositivo.
video.FrameGrabInterval=2; % de todos los frames que se capturan, sólo se van grabando de 5 en 5.

start(video) % el dispositivo de video empieza a funcionar con la configuración almacenada en el objeto.

for i=1:length(gamma)
    
    I=getdata(video,1); % captura un frame guardado en memoria.
    I=imadjust(I ,[], [],gamma(i));

    imshow(I) 

end

stop(video)

% 4.- Todos los píxeles que tengan una intensidad mayor que un determinado umbral. Asignar
% inicialmente el valor 0 a este umbral e ir aumentándolo progresivamente. 


video.ReturnedColorSpace = 'grayscale';

video.FrameGrabInterval=1;

umbral = 0:255;

start(video)

for i =1:length(umbral)
   
    I = getdata(video,1);
    
    Ib = (I > umbral(i));
    
    imshow(Ib)
    
end

stop(video)

% 5.- Las diferencias que se producen entre los distintos frames que captura la webcam (utilizar
% la instrucción imabsdiff).
video.ReturnedColorSpace = 'grayscale';


% Para capturar una secuencia de frames:
video.TriggerRepeat=inf; 

video.FramesPerTrigger=1;

% número de disparos programados para el dispositivo.
video.FrameGrabInterval=3; 

start(video)

frame_ant = getdata(video,1); %Capturamos el primer frame

while (video.FramesAcquired<100)
   
    frame = getdata(video,1);
    Imag_dif = imabsdiff(frame_ant,frame);
    frame_ant = frame; %Guardamos el frame para hacer la diferencia con el sig
    imshow(Imag_dif);
    
end

stop(video)

% 6.- El movimiento más significativo a partir de diferencias de imágenes de intensidad.
close all


% Para capturar una secuencia de frames:
video.TriggerRepeat=inf; 

video.FramesPerTrigger=1;

% número de disparos programados para el dispositivo.
video.FrameGrabInterval=2; 

umbral = 100;

start(video)

frame_ant = getdata(video,1);

while (video.FramesAcquired<300)
   
    frame = getdata(video,1);
    Imag_dif = imabsdiff(frame_ant,frame);
    Mov_sig = (Imag_dif > umbral);
    
    frame_ant = frame;
    
    subplot(1,3,1) , imshow(frame);
    subplot(1,3,2) , imshow(Imag_dif);
    subplot(1,3,3) , imshow(Mov_sig);
    
end

stop(video)


% 7.- El seguimiento del movimiento del objeto mayor detectado en las diferencias
% significativas de imágenes de intensidad. El seguimiento debe visualizarse a través de un
% punto rojo situado en el centroide del objeto.

clear all
clc


video=videoinput('winvideo',1,'YUY2_640x480'); %El formato de mi webcam es YUY2_640x480

video.ReturnedColorSpace = 'grayscale';

% Para capturar una secuencia de frames:
video.TriggerRepeat=inf; 

video.FramesPerTrigger=1;

% número de disparos programados para el dispositivo.
video.FrameGrabInterval=2;

umbral = 100;

start (video)


frame_ant = getdata(video,1);

while (video.FramesAcquired<200)
   
    frame = getdata(video,1);
    Imag_dif = imabsdiff(frame_ant,frame);
    Mov_sig = (Imag_dif > umbral);
    
    [ Ietiq, N ] = bwlabel(Mov_sig);
    
     if N ~= 0
        
    stats=regionprops(Ietiq,'Area','Centroid');

    areas=cat(1,stats.Area); % vector columna con las áreas de cada objeto
    centroides=cat(1,stats.Centroid); 
    
    [areas_ord, indices] = sort(areas,'descend');
        
    x = centroides(indices(1),1);
    y = centroides(indices(1),2);
    
     else
         
     x = 1;
     y = 1;
     
     end
    
    frame_ant = frame;
    
    subplot(1,2,1) , imshow(frame); hold on, plot(x,y,'R*'),hold off;
    subplot(1,2,2) , imshow(Mov_sig); hold on, plot(x,y,'R*'),hold off;
  
    
end

stop(video)


%EJERCICIO EXTRA
clear

video = VideoReader('Ejemplo.avi');

NFrames = video.Duration*video.FrameRate;
NFilas = video.Height;
NColumnas = video.Width;

ValoresX = round((NColumnas-1) * rand(NFrames,1) +1);
ValoresY = round((NFilas-1) * rand(NFrames,1) +1);

aviobj = VideoWriter('EjemploCuadrado.avi');
aviobj.FrameRate = video.FrameRate;
open(aviobj);

for i=1:NFrames

    I = read(video,i);
    
    x = ValoresX(i,1);
    y = ValoresY(i,1);
    
    if (y>1 && y<NFilas) && (x>1 && x<NColumnas) 
       I(y-1:y+1,x-1:x+1,1) = 255;
       I(y-1:y+1,x-1:x+1,2) = 0;
       I(y-1:y+1,x-1:x+1,3) = 0;
    end
    
    writeVideo(aviobj,I)
    
end

close(aviobj);