{-
Anthony Galczak
agalczak@unm.edu
-}

import Data.List

--3.1
--Ex.4 on pg.109 of programming in haskell book
balance :: [a] -> Tree a
balance xs = undefined



data Tree a = Leaf a | Node (Tree a) (Tree a)

splitHalf :: [a] -> ([a],[a])
splitHalf xs = splitAt (length xs `div` 2) xs


--3.2
--Goldbach conjecture

-- Finds all the factors of a given number n
factors :: Int -> [Int]
factors n = [x | x <- [1..n], n `mod` x == 0]

-- Confirms whether or not a number is prime
prime :: Int -> Bool
prime n = factors n == [1,n]

-- Gives all primes from 2 to n
primes :: Int -> [Int]
primes n = [x | x <- [2..n], prime x]


-- Gives a list of tuples each of which correspond to a prime+prime=n where
-- the tuple is (prime, prime)
-- TODO: Add case where if n isn't even or over 2 then return empty list (or something else...)
-- TODO: Do I need to have (3,89) and (89,3) or should I remove these?
goldbach :: Int -> [(Int, Int)]
goldbach n = [(x,y) | x <- primes n, y <- primes n, x + y == n]



--3.3
--Higher-order Functions
-- Think of foldr (+) 0 [1,2,3] == 3 + (2 + (1 + 0))
-- If we use the given example of (church 4) tail "ABCDEFGH"
-- In this case it is (tail . tail . tail . tail . id) applied to "ABCDEFGH"
church :: Int -> (c -> c) -> c -> c
church n = (\f -> foldr (.) id (replicate n f)) 





