(deftemplate Persona
	(field nombre)
	(field edad)
	(field NombreConyuge)
	(field PosicionEconomica)
	(field salario))
(defrule mostrarSesenta
	(Persona (nombre ?N) (edad 60))
	=>
	(printout t ?N " tiene 60 anios" crlf))
(defrule mostrarCuarenta
	(Persona (nombre ?N) (edad 40))
	=>
	(printout t ?N " tiene 40 anios" crlf))
(defrule mostrar
	(Persona (nombre ?N))
	=>
	(printout t "nombre: " ?N (edad ?) (NombreConyuge ?) (PosicionEconomica ?) (salario ?) crlf))
(defrule conyug
	(Persona (NombreConyuge ?N))
	(Persona (nombre ?N) (PosicionEconomica desahogada))
	=>
	(printout t ?N " tiene una posicion economica desahogada" crlf))
(defrule anCon
	(Persona (nombreConyuge ?N))
	(Persona (nombre ?N) (PosicionEconomica desahogada))
	=>
	(printout t ?N " añadido" crlf)
	(assert DatosFiscales ?N ConyugeDesahogado))
(defrule borrar
	(Persona (nombre ?N) (PosicionEconomica desahogada))
	=>
	(printout t ?N " Borrado" crlf)
	(retract ?N edad? NombreConyuge? PosicionEconomica? salario?))
