(deftemplate pais
	(field nombre)
	(multifield banderacolor)
)



(defrule menu

		?hecho_inicial <- (initial-fact)

		=>

		(printout t "Inserte los colores de una bandera. Ejem verde blanco verde:" crlf)
		(printout t crlf)
		
		(printout t "Colores disponibles para insertar:" crlf)
		(printout t "1.- Rojo" crlf)
		(printout t "2.- Blanco" crlf)
		(printout t "3.- Verde" crlf)
		(printout t "4.- Amarillo" crlf)
		(printout t "5.- Azul" crlf)
		(printout t "6.- Negro" crlf)

		; Leemos la opción introducida por el usuario
		(bind ?opcion (read))

		; Insertamos la opción en la base de conocimientos
		(assert (banderacolor ?opcion))

		; Borramos el hecho inicial para que no salga el menú
		(retract ?hecho_inicial)

	)

(defrule dimecolor
	(pais
		(nombre ?nom)
		(banderacolor ?ban)
	)

	(test (= ?ban "rojo amarillo rojo" ))
	=> 
	(printout t ?ban " Ese color pertenece a la bandera de: " ?nom)

	(test (= ?ban "rojo blanco azul"))
	=> 
	(printout t ?ban " Ese color pertenece a la bandera de: " ?nom)

	(test (= ?ban "verde blanco verde"))
	=> 
	(printout t ?ban " Ese color pertenece a la bandera de: " ?nom)
	
	(test (= ?ban "negro rojo amarillo"))
	=> 
	(printout t ?ban " Ese color pertenece a la bandera de: " ?nom)

)








 
	

