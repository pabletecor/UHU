function Extent = Funcion_Calcula_Extent(Ib)

    IbCent = Funcion_Centra_Objeto(Ib);
    Extent = 0;
    extentRots = 0;
    IbRot = zeros(size(IbCent));
    
    
    for i=0:5:355
        
        IbRot = imrotate(IbCent,i);
        
        %Calculo de BBox

        stats = regionprops(IbRot,'BoundingBox');
        bb = cat(1,stats.BoundingBox);
        %columna-fila:Esquina superior izquierda
        %ancho-alto

        fminM=bb(2);
        cminM=bb(1);
        fmaxM=fminM+bb(4);
        cmaxM=cminM+bb(3);
        

        %Calculo manual del descriptor
        numPixBB = (cmaxM-cminM)*(fmaxM-fminM);
        numPixObjt = sum(IbRot(:));
        extentRots = numPixObjt/numPixBB;
        
        %Seleccion del maximo extend
        if extentRots > Extent
            Extent = extentRots;
        end
        
    end

end