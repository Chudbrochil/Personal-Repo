module Homework4 where

--No other imports allowed
import qualified Data.List as L

--4.1 Genome Lists (40pts)
insertions :: String -> [String]
insertions = undefined

deletions :: String -> [String]
deletions = undefined

substitutions :: String -> [String]
substitutions = undefined

transpositions :: String -> [String]
transpositions = undefined

--4.2 Sorting (20pts)
insert :: Ord a => a -> [a] -> [a]
insert num [] = [num]
insert num (x:xs) = if num <= x then num : x : xs else x : insert num xs

isort :: Ord a => [a] -> [a]
isort [] = []
isort (x:xs) = insert x (isort xs)

fileisort :: String -> String -> IO ()
fileisort = undefined

--4.3 Game Trees (40pts)
data Field = B | R | G
             deriving (Eq, Ord, Show)
type Board = [Field]

strategyForRed :: Board -> Int
strategyForRed = undefined

strategyForGreen :: Board -> Int
strategyForGreen = undefined

--4.4 (Optional) Drawing Game Trees and Strategies (30pts EC)
drawStrategy :: Bool -> String -> IO ()
drawStrategy = undefined
