% Leer imagenes
I=imread('X.jpg'); % Imagen en escala de grises - ver variable en workspace
Ic = imread('P1_1.jpg'); % Imagen en color

% Obtencion filas y columnas
[NumFilas NumColumnas NumComp] = size (I);
[NumFilas NumColumnas NumComp] = size (Ic);

% Visualizar variables
imshow(I),figure,imshow(Ic)
imtool(I),imtool(Ic) % inspect pixel values y coordenadas

% Acceder al valor de intensidad de coordenadas
I(3,5) % Fila 3, columna 5
Ic(3,5) % Comprobar que solo hay un valor, ¿que representa ese valor?

Ic(3,5,1) % componente roja
Ic(3,5,2) % componente verde
Ic(3,5,3) % componente azul

% Con imtool(I), obtenemos Pixel info: (141,200) 147
I(200,141)

% Modificar valores

% Poner rectangulo blanco en imagen intensidad
Imod = I;
Imod(50:150,200:300) = 255; 
imshow(I),figure,imshow(Imod)
close all;
% Poner rectangulo azul puro en imagen intensidad
Icmod = Ic;
Icmod(50:150,200:300,1) = 0;
Icmod(50:150,200:300,2) = 0;
Icmod(50:150,200:300,[1 2]) = 0; %Igual que el anterior
Icmod(50:150,200:300,3) = 255;
figure, imshow(Icmod);


% Poner rectangulo azul en imagen color
Ic_mod = Ic;
Ic_mod(100:150,200:400,1:2) = 0;
Ic_mod(100:150,200:400,3) = 255;
imshow(Ic),figure, imshow(Ic_mod)

% Seleccionar una componente de color
Ir = Ic(:,:,1);
Ig = Ic(:,:,2);
Ib = Ic(:,:,3);
imshow(Ic),figure,imshow(Ir),figure,imshow(Ig),figure,imshow(Ib)
close all;
%subplot representa varias imagenes en la misma "figure"
%subplot(nfilas,ncolumnas,orden)
subplot (1,3,1), imshow(Ir), subplot (1,3,2), imshow(Ig), subplot (1,3,3), imshow(Ib)
close all;

% Histogramas
imshow(I),figure,imhist(I)

[num_pix niveles_gris] = imhist(I);

% Accede al numero de pixeles que hay en I con un nivel de gris 128
num_pix(128+1)

% Representacion histograma con stem
stem (0:255, num_pix, '.r'), hold on, imhist(I)

% Operando con imagenes
I(200,300) % Valor 151

% Guarda en una variable el valor de intensidad de ese pixel +10
A = I(200,300) + 10

% Guarda en otra variable el valor de intensidad de ese pixel +105
B = I(200,300) + 105

% ¿Que observas?

%'B es 255 por las limitaciones de uint8'

% IMPORTANTE: ANTES DE OPERAR CON VALORES DE IMAGENES HAY QUE HACER UNA
% CONVERSION DEL FORMATO DE LOS DATOS - DE UINT8 A DOUBLE

I = double (I);
B = I(200,300) + 105

% EJEMPLO BASICO DE PROCESAMIENTO: SEGMENTAR TODOS LOS PIXELES
% PERTENECIENTES A LETRAS X EN IMAGEN I

[N M] = size(I);

Ibin = zeros(N,M);

for i=1:N
    for j=1:M
        if I(i,j) < 100
            Ibin(i,j) = 1;
        end
    end
end

% PROGRAMACION MAS EFICAZ: 
Ibin = I < 100; % datos tipo logico
Ibin = double(I<100); % para tenerlos tipo double como antes

% Visualiza la informacion
imshow(uint8(Ibin));

imshow(uint8(255*Ibin));

% GUARDAR LA INFORMACION
save ImagenSegmentada I Ibin
imwrite(uint8(255*Ibin),'ImagenBinaria.tif');





