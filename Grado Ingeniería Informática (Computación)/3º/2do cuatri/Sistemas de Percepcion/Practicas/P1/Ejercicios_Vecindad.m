%%Ejercicios Vecindad

%1
A = rand(5,5);

%2

i=3;
j=4;

B = A(i-1:i+1 , j-1:j+1);

%3

i=3;
j=4;

C2 = [ A(i+1,j) A(i-1,j) A(i,j+1) A(i,j-1) ];

C = [ A(i,j-1:j+1) A(i-1:i+1,j)'] ;

ind = find (C==A(i,j))
C(ind) = []

%4

i=3;
j=4;

D2 = [A(i+1,j) A(i+1,j+1) A(i+1,j-1)  A(i-1,j) A(i,j+1) A(i,j-1)];

D = [ A(i-1,j-1:j+1) ];

n=[I(i,j) I(i-1,j) I(i+1,j) I(i,j-1) I(i,j+1) I(i-1,j-1) I(i+1,j-1) I(i+1,j+1) I(i-1,j+1) ];

n=A(i-1:i+1, j-1:j+1)

vec = n(:)'
%5

D = [D A(i,j-1:2:j+1)];


%6

E=A;
ind = find(A<0.5);
E(ind)=0;

%otra opcion
E=A;
E(A<0.5) = 0;

%7

ind = find(A>0.2 & A<0.7);
total=sum(A(ind));
i=length(ind);
media=total/i;

%otra opcion

media = mean(A (A>0.2 & A<0.7) ) % A accede a los valores que cumplen la 
                                 %condicion entre parentesis

%8

Aa=rand(5,5);
Ba=rand(5,5);
ind=find(Ba>0.5);
total=sum(Aa(ind));
i=length(ind);
media=total/i;

%otra opcion

Aa=rand(5,5);
Ba=rand(5,5);

ROI = Ba>0.5;
mean(Aa(ROI))


%9
clear
%Para imagenes en blanco y negro
Icbyn = cat(3,I,I,I);



Ic=imread('P1_1.jpg');
R = double(Ic(:,:,1));
G = double(Ic(:,:,2));
B = double(Ic(:,:,3));


I = ( R+G+B ) /3 ;

imshow(uint8(I))  %Solo usamos uint8 para ver las imagenes
imshow(Ic);
ROI = (Ic>50 & Ic<100);

c=[255 255 0];
funcion_color(Ic,ROI,c);





