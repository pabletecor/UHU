%Cuenta los pixeles que no son la x

imshow (I)

[A,B] = size (I);
contador = 0;

for i=1:A
    for j=1:B
        if I(i,j) < 100
            contador = contador +1;
        end
    end
end