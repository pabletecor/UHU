(defrule r41
	(plantilla (res si)(tip decisiondeportes) )  => (assert(plantilla (dep si)(tip decisionsalir)))
)
(defrule r42
	(plantilla (res no)(tip decisiondeportes) )  => (assert(plantilla(dep no)(tip decisionexcursiones)))
)
