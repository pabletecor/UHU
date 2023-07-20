-- Funcion que devuelva los valores mayores de 50 y menores de 100 de una lista infinita que comienza por 10
-- con incrementos de 10

filter (50<) (take 9 (iterate (+10) 10))