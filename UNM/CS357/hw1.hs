{-
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS357 Homework 1
-}

import Data.List

-- 1.1 test
test :: Int -> Int -> Bool
test n m = n `mod` 2 == 1 && m `mod` 2 == 1

-- 1.2 # 1 stutter
stutter :: [Char] -> [Char]
stutter [] = []
stutter (x:xs) = x : x : stutter(xs)

-- 1.2 # 2 compress 
compress :: [Char] -> [Char]
compress [] = [] -- Need this base case for case in which you call compress ""
compress [a] = [a]
compress (x:y:xs) = if x == y then x : compress xs else x : y : compress xs

-- 1.2 # 3 zipSum
zipSum :: [Int] -> [Int] -> [Int]
zipSum [] [] = []
zipSum (x:xs) [] = (x:xs)
zipSum [] (x:xs) = (x:xs)
zipSum (x:xs) (y:ys) = (x+y) : zipSum xs ys

-- 1.3 # 1 setUnion
-- Decided that iterating over the list manually was more in spirit of the 
-- assignment, but the nub solution seems more concise
setUnion :: [Integer] -> [Integer] -> [Integer]
--setUnion xs ys = nub (xs ++ ys) -- This requires no base case, slick...
setUnion xs [] = xs
setUnion [] ys = ys
setUnion (x:xs) (y:ys)
    | x < y = x : setUnion xs (y:ys)
    | x == y = setUnion xs (y:ys)
    | x > y = y : setUnion (x:xs) ys

-- 1.3 # 2 setIntersection
-- list comprehension makes this easier to understand
setIntersection :: [Integer] -> [Integer] -> [Integer]
setIntersection [] _ = []
setIntersection xs ys = [x | x <- xs, y <- ys, x == y]

-- 1.3 # 3 setDifference (anti-union)
-- Only difference here between setUnion is we skip both x and y on ==
setDifference :: [Integer] -> [Integer] -> [Integer]
setDifference xs [] = xs
setDifference [] ys = ys
setDifference (x:xs) (y:ys)
    | x < y = x : setDifference xs (y:ys)
    | x == y = setDifference xs ys
    | x > y = y : setDifference (x:xs) ys

-- 1.3 # 4 setEqual
-- == returns a bool
setEqual :: [Integer] -> [Integer] -> Bool
setEqual xs ys = xs == ys 

-- 1.4 dr
digits :: Integer -> [Integer]
digits 0 = []
digits n = digits (div n 10) ++ [mod n 10]

dr :: Integer -> Int
dr a
        | (length (digits a)) > 1 = dr (sum (digits a))
        | otherwise = fromIntegral (sum (digits a) )

