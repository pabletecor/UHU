;
;
;	Autores: Manuel Bracero González
;		 Daniel Calle Rosa
;
;
;	Descripción: Práctica 1. Apartado 3.
;
;






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;											      ;
;	3. Realizar un programa CLIPS que gestione información acerca de personas.	      ;
; La información sobre las personas se almacenará en hechos con plantillas y deberá contener  ;
; su nombre, color de ojos, edad y nacionalidad. El programa mostrará un menú con las	      ;
; opciones de agregar persona, borrar persona por nombre, listar todas las personas, mostrar  ;
; personas con ojos negros, mostrar personas con edad entre un rango introducido por teclado, ;
; mostrar personas con nacionalidad no española y salir del programa. La opción se	      ;
; introducirá por teclado, mostrará el resultado y a continuación se volverá a mostrar el     ; 
; menú pidiendo de nuevo que se introduzca una opción.					      ;
;											      ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



; Base de conocimiento


; Hechos

	; Inicialmente no hay personas en la base de conocimiento
	(deffacts personas

		(num_personas 0)
	)


; Plantillas

	(deftemplate persona

		(slot nombre (type SYMBOL))
		(slot color_ojos (type SYMBOL))
		(slot edad (type INTEGER))
		(slot nacionalidad (type SYMBOL))
	)


	(deftemplate mostrar_persona
	; Creamos esta plantilla para las opciones de listar (3, 4, 5 ó 6)

		(slot nombre (type SYMBOL))
		(slot color_ojos (type SYMBOL))
		(slot edad (type INTEGER))
		(slot nacionalidad (type SYMBOL))
	)

; Reglas


	; Mostramos el menú
	(defrule menu

		?hecho_inicial <- (initial-fact)

		=>

		(printout t "MENÚ :" crlf)
		(printout t crlf)

		(printout t "1.- Agregar persona" crlf)
		(printout t "2.- Borrar persona por nombre" crlf)
		(printout t "3.- Listar todas las personas" crlf)
		(printout t "4.- Mostrar personas con ojos negros" crlf)
		(printout t "5.- Mostrar personas con edad entre un rango introducido por teclado" crlf)
		(printout t "6.- Mostrar personas con nacionalidad no española" crlf)
		(printout t crlf)

		(printout t "7.- Salir del programa" crlf)
		(printout t crlf)
		(printout t crlf)

		(printout t "                      (Elige una opción)" crlf)

		; Leemos la opción introducida por el usuario
		(bind ?opcion (read))

		; Insertamos la opción en la base de conocimientos
		(assert (op ?opcion))

		; Borramos el hecho inicial para que no salga el menú
		(retract ?hecho_inicial)

	)

	; 1.- Agregar persona
	(defrule opcion1

		; Si ha elegido la opción 1
		?hecho_opcion <- (op 1)

		; Cogemos el número de personas que haya para incrementarlo posteriormente
		?hecho_personas <- (num_personas ?personas)

		=>

		(printout t crlf)
		(printout t "Escribe el nombre de la persona" crlf)
		(bind ?nom (read))
		(printout t crlf)

		(printout t crlf)
		(printout t "Escribe el color de ojos de " ?nom crlf)
		(bind ?ojos (read))
		(printout t crlf)

		(printout t crlf)
		(printout t "Escribe la edad de " ?nom crlf)
		(bind ?anos (read))
		(printout t crlf)

		(printout t crlf)
		(printout t "Escribe la nacionalidad de " ?nom crlf)
		(bind ?nac (read))
		(printout t crlf)

		; Insertamos la persona en la base de conocimientos
		(assert 
			(persona
				(nombre ?nom)
				(color_ojos ?ojos)
				(edad ?anos)
				(nacionalidad ?nac)
			)
		)

		; Borramos la opción
		(retract ?hecho_opcion)

		; Borramos el número de personas
		(retract ?hecho_personas)

		; Insertamos a un persona más
		(assert (num_personas (+ ?personas 1)))

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))
	)

	; 2.- Borrar persona por nombre
	(defrule opcion2

		; Si ha elegido la opción 2
		?hecho_opcion <- (op 2)

		; Si hay personas
		(persona (nombre ?))

		=>

		(printout t crlf)
		(printout t "Escribe el nombre de la persona que quieres borrar" crlf)
		(bind ?nom (read))
		(printout t crlf)

		; Insertamos un hecho para llamar a la regla de borrar personas
		(assert (borrar_persona ?nom))

		; Borramos la opción
		(retract ?hecho_opcion)
	)

	; Borramos a la persona (existe la persona con ese nombre)
	(defrule borrar_persona1

		; Buscamos el nombre de la persona para borrarla
		?hecho_borrar <- (borrar_persona ?nom)

		; Buscamos a la persona...
		?hecho_persona <- (persona (nombre ?nom))

		; Cogemos el número de personas que haya para decrementarlo posteriormente
		?hecho_personas <- (num_personas ?personas)

		=>

		; ... y la borramos
		(retract ?hecho_persona)

		; Borramos el nombre de la persona a borrar
		(retract ?hecho_borrar)

		; Borramos el número de personas
		(retract ?hecho_personas)

		; Quitamos a un persona más
		(assert (num_personas (- ?personas 1)))

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))
	)

	; Borramos a la persona (NO existe la persona con ese nombre)
	(defrule borrar_persona2

		; Buscamos el nombre de la persona para borrarla
		?hecho_borrar <- (borrar_persona ?nom)

		; Si no está la persona
		(persona (nombre ~?nom))

		=>

		; Borramos el nombre de la persona a borrar
		(retract ?hecho_borrar)

		; Mostramos un mensaje para decir que no existe ninguna persona
		(printout t crlf)
		(printout t "No hay ninguna persona con el nombre " ?nom crlf)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))
	)

	; Borramos a la persona (NO existe ninguna persona)
	(defrule borrar_persona3

		; Buscamos el nombre de la persona para borrarla
		?hecho_borrar <- (borrar_persona ?nom)

		; Si no hay personas
		(num_personas 0)

		=>

		; Borramos el nombre de la persona a borrar
		(retract ?hecho_borrar)

		; Mostramos un mensaje para decir que no existe ninguna persona
		(printout t crlf)
		(printout t "No hay ninguna persona en la base de conocimientos" crlf)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))
	)

	; 3.- Listar todas las personas
	(defrule opcion3

		; Si ha elegido la opción 3
		(op 3)

		; Cogemos todos los atributos de las personas
		(persona (nombre ?nom) (color_ojos ?ojos) (edad ?anos) (nacionalidad ?nac))

		=>

		; Insertamos un hecho que sea mostrar persona para posteriormente mostrarlos
		(assert
			(mostrar_persona
				(nombre ?nom)
				(color_ojos ?ojos)
				(edad ?anos)
				(nacionalidad ?nac)
			)
		)

	)

	; Mostramos a todas las personas
	(defrule mostrar_todos

		; Si la opción es 3
		(op 3)

		; Si hay personas
		(persona (nombre ?))

		; Si para todas las personas existe un mostrar_persona
		(forall (persona (nombre ?x)) (mostrar_persona (nombre ?x)))

		=>

		; Mostramos la cabecera para listar a todas las personas
		(printout t crlf)
		(printout t "PERSONAS" crlf)
		(printout t "--------" crlf)
		(printout t crlf)

		(assert (imprimir))

	)

	; 4.- Mostrar personas con ojos negros
	(defrule opcion4

		; Si ha elegido la opción 4
		?hecho_opcion <- (op 4)

		; Cogemos todos los atributos de las personas con ojos negros...
		(persona (nombre ?nom) (color_ojos negros) (edad ?anos) (nacionalidad ?nac))

		=>

		; Insertamos un hecho que sea mostrar persona para posteriormente mostrarlos
		(assert
			(mostrar_persona
				(nombre ?nom)
				(color_ojos negros)
				(edad ?anos)
				(nacionalidad ?nac)
			)
		)

	)

	; Si no hay personas con ojos negros
	(defrule si_no_personas_ojos_negros

		; Si ha elegido la opción 4
		?hecho_opcion <- (op 4)

		; Si no hay personas con ojos negros
		(not (persona (color_ojos negros)))

		=>

		; Informamos al usuario que no hay personas con ojos negros
		(printout t crlf)
		(printout t "No hay personas con ojos negros" crlf)
		(printout t crlf)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)
		
		; Borramos la opción
		(retract ?hecho_opcion)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))

	)

	; Mostramos a todas las personas con ojos negros
	(defrule mostrar_ojos_negros

		; Si la opción es 4
		(op 4)

		; Si para todas las personas existe un mostrar_persona
		(forall (persona (nombre ?x) (color_ojos negros)) (mostrar_persona (nombre ?x)))

		=>

		; Mostramos la cabecera para listar a todas las personas
		(printout t crlf)
		(printout t "PERSONAS CON OJOS NEGROS" crlf)
		(printout t "------------------------" crlf)
		(printout t crlf)

		(assert (imprimir))

	)

	; 5.- Mostrar personas con edad entre un rango introducido por teclado
	(defrule opcion5

		; Si ha elegido la opción 5
		(op 5)

		; Si hay personas
		(persona (nombre ?))

		=>

		(printout t crlf)
		(printout t "Escribe la edad mínima que quieres mostrar" crlf)
		(bind ?edad_min (read))

		(printout t crlf)
		(printout t "Escribe la edad máxima que quieres mostrar" crlf)
		(bind ?edad_max (read))
		(printout t crlf)

		; Insertamos en la base de conocimientos la edad mínima de personas a buscar
		(assert (edad_minima ?edad_min))

		; Insertamos en la base de conocimientos la edad máxima de personas a buscar
		(assert (edad_maxima ?edad_max))

	)

	; Buscamos a las personas entre unas edades
	(defrule buscar_edades

		; Buscamos la edad mínima
		(edad_minima ?edad_min)

		; Buscamos la edad mínima
		(edad_maxima ?edad_max)

		; Cogemos todos los atributos de las personas
		(persona (nombre ?nom) (color_ojos ?ojos) (edad ?anos) (nacionalidad ?nac))

		; Vemos si la edad está en el rango
		(test	(and	(<= ?edad_min ?anos)
				(<= ?anos ?edad_max)
			)
		)

		=>

		; Insertamos un hecho que sea mostrar persona para posteriormente mostrarlos
		(assert
			(mostrar_persona
				(nombre ?nom)
				(color_ojos negros)
				(edad ?anos)
				(nacionalidad ?nac)
			)
		)

	)

	; Mostramos a todas las personas con edad entre un rango
	(defrule mostrar_rango_edad

		; Si la opción es 5
		(op 5)

		; Buscamos la edad mínima
		?hecho_minima <- (edad_minima ?min)

		; Buscamos la edad mínima
		?hecho_maxima <- (edad_maxima ?max)

		=>

		; Mostramos la cabecera para listar a todas las personas
		(printout t crlf)
		(printout t "PERSONAS CON EDADES COMPRENDIDAS ENTRE " ?min " Y " ?max " AÑOS" crlf)
		(printout t "---------------------------------------------------------------" crlf)
		(printout t crlf)

		; Borramos la edad mínima...
		(retract ?hecho_minima)

		; ... y la máxima
		(retract ?hecho_maxima)

		(assert (imprimir))

	)

	; Si no se encuentra ninguna persona en el rango establecido
	(defrule no_en_rango

		; Si la opción es 5
		?hecho_opcion <- (op 5)
		
		; Buscamos la edad mínima
		?hecho_minima <- (edad_minima ?min)

		; Buscamos la edad mínima
		?hecho_maxima <- (edad_maxima ?max)

		; Si no se ha encontrado ninguna persona entre el rango
		(not (mostrar_persona))

		=>

		; Mostramos la cabecera para listar a todas las personas
		(printout t crlf)
		(printout t "No existen personas con edades comprendidas entre " ?min " y " ?max " años" crlf)
		(printout t crlf)

		; Borramos la edad mínima...
		(retract ?hecho_minima)

		; ... y la máxima
		(retract ?hecho_maxima)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)
		
		; Borramos la opción
		(retract ?hecho_opcion)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))

	)

	; 6.- Mostrar personas con nacionalidad no española
	(defrule opcion6

		; Si ha elegido la opción 6
		?hecho_opcion <- (op 6)

		; Cogemos todos los atributos de las personas...
		(persona (nombre ?nom) (color_ojos ?ojos) (edad ?anos) (nacionalidad ?nac&~espanola))

		=>

		; Insertamos un hecho que sea mostrar persona para posteriormente mostrarlos
		(assert
			(mostrar_persona
				(nombre ?nom)
				(color_ojos ?ojos)
				(edad ?anos)
				(nacionalidad ?nac)
			)
		)

	)

	; Si no hay personas con nacionalidad no española
	(defrule si_no_personas_nacionalidad_no_espanola

		; Si ha elegido la opción 6
		?hecho_opcion <- (op 6)

		; Si no hay personas con nacionalidad no española
		(not (persona (nacionalidad ~espanola)))

		=>

		; Informamos al usuario que no hay personas con nacionalidad no española
		(printout t crlf)
		(printout t "No hay personas con nacionalidad no española" crlf)
		(printout t crlf)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)
		
		; Borramos la opción
		(retract ?hecho_opcion)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))

	)

	; Mostramos a todas las personas no españolas
	(defrule mostrar_no_espanolas

		; Si la opción es 6
		(op 6)

		; Si para todas las personas existe un mostrar_persona
		(forall (persona (nombre ?x) (nacionalidad ?nac&~espanola)) (mostrar_persona (nombre ?x)))

		=>

		; Mostramos la cabecera para listar a todas las personas con nacionalidad no española
		(printout t crlf)
		(printout t "PERSONAS CON NACIONALIDAD NO ESPAÑOLA" crlf)
		(printout t "-------------------------------------" crlf)
		(printout t crlf)

		(assert (imprimir))

	)

	; Imprime a las personas
	(defrule imprimir

		(imprimir)

		; Cogemos todos los atributos de las personas para mostrar
		?hecho_mostrar <- (mostrar_persona (nombre ?nom) (color_ojos ?ojos) (edad ?anos) (nacionalidad ?nac))

		=>

		; Mostramos el nombre de la persona
		(printout t "Nombre :" ?nom crlf)

		; Mostramos el color de los ojos
		(printout t "Color de ojos :" ?ojos crlf)

		; Mostramos su edad
		(printout t "Edad :" ?anos crlf)

		; Mostramos la nacionalidad
		(printout t "Nacionalidad :" ?nac crlf)
		(printout t crlf)

		; Borramos mostrar personas
		(retract ?hecho_mostrar)

	)

	; Si ya se han mostrado todas las personas
	(defrule terminar_imprimir

		?hecho_opcion <- (op ?opcion)

		?hecho_imprimir <- (imprimir)

		; Si opción está entre 3 y 6
		(test	(and	(<= 3 ?opcion)
				(<= ?opcion 6)
			)
		)

		; Si no hay mostrar personas
		(not (mostrar_persona))

		=>

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)

		
		; Borramos la opción
		(retract ?hecho_opcion)

		
		; Borramos el hecho imprimir
		(retract ?hecho_imprimir)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))

	)

	; Si no hay personas introducidas en la base de conocimiento
	(defrule sin_personas

		; Si no hay personas en la base de conocimiento
		(num_personas 0)

		?hecho_opcion <- (op ?opcion)

		; Si opción está entre 2 y 6
		(test	(and	(<= 2 ?opcion)
				(<= ?opcion 6)
			)
		)

		=>

		; Mostramos la nacionalidad
		(printout t "No hay niguna persona en la base de conocimiento" crlf)

		; Borramos la opción
		(retract ?hecho_opcion)

		; Hacemos que el usuario pulse intro para volver al menú
		(printout t crlf)
		(printout t "PULSA CUALQUIER TECLA E INTRO PARA VOLVER A MOSTRAR EL MENÚ " crlf)
		(bind ?intro (read))
		(printout t crlf)

		; Insertamos el hecho inicial para que entre en el menú
		(assert (initial-fact))
	)

	; Opción de salir del programa
	(defrule salir

		; Si ha elegido la opción 7
		?hecho_opcion <- (op 7)

		=>

		; Borramos la opción para que salga del programa
		(retract ?hecho_opcion)
	)