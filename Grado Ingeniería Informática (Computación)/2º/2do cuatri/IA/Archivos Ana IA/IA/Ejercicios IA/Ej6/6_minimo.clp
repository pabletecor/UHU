(defrule inicial
	(vector $?x)
=>
	(assert (vector-aux ?x))
)

(defrule ordenar
	?f<- (vector-aux $?b ?m1 ?m2&:(< ?m2 ?m1) $?e)
=>
	(retract ?f)
	(assert (vector-aux $?b ?m2 ?m1 $?e))
)
(defrule final
	(not (vector-aux $?b ?m1 ?m2&:(< ?m2 ?m1) $?e))
	(vector $?x)
	(vector-aux ?m1 $?y)
=>
	(printout t "El minimo elemento del vector " ?x " es " ?m1 crlf))