.model small
.code
org100h
programa_int:
jmp reside
contador_int db 0

Rutina_serivicio PROC
cli
inc contador_int
cmp contador_int,2
jne fin
mov ah,0
int 16h
mov al,03h
mov ah,0
int 10h

mov ah,4ch
int 21h
fin:
sti
iret
endp

reside:
mov dx,offset Rutina_servicio
mov ax,0
mov es,ax
mov si,1ch*4
cli
mov es:[si],dx
mov es:[si+2],cs
sti
mov dx,offset reside
int 27h
END