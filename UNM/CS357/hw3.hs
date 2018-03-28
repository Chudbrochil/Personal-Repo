
{-
Anthony Galczak
agalczak@unm.edu
-}

import Data.List

--3.1
--Ex.4 on pg.109 of programming in haskell book
balance :: [a] -> Tree a
--balance [] = Leaf -- TODO: Is this case right? What should this be?
balance [x] = Leaf x
balance xs = Node (balance leftHalf) (balance rightHalf)
	where (leftHalf, rightHalf) = splitHalf xs

data Tree a = Leaf a | Node (Tree a) (Tree a) deriving (Eq, Show)

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
-- x + y == n makes sure it equals the number n, x <= y verifies we only get 1 permutation
-- the if-else check makes sure that the number is even and >2
goldbach :: Int -> [(Int, Int)]
goldbach n = if n > 2 && n `mod` 2 == 0 then gold' else []
	where gold' = [(x,y) | x <- primes n, y <- primes n, x + y == n, x <= y]


--3.3
--Higher-order Functions
-- Think of foldr (+) 0 [1,2,3] == 3 + (2 + (1 + 0))
-- If we use the given example of (church 4) tail "ABCDEFGH"
-- In this case it is (tail . tail . tail . tail . id) applied to "ABCDEFGH"
church :: Int -> (c -> c) -> c -> c
church n = (\f -> foldr (.) id (replicate n f)) 


--3.4 Recursive Functions Over Lists (10pts)
type Set = [Int]

powerset :: [Int] -> [[Int]]
powerset = subsequences

--3.5 Lists And Strings (10pts)
example :: [[(Double, Double)]]
example = [[(100.0,100.0),(100.0,200.0),(200.0,100.0)],
  [(150.0,150.0),(150.0,200.0),(200.0,200.0),(200.0,150.0)]]

makeCommand :: [[(Double, Double)]] -> String
makeCommand = undefined

--3.6 Trees (25pts)
{-data T = Leaf | Node T T deriving (Eq, Show)

data P = GoLeft P | GoRight P | This deriving (Eq, Show)

allpaths :: T -> [P]
allpaths = undefined
-}
--3.7 Logic (25pts)
type Expr = [[Int]]

eval :: (Int -> Bool) -> Expr -> Bool
eval = undefined

satisfiable :: Expr -> Bool
satisfiable = undefined








