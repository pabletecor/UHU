function XImagen = funcion_calcula_descriptores_imagen(IEtiq,N)

        Stats = regionprops(IEtiq, 'Area','Perimeter','Eccentricity','Solidity','Extent','EulerNumber');
        temp = zeros(23,N);
        temp(1,:) = (cat(1,Stats.Perimeter).^2 ./ cat(1,Stats.Area))'; %Compacticidad
        temp(2,:) = cat(1,Stats.Eccentricity)'; %Excentricidad
        temp(3,:) = cat(1,Stats.Solidity)'; %Solidez
        temp(4,:) = cat(1,Stats.Extent)'; %Extension
        temp(5,:) = Funcion_Calcula_Extent(IEtiq)'; %Extension Invariable Rot
            for k = 1:N
                IBin = IEtiq == k;
                auxHu(:,k) = Funcion_Calcula_Hu(IBin); %Hu
                auxDF(:,k) = Funcion_Calcula_DF(IBin,10); %Desc Fourier
            end
        temp(6:12,:) = auxHu;
        temp(13:22,:) = auxDF;
        temp(23,:) = cat(1,Stats.EulerNumber)'; %NEuler
    
    XImagen = temp';
end