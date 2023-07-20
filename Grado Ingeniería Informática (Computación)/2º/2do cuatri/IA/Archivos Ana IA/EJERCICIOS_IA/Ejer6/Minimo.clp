(deffacts minimo
 (val 17)
 (val 34)
 (val 46)
 (val 100)
 (val 58)
 (val 77)
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




