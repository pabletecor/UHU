(defrule r21 
	(plantilla (res playa)(tip decisionlugar) )  => (assert(plantilla (lug playa)(tip decisiondistancia)))
)
(defrule r22 
	(plantilla (res ciudad)(tip decisionlugar) )  => (assert(plantilla (lug ciudad)(tip decisiondistancia)))
)
(defrule r23 
	(plantilla (res naturaleza)(tip decisionlugar) )  => (assert(plantilla (lug naturaleza)(tip decisiondistancia)))
)