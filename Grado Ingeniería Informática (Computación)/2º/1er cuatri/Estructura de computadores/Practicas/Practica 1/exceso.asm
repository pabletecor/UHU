.model small

.data

cadena db 5,0,0,0,0,0,0
peso db 8,4,2,1         

.code 

mov ax, seg cadena
mov ds, ax
mov dx, offset cadena
mov ah, 0ah
int 21h

sub cadena[2],48
sub cadena[3],48
sub cadena[4],48
sub cadena[5],48


mov al,cadena[2]
mul peso[0]
mov bl,al

mov al,cadena[3]
mul peso[1]
add bl,al

mov al,cadena[4]
mul peso[2]
add bl,al

mov al,cadena[5]
mul peso[3]
add bl,al

mov ax,bx
mov bl,10
mov cl,peso[0]

cmp cadena[2],0 
je negativo
jmp pos


negativo:
mov bl,cl
sub bl,al
add bl,48
mov bh,45
mov al,03h
mov ah,00h
int 10h


mov ax,0b800h
mov es,ax

mov ah,00001111b
mov al,bh
mov es:[di], ax
mov al,bl
mov es:[di+2],ax

mov ah, 00h
int 16h

mov ah,4ch
int 21h

jmp fin
pos:
sub al,peso[0]
add al,48
mov bl,al

mov al,03h
mov ah,00h
int 10h

mov ax,0b800h
mov es,ax
mov ah,00001111b
mov al,bl
mov es:[di], ax

mov ah, 00h
int 16h

mov ah,4ch
int 21h
jmp fin
fin:
end
