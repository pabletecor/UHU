function YTest= funcion_knn ( XTest, XTrain, YTrain, k, distancia)

[numValores, numAtributos] = size(XTrain);
[numMuestras, numAtributos2] = size(XTest);
valoresClases = unique(YTrain);
numClases = length(valoresClases);
YTest = zeros(numMuestras,1); 

    for i=1:numMuestras
    
        if strcmp (distancia,'euclidea') %DISTANCIA EUCLIDEA

     
            XTest_i = XTest(i,:)'; 
            XTestRep = repmat(XTest_i, 1, numValores);
            distancias = sqrt( sum( (XTrain'- XTestRep).^2 ) )';

            
       elseif strcmp (distancia,'mahalanobis') % DISTANCIA DE MAHALANOBIS

            XTest_i = XTest(i,:);
            distancias = zeros(numValores);
            % Matrices de covarianzas de cada clase 
            mCov = zeros(numAtributos, numAtributos, numClases);

            n=0;
            
            for p=1:numClases %Recorro cada clase

                FoI = YTrain==valoresClases(p);
                XClase = XTrain(FoI,:);         %Cogemos las de la clase
                mCov(:,:,p) = cov(XClase,1);    %Matriz covarianza de cada clase
                
                for m=1:size(XClase,1)%Recorro cada muestra de cada clase
                    
                    posicion = n+m;
                    XTrain_i = XClase(i,:);
                    distancias(posicion) = (XTrain_i - XTest_i) * pinv(mCov(:,:,p)) * (XTrain_i- XTest_i)';
                    
                end
                
                n=n+m;%La incrementamos
                
            end
            
            distancias = sqrt(sum(distancias'))'; 
        
        
        end
        
         % 2 - LOCALIZAR LAS k INSTANCIAS DE XTrain MAS CERCANAS A TEST

        [v, indices] = sort(distancias, 'ascend'); %En orden ascendente (de menor a mayor)

    % 3.- CREAR UN VECTOR CON LAS CODIFICACIONES DE LAS CLASES DE ESAS 
    % k-INSTANCIAS MÁS CERCANAS 

        vect_K = YTrain(indices(1:k));

    % 4.- ANALIZAR ESE VECTOR PARA CONTAR EL NÚMERO DE VECES QUE APARECE 
    % CADA CODIFICACIÓN  
    
        clases = unique(YTrain);
        Tipo=zeros(length(clases), 1);
        for j=1:length(clases)
            Tipo(j) = sum(vect_K==clases(j));
        end
        

    % 5.- EL VALOR DE YTEST EN LA POSICIÓN CORRESPONDIENTE A LA INSTANCIA DE 
    % XTEST QUE SE ESTÁ ANALIZANDO ES LA CODIFICACIÓN DE LA CLASE MÁS NUMEROSA.   
        
        [vector, ind] = max(Tipo);
        
    
        % - SI HAY MÁS DE UNA CLASE CON EL NÚMERO MÁXIMO DE VOTOS, DEVOLVER LA 
        % CLASE DE LA INSTANCIA MÁS CERCANA A LA DE TEST (ENTRE ESAS INSTANCIAS 
        % DE LAS CLASES MÁS NUMEROSAS)
        
        
        Varios= sum(Tipo==vector);
        if Varios > 1
            
            iguales=true;
            Cuales=Tipo==vector;
            m=1;
            while iguales && m < length(vector)
                pos = YTrain(int(indices(m)));
                for k=1:numAtributos
                    if Cuales(k) == pos
                       iguales=false;
                        YTest(i)=k; 
                    end
                end
                m=m+1;
                
            end
            
        else 
            
          YTest(i) = ind;   
            
        end
               
   end





end