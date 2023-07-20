function funcion_compara_matrices(A,B)
    Error = double(A) - double(B); 
    
    m = min(Error(:));  %Valor minimo de toda la matriz
    M = min(Error(:));  %Valor maximo de toda la matriz

    if m==M && m==0
        disp('Matrices iguales')
    else
        disp('Matrices diferentes')

    end
end