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
findMax :: [(Int, Integer)] -> (Int, Integer) -> (Int, Integer)
findMax [] max = max
findMax (x:xs) max -- = if snd x >= snd max then findMax xs x else findMax xs max
	-- If # of terms is higher, set max to x
	| snd x > snd max = findMax xs x
	-- If # of terms is equal, set max to x if the num itself is higher
	| (snd x >= snd max) && (fst x > fst max) = findMax xs x
	| otherwise = findMax xs max

-- Returns a tuple of (num, collatzHelper num)
collatzNums :: [Int] -> [(Int, Integer)]
collatzNums [] = []
collatzNums (x:xs) = (x, collatzHelper x) : collatzNums xs

-- Returns the collatz value of a given number
collatzHelper :: Int -> Integer
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
-- Add each element of the list with *10
numbers :: [Int] -> Int
numbers nums = foldl ( (+) . (*10) ) 0 nums


--2.6
type Numeral = (Int, [Int])

example = (10, [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0])

--2.6 1
makeLongInt :: Integer -> Int -> Numeral
makeLongInt n r = (r, reverse(fixInts (getList n r)))

-- TODO: Eliminate this method
fixInts :: [Integer] -> [Int]
fixInts xs = map fromIntegral xs

getList :: Integer -> Int -> [Integer]
getList 0 r = []
getList n r = n `mod` toInteger r : getList (n `div` toInteger r) r


--2.6 2
-- Almost exact same as 2.5
evaluateLongInt :: Numeral -> Integer
evaluateLongInt (base, nums) = toInteger(foldl ( (+) . (*base) ) 0 nums)

--2.6 3
-- TODO: Remove integer arithmetic
-- First, make an int out our given list of ints.
-- Second, make a new list of ints with our new base
-- Reverse that and put it in a tuple with the new base
-- NOTE: THIS IS ALL DONE WITH JUST INT MATH, NOT INTEGER
changeRadixLongInt :: Numeral -> Int -> Numeral 
changeRadixLongInt (r1, ds1) r2 = (r2, reverse (newBaseList r2 (makeInt (r1, ds1))))

-- Makes an int number out of our initial base and nums
makeInt :: (Int, [Int]) -> Int
makeInt (r, nums) = foldl ((+) . (*r)) 0 nums

-- Divides our new int by the 2nd base given
newBaseList :: Int -> Int -> [Int]
newBaseList _ 0 = []
newBaseList r num = num `mod` r : newBaseList r (num `div` r) 


--2.6 4
-- TODO: Remove integer arithmetic
addLongInts :: Numeral -> Numeral -> Numeral
addLongInts (r1, ds1) (r2, ds2)
	| r1 == r2 = makeLongInt (evaluateLongInt (r1, ds1) + evaluateLongInt (r2, ds2)) r1
	| r1 < r2 = addLongInts (changeRadixLongInt (r1, ds1) r2) (r2, ds2)
	| r1 > r2 = addLongInts (r1, ds1) (changeRadixLongInt (r2, ds2) r1)

--2.6 5
-- TODO: Remove integer arithmetic
mulLongInts :: Numeral -> Numeral -> Numeral
mulLongInts (r1, ds1) (r2, ds2)
	| r1 == r2 = makeLongInt (evaluateLongInt (r1, ds1) * evaluateLongInt (r2, ds2)) r1
	| r1 < r2 = mulLongInts (changeRadixLongInt (r1, ds1) r2) (r2, ds2) 
	| r1 > r2 = mulLongInts (r1, ds1) (changeRadixLongInt (r2, ds2) r1)
