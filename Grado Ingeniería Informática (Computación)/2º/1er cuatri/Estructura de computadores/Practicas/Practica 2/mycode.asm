.model small
.code
	org 100h
main:
	jmp residente
	msj_tiempo db '00:00'
	co db 0
	contador_int db 18
	
;RTI

tiempo_p proc	;proc indica que es un programa residente
	cli
	push ax
	push dx
	push si
	push di   
	push es
	cmp co,0
	je imprime_mensaje
	jmp no
	imprime_mensaje:
	mov al,03h	;pantalla en modo texto
	mov ah,0
	int 10h
	mov ax, 0b800h	;direccion de memoria de video
	mov es, ax
	inc co
	no:

	dec contador_int
	cmp contador_int,0
	je suma
	jmp fin_rsi
suma:
	mov contador_int,18
	inc msj_tiempo[4]
	cmp msj_tiempo[4],58
	je decenas
	jmp pinta
decenas:
	mov msj_tiempo[4],48
	inc msj_tiempo[3]
	cmp msj_tiempo[3],54
	je minutos
	jmp pinta
minutos:
	mov msj_tiempo[3],48
	inc msj_tiempo[1]
	cmp msj_tiempo[1],10
	je decenas_minutos
	jmp pinta
decenas_minutos:
	mov msj_tiempo[1],48
	inc msj_tiempo[0]
	cmp msj_tiempo[0],54
	je reset
	jmp pinta
reset:
mov msj_tiempo[0],48
mov msj_tiempo[1],48
mov msj_tiempo[3],48
mov msj_tiempo[4],48
jmp pinta


;El segmento extra esta definiendo la memoria de video
;IMPRIMIR POR PANTALLA

pinta:
	
	mov si,0
	mov di,1640h	;VALOR A ELEGIR

	bucle:  
	    
		mov al, msj_tiempo[si]  
		add al,48
		mov ah,10111000b
	
		mov es:[di],ax
		add di,2
		inc si
		cmp si,4
	jbe bucle
	
fin_rsi:	;Saltar directamente si el contador no ha llegado a 0

    	pop es
	pop di
	pop si
	pop dx
	pop ax
	sti	;Habilitamos las interrupciones
	iret	;Devolvemos el control al programa principal

endp ;Inicializa el vector de interrupcion a la direccion donde va a estar la rutina y deja residente en memoria el codigo de esta rutina

residente:

mov dx, offset tiempo_p
mov ax,0
mov es,ax
mov si,1Ch*4
cli ;desactivamos las interrupciones
mov es:[si], dx	;dx es el offset(desplazamiento)
mov es:[si+2], cs
sti 	;reactivamos las interrupciones
mov dx, offset residente
int 27h	;Deja residente el codigo de la RTI

end main