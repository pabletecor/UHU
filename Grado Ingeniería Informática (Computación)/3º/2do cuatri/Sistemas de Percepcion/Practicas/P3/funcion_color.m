%Script
function sol =  funcion_color(I,ROI,c)

    %Dimensiones de I
    [i,j,k] = size(I);
    
    if k < 3 %Imagen en ByN
    
        Ic = double(I);
        Ib2 = double(ROI);
        
    else %Imagen a color
        
       R = double(I(:,:,1));
       G = double(I(:,:,2));
       B = double(I(:,:,3));
       Ic = (R+G+B)/3;
       
       r = double(ROI(:,:,1));
       g = double(ROI(:,:,2));
       b = double(ROI(:,:,3));
       Ib2 = (r+g+b)/3;
 
        
    end
    
    %Matriz Ib invertida
    Ib3 = Ib2==0;
    %Mantenemos el fondo del mismo color (No se modifica)
    Fondo = Ib3.*Ic;
    %Cambiamos las zonas del ROI
    CambiarR = (Ib2.*c(1));
    CambiarG = (Ib2.*c(2));
    CambiarB = (Ib2.*c(3));
    
    %Solucion = fondo + imagen cambiada
    Imagen1 = cat (3,CambiarR,CambiarG,CambiarB);
    Imagen2 = cat (3,Fondo,Fondo,Fondo);
    sol = uint8(Imagen1+Imagen2);
    
    %Mostramos
    %imshow(Sol)

end