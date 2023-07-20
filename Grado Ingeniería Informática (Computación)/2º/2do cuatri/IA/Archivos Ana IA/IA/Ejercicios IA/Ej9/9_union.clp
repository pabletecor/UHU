(deffacts datos-iniciales
(conjunto-1 B C A D E E B C E)
(conjunto-2  E E B F D E))


(defrule union-con-primero-compartido
(union $?)
?conjunto-2 <- (conjunto-2 ?e $?r-2)
(conjunto-1 $? ?e $?)
=>
(retract ?conjunto-2)
(assert (conjunto-2 ?r-2)))
(defrule union-con-primero-no-compartido
?union <- (union $?u)
?conjunto-2 <- (conjunto-2 ?e $?r-2)
(not (conjunto-1 $? ?e $?))
=>
(retract ?conjunto-2 ?union)
(assert (conjunto-2 ?r-2)
(union ?u ?e)))

(defrule calcula-union
=>
(assert (union)))
(defrule union-base
?union <- (union $?u)
?conjunto-1 <- (conjunto-1 $?e-1)
?conjunto-2 <- (conjunto-2)
=>
(retract ?conjunto-1 ?conjunto-2 ?union)
(assert (union ?e-1 ?u))
(assert (escribe-solucion)))
(defrule escribe-solucion
(escribe-solucion)
(union $?u)
=>
(printout t "La union es " ?u crlf))

