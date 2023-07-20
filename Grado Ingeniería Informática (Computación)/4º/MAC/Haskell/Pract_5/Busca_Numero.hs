--Ejecicio 1 practica 6

juego::IO()
juego
	do putStrLn "Piensa en un numero entre el 1 y el 100."
	  busca_numero 1 100
	  putStrLn "Fin del juego"
	  
busca_numero:: Int -> Int-> IO()
busca_numero a b = 