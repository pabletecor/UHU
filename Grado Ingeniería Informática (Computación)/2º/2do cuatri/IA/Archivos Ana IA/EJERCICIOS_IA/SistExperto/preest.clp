(defrule pregdecision 
	(plantilla 
		(nom ?nombre) 
		(tipo decision) 
		(pre ?pregunta)
	) 
	(not (res ?)) 
=> 
(printout t ?pregunta) 
(assert 
		(res (read))
		(tipo decision)		
)
) 

