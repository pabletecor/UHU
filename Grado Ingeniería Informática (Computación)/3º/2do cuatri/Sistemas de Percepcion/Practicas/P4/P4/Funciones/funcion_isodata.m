function T = funcion_isodata(h,umbralParada)

    
varControl = true;

gIni = 1; gFin = 256;
T = calcula_valor_medio_region_histograma(h,gIni,gFin);

while varControl

    gIni = 1; gFin = round(T);
    gMean1 = calcula_valor_medio_region_histograma(h,gIni,gFin); %Nivel de gris medio de la primera agrup
    
    gIni = round(T) + 1; gFin = 256;
    gMean2 = calcula_valor_medio_region_histograma(h,gIni,gFin); %Nivel de gris medio de la segunda agrup
    
    newT =mean([gMean1 gMean2]); %Esta forma de calcular la media acepta que
    %gMean1 o gMean2 puedan ser conjuntos vacios
    
    if abs(T-newT) <= umbralParada
       
        varControl = false;
        
    end
    
    T = newT;
    
end

T = T-1;

end