(deffacts datos-iniciales
                (vector B C D )
                (D D)
                (L L)
                (B B)
                (M M ))

(defrule copia
        (vector $?u)
 =>
        (assert (vector-aux $?u))
)

(defrule C1
        (D ?e)
        (L ?u)
        ?vector-aux <- (vector-aux $?e1 ?m1&C $?e2)
=>
        (retract ?vector-aux )
        (assert (vector-aux $?e1 ?e ?u $?e2 ))
)

(defrule C2
        (B ?e)
        (M ?u)
        ?vector-aux <- (vector-aux $?e1 ?m1&C $?e2)
=>
        (retract ?vector-aux )
        (assert (vector-aux $?e1 ?e ?u $?e2 ))
)


(defrule B
        (M ?u)
        ?vector-aux <- (vector-aux $?e1 ?m1&B $?e2)
=>
        (retract ?vector-aux )
        (assert (vector-aux $?e1 ?u ?u $?e2 ))
)

(defrule Z
        (B ?e)
        (M ?u)
        ?vector-aux <- (vector-aux $?e1 ?m1&Z $?e2)
=>
        (retract ?vector-aux )
        (assert (vector-aux $?e1 ?e ?e ?u $?e2 ))
)