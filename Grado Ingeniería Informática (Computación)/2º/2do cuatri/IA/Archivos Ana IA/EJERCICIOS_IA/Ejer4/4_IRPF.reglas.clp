(defrules reglasIRPF

	(test (= ?Edad 40))
	=>

	(printout t crlf ?Nombre "tiene " ?Edad "a�os")

	(test (= ?Edad 60))
	=>

	(printout t crlf ?Nombre "tiene " ?Edad "a�os")

	(Persona ? ? No ? ?)
	=>

	(printout t crlf "Datos:"
		"Nombre:" ?Nombre
		"Edad:" ?Edad
		"Posicion:" ?PosicionEconomica
		"Salario:" ?Salario
		"Nombre Conyuge:" ?NombreConyuge)

	(test ( neq ?NombreConyuge No))
	=>

	(printout t crlf "Datos:"
		"Nombre:" ?Nombre
		"Edad:" ?Edad
		"Posicion:" ?PosicionEconomica
		"Salario:" ?Salario)

)
	