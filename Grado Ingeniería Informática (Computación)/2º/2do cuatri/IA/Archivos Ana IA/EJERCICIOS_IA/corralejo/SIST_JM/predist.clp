(defrule predist
	(plantilla
		(nombre ?nombre) 
		(tipo decisiondistancia) 
		(pregunta ?pregunta)
	) 
	(not (resp ?)) 
=> 
(printout t "¿Cerca o lejos?") 
(assert (resp (read)))
) 

(defrule resp
(tipo decisiondistancia)
?respu <- (resp ~lejos&~cerca) 
=> 
(retract ?resp)
)