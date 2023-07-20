(defrule r11 
		(res invierno)
		(tipo decision) 	  
=> 
(assert
	(plantilla 
		(est invierno)
		(tipo decisionlugar)
	)
)
)
(defrule r12 
	
		(res verano)
		(tipo decision) 	 
=> 
(assert
	(plantilla 
		(est verano)
		(tipo decisionlugar)
	)
)
)

(defrule r13 	
		(res primavera)
		(tipo decision) 	 
=> 
(assert
	(plantilla 
		(est primavera)
		(tipo decisionlugar)
	)
)
)

(defrule regla14 
	
		(res otoño)
		(tipo decision)  
=> 
(assert
	(plantilla
		(est otoño)
		(tipo decisionlugar)
	)
)
)       