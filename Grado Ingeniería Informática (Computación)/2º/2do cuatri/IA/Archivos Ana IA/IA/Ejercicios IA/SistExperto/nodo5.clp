(defrule r51
	(plantilla (res si)(tip decisionexcursiones) )  => (assert(plantilla (exc si)(tip decisionsalir)))
)
(defrule r52
	(plantilla (res no)(tip decisionexcursiones) )  => (assert(plantilla (exc no)(tip decisionsalir)))
)

