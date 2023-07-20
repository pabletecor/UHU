(deftemplate Articulo
 (field Nombre)
 (field Tipo)  
 (field Forrado)    
 (field Empaquetado)  
 (field Dimension)  
)

(deftemplate Caja
 (field IdCaja)
 (field Abierta)  
 (field Empezada)   
 (field TipoContenido)  
 (field EspacioLibre))


(deffacts Inventario
	(Articulo
		(Nombre bombilla)
		(Tipo fragil)
		(Forrado No)
		(Empaquetado No)
		(Dimension 10))

	(Articulo
		(Nombre martillo)
		(Tipo pesado)
		(Forrado No)
		(Empaquetado No)
		(Dimension 10))

	(Articulo
		(Nombre mancuernas)
		(Tipo pesado)
		(Forrado No)
		(Empaquetado No)
		(Dimension 5))

	(Articulo
		(Nombre microprocesadores)
		(Tipo fragil)
		(Forrado No)
		(Empaquetado No)
		(Dimension 10)))

(deffacts MisCajas
	(Caja
		(IdCaja 1)
		(Abierta Si)
		(Empezada No)
		(TipoContenido fragil)
		(EspacioLibre 6))

	(Caja
		(IdCaja 2)
		(Abierta Si)
		(Empezada No)
		(TipoContenido pesado)
		(EspacioLibre 15))

	(Caja
		(IdCaja 3)
		(Abierta No)
		(Empezada No)
		(TipoContenido fragil)
		(EspacioLibre 5)))