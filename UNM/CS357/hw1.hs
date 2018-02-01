{-
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS357 Homework 1
-}



-- 1.1
test :: Int -> Int -> Bool
test n m = n `mod` 2 == 1 && m `mod` 2 == 1



-- 1.2 # 1
stutter :: [Char] -> [Char]
stutter [] = []
stutter (x:xs) = x : x : stutter(xs)


-- 1.2 # 2
compress :: [Char] -> [Char]
compress [] = []
compress (x:y:xs) = if x == y then compress (x:xs) else x:compress (xs)






-- In class take replacement
take' :: Int -> [a] -> [a]
take' 0 _ = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs


-- My failed attempt at take
{-

take1 :: Int -> [a] -> [a]
take1 n [] = []
take1 0 (x:xs) = x:xs
take1 n (x:xs) = take1 (n-1) xs

-}


