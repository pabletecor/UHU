function funcion_representa_datos(X,Y,espacioCcas,nombresProblema)

    [numMuestras,numDescriptores] = size(X);
    codifClases = unique(Y);
    numClases = length(codifClases);

    if length(espacioCcas)<3 %2 dim


    figure, hold on
    
        for i =1:numClases

            datosClase = X(Y==codifClases(i),espacioCcas);
            plot(datosClase(:,1),datosClase(:,2),nombresProblema.simbolos{i})

        end

    legend(nombresProblema.clases)
    xlabel(nombresProblema.descriptores{espacioCcas(1)})
    ylabel(nombresProblema.descriptores{espacioCcas(2)})

    
    %3 Dimensiones
    else 
        
 figure, hold on
    
        for i =1:numClases

            datosClase = X(Y==codifClases(i),espacioCcas);
            plot3(datosClase(:,1),datosClase(:,2),datosClase(:,3),nombresProblema.simbolos{i})

        end

    legend(nombresProblema.clases)
    xlabel(nombresProblema.descriptores{espacioCcas(1)})
    ylabel(nombresProblema.descriptores{espacioCcas(2)})
    zlabel(nombresProblema.descriptores{espacioCcas(3)})


    end

end