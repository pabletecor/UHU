(defrule r31
	(plantilla 
		(res cerca)
		(tipo decisiondistancia) 
	)  
=> 
(assert
	(plantilla 
		(dis cerca)
		(tipo decisiondeportes)
	)
)
)
(defrule r32
	(plantilla 
		(res lejos)
		(tipo decisiondistancia) 
	)  
=> 
(assert
	(plantilla 
		(dis lejos)
		(tipo decisiondeportes)
	)
)
)

