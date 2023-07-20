(defrule predep 
	(plantilla 
		(nom ?nombre) 
		(tipo decisiondeportes) 
		(pre ?pregunta)
	) 
	(not (resp ?)) 
=> 
(printout t "¿Te gustan los deportes? Si/no") 
(assert (resp (read)))
) 
(defrule res1
(tipo decisiondeportes)
?resp <- (resp ~no&~si) 
=>
(retract ?resp)
)