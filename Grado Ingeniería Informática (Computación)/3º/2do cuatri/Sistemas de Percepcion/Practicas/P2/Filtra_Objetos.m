function Ibfilt = Filtra_Objetos (Ietiq , NumPix)


% Ibfilt=bwareaopen(Ib,NumPix);

    areas=Calcula_Areas(Ietiq);
    for i=1:size(areas)
        if(areas(i)<NumPix)
            Ietiq(Ietiq==i)=0;
        end
    end
    Ibfilt=Ietiq;


imshow(Ibfilt);

end