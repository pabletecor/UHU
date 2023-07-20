(defrule r21 
	(plantilla 
		(res playa)
		(tipo decisionlugar) 
	)  
=> 
(assert
	(plantilla 
		(lugar playa)
		(tipo decisiondistancia)
	)
)

)
(defrule r22 
	(plantilla 
		(res ciudad)
		(tipo decisionlugar) 
	)  
=> 
(assert
	(plantilla 
		(lugar ciudad)
		(tipo decisiondistancia)
	)
)

)
(defrule r23 
	(plantilla 
		(res naturaleza)
		(tipo decisionlugar) 
	)  
=> 
(assert
	(plantilla 
		(lugar naturaleza)
		(tipo decisiondistancia)
	)
)

)