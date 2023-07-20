(deftemplate DatosExploracion
    (field Nombre)
    (multifield Sintomas)
    (field GravedadAfeccion))

(deftemplate FichaPaciente
    (field Nombre)
    (field Casado)
    (field Direccion))

(defrule DiagnosticoEccema
    (DatosExploracion
        (Nombre ?N)
        (Sintomas $? picor $? vesiculas $?))

    (FichaPaciente 
        (Nombre ?N))
    =>
        (printout t "Posible diagnóstico para el paciente " ?N " : Eccema " crlf)
)