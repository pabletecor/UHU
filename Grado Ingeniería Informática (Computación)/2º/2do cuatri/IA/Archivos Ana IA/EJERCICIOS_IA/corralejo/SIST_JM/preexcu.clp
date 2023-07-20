(defrule preexc 
	(plantilla 
		(nom ?nombre) 
		(tipo decisionexcursion) 
		(pre ?pregunta)
	) 
	(not (resp ?)) 
=> 
(printout t "¿Te gustan las excursiones? Si/no") 
(assert (res (read)))
) 

(defrule res
(tipo decisionexcursion)
?res <- (res ~si&~no) 
=> 
(retract ?res)
)