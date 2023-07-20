(deftemplate Persona
		(field nombre)
		(field color-ojos)
		(field edad)
		(field nacionalidad) 
);la plantilla para las personas

(defrule comienzo
	=>
(assert (Menu 1)))


(defrule Menu
 ?p <- (Menu 1)
 =>
 
  (printout t "MENU" crlf)
  (printout t "1.Agregar persona" crlf)
  (printout t "2.Borrar persona por nombre" crlf)
  (printout t "3.Listar todas las personas" crlf)
  (printout t "4.Mostrar personas con ojos negros" crlf)
  (printout t "5.Mostrar personas entre dos edades" crlf)
  (printout t "6.Mostrar personas de nacionalidad no española" crlf)
  (printout t "7.Salir del programa" crlf)
  (printout t "Introduzca una opción: " crlf)
  (bind ?n (read))
  (assert (funcion ?n))
  (retract ?p)
 ) 
 
 
(defrule opcion1
?f <- (funcion 1)
=>
		(printout t "Introduzca el nombre de la persona: " crlf)
		(bind ?nombre (read))
		(printout t "Introduzca su color de ojos: " crlf)
		(bind ?color-ojos (read))
		(printout t "Introduzca su Edad: " crlf)
		(bind ?edad (read))
		(printout t "Introduzca su nacionalidad" crlf)
		(bind ?nacionalidad (read))
		
		(assert (Persona
			(nombre ?nombre)
			(color-ojos ?color-ojos)
			(edad ?edad)
			(nacionalidad ?nacionalidad)
			) ;introducimos a la persona una vez obtenidos sus datos
		)
	(assert(Menu 1))
	(retract ?f )
)


(defrule opcion2
	?f <- (funcion 2)

	=>
	(printout t "Introduzca el nombre de la persona a borrar: " crlf)
	(bind ?nombre (read))
	(assert (name ?nombre))
	(retract ?f)
); Necesitamos una segunda regla para borrar a la persona


		(defrule reglaBorrar
			?f <- (name ?nombre)
			?p <- (Persona (nombre ?nombre))
			
			=>
			(assert(Menu 1))
			(retract ?p)
			(retract ?f)
		)	
		
		

(defrule opcion3
?f <- (funcion 3)
=>
	(do-for-all-facts ((?p Persona)) TRUE
		(format t " %s %s %d %s%n" ?p:nombre ?p:color-ojos ?p:edad ?p:nacionalidad))
	(assert (Menu 1))
	(retract ?f)
) ;Regla para mostrar a todas las personas




(defrule opcion4
?f <- (funcion 4)

=>
	(do-for-all-facts ((?p Persona)) (eq ?p:color-ojos negros)
		(format t " %s %s %d %s%n" ?p:nombre ?p:color-ojos ?p:edad ?p:nacionalidad))
	(assert (Menu 1))
	(retract ?f)

); regla para ver a las personas con ojos negros. Igual a la anterior pero poniendo una condición




(defrule opcion5
?f <- (funcion 5)

=>
	(printout t "Introduzca la edad menor: " crlf)
	(bind ?n (read))
	(printout t "Introduzca la edad mayor: " crlf)
	(bind ?e (read))
	(printout t "PERSONAS EN EL INTERVALO  " ?n "-" ?e crlf)
	(do-for-all-facts ((?p Persona)) (and(>= ?p:edad ?n) (<= ?p:edad ?e))
		(format t " %s %s %d %s%n" ?p:nombre ?p:color-ojos ?p:edad ?p:nacionalidad))
	(assert(Menu 1))
	(retract ?f)
)




(defrule opcion6
?f <- (funcion 6)
=>
	(do-for-all-facts ((?p Persona)) (not (eq ?p:nacionalidad española))
		(format t " %s %s %d %s%n" ?p:nombre ?p:color-ojos ?p:edad ?p:nacionalidad))
	(assert (Menu 1))
	(retract ?f)
)



(defrule opcion7
?f <- (funcion 7)
=>
	(retract ?f)
) ;regla para salir del menu