function T_otsu = funcion_otsu(I)

    h = imhist(uint8(I));
    
    %Consideramos en toda la programacion niveles de gris de 1 a 256,
    %despues al resultado le resyamos una unidad
    
    
    gIni = 1; gFin=256;
    
    [gmedio,numPix] = calcula_valor_medio_region_histograma(h,gIni,gFin);
    
    var = zeros(256,1); %Para almacenar la varianza entre clases
    
    for g = 2:255   %Los extremos de g=0 y g=255 no son posibles umbrales (de 2 a 255)
        
        T=g;
        var(g) = calcula_varianza_entre_clases(T,h,numPix,gmedio);
        
    end
    
    [~, indice] = max(var);
    
    T_otsu = indice-1;


end




function  var = calcula_varianza_entre_clases(T,h,numPix,gmedio)

    %Calculo del numero de pixeles y el valor de gris medio de cada clase
    [gMean1,N1] = calcula_valor_medio_region_histograma(h,1,T);
    [gMean2,N2] = calcula_valor_medio_region_histograma(h,T+1,256);


    %Probabilidad de ocurrencia de un nivel de gris en cada clase
    prob1 = N1/numPix;
    prob2 = N2/numPix;

    %Varianza entre clases ponderada por la prob de ocurrencia

    var = prob1 * (gMean1-gmedio).^2 + prob2 * (gMean2-gmedio).^2;
    if isempty(var)
       
        var=0;
        
    end
    

end
 
 