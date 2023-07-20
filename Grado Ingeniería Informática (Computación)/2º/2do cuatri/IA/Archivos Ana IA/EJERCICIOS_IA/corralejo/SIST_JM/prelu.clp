(defrule prelugar 
	(plantilla 
		(tipo decisionlugar) 
		(est ?estacion)
	) 
	(not (resp ?)) 
=> 
(printout t "¿playa ciudad o naturaleza?") 
(assert 
		(resp (read))
		(tipo decisionlugar)
		(est ?estacion)

)
)
(defrule resp
(tipo decisionlugar)
?res <- (res ~playa&~ciudad&~naturaleza) 
=>
(retract ?res)
)