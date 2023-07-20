function [g_MinEntreMax, gmax1,gmax2] = Funcion_MinEntreMax(Id,vectorPesos )

h=imhist(uint8(Id));

if not (isempty(vectorPesos))
    
    h=funcion_suaviza_vector_medias_moviles(h,vectorPesos);
    
end

%1 Nivel de gris del máximo mayor

[NumPixMax,g1max] = max(h); %CUIDADO: el nivel real de gris es g1max - 1

%close all, imhist(uint8(Id)); axis([0 255 0 max(h) + 1000]), title(num2str(g1max))


%Nivel de gris del segundo maximo mayor

%Asegurarse de que la separacion entre los dos sea suficiente para evitar
%que ambo se encuentren en la misma clase

%Evaluar para cada nivel de gris: [(g-gmax)^2 * h(g)] 1 ? g ? 256

valores2Max = zeros(256,1);

for g=1:256
    
   valores2Max(g) = (g-g1max)^2 * h(g);
    
end

[~,g2max] = max(valores2Max);

%close all, imhist(uint8(Id)); axis([0 255 0 max(h) + 1000]), title(num2str(g2max))


%Calculamos el minimo entre maximos

%Calculamos el minimo de h garantizando que esté entre los dos máximos

%Modificamos los valores de h que no se encuentren entre los dos máximos y
%les asignamos el valor más alto posible

if g1max<g2max
    
    h(1:g1max) = NumPixMax;
    h(g2max:256) = NumPixMax;
    gmax1 = g1max-1;
    gmax2 = g2max-1;

    
    
else
    
    h(1:g2max) = NumPixMax;
    h(g1max:256) = NumPixMax;
    gmax2 = g1max-1;
    gmax1 = g2max-1;
    
    
end

%close all, imhist(uint8(Id)); axis([0 255 0 max(h) + 1000]),figure, stem(0:255,h,'.r')

[~,indice] = min(h);
g_MinEntreMax = indice-1;



end