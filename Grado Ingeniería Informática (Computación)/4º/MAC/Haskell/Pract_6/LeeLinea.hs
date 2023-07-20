import IO
main =  do hdl <- openFile "C:/Users/usuario/Desktop/texto.txt" ReadMode
	   lee hdl
lee hdl =  do t <- hIsEOF hdl
	      if t then return()
	           else do y <- hGetLine hdl
			   putStrLn y
			   lee hdl