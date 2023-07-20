.model small

.data

cadena db 5,0,0,0,0,0,0 ;Creamos una variable de 4 digitos
peso db 8,4,2,1         

.code 

mov ax, seg cadena
mov ds, ax
mov dx, offset cadena
mov ah, 0ah
int 21h	; Introducimos un valor por teclado

sub cadena[2],48
sub cadena[3],48
sub cadena[4],48
sub cadena[5],48


mov al,cadena[2]
mul peso[0]
mov bx,ax

mov al,cadena[3]
mul peso[1]
add bx,ax

mov al,cadena[4]
mul peso[2]
add bx,ax

mov al,cadena[5]
mul peso[3]
add bx,ax

mov ax,bx
mov bl,10

div bl  ; en ah se almacena el resto(unidad) y en al el cociente (decenas)
mov cl,al
add cl,48
mov ch,ah
add ch,48 

mov al,03h
mov ah,00h
int 10h


mov ax,0b800h
mov es,ax

mov ah,00001111b
mov al,cl
mov es:[di], ax
mov al,ch
mov es:[di+2],ax

mov ah, 00h
int 16h



mov ah,4ch
int 21h

end

