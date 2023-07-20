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
	(nodo 
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
