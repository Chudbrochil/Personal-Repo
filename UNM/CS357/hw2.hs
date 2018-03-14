{-
Anthony Galczak
agalczak@unm.edu
-}

module Homework2
( collatz, 
  haskellFileNames, 
  select, 
  prefixSum, 
  numbers, 
  Numeral, 
  makeLongInt, 
  evaluateLongInt, 
  changeRadixLongInt, 
  addLongInts, 
  mulLongInts
) where


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
makeLongInt n r = (r, reverse(map fromIntegral (getList n r)))

getList :: Integer -> Int -> [Integer]
getList 0 r = []
getList n r = n `mod` toInteger r : getList (n `div` toInteger r) r


--2.6 2
-- Almost exact same as 2.5
evaluateLongInt :: Numeral -> Integer
evaluateLongInt (base, nums) = foldl (evaluate' base) 0 nums

evaluate' :: Int -> Integer -> Int -> Integer
evaluate' base v x = (v * toInteger base) + (toInteger x)

--2.6 3
-- It took me somewhere around 15 hours to do 2.6.3-2.6.5
-- This results in a mixture of styles as I've approached this problem from different angles...
-- My multiplication is the "new style" that is leaner, "better", and was ultimately the only way I figured
-- out how to do multiplication of ints by a base.
-- My addition of lists is the "old style" which is rather reverse heavy. I got this working very early on and
-- have decided not to "fix it".
changeRadixLongInt :: Numeral -> Int -> Numeral
changeRadixLongInt (r1, ds1) r2 = (r2, dropWhile (==0) (radixHelper ds1 r1 r2 [0]))

-- This is ultimately the "horner method" in code
radixHelper :: [Int] -> Int -> Int -> [Int] -> [Int]
radixHelper [x] r1 r2 tally = listAdd tally [x] r2
radixHelper (x:xs) r1 r2 tally = radixHelper xs r1 r2 newTally
    where newTally = multiByNum (listAdd tally [x] r2) r1 r2

-- Recursive method for multiplying a single digit by a list of ints
-- This is useful for multiplying a list of ints by a given base or in a combined method
-- of multiplying a single int of a list of ints by a list of ints.
multiByNum :: [Int] -> Int -> Int -> [Int]
multiByNum (x:[]) y r2 = xy `div` r2 : [xy `mod` r2] -- We will reach this as the bottom (most right) digit of the multi
    where xy = x*y
multiByNum (x:xs) y r2 = xy `div` r2 : xy `mod` r2 : digit
    where (carry:digit) = multiByNum xs y r2
          xy = x*y + carry

-- Allows me to add lists of ints, i.e. list+ [1,2] [5] 7 = [2,0]  (Think: [1,2] + [5] = [2,0])
listAdd :: [Int] -> [Int] -> Int -> [Int]
listAdd xs ys r = reverse (fixBaseCarrys r 0 (reverse (addListOfInts (normalizeLists (length xs) ys) (normalizeLists (length ys) xs))))

-- We will likely have carries when adding two lists together. Fixing the bases 
fixBaseCarrys :: Int -> Int -> [Int] -> [Int]
fixBaseCarrys r rem [] = if rem == 0 then [] else [rem]
fixBaseCarrys r rem (x:xs)
    | sum >= r = (sum `mod` r) : fixBaseCarrys r (sum `div` r) xs
    | otherwise = sum : fixBaseCarrys r (sum `div` r) xs
    where sum = x + rem

-- Padding a given list with 0's on the left
normalizeLists :: Int -> [Int] -> [Int]
normalizeLists otherLen nums = take (otherLen - (length nums)) (repeat 0) ++ nums

addListOfInts :: [Int] -> [Int] -> [Int]
addListOfInts xs ys = zipWith (+) xs ys

--2.6 4
-- This is loosely based on the specification that was given to us. I have removed any semblance of Integer arithmetic via
-- (+) or in internal methods. All math in the following 3 methods is done strictly in Int arithmetic.
addLongInts :: Numeral -> Numeral -> Numeral
addLongInts (r1, ds1) (r2, ds2)
    | r1 == r2 = (r1, (reverse (addTwoSameBase r1 (reverseAndFix (length ds2) ds1) (reverseAndFix (length ds1) ds2) 0)))
    | r1 < r2 = addLongInts (changeRadixLongInt (r1, ds1) r2) (r2, ds2)
    | r1 > r2 = addLongInts (r1, ds1) (changeRadixLongInt (r2, ds2) r1)

-- Normalizes the lists against eachother. The function (addTwoSameBase) that the lists of ints will be passed into is
-- rather fragile so it needs the nums in opposite order and of same list length
reverseAndFix :: Int -> [Int] -> [Int]
reverseAndFix otherLen nums = reverse ((take (otherLen - (length nums)) (repeat 0)) ++ nums)

-- Adds two lists of ints together
-- If the addition will cause overflow of the given radix, then set the carry bit to 1
addTwoSameBase :: Int -> [Int] -> [Int] -> Int -> [Int]
addTwoSameBase r _ [] carry = if carry == 0 then [] else [carry]
addTwoSameBase r [] _ carry = if carry == 0 then [] else [carry]
addTwoSameBase r (x:xs) (y:ys) carry = if sum >= r then (sum `mod` r) : addTwoSameBase r xs ys 1
                       else sum : addTwoSameBase r xs ys 0
                       where sum = x + y + carry

--2.6 5
mulLongInts :: Numeral -> Numeral -> Numeral
mulLongInts (r1, ds1) (r2, ds2)
    | r1 == r2 = (r1, dropWhile (==0) (multTwoSameBase ds1 ds2 r1 0))
    | r1 < r2 = mulLongInts (changeRadixLongInt (r1, ds1) r2) (r2, ds2) 
    | r1 > r2 = mulLongInts (r1, ds1) (changeRadixLongInt (r2, ds2) r1)

-- Multiplies two lists of ints of the same base together. This uses the multiByNum from 2.6.3 to multiply a single
-- digit by a list of ints. Zeros keeps a running tally of where we are in our multiplications. When we move over
-- one more digit, we'll need to pad more zeros to the right. Just like grade-school multiplication
multTwoSameBase :: [Int] -> [Int] -> Int -> Int -> [Int]
multTwoSameBase [] ys r zeros = []
multTwoSameBase (x:xs) ys r zeros = listAdd list1 list2 r
    where list1 = addZeros (multiByNum ys (last (x:xs)) r) zeros
          list2 = multTwoSameBase (reverse((tail(reverse(x:xs))))) ys r (zeros + 1)

-- Adds arbitrary amount of zeros to end of a list, useful for doing the list multiplication
addZeros :: [Int] -> Int -> [Int]
addZeros num howMany = num ++ (take howMany) (repeat 0)


