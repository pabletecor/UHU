(defrule preexc 
	(plantilla (nom ?nombre) (tip decisionexcursion) (pre ?pregunta)) (not (res ?)) => (printout t "¿Te gustan las excursiones? Si/no") (assert (res (read)))
) 

(defrule res
	(tip decisionexcursion)?res <- (res ~si&~no) => (retract ?res)
)