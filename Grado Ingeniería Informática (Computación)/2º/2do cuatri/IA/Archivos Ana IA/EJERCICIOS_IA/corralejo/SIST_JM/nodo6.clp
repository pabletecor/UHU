(defrule regla61
	(nodo 
		(respuesta si)
		(tipo decisionsalir) 
	)  
=> 
(assert
	(nodo 
		(salir si)
		(tipo fin)
	)
)


)




(defrule regla62
	(nodo 
		(respuesta no)
		(tipo decisionsalir) 
	)  
=> 
(assert
	(nodo 
		(salir no)
		(tipo fin)
	)
)


)
