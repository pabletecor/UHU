--El programa le pide al jugador humano que piense un número entre 1 y 100 y tratara de acertar
--el número que ha pensado preguntando al jugador. El jugador responderá encontrado, mayor o
--menor y en función de la respuesta, se realizara una modificación del número buscado
--mejorando el ejercicio que vimos en clase que realizaba una búsqueda secuencial. Esta
--modificación se realizará calculando el nuevo número de la siguiente forma:
--proximo = (x+y) div 2

juego_busqueda_mejorada :: IO () --Nuestra funcion juego_busqueda_mejorada es una funcion que devuelte el tipo de dato IO, y no tiene ninguna entrada.
juego_busqueda_mejorada =
    do putStrLn "Piensa un numero entre el 1 y el 100." --Usando el bloque do definimos la secuencia de acciones
       adivina_numero 1 100 --Entramos por primera vez en la funcion adivina_numero con el rango que nos dice el enunciado: entre 1 y 100
       putStrLn "Fin del juego"

adivina_numero :: Int -> Int -> IO () -- A esta función que usaremos recursivamente se le pasan dos Int (rango del numero a adivinar) y 
--devuelve un tipo IO (salida de la funcion) con return()
adivina_numero x y =
    do putStr ("Piensas en el numero " ++ show proximo ++ "? [No es mayor? / No es menor? / encontrado] ") --Imprimos por pantalla el número que creemos que puede ser
       respuesta <- getLine --Realizamos la operacion de entrada/salida de leer por pantalla con getline y la almacenamos en la variable respuesto con <-
       case respuesta of
         "mayor"  -> adivina_numero (proximo+1) y --Recursivamente llamamos a la funcion con un rango mayor
         "menor"  -> adivina_numero x (proximo-1) --Recursivamente llamamos a la funcion con un rango menor
         "encontrado" -> return () --Salimos de la funcion adivina_numero y volvemos a juego_busqueda_mejorada
         _        -> adivina_numero x y --Recursivamente llamamos a la funcion con el mismo rango 
    where
      proximo = (x+y) `div` 2