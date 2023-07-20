(defrule Suma "Cargar suma.clp y una vez cargado escribir 
(+ numA numB numC numD numE) "
     (numeros ?a ?b ?c ?d ?e)
=>
     (assert (resultado_suma (+ ?a ?b ?c ?d ?e)))
)