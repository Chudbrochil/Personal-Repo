
{-
Anthony Galczak
agalczak@unm.edu
-}

module Homework3
( balance,
  goldbach,
	church,
	powerset,
	makeCommand,
	allpaths,
	eval,
	satisfiable
	) where

import Data.List

--3.1
--Ex.4 on pg.109 of programming in haskell book
balance :: [a] -> Tree a
balance [x] = LeafT x
balance xs = NodeT (balance leftHalf) (balance rightHalf)
	where (leftHalf, rightHalf) = splitHalf xs

data Tree a = LeafT a | NodeT (Tree a) (Tree a) deriving (Eq, Show)

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

-- Builds a string for a given tuple of double's for PostScript
parseTuple :: String -> (Double, Double) -> String
parseTuple str tup = show (fst tup) ++ " " ++ show (snd tup) ++ str

-- Builds the entire string for a whole list of tuples in PostScript
parseList :: [(Double, Double)] -> String
parseList xs = (foldl (++) start rest) ++ "closepath\nstroke\n\n"
	where {start = parseTuple " moveto\n" (head xs);
				rest = map (parseTuple " lineto\n") (tail xs)}

-- Gets the string needed for the line of the bounding box for PostScript
calcBoundingBox :: [(Double, Double)] -> String
calcBoundingBox xs = "%%BoundingBox: " ++ parseTuple " " lowerLeft ++ parseTuple "" upperRight ++ "\n\n"
	where {lowerLeft = head (sort xs);
				 upperRight = last (sort xs)}

makeCommand :: [[(Double, Double)]] -> String
makeCommand xss = start ++ points ++ end
	where {start = "%!PS-Adobe-3.0 EPSF-3.0\n" ++ calcBoundingBox (concat xss);
				 points = concat (map parseList xss);
			 	 end = "showpage\n%%EOF\n"}


--3.6 Trees (25pts)
data T = Leaf | Node T T deriving (Eq, Show)

data P = GoLeft P | GoRight P | This deriving (Eq, Show)

allpaths :: T -> [P]
allpaths = undefined
--allpaths Leaf = This
--allpaths Node T1 T2 = This : (GoLeft allpaths T1)

--3.7 Logic (25pts)
type Expr = [[Int]]

checkList :: (Int -> Bool) -> [Int] -> Bool
checkList p [] = False -- Only one value has to be true, if we get to base case, then nothing was true
checkList p (x:xs)
	| x < 0 = if p x == False then True else checkList p xs    -- Negative numbers are "not's"
	| otherwise = if p x then True else checkList p xs

eval :: (Int -> Bool) -> Expr -> Bool
eval _ [] = True
eval p (x:xs) = checkList p x && eval p xs




satisfiable :: Expr -> Bool
satisfiable [] = False
-- x is a list of ints
--satisfiable (x:xs) = eval (>=0) (clauses x)
--satisfiable (x:xs) = eval (elem )
--satisfiable xs = eval (tval row) xs
satisfiable xs = or [True | row <- tableGen xs, eval (tval row) xs]


tval :: [Int] -> Int -> Bool
tval xs i = specialElem i xs


tableGen :: [[Int]] -> [[Int]]
tableGen xs = clauses (nub (map abs (concat xs)))

specialElem :: Int -> [Int] -> Bool
specialElem e xs = elem e xs || elem (-e) xs



-- We want to make a list of all permutations, so a list of lists for each clause
-- [1,2] = [[1,2],[-1,2],[1,-2],[-1,-2]]

-- Input looks like [1,2], outputs [[1,-1],[2,-2]]. This is a very natural segway
-- into using sequence to grab 1 element from each list
negAndPos :: [Int] -> [[Int]]
negAndPos [] = []
negAndPos (x:xs) = [x, -x] : negAndPos xs

-- Generates all of our possible permutations for a given clause
clauses :: [Int] -> [[Int]]
clauses xs = [clause | clause <- sequence (negAndPos xs), length (clause) == length(xs)]


-- The memory cost on this will be huge... scales something like 2^(n^k), ew, n length of clause, k number of clauses
--allClauses :: [[Int]] -> [[[Int]]]
--allClauses xss = undefined
-- So I won't do it...
