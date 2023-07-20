--Obtener los numeros divisibles por el parametro que indiquemos de una lista de 100 numeros

divisibles::Integral a => a ->[a]

divisibles y = filter (\x -> mod x y== 0) [1..100]