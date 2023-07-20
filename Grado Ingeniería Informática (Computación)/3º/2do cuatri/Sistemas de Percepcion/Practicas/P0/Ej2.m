%Cambiamos a blanco y negro (true y false) los valores de la matriz
[A,B] = size (I);
contador = 0;
umbral=100;

Ib=false(size(I));

for i=1:A
    for j=1:B
        if I(i,j) < umbral
            Ib(i,j)=true;
        end
        
        
    end
end

Ib= I < umbral;
numPixeles = sum(Ib(:));

%Escribo la imagen en un nuevo archivo
imwrite(Ib,'imagen_binaria.png')

