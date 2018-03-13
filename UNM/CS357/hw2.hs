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
makeLongInt n r = (r, reverse(map fromIntegral (getList n r)))

getList :: Integer -> Int -> [Integer]
getList 0 r = []
getList n r = n `mod` toInteger r : getList (n `div` toInteger r) r


--2.6 2
-- Almost exact same as 2.5
evaluateLongInt :: Numeral -> Integer
evaluateLongInt (base, nums) = toInteger(foldl ( (+) . (*base) ) 0 nums)

--2.6 3
-- First, make an int out our given list of ints.
-- Second, make a new list of ints with our new base
-- Reverse that and put it in a tuple with the new base
-- 
-- All operations are done with strictly Int arithmetic
--changeRadixLongInt :: Numeral -> Int -> Numeral 
--changeRadixLongInt (r1, ds1) r2 = (r2, reverse (newBaseList r2 (makeInt (r1, ds1))))

-- Makes an int number out of our initial base and nums
--makeInt :: (Int, [Int]) -> Int
--makeInt (r, nums) = foldl ((+) . (*r)) 0 nums

-- Divides our new int by the 2nd base given
--newBaseList :: Int -> Int -> [Int]
--newBaseList _ 0 = []
--newBaseList r num = num `mod` r : newBaseList r (num `div` r) 




changeRadixLongInt :: Numeral -> Int -> Numeral
changeRadixLongInt (r1, ds1) r2 = (r2, radixHelper ds1 r1 r2 [0])

radixHelper :: [Int] -> Int -> Int -> [Int] -> [Int]
radixHelper [x] r1 r2 tally = listAdd tally [x] r2
radixHelper (x:xs) r1 r2 tally = radixHelper xs r1 r2 newTally
	where newTally = listMult (listAdd tally [x] r2) (reverse(splitBaseIntoList r1 r2)) r2 0


addZeros :: [Int] -> Int -> [Int]
addZeros num howMany = num ++ (take howMany) (repeat 0)


-- reverse inputs and then reverse outputs
oneByList :: Int -> [Int] -> Int -> Int -> [Int]
oneByList x [] r carry = if carry == 0 then [] else [carry]
oneByList x (y:ys) r carry = (prod `mod` r) : oneByList x ys r (prod `div` r)
	where prod = (x * y) + carry

listMult :: [Int] -> [Int] -> Int -> Int -> [Int]
listMult [] ys r zeros = []
-- Literal magic happens here
listMult (x:xs) ys r zeros = listAdd (normalizeLists (length(list2)) list1) (normalizeLists (length(list1)) list2) r
	where list1 = (addZeros (reverse (oneByList (last (x:xs)) (reverse ys) r 0)) zeros)
	      list2 = (listMult (tail(reverse (x:xs))) ys r (zeros + 1))





-- Example, f 7 5 = [1,2], f 5 7 = [5]
splitBaseIntoList :: Int -> Int -> [Int]
splitBaseIntoList rFrom rTo = if rFrom <= rTo then [rFrom] else (rFrom `mod` rTo) : splitBaseIntoList (rFrom `div` rTo) rTo




--LISTADD
-- Allows me to add lists of ints, i.e. list+ [1,2] [5] 7 = [2,0]  (Think: [1,2] + [5] = [2,0])
listAdd :: [Int] -> [Int] -> Int -> [Int]
listAdd xs ys r = reverse (fixBaseCarrys r 0 (reverse (addListOfInts (normalizeLists (length xs) ys) (normalizeLists (length ys) xs))))

-- We will likely have carries when adding two lists together. Fixing the bases 
-- rem - remainder, r - radix
fixBaseCarrys :: Int -> Int -> [Int] -> [Int]
fixBaseCarrys r rem [] = if rem == 0 then [] else [rem]
fixBaseCarrys r rem (x:xs)
	| sum >= r = (sum `mod` r) : fixBaseCarrys r (sum `div` r) xs
	| otherwise = sum : fixBaseCarrys r (sum `div` r) xs
	where sum = x + rem

normalizeLists :: Int -> [Int] -> [Int]
normalizeLists otherLen nums = take (otherLen - (length nums)) (repeat 0) ++ nums

addListOfInts :: [Int] -> [Int] -> [Int]
addListOfInts xs ys = zipWith (+) xs ys







-- new changeRadix
{-changeRadixLongInt :: Numeral -> Int -> Numeral
changeRadixLongInt (r1, ds1) r2 = (r2, radixHelper ds1 r1 r2 0)


-- rem is remainder, r1 old radix, r2 new radix, 
radixHelper :: [Int] -> Int -> Int -> Int -> [Int]
radixHelper [] r1 r2 rem = if rem == 0 then [] else [rem]
radixHelper (x:xs) r1 r2 rem
	| sum >= r2 = (sum `mod` r2) : radixHelper xs r1 r2 (sum - r2)  -- overflow
	| otherwise = sum : radixHelper xs r1 r2 0        -- no overflow
	where sum = x + rem

-}




{-
listMult :: [Int] -> [Int] -> Int -> [Int]
listMult xs ys r = reverse (fixBaseCarrys r 0 (reverse (multListOfInts (normalizeLists (length xs) ys) (normalizeLists (length ys) xs))))

multListOfInts :: [Int] -> [Int] -> [Int]
multListOfInts xs ys = zipWith (*) xs ys

--test xs ys r = reverse (multListOfInts (normalizeLists (length xs) ys) (normalizeLists (length ys) xs))

spli
listMultByBase :: Int -> Int -> Int -> [Int] -> [Int]
listMultByBase r1 r2 rem [] = if rem == 0 then [] else [rem]
listMultByBase r1 r2 rem (x:xs)
	| sum >= r2 = (sum `mod` r2) : listMultByBase r1 r2 (sum - r2) xs
	| otherwise = sum : listMultByBase r1 r2 0 xs
	where sum = (x * r1) + rem

--test :: Int -> Int -> [Int] -> [Int]
--test r1 r2 xs = reverse (fixBaseCarrys r1 0 (listMultByBase r1 r2 0 (reverse xs)))


-}



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
--TODO: Put some comments here. This is almost a carbon copy of 2.6.4, I need to fix changeRadix
mulLongInts :: Numeral -> Numeral -> Numeral
mulLongInts (r1, ds1) (r2, ds2)
    | r1 == r2 = (r1, (reverse (multTwoSameBase r1 (reverseAndFix (length ds2) ds1) (reverseAndFix (length ds1) ds2) 0)))
    | r1 < r2 = mulLongInts (changeRadixLongInt (r1, ds1) r2) (r2, ds2) 
    | r1 > r2 = mulLongInts (r1, ds1) (changeRadixLongInt (r2, ds2) r1)


multTwoSameBase :: Int -> [Int] -> [Int] -> Int -> [Int]
multTwoSameBase r _ [] carry = if carry == 0 then [] else [carry]
multTwoSameBase r [] _ carry = if carry == 0 then [] else [carry]
multTwoSameBase r (x:xs) (y:ys) carry = if sum >= r then (sum `mod` r) : multTwoSameBase r xs ys 1
                       else sum : addTwoSameBase r xs ys 0
                       where sum = (x * y) + carry


