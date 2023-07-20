(deftemplate node

(slot name)

(slot type)

(slot question)

(slot otonyo-node)

(slot invierno-node)

(slot primavera-node)

(slot verano-node)

(slot playa-node)

(slot ciudad-node)

(slot naturaleza-node)

(slot lejos-node)

(slot cerca-node)

(slot europa-node)

(slot noeuropa-node)

(slot central-node)

(slot oriental-node)

(slot occidental-node)

(slot norte-node)

(slot yes-node)

(slot no-node)

(slot frio-node)

(slot calor-node)

(slot relaja-node)

(slot deportes-node)

(slot nieve-node)

(slot lagos-node)

(slot compras-node)

(slot arte-node)

(slot desierto-node)

(slot montanya-node)

(slot answer))

(defrule initialize-1

(not (node (name root)))

=>

(load-facts "destinos.dat")

(assert (current-node root)))

(defrule initialize-2

(declare (salience 100))

?fact <- (next-gensym-id ?id)

=>

(retract ?fact)

(setgen ?id))

 
;;; DEFINO LA ESTACION 

(defrule ask-decision-estacion-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decision)

(question ?question))

(not (answer1 ?))

=>

(printout t ?question " (otonyo, invierno, verano, primavera) ")

(assert (answer1 (read))))

(defrule bad-answer-estacion

?answer1 <- (answer1 ~otonyo&~invierno&~verano&~primavera)

=>

(retract ?answer1))

(defrule proceed-to-otonyo-branch

?node <- (current-node ?name)

(node (name ?name)

(type decision)

(otonyo-node ?otonyo-branch))

?answer1 <- (answer1 otonyo)

=>

(retract ?node ?answer1)

(assert (current-node ?otonyo-branch)))

(defrule proceed-to-invierno-branch

?node <- (current-node ?name)

(node (name ?name)

(type decision)

(invierno-node ?invierno-branch))

?answer1 <- (answer1 invierno)

=>

(retract ?node ?answer1)

(assert (current-node ?invierno-branch)))

(defrule proceed-to-primavera-branch

?node <- (current-node ?name)

(node (name ?name)

(type decision)

(primavera-node ?primavera-branch))

?answer1 <- (answer1 primavera)

=>

(retract ?node ?answer1)

(assert (current-node ?primavera-branch)))

(defrule proceed-to-verano-branch

?node <- (current-node ?name)

(node (name ?name)

(type decision)

(verano-node ?verano-branch))

?answer1 <- (answer1 verano)

=>

(retract ?node ?answer1)

(assert (current-node ?verano-branch)))


;;;DEFINO PLAYA, CIUDAD O NATURALEZA


(defrule ask-decision-paisaje-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionpaisaje)

(question ?question))

(not (answer ?))

=>

(printout t ?question " (playa, ciudad o naturaleza) ")

(assert (answer2 (read))))

(defrule bad-answer-paisaje

?answer2 <- (answer ~playa & ~ciudad & ~naturaleza)

=>

(retract ?answer2))

(defrule proceed-to-playa-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionpaisaje)

(playa-node ?playa-branch))

?answer2 <- (answer2 playa)

=>

(retract ?node ?answer2)

(assert (current-node ?playa-branch)))

(defrule proceed-to-ciudad-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionpaisaje)

(ciudad-node ?ciudad-branch))

?answer2 <- (answer2 ciudad)

=>

(retract ?node ?answer2)

(assert (current-node ?ciudad-branch)))

(defrule proceed-to-naturaleza-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionpaisaje)

(naturaleza-node ?naturaleza-branch))

?answer2 <- (answer2 naturaleza)

=>

(retract ?node ?answer2)

(assert (current-node ?naturaleza-branch)))




;;; LEJOS O CERCA

(defrule ask-decision-cerca-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisioncerca)

(question ?question))

(not (answer3 ?))

=>

(printout t ?question " (cerca o lejos) ")

(assert (answer3 (read))))

(defrule bad-answer-cerca

?answer3 <- (answer3 ~cerca&~lejos)

=>

(retract ?answer3))

(defrule proceed-to-lejos-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncerca)

(lejos-node ?lejos-branch))

?answer3 <- (answer3 lejos)

=>

(retract ?node ?answer3)

(assert (current-node ?lejos-branch)))

(defrule proceed-to-cerca-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncerca)

(cerca-node ?cerca-branch))

?answer3 <- (answer3 cerca)

=>

(retract ?node ?answer3)

(assert (current-node ?cerca-branch)))




;;;EUROPA O FUERA EUROPA



(defrule ask-decision-europa-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisioneuropa)

(question ?question))

(not (answer4 ?))

=>

(printout t ?question " ( Europa o fuera ) ")

(assert (answer4 (read))))

(defrule bad-answer-europa

?answer4 <- (answer4 ~Europa&~fuera)

=>

(retract ?answer4))

(defrule proceed-to-europa-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioneuropa)

(europa-node ?europa-branch))

?answer4 <- (answer4 Europa)

=>

(retract ?node ?answer4)

(assert (current-node ?europa-branch)))

(defrule proceed-to-noeuropa-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioneuropa)

(noeuropa-node ?noeuropa-branch))

?answer4 <- (answer4 fuera)

=>

(retract ?node ?answer4)

(assert (current-node ?noeuropa-branch)))




;;; COMPRAS O ARTE




(defrule ask-decision-compras-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisioncompras)

(question ?question))

(not (answer5 ?))

=>

(printout t ?question " ( compras o arte ) ")

(assert (answer5 (read))))

(defrule bad-answer-compras

?answer5 <- (answer5 ~compras&~arte)

=>

(retract ?answer5))

(defrule proceed-to-compras-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncompras)

(compras-node ?compras-branch))

?answer5 <- (answer5 compras)

=>

(retract ?node ?answer5)

(assert (current-node ?compras-branch)))

(defrule proceed-to-arte-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncompras)

(arte-node ?arte-branch))

?answer5 <- (answer5 arte)

=>

(retract ?node ?answer5)

(assert (current-node ?arte-branch)))


;;; NIEVE O LAGOS

(defrule ask-decision-nieve-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionnieve)

(question ?question))

(not (answer6 ?))

=>

(printout t ?question " ( nieve o lagos ) ")

(assert (answer6 (read))))

(defrule bad-answer-nieve

?answer6 <- (answer6 ~nieve&~lagos)

=>

(retract ?answer6))

(defrule proceed-to-nieve-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionnieve)

(nieve-node ?nieve-branch))

?answer6 <- (answer6 nieve)

=>

(retract ?node ?answer6)

(assert (current-node ?nieve-branch)))

(defrule proceed-to-lagos-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionnieve)

(lagos-node ?lagos-branch))

?answer6 <- (answer6 lagos)

=>

(retract ?node ?answer6)

(assert (current-node ?lagos-branch)))



;;; DEPORTES O RELAJACION



(defrule ask-decision-relaja-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionrelaja)

(question ?question))

(not (answer7 ?))

=>

(printout t ?question " ( relajacion o deportes ) ")

(assert (answer7 (read))))

(defrule bad-answer-relaja

?answer7 <- (answer7 ~relajacion&~deportes)

=>

(retract ?answer7))

(defrule proceed-to-relaja-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionrelaja)

(relaja-node ?relaja-branch))

?answer7 <- (answer7 relajacion)

=>

(retract ?node ?answer7)

(assert (current-node ?relaja-branch)))

(defrule proceed-to-deportes-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionrelaja)

(deportes-node ?deportes-branch))

?answer7 <- (answer7 deportes)

=>

(retract ?node ?answer7)

(assert (current-node ?deportes-branch)))



;;; DESIERTO O MONTANYA 

(defrule ask-decision-desierto-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisiondesierto)

(question ?question))

(not (answer8 ?))

=>

(printout t ?question " ( desierto o montanya ) ")

(assert (answer8 (read))))

(defrule bad-answer-desierto

?answer8 <- (answer8 ~desierto&~montanya)

=>

(retract ?answer8))

(defrule proceed-to-desierto-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisiondesierto)

(desierto-node ?desierto-branch))

?answer8 <- (answer8 desierto)

=>

(retract ?node ?answer8)

(assert (current-node ?desierto-branch)))

(defrule proceed-to-montanya-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisiondesierto)

(montanya-node ?montanya-branch))

?answer8 <- (answer8 montanya)

=>

(retract ?node ?answer8)

(assert (current-node ?montanya-branch)))





 

;;; YES OR NO


(defrule ask-decision-yesno-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionbinaria)

(question ?question))

(not (answer9 ?))

=>

(printout t ?question " ( si o no ) ")

(assert (answer9 (read))))

(defrule bad-answer-binaria

?answer9 <- (answer9 ~si&~no)

=>

(retract ?answer9))

(defrule proceed-to-yes-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionbinaria)

(yes-node ?yes-branch))

?answer9 <- (answer9 si)

=>

(retract ?node ?answer9)

(assert (current-node ?yes-branch)))

(defrule proceed-to-no-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionbinaria)

(no-node ?no-branch))

?answer9 <- (answer9 no)

=>

(retract ?node ?answer9)

(assert (current-node ?no-branch)))




;;; EUROPA CENTRAL O NORTE


(defrule ask-decision-central-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisioncentral)

(question ?question))

(not (answer10 ?))

=>

(printout t ?question " ( central o norte ) ")

(assert (answer10 (read))))

(defrule bad-answer-central

?answer10 <- (answer10 ~central&~norte)

=>

(retract ?answer10))

(defrule proceed-to-central-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncentral)

(central-node ?central-branch))

?answer10 <- (answer10 central)

=>

(retract ?node ?answer10)

(assert (current-node ?central-branch)))

(defrule proceed-to-norte-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisioncentral)

(norte-node ?norte-branch))

?answer10 <- (answer10 norte)

=>

(retract ?node ?answer10)

(assert (current-node ?norte-branch)))





;;; ORIENTAL O OCCIDENTAL

(defrule ask-decision-oriental-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionoriental)

(question ?question))

(not (answer11 ?))

=>

(printout t ?question " ( oriental u occidental ) ")

(assert (answer11 (read))))

(defrule bad-answer-oriental

?answer11 <- (answer11 ~oriental&~occidental)

=>

(retract ?answer11))

(defrule proceed-to-oriental-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionoriental)

(oriental-node ?oriental-branch))

?answer11 <- (answer11 oriental)

=>

(retract ?node ?answer11)

(assert (current-node ?oriental-branch)))

(defrule proceed-to-occidental-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionoriental)

(occidental-node ?occidental-branch))

?answer11 <- (answer11 occidental)

=>

(retract ?node ?answer11)

(assert (current-node ?occidental-branch)))






;;; FRIO O CALOR

(defrule ask-decision-frio-node-question

?node <- (current-node ?name)

(node (name ?name)

(type decisionfrio)

(question ?question))

(not (answer12 ?))

=>

(printout t ?question " ( frio o calor ) ")

(assert (answer12 (read))))

(defrule bad-answer-frio

?answer12 <- (answer12 ~frio&~calor)

=>

(retract ?answer12))

(defrule proceed-to-frio-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionfrio)

(frio-node ?frio-branch))

?answer12 <- (answer12 frio)

=>

(retract ?node ?answer12)

(assert (current-node ?frio-branch)))

(defrule proceed-to-calor-branch

?node <- (current-node ?name)

(node (name ?name)

(type decisionfrio)

(calor-node ?calor-branch))

?answer12 <- (answer12 calor)

=>

(retract ?node ?answer12)

(assert (current-node ?calor-branch)))




;;;RESPUESTA CORRECTA




(defrule ask-if-answer-node-is-correct

?node <- (current-node ?name)

(node (name ?name) (type answer) (answer ?value))

(not (answer ?))

=>

(printout t "LE RECOMIENDO: " crlf ?value crlf)

(printout t "Le gusta alguno de estos destinos? (si o no) ")

(assert (answerF (read))))

(defrule answer-node-guess-is-correct

?node <- (current-node ?name)

(node (name ?name) (type answerF))

?answerF <- (answerF si)

=>

(assert (ask-try-again))

(retract ?node ?answerF))

(defrule answer-node-guess-is-incorrect

?node <- (current-node ?name)

(node (name ?name) (type answer))

?answerF <- (answerF no)

=>

(assert (replace-answer-node ?name))

(retract ?answerF ?node))

(defrule ask-try-again

(ask-try-again)

(not (answerF ?))

=>

(printout t "Otra prueba? (si o no) ")

(assert (answerF2 (read))))

(defrule one-more-time

?phase <- (ask-try-again)

?answerF2 <- (answerF2 si)

=>

(retract ?phase ?answerF2)

(assert (current-node root)))

(defrule no-more

?phase <- (ask-try-again)

?answer <- (answer no)

=>

(retract ?phase ?answer)

(bind ?g (gensym*))

(assert (next-gensym-id (sub-string 4 (str-length ?g) ?g)))

)

(defrule replace-answer-node

?phase <- (replace-answer-node ?name)

?data <- (node (name ?name)

(type answer)

(answer ?value))

=>

(retract ?phase)



; Determine if the player wants to try again

(assert (ask-try-again)))

 
