(defrule r51
	(plantilla 
		(respuesta si)
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
		(excs no)
		(tipo decisionsalir)
	))
)

