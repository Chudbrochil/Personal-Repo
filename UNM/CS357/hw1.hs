{-
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS357 Homework 1
-}


-- 1.1 test
test :: Int -> Int -> Bool
test n m = n `mod` 2 == 1 && m `mod` 2 == 1

-- 1.2 # 1 stutter
stutter :: [Char] -> [Char]
stutter [] = []
stutter (x:xs) = x : x : stutter(xs)


-- 1.2 # 2 compress TODO: Get explanation on why I needed [a] as a base case... because I'm doing x:y:xs?
compress :: [Char] -> [Char]
compress [a] = [a]
compress (x:y:xs) = if x /= y then x : compress (y:xs) else compress (y:xs)

-- 1.2 # 3 zipSum
zipSum :: [Int] -> [Int] -> [Int]
zipSum [] [] = []
zipSum (x:xs) [] = (x:xs)
zipSum [] (x:xs) = (x:xs)
zipSum (x:xs) (y:ys) = (x+y) : zipSum xs ys


-- 1.3 # 1 setUnion TODO: NOT DONE
setUnion :: [Integer] -> [Integer] -> [Integer]
setUnion [] [] = []
setUnion (x:xs) [] = (x:xs)
setUnion [] (x:xs) = (x:xs)
setUnion (x:xs) (y:ys) = if x == y then x : setUnion xs ys else setUnion xs (y:ys)


-- 1.3 # 2 setIntersection

-- 1.3 # 3 setDifference

-- 1.3 # 4 setEqual





-- 1.4 # 1 dr






-- In class take replacement
take' :: Int -> [a] -> [a]
take' 0 _ = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs



