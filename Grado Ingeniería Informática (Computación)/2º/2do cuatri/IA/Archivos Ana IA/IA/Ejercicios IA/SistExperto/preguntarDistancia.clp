(defrule predist
	(plantilla(nom ?nombre) (tip decisiondistancia) (pre ?pregunta)) (not (res ?)) => (printout t "¿Cerca o lejos?") (assert (res (read)))
) 

(defrule res
(tipo decisiondistancia)?res <- (res ~lejos&~cerca) => (retract ?res)
)