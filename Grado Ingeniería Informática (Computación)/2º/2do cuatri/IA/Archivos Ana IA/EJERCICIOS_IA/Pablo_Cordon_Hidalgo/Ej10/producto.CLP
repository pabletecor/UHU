(deftemplate productos

(field codigovendedor)
(field codigoproducto)
(field pvpproducto))

(defrule dependencia
(test (= codigoproducto codigoproducto ))
=>

(printout t "Precio:" ?pvppproducto crlf) )
