(defrule presalir
	(plantilla
		(nom ?nombre) 
		(tipo decisionexcursion) 
		(pre ?pregunta)
	) 
	(not (res ?)) 
=> 
(printout t "¿Quieres salir de noche? Si/no") 
(assert (res (read)))
) 
(defrule res
(tipo decisionexcursion) 
?res <- (res ~si&~no) 
=>
(retract ?res)
)