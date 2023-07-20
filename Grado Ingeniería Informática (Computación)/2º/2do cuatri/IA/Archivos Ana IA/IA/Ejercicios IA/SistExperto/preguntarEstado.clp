(defrule pregdecision 
	(plantilla (nom ?nombre) (tip decision) (pre ?pregunta)) (not (res ?)) => (printout t ?pregunta) (assert (res (read))(tip decision))
) 

