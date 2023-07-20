(deftemplate Persona
	(field nombre)
	(field colordeojos)
	(field edad)
	(field nacionalidad)) ;defino la plantilla persona con sus campos

(defrule Reset
	=>
	(assert (Menu 1)))

(defrule Menu
	?P <- (Menu 1)
	=>
	(printout t "-----------------------------------" crlf)
	(printout t "1.Agregar persona" crlf)
  	(printout t "2.Borrar persona por nombre" crlf)
  	(printout t "3.Listar todas las personas" crlf)
  	(printout t "4.Mostrar personas con ojos negros" crlf)
  	(printout t "5.Mostrar personas entre dos edades" crlf)
  	(printout t "6.Mostrar personas de nacionalidad no española" crlf)
  	(printout t "7.Salir del programa" crlf)
	(printout t "-----------------------------------" crlf)
  	(printout t "Introduzca una opción: " crlf)
  	(bind ?n (read))
  	(assert (funcion ?n))
  	(retract ?P)) 

(defrule Op1	
	?F <- (funcion 1)
	=>
	(printout t "Introduce el nombre: " crlf)
	(bind ?nombre (read))
	(printout t "Introduce su color de ojos: " crlf)
	(bind ?colordeojos (read))
	(printout t "Introduce su edad: " crlf)
	(bind ?edad (read))
	(printout t "Introduce su nacionalidad: " crlf)
	(bind ?nacionalidad (read))
		
	(assert (Persona
		(nombre ?nombre)
		(colordeojos ?colordeojos)
		(edad ?edad)
		(nacionalidad ?nacionalidad)))
	
	(assert(Menu 1))
	(retract ?F ))	;defino la regla correspondiente a la primera opcion, en la cual pide los datos e introduce a la persona

(defrule Op2
	?F <- (funcion 2)
	=>
	(printout t "Introduce el nombre de la persona a borrar: " crlf)
	(bind ?nombre (read))
	(assert (name ?nombre))
	(retract ?F))

	(defrule Borrar
		?F <- (name ?nombre)
		?P <- (Persona (nombre ?nombre))
		=>
		(assert(Menu 1))
		(retract ?P)
		(retract ?F));defino la regla correspondiente a la segunda opcion y la regla para borrar a las personas, en la cual se introduce el nombre de la persona que se pretenda borrar	

(defrule Op3
	?F <- (funcion 3)
	=>
	(do-for-all-facts ((?P Persona)) TRUE
		(format t " %s %s %d %s%n" ?P:nombre ?P:colordeojos ?P:edad ?P:nacionalidad))
	(assert (Menu 1))
	(retract ?F))	;defino la regla que corresponde con la  tercera opcion, para mostrar a todas las personas

(defrule Op4
	?F <- (funcion 4)
	=>
	(do-for-all-facts ((?P Persona)) (eq ?P:colordeojos negros)
		(format t " %s %s %d %s%n" ?P:nombre ?P:colordeojos ?P:edad ?P:nacionalidad))
	(assert (Menu 1))
	(retract ?F))	;defino la regla de la cuarta opcion, para visualizar a las personas con ojos negros

(defrule Op5
	?F <- (funcion 5)
	=>
	(printout t "Introduce la edad minima: " crlf)
	(bind ?n (read))
	(printout t "Introduce la edad maxima: " crlf)
	(bind ?e (read))
	(printout t "Personas entre las edades  " ?n "-" ?e crlf)
	(do-for-all-facts ((?P Persona)) (and(>= ?P:edad ?n) (<= ?P:edad ?e))
		(format t " %s %s %d %s%n" ?P:nombre ?P:colordeojos ?P:edad ?P:nacionalidad))
	(assert(Menu 1))
	(retract ?F))	;defino la regla de la quinta opcion, para visualizar a las personas que se encuentren en un intervalo de edades

(defrule Op6
	?F <- (funcion 6)
	=>
	(do-for-all-facts ((?P Persona)) (not (eq ?P:nacionalidad espanola))
		(format t " %s %s %d %s%n" ?P:nombre ?P:colordeojos ?P:edad ?P:nacionalidad))
	(assert (Menu 1))
	(retract ?F))

(defrule Op7
	?F <- (funcion 7)
	=>
	(retract ?F)) ;regla para salir del menu