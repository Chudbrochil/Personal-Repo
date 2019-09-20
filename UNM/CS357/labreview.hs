

{-
[1,2,3,4,5] :: [Int]

['a', 'b'] :: [Char]

"abcd" :: [Char] -- or String

[True, False] :: [Bool]

(+1) :: Int -> Int

[(+1), (+2)] :: [(Int -> Int)]

map myEven [1,2,3,4] :: [Bool]

zip :: [a] -> [b] -> [(a,b)]

zip [1,2,3][4,5,6] :: [(Int,Int)]

zip "abc" [1,2,3] :: [(Char, Int)]

map myEven :: [Int] -> [Bool]

zip "abc" :: [a] -> [(Char, a)]

(\x -> x + 1) :: Int -> Int

(\x -> x + 1) 1 :: Int

(\x -> (\y -> x + y)) :: Int -> (Int -> Int)

add x = \y -> x + y

(\x -> (\y -> x + y)) 1 2 :: Int

(\x y -> x + y)

(\x y -> myEven x && myOdd) 1 2 :: Bool

(\x -> [x,x]) 1 :: [Int]

(\f x -> f x + f x) :: (a -> Int) -> a -> Int

(\x y z -> myEven ((x*y*z) `div` (x-y-z))) :: Int -> Int -> Int -> Bool


-}

-- In class take replacement
take' :: Int -> [a] -> [a]
take' 0 _ = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs



-- 02-13-17

concat' :: [[a]] -> [a]
concat' xss = [ x | xs <- xss, x <- xs]

positions :: Int -> [Int] -> [Int]
positions n xs = [ y | (x,y) <- zip xs [0..], x == n]

scalarMult :: Int -> [[Int]] -> [[Int]]
scalarMult s xss = [ map (*s) xs | xs <- xss]
-- scalarMult s xss = (map . map) (*s) xss
-- scalarMult s xss = [[ x*s | x <- xs | xs <- xss ]


-- 02-20-17 lab

foldRight :: (a -> b -> b) -> b -> [a] -> b
foldRight f z [] = z
foldRight f z (x:xs) = f x (foldRight f z xs)

sum' :: [Int] -> Int
sum' xs = foldRight (\x ys -> x + ys) 0 xs
-- sum' xs = foldRight (+) 0 xs
-- sum = foldRight (+) 0
-- Think of 1 : (2 : (3 : []))



const' :: a -> b -> a
const' x y = x

length' :: [a] -> Int
length' xs = foldRight (\x ys -> 1 + ys) 0 xs

length'' :: [a] -> Int
length'' xs = foldRight((+) . (const 1)) 0 xs

filter' p xs = foldRight (\x ys -> if p x then x : ys else ys) []





