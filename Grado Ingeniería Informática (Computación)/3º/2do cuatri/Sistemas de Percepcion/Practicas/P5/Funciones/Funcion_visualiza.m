function I=Funcion_visualiza(im,ib,color)

[NumFilas, NumColumnas, NumComp] = size (im);

if NumComp==3 %Imagen a color
    IR=im(:,:,1);
    IG=im(:,:,2);
    IB=im(:,:,3);
else %Imagen escala de grises
    IR=im(:,:);
    IG=im(:,:);
    IB=im(:,:);
end

%     IB(ib==1)=color(3);
%     IG(ib==1)=color(2);
%     IR(ib==1)=color(1);
     
    IB(ib(:,:))=color(3);
    IG(ib(:,:))=color(2);
    IR(ib(:,:))=color(1);

    I=cat(3,IR,IG,IB);
    
    imshow(I)

end