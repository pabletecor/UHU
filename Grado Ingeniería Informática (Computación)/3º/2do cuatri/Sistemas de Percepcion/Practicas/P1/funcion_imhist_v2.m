function D = funcion_imhist_v2(F)

D=zeros(256,1);

    for g=0:255
        
        ROI = F==g; 
        D(g+1) = sum(ROI(:));
        
    end


end


