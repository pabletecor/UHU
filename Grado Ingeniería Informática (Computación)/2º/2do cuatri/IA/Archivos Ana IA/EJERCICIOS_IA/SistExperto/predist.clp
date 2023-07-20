(defrule predist
	(plantilla
		(nom ?nombre) 
		(tipo decisiondistancia) 
		(pre ?pregunta)
	) 
	(not (resp ?)) 
=> 
(printout t "¿Cerca o lejos?") 
(assert (res (read)))
) 

(defrule res
(tipo decisiondistancia)
?res <- (res ~lejos&~cerca) 
=> 
(retract ?res)
)