function [suma, media] = media (x)

n=length (x);
suma= sum (x);
function [a] = med (sumandos, nelementos)
%calculalamedia
a= sumandos/nelementos;
end

media=med(n,suma)
end
