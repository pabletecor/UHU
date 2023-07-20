;;realiza la pregunta estacion
(defrule ask-decision-estacion-node-question 
(declare(salience 10))
	?node <- (current-node ?name)
	(node (name ?name)
	(type decisionestacion)
	(question ?question))
	(not (answer ?))
	=>
	(printout t ?question " (invierno,verano..) ")
	(assert (answer (read)))
)

;;ver si la decision es correcta 
(defrule bad-answer-estacion 
(declare(salience 10))
	?node <- (current-node root)
?answer <- (answer ~otonyo&~invierno&~primavera&~verano) 
=>
(retract ?answer))

;;otoño
(defrule proceed-to-otonyo-branch 
(declare(salience 10))
	?node <- (current-node ?name) 
		(node 
		(name ?name) 
		(type decisionestacion) 
		(otonyo-node ?otonyo-branch)) 
	?answer <- (answer otonyo) 
	;;?P1 <- (answer otonyo)
	=> 
	(assert (Pr1 otonyo))
	(retract ?node ?answer) 
	(assert (current-node ?otonyo-branch))
)

;;invierno 
(defrule proceed-to-invierno-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionestacion)
		(invierno-node ?invierno-branch))
	?answer <- (answer invierno)
	=>
(assert (Pr1 invierno))
	(retract ?node ?answer)
	(assert (current-node ?invierno-branch))
)

;;primavera 
(defrule proceed-to-primavera-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionestacion)
		(primavera-node ?primavera-branch))
	?answer <- (answer primavera)
	=>
	(assert (Pr1 primavera))
	(retract ?node ?answer)
	(assert (current-node ?primavera-branch))
)

;;verano 
(defrule proceed-to-verano-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionestacion)
		(verano-node ?verano-branch))
	?answer <- (answer verano)
	=>
	(assert (Pr1 verano))
	(retract ?node ?answer)
	(assert (current-node ?verano-branch))
)
;;

(defrule ask-decision-paisaje-node-question
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionpaisaje)
		(question ?question))
		(not (answer ?))
	=>
		(printout t ?question " (playa, ciudad o naturaleza) ")
		(assert (answer (read)))
)

;;ver si la decision es correcta
(defrule bad-answer-paisaje
(declare(salience 10))
	?node <- (current-node node1|node2|node3|node4)
	?answer <- (answer ~playa&~ciudad&~naturaleza)
	=>
		(retract ?answer))


;;playa
(defrule proceed-to-playa-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionpaisaje)
		(playa-node ?playa-branch))
	?answer <- (answer playa)
	=>
(assert (Pr2 playa))
		(retract ?node ?answer)
		(assert (current-node ?playa-branch)))

;;ciudad
(defrule proceed-to-ciudad-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionpaisaje)
		(ciudad-node ?ciudad-branch))
	?answer <- (answer ciudad)
	=>
(assert (Pr2 ciudad))
		(retract ?node ?answer)
		(assert (current-node ?ciudad-branch)))

;;naturaleza
(defrule proceed-to-naturaleza-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionpaisaje)
		(naturaleza-node ?naturaleza-branch))
	?answer <- (answer naturaleza)
	=>
(assert (Pr2 naturaleza))
		(retract ?node ?answer)
		(assert (current-node ?naturaleza-branch)))
		
		
		
;;decisión de cerca o lejos
(defrule ask-decision-cercalejos-node-question
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisioncercalejos)
		(question ?question))
		(not (answer ?))
	=>
		(printout t ?question " (cerca o lejos) ")
		(assert (answer (read)))
)

;;ver si la decision es correcta
(defrule bad-answer-cercalejos
(declare(salience 10))
	?node <- (current-node node5|node6|node7)
	?answer <- (answer ~cerca&~lejos)
	=>
		(retract ?answer))


;;cerca
(defrule proceed-to-cerca-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisioncercalejos)
		(cerca-node ?cerca-branch))
	?answer <- (answer cerca)
	=>
		(retract ?node ?answer)
		(assert (current-node ?cerca-branch)))

;;lejos
(defrule proceed-to-lejos-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisioncercalejos)
		(lejos-node ?lejos-branch))
	?answer <- (answer lejos)
	=>
		(retract ?node ?answer)
		(assert (current-node ?lejos-branch))
)




;;decisión de deporte si o no
(defrule ask-decision-deportes-node-question
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisiondeporte)
		(question ?question))
		(not (answer ?))
	=>
		(printout t ?question " (si o no) ")
		(assert (answer (read)))
)

;;ver si la decision es correcta
(defrule bad-answer-deporte
(declare(salience 10))
	?node <- (current-node node8|node9)
	?answer <- (answer ~si&~no)
	=>
		(retract ?answer))


;;si
(defrule proceed-to-si1-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisiondeporte)
		(si1-node ?si1-branch))
	?answer <- (answer si)
	=>
(assert (Pr3 si))
		(retract ?node ?answer)
		(assert (current-node ?si1-branch)))

;;no
(defrule proceed-to-no1-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisiondeporte)
		(no1-node ?no1-branch))
	?answer <- (answer no)
	=>
(assert (Pr3 no))
		(retract ?node ?answer)
		(assert (current-node ?no1-branch))
)	
		
		
		
		
;;excursion si o no
(defrule ask-decision-excursion-node-question
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionexcursion)
		(question ?question))
		(not (answer ?))
	=>
		(printout t ?question " (si o no) ")
		(assert (answer (read)))
)

;;ver si la decision es correcta
(defrule bad-answer-excursion
(declare(salience 10))
	?node <- (current-node node11)
	?answer <- (answer ~si&~no)
	=>
		(retract ?answer))


;;si
(defrule proceed-to-si2-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionexcursion)
		(si2-node ?si2-branch))
	?answer <- (answer si)
	=>

		(retract ?node ?answer)
		(assert (current-node ?si2-branch)))

;;no
(defrule proceed-to-no2-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionexcursion)
		(no2-node ?no2-branch))
	?answer <- (answer no)
	=>

		(retract ?node ?answer)
		(assert (current-node ?no2-branch))
)	
	

;;salir si o no
(defrule ask-decision-noche-node-question
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionnoche)
		(question ?question))
		(not (answer ?))
	=>
		(printout t ?question " (si o no) ")
		(assert (answer (read)))
)

;;ver si la decision es correcta
(defrule bad-answer-noche
(declare(salience 10))
	?node <- (current-node node10|node12|node13)
	?answer <- (answer ~si&~no)
	=>
		(retract ?answer))


;;si
(defrule proceed-to-si3-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionnoche)
		(si3-node ?si3-branch))
	?answer <- (answer si)
	=>
		(assert (Pr4 si))
		(retract ?node ?answer)
		(assert (current-node ?si3-branch)))

;;no
(defrule proceed-to-no3-branch
(declare(salience 10))
	?node <- (current-node ?name)
		(node 
		(name ?name)
		(type decisionnoche)
		(no3-node ?no3-branch))
	?answer <- (answer no)
	=>
		(assert (Pr4 no))
		(retract ?node ?answer)
		(assert (current-node ?no3-branch))
)	



