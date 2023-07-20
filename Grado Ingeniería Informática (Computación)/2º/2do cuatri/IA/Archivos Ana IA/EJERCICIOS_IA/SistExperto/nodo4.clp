(defrule r41
	(plantilla 
		(res si)
		(tipo decisiondeportes) 
	)  
=> 
(assert
	(plantilla 
		(dep si)
		(tipo decisionsalir)
	)
)
)(defrule r42
	(plantilla 
		(res no)
		(tipo decisiondeportes) 
	)  
=> 
(assert
	(plantilla
		(dep no)
		(tipo decisionexcursiones)
	))
)
