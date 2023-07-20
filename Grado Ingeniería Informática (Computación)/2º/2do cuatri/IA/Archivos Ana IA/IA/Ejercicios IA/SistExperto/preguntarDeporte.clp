(defrule predep 
	(plantilla (nom ?nombre) (tip decisiondeportes) (pre ?pregunta))(not (res ?)) => (printout t "¿Te gustan los deportes? Si/no") (assert (res (read)))
)
 
(defrule res1
(tipo decisiondeportes)?res <- (res ~no&~si) =>(retract ?res)
)