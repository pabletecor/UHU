.MODEL small

.DATA
mensaje db ' PABLOYMANU0123456789-PABLOYMANU012345789-PABLOYMANU0123456789-PABLOYMANU0123456789-PABLOYMANU0123456789 *'
;Cadena formada por 1 comienzo de cadena + 20 char * 5 + 4 guiones + 2 espacios + 1 asterisco
contador db 0
color db 01h

.CODE
	MOV AX, seg mensaje
	MOV DS,AX

	MOV AL,03h
	MOV AH,0
	INT 10h


	MOV AX, 0B800h ;dir memoria modo texto
	MOV ES, AX
pinta_cadena:
	MOV AL, mensaje[si]
	INC color
	MOV AH, color
	CMP color, 0Eh
	jne patata
	MOV color,00h
	patata:
	MOV ES:[DI], AX
	ADD DI,2

	INC SI
	CMP mensaje[SI], ""
	JNE pinta_cadena

	MOV SI,0
	INC mensaje[0]
	CMP DI, 4158 ;EL VALOR MAXIMO QUE PUEDA ALCANZAR DI ES 4158
	JBE pinta_cadena
	MOV DI,0 ;si ha llegado al final vuelve al principio de la pantalla
	jmp pinta_cadena ;si ha llegado al final vuelve al principio de la pantalla


END