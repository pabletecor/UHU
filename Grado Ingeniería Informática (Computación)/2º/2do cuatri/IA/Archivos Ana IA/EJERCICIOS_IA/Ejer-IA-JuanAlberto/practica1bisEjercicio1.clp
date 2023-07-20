;Primero voy a definir la plantilla de un animal

(deftemplate Animal
		(field pelo) 			;si introducimos un hecho de un animal con pelo: peloSi
		(field leche) 			;si da leche ponemos: lecheSi
		(field rumiante)		;si es rumiante ponemos: rumianteSi. 
		(field pezunas)			;si tienes pezuñas ponemos: pezunasSi
		(field rallas)			;si queremos introducir una cebra será: rallasSi, e ignoramos el campo cuello
		(field cuello)			;por el contrario, si es una jirafa, será: cuelloSi, e ignoramos el campo rallas
)
		
; Ahora defino cuatro reglas: una para saber si es mamifero, otra para saber si es ungulado, y las dos ultimas para asertar los diferentes hechos

(defrule Mamifero
	(Animal 
	   (pelo peloSi)
	   (leche lecheSi))		;hasta aquí se comprueban las características de si es mamífero
	    =>
	   (assert (Mamifero 1))
)		

 (defrule Ungulado
	?m <- (Mamifero 1)
	(Animal
		(rumiante ?rumiante)
		(pezunas ?pezunas))
		(test (or (eq ?rumiante rumianteSi)
				 (eq ?pezunas pezunasSi))) ;Si es rumiante O tiene pezuñas es ungulado (las cebras no son rumiantes pero si ungulados)
		=>
		(assert (Ungulado 1))
		(retract ?m)
 )

 
(defrule EsJirafa
	?u <- (Ungulado 1)
	(Animal 
	   (cuello cuelloSi)) 
	   =>
	   (assert (es-jirafa))
	   (retract ?u)
) ;Si es mamifero, ungulado y tiene el cuello largo es una jirafa


(defrule EsCebra
	?u <- (Ungulado 1)
	 (Animal
		(rallas rallasSi))
	=>
	(assert (es-cebra))
	(retract ?u)
) ;Si es mamifero, ungulado y tiene rallas es una cebra