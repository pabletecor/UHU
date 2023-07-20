-- filter2 (==1) [1,2,3]
filter2::(a->Bool) -> [a] -> [a]

filter2 f [] = []
filter2 p xs = [x | x <- xs, p xs]