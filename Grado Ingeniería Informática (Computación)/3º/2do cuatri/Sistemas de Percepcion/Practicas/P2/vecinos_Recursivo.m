function [Imagenbinaria,Imagendouble] = vecinos_Recursivo(Imagenbinaria,Imagendouble,fila,columna,numero)
    
    if(Imagenbinaria(fila,columna) == 255)
        
        Imagenbinaria(fila,columna) = 0;
        Imagendouble(fila,columna) = numero;
        
        if(fila > 1)
            [Imagenbinaria,Imagendouble] = vecinos_Recursivo(Imagenbinaria,Imagendouble,fila-1,columna,numero);
        end
        if(fila < size(Imagenbinaria,1))
            [Imagenbinaria,Imagendouble] = vecinos_Recursivo(Imagenbinaria,Imagendouble,fila+1,columna,numero);
        end
        if(columna > 1)    
            [Imagenbinaria,Imagendouble] = vecinos_Recursivo(Imagenbinaria,Imagendouble,fila,columna-1,numero);
        end
        if(columna < size(Imagenbinaria,2))    
            [Imagenbinaria,Imagendouble] =vecinos_Recursivo(Imagenbinaria,Imagendouble,fila,columna+1,numero);
        end
    end
    
end