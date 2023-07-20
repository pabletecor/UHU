(defrule r51
	(plantilla 
		(res si)
		(tipo decisionexcursiones) 
	)  
=> 
(assert
	(plantilla 
		(exc si)
		(tipo decisionsalir)
	)
))
(defrule r52
	(plantilla 
		(res no)
		(tipo decisionexcursiones) 
	)  
=> 
(assert
	(plantilla 
		(exc no)
		(tipo decisionsalir)
	))
)

