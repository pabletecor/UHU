(defrule r11 
		(res invierno)(tip decision) 	  => (assert(plantilla (est invierno)(tip decisionlugar)))
)
(defrule r12 
		(res verano)(tip decision) 	 => (assert(plantilla (est verano)(tipo decisionlugar)))
)

(defrule r13 	
		(res primavera)(tip decision) 	 => (assert(plantilla (est primavera)(tip decisionlugar)))
)

(defrule regla14 
		(res oto�o)(tip decision)  => (assert(plantilla(est oto�o)(tip decisionlugar)))
)       