{-
Anthony Galczak
agalczak@unm.edu
-}

--No other imports are allowed
import Data.List

--2.1
collatz :: [Int] -> Int
collatz = undefined

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
select = undefined

--2.4
prefixSum :: [Int] -> [Int]
prefixSum = undefined

--2.5
numbers :: [Int] -> Int
numbers = undefined

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
