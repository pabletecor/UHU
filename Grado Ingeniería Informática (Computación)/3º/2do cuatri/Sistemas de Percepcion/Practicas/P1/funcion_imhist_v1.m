function D = funcion_imhist_v1(I)

[A,B] = size(I);
D=zeros(256,1);

    for i=1:A
        for j=1:B
            
            ind_h = double(I(i,j))+1;   %+1 porque el valor 0 va en la pos 1 y etc.
            D(ind_h) = D(ind_h) + 1; 
        
        end
    end


end

