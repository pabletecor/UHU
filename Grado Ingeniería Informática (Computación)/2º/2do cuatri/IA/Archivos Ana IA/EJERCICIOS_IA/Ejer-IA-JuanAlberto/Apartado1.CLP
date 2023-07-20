(deftemplate Animal	
	(field pelo)	
	(field leche)	
	(field rumiante)
	(field pezunas)
	(field rallas)	
	(field cuello)) ;defino la plantilla Animal con sus campos	

(defrule Mamifero	
	(Animal
	 	(pelo tienepelo)
	 	(leche daleche))
		=>
		(assert (Mamifero 1)))	;defino la regla Mamifero, entonces si tiene pelo y da leche afirmo Mamifero	

(defrule Ungulado	
	?M <- (Mamifero 1)	
	(Animal
		(rumiante ?rumiante)
		(pezunas ?pezunas))
		(test (or (eq ?rumiante esrumiante)
			(eq ?pezunas tienepezunas)))
		=>
		(assert (Ungulado 1))
		(retract ?M))	;defino la regla Ungulado, entonces si un animal tiene pezuñas o es rumiante afirmo dicha regla

(defrule EsJirafa
	?U <- (Ungulado 1)
	(Animal
		(cuello cuellolargo))
		=>
		(assert (esjirafa))
		(retract ?U))	;defino la regla EsJirafa, entonces si un animal ungulado tiene el cuello largo afirmo que EsJirafa

(defrule EsCebra
	?U <- (Ungulado 1)
	(Animal
		(rallas tienerallas))
		=>
		(assert (escebra))
		(retract ?U))	;defino la regla EsCebra, entonces si un animal ungulado tiene rallas, afirmo que EsCebra	