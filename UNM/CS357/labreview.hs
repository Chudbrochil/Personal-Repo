


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




-- In class take replacement
take' :: Int -> [a] -> [a]
take' 0 _ = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs





