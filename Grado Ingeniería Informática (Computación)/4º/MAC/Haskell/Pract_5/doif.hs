--Entrada y salida: bloque do con if

doconif = do	
	putStrLn "En que numero estoy pensando?"
	demo <- getLine
	if demo == "5"
	then putStrLn "Lo has escrito bien"
	else putStrLn "Has fallado"
	

main = do	
	line <- getLine
	if null line
	then return ()
	else do 
		putStrLn (reverseWords line)
		main
		
reverseWords::String -> String
reverseWords = unwords . map reverse . words

