(deffacts minimo
 (val 112)
 (val 333)
 (val 1005995)
 (val 45)
 (val 67)
 (val 4)
)

 (defrule init "Inicializa el calculo del MINIMO"
 (not (min ?))
 =>
 (assert (min 0))
 )

(defrule calcula-min
 (val ?y)
 (forall (val ?x) (test (<= ?y ?x)))
 =>
(printout t "El minimo es:" ?y crlf) 
 )