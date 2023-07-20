function XImagen = funcion_calcula_descriptores_extent_hu_imagen(Ietiq,N)

    stats = regionprops(Ietiq,'Extent');
    ExtentImagen = cat(1,stats.Extent);

       for j =1:N

           Iobjeto=Ietiq==j;
           
           ExtentRot = Funcion_Calcula_Extent(Iobjeto);
           m = Funcion_Calcula_Hu(Iobjeto);
           XImagen(j,:) = [ExtentRot m'];
       end
    
       
end