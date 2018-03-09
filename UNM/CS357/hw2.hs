{-
Anthony Galczak
agalczak@unm.edu
-}

--No other imports are allowed
import Data.List

--2.1
collatz :: [Int] -> Int
collatz xs = fst (findMax (collatzNums xs) (0,0))

-- Returns the max tuple
findMax :: [(Int, Int)] -> (Int, Int) -> (Int, Int)
findMax [] max = max
findMax (x:xs) max -- = if snd x >= snd max then findMax xs x else findMax xs max
	-- If # of terms is higher, set max to x
	| snd x > snd max = findMax xs x
	-- If # of terms is equal, set max to x if the num itself is higher
	| (snd x >= snd max) && (fst x > fst max) = findMax xs x
	| otherwise = findMax xs max

-- Returns a tuple of (num, collatzHelper num)
collatzNums :: [Int] -> [(Int, Int)]
collatzNums [] = []
collatzNums (x:xs) = (x, collatzHelper x) : collatzNums xs

-- Returns the collatz value of a given number
collatzHelper :: Int -> Int
collatzHelper 1 = 1
collatzHelper n
	| n `mod` 2 == 0 = 1 + (collatzHelper (n `div` 2))
	| otherwise = 1 + (collatzHelper (n*3 + 1))

--2.2
haskellFileNames :: [String] -> [String]
haskellFileNames xs = filter (\x -> specialSuffix ".hs" x || specialSuffix ".lhs" x) xs

-- Helper for 2.2
-- Basically is a "isSuffixOf" that trims white space at end, easily expandable to 
-- other character types to trim like . , ' etc.
specialSuffix :: [Char] -> [Char] -> Bool
specialSuffix [] ys = False
specialSuffix xs [] = False
specialSuffix xs ys = isSuffixOf xs [y | y <- ys, not (elem y " ")]


--2.3
select :: (t -> Bool) -> [t] -> [a] -> [a]
select p [] _ = []
select p _ [] = []
-- If the predicate of num is true, add x to our list
select p (num:nums) (x:xs) = if p num then x : select p nums xs else select p nums xs


--2.4
prefixSum :: [Int] -> [Int]
prefixSum xs = scanl1 (+) xs

--2.5
numbers :: [Int] -> Int
-- Point-free add each element of the list with *10
numbers = foldl ( (+) . (*10) ) 0


--2.6
type Numeral = (Int, [Int])

example = (10, [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0])

--2.6 1
makeLongInt :: Integer -> Int -> Numeral
makeLongInt = undefined

--2.6 2
evaluateLongInt :: Numeral -> Integer
evaluateLongInt = undefined

--2.6 3
changeRadixLongInt :: Numeral -> Int -> Numeral 
changeRadixLongInt = undefined

--2.6 4
addLongInts :: Numeral -> Numeral -> Numeral
addLongInts = undefined

--2.6 5
mulLongInts :: Numeral -> Numeral -> Numeral
mulLongInts = undefined
