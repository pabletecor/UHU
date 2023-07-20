.MODEL small

.data
;(28*80*12) + ultima impresion + 28
;(nº instrucciones bucle * long cadena entera * 12 veces que se repite) +  ultima impresion + instrucciones rutina de servicio a la ejec
mensaje db ' PABLOYMANU123456789-PABLOYMANU12345789-PABLOYMANU123456789-PABLOYMANU1234567000 *'
;Cadena formada por 1 comienzo de cadena + 19 char * 4 + 3 + 1 asterisco

.code
	MOV AX, seg mensaje
	MOV DS,AX

	MOV AL,03h
	MOV AH,0
	INT 10h


	MOV AX, 0B800h ;dir memoria modo texto
	MOV ES, AX
pinta_cadena:
	MOV AL, mensaje[si]
	MOV AH, 0Fh
	MOV ES:[DI], AX
	ADD DI,2

	INC SI
	CMP mensaje[SI], " "
	JNE pinta_cadena

	MOV SI,0
	INC mensaje[0]
	CMP DI, 4158 ;EL VALOR MAXIMO QUE PUEDA ALCANZAR DI ES 4158
	JBE pinta_cadena
	MOV DI,0 ;si ha llegado al final vuelve al principio de la pantalla
	INC mensaje[79]
	cmp mensaje[79],58
	jne salto
	INC mensaje[78]
	mov mensaje[79],48
	salto:
	MOV AL,03h
	MOV AH,0
	INT 10h
	jmp pinta_cadena ;si ha llegado al final vuelve al principio de la pantalla
	

END