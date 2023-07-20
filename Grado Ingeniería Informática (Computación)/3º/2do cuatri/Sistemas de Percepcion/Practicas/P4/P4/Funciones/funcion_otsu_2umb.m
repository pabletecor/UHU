function [T1, T2] = funcion_otsu_2umb(I)

    h = imhist(uint8(I));
    
    %Consideramos en toda la programacion niveles de gris de 1 a 256,
    %despues al resultado le resyamos una unidad
    
    
    gIni = 1; gFin=256;
    
    [gmedio,numPix] = calcula_valor_medio_region_histograma(h,gIni,gFin);
    
    var = zeros(256,1); %Para almacenar la varianza entre clases
    umbrales = [];
    ind = 0;
    
    for g1=2:254 %Los extremos de g=0 y g=255 no son posibles umbrales (de 2 a 254)
        for g2 = g1 + 1:255   
            
            ind = ind + 1;
            T1=g1;
            T2=g2;
            umbrales = [umbrales;[T1-1 T2-1]]
            var(ind) = calcula_varianza_entre_3clases(T1,T2,h,numPix,gmedio);

        end
    end
    
    [~, indice] = max(var);
    
    T1 = umbrales(indice,1);
    T2 = umbrales(indice,2);

end


function var = calcula_varianza_entre_3clases(T1,T2,h,numPix,gmedio)

    %Calculo del numero de pixeles y el valor de gris medio de cada clase
    [gMean1,N1] = calcula_valor_medio_region_histograma(h,1,T1-1);
    [gMean2,N2] = calcula_valor_medio_region_histograma(h,T1,T2-1);
    [gMean3,N3] = calcula_valor_medio_region_histograma(h,T2,256);


    %Probabilidad de ocurrencia de un nivel de gris en cada clase
    prob1 = N1/numPix;
    prob2 = N2/numPix;
    prob3 = N3/numpix;
    %Varianza entre clases ponderada por la prob de ocurrencia

    var = prob1 * (gMean1-gmedio).^2 + prob2 * (gMean2-gmedio).^2 + prob3 * (gMean3-gmedio).^2 ;
    if isempty(var)
       
        var=0;
        
    end

end