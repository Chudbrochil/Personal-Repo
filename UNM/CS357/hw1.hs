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
compress [] = [] -- Need this base case for case in which you call compress ""
compress [a] = [a]
compress (x:y:xs) = if x /= y then x : compress (y:xs) else compress (y:xs)

-- 1.2 # 3 zipSum
zipSum :: [Int] -> [Int] -> [Int]
zipSum [] [] = []
zipSum (x:xs) [] = (x:xs)
zipSum [] (x:xs) = (x:xs)
zipSum (x:xs) (y:ys) = (x+y) : zipSum xs ys



-- Above this line has been thoroughly tested an is done. 
-- So far 1.3 #1, 1.3 #3 and 1.4 not done
-- Everything below this hasn't been rigorously tested


-- 1.3 # 1 setUnion TODO: NOT DONE
setUnion :: [Integer] -> [Integer] -> [Integer]
setUnion [] _ = []
setUnion _ [] = []
setUnion (x:xs) (y:ys) = if notElem x (y:ys) then setUnion xs (y:ys) else x : setUnion (xs) (y:ys)

-- 1.3 # 2 setIntersection
setIntersection :: [Integer] -> [Integer] -> [Integer]
setIntersection [] _ = []
setIntersection _ [] = []
setIntersection xs ys = [x | x <- xs, y <- ys, x == y]

-- 1.3 # 3 setDifference (anti-union) TODO: Does not work for two distinct lists, just returns (x:xs)
setDifference :: [Integer] -> [Integer] -> [Integer]
setDifference [] _ = []
setDifference _ [] = []
setDifference (x:xs) (y:ys) = if elem x (y:ys) then setDifference xs (y:ys) else x : setDifference (xs) (y:ys)


-- 1.3 # 4 setEqual
setEqual :: [Integer] -> [Integer] -> Bool
setEqual [] _ = False
setEqual _ [] = False
setEqual xs ys = xs == ys -- == returns a bool...

-- 1.4 # 1 dr TODO: NOT DONE
--dr :: Integer -> Int
--dr a =  if a < 10 then a else a `mod` 10 + dr a -- I don't think base case catches everything



