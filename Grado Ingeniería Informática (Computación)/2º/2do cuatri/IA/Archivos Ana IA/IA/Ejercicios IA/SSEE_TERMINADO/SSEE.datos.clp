
(deffacts current-node
		(current-node root))


(deftemplate node
	(field name)
	(field type)
	(field question)
	(field otonyo-node)
	(field invierno-node)
	(field primavera-node)
	(field verano-node)
	(field playa-node)
	(field ciudad-node)
	(field naturaleza-node)
	(field cerca-node)
	(field lejos-node)
	(field si1-node)
	(field no1-node)
	(field si2-node)
	(field no2-node)
	(field si3-node)
	(field no3-node)
			;;;Definimos aquí todos los nodos del árbol
	(field answer)
)


;;inicio (estación)=
(deffacts root
	(node 
	(name root)
	(type decisionestacion) 
	(question "En que estacion le gustaria viajar?") 
	(otonyo-node node1) 
	(invierno-node node2) 
	(primavera-node node3)
	(verano-node node4)
	(answer nil))	

)

;;elecciónpaisaje
(deffacts node1
	(node
	(name node1)
	(type decisionpaisaje) 
	(question "Que paisaje desea?") 
	(playa-node node5)
	(ciudad-node node6)
	(naturaleza-node node7)
	(answer nil))
	

)
(deffacts node2
	(node 
	(name node2)
	(type decisionpaisaje) 
	(question "Que paisaje desea?") 
	(playa-node node5)
	(ciudad-node node6)
	(naturaleza-node node7)
	(answer nil))	

)

(deffacts node3
	(node 
	(name node3)
	(type decisionpaisaje) 
	(question "Que paisaje desea?") 
	(playa-node node5)
	(ciudad-node node6)
	(naturaleza-node node7)
	(answer nil))	

)

(deffacts node4
	(node 
	(name node4)
	(type decisionpaisaje) 
	(question "Que paisaje desea?") 
	(playa-node node5)
	(ciudad-node node6)
	(naturaleza-node node7)	
	(answer nil))
)

;
(deffacts node5
	(node 
	(name node5)
	(type decisioncercalejos) 
	(question "Cerca o lejos?") 
	(cerca-node node8)
	(lejos-node node9)	
	(answer nil))
)

;;cerca o lejos
(deffacts node6
	(node 
	(name node6)
	(type decisioncercalejos) 
	(question "Cerca o lejos?") 
	(cerca-node node8)
	(lejos-node node9)	
	(answer nil))
)

(deffacts node7
	(node 
	(name node7)
	(type decisioncercalejos) 
	(question "Cerca o lejos?") 
	(cerca-node node8)
	(lejos-node node9)	
	(answer nil))
)

;;deporte si o no
(deffacts node8
	(node 
	(name node8)
	(type decisiondeporte) 
	(question "Te gustan los deportes?") 
	(si1-node node10)
	(no1-node node11)	
	(answer nil))
)

(deffacts node9
	(node 
	(name node9)
	(type decisiondeporte) 
	(question "Te gustan los deportes?")
	(si1-node node10)
	(no1-node node11)	
	(answer nil))
)


;;deporte si => quieres salir noche
(deffacts node10
	(node 
	(name node10)
	(type decisionnoche) 
	(question "Salir por la noche?") 
	(si3-node node14)
	(no3-node node15)	
	(answer nil))
)

;;deporte no => Te gustan las excursiones
(deffacts node11
	(node 
	(name node11)
	(type decisionexcursion) 
	(question "Te gustan las excursiones?")
	(si2-node node12)
	(no2-node node13)	
	(answer nil))
)


;;excursión si => quieres salir noche
(deffacts node12
	(node 
	(name node12)
	(type decisionnoche) 
	(question "Salir por la noche?") 
	(si3-node node14)
	(no3-node node15)	
	(answer nil))
)

;;excursión no
(deffacts node13
	(node 
	(name node13)
	(type decisionnoche) 
	(question "Salir por la noche?")
	(si3-node node14)
	(no3-node node15)	
	(answer nil))
)


