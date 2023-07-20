(defrule r31
	(plantilla (res cerca)(tip decisiondistancia) )  => (assert(plantilla (dist cerca)(tip decisiondeportes)))
)
(defrule r32
	(plantilla (res lejos)(tip decisiondistancia) )  => (assert(plantilla (dist lejos)(tip decisiondeportes)))
)

