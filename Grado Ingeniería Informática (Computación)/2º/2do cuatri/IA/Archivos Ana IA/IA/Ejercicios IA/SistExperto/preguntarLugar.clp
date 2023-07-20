(defrule prelugar 
	(plantilla (tip decisionlugar) (est ?estacion)) (not (res ?)) => (printout t "¿playa ciudad o naturaleza?") (assert (res (read))(tip decisionlugar)(est ?estacion))
)

(defrule res
	(tipo decisionlugar)?res <- (res ~playa&~ciudad&~naturaleza) =>(retract ?res)
)