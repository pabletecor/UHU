(defrule r61
	(plantilla (res si)(tip decisionsalir) )  => (assert(plantilla(salir si)(tipo fin)))
)
(defrule r62
	(plantilla 	(res no)(tip decisionsalir) )  => (assert(plantilla(salir no)(tipo fin)))
)
