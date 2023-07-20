function Areas = Calcula_Areas(Matriz_Etiquetada)

    unicos=unique(Matriz_Etiquetada);
    N=size(unicos);
    Areas=[];
    for i=1:N
        x=unicos(i);
        if(x~=0)
            Areas = [Areas; sum(Matriz_Etiquetada(:)==x)]; 
        end
    end
    
end