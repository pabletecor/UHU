function centroides = Calcula_Centroides (Matriz_Etiquetada)

    N = max(Matriz_Etiquetada(:));
    Centroide = zeros(N,2);

%X se corresponde con las columnas en matlab 
%Y se corresponde con las filas

for i=1:N

        [f,c] = find(Matriz_Etiquetada==i);
        divX = size(c,1);
        divY = size(f,1);
        Centroide(i,1) = sum(c)/divX;
        Centroide(i,2) = sum(f)/divY;


    
end

imshow(Matriz_Etiquetada)
hold on
plot(centroides(:,1),centroides(:,2),'b*')
hold off

end