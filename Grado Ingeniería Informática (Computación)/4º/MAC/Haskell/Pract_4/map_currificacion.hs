suma3::Num a => a -> a -> a -> a
suma3 x y z = x + y + z

funcionMapCurrificada::[(a->b)] -> [a] -> [b]
funcionMapCurrificada _ [] = []
funcionMapCurrificada (f:fs) (x:xs) = f x : (funcionMapCurrificada fs fx)
