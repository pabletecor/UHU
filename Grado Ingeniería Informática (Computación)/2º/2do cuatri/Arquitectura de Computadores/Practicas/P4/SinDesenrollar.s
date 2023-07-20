.data
	A: .word 1,6,8,0,2,44,0,5,0,0
	T: .word 40
	i: .word 4
	B: .word 0
.text
start:
	LW R3,i
	LW R2,T
	LW R4,B	

loop:
	SUB R2,R2,R3
	LW R1,A(R2)
	SEQ R5,R1,R0
	ADD R4,R4,R5
	BNEZ R2,loop
	NOP

trap #6