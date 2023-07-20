.data

	A: .word 5,4,7,7,4,5,1,3,10,50
	T: .word 36
	i: .word 4
	B: .word 0
	
.text

start:
	LW R3,T 
	LW R4,i 
	ADD R2,R0,R0
	LW R1,A(R3) 
	
	

loop:   SUB R3,R3,R4 
	LW R2,A(R3) 
	SUB R1,R1,R2 
	BNEZ R3,loop 
	NOP	

	SW B,R1

trap #6


