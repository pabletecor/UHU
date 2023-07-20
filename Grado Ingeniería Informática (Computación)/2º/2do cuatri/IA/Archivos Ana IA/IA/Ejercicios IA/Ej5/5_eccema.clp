(deftemplate FichaPaciente
	(field Nombre)
	(field Edad)
	(field Casado)
	(field Sexo)
	(field Peso))

(deftemplate DatosExploracion
	(field Nombre)
	(multifield Sintomas)
	(field GravedadAfeccion))

(deftemplate Diagnostico
	(field Nombre)
	(field Resultado)
	(field ProximaRevision))

(defrule DiagnosticoEccema
	(DatosExploracion 
		(Nombre ?N)
		(Sintomas $? picor $? vesiculas $?)) 
		=>
		(printout t "Primer diagnóstico del paciente " ?N ": Eccema"crlf))
		(assert (DiagnosticoEccema
					(Nombre ?N)
					(Resultado Eccema)
					ProximaRevision Septiembre17)))
)