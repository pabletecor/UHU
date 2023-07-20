(defrule pregdecision 
	(plantilla 
		(nom ?nombre) 
		(tipo decision) 
		(pre ?pregunta)
	) 
	(not (res ?)) 
=> 
(printout t ?pre) 
(assert 
		(res (read))
		(tipo decision)		
)
) 

