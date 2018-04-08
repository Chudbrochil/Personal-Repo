module Homework4 where

--No other imports allowed
import qualified Data.List as L

--4.1 Genome Lists (40pts)
insertions :: String -> [String]
insertions = undefined

deletions :: String -> [String]
--deletions xs = [num <- [0..maxNum], deleteHelper num xs]
deletions xs = map (deleteHelper xs) indices
	where indices = [0..((length xs) - 1)]


deleteHelper :: String -> Int -> String
deleteHelper xs index = ys ++ zs
	where (ys, z:zs) = splitAt index xs




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

--fileisort :: String -> String -> IO ()
--fileisort fn1 fn2 = do
--	let sortedFile = L.intercalate "\n" (isort (lines fn1))
	--putStrLn newFile fn2
--	writeFile fn2 sortedFile

-- Lines gives each line, we sort all the lines and then concat+intersperse
-- "\n" (called intercalate). Lastly, we write to the file fn2.
fileisort :: String -> String -> IO ()
fileisort fn1 fn2 = writeFile fn2 (L.intercalate "\n" (isort (lines fn1)))


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
