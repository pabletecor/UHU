(defrule r61
	(plantilla 
		(res si)
		(tipo decisionsalir) 
	)  
=> 
(assert
	(plantilla
		(salir si)
		(tipo fin)
	)
))(defrule r62
	(plantilla 
		(res no)
		(tipo decisionsalir) 
	)  
=> 
(assert
	(plantilla
		(salir no)
		(tipo fin)
	)
))
