import Control.Parallel

a = 8
b = 7



par factorial :: Int -> Integer
factorial n
  | n < 0 = error "Must be non negative"
  | n == 0 = 1
  | otherwise = n * factorial (n-1)

add1 :: Int -> Int
add1 = \n -> n + 1

doSomething :: (Int -> Int) -> Int -> Int
doSomething f n = f n




