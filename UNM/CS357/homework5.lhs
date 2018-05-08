\begin{code}
import Data.List

--5.1 Trees

\end{code}

My bfs code traverses the code once via DFS (distance()) to calculate the distances
from root to each node. I process over this list of distances with countPredecessors()
to get a list of ints corresponding to the BFS traversal. countPredecessors() has
to be called with two lists, this is done in getBFNums().
Then I do the traversal with bfswithDfs().

Analysis of the run-time.
N vertices
O(N) for distance
O(N^2) for countPredecessors as it compares each element against each other element
O(N) for bfsWithDfs

Total asymptotic run-time is bounded by countPrecedessors.
O(N^2)

\begin{code}


data Tree a = E
            | T a (Tree a) (Tree a)
            deriving (Eq, Show, Ord)


bfnum :: Tree a -> Tree Int
bfnum t = fst (bfsWithDfs t (getBFSNums t))


-- Distance for DFS solution
distance :: Tree a -> Int -> [Int]
distance (E) depth = []
distance (T a t1 t2) depth = [depth] ++ (distance t1 (depth + 1)) ++ (distance t2 (depth + 1))

-- Counting your predecesors <= num to left and < num to right.
-- This will ultimately give a list of the BFS numbering for a DFS traversal
countPredecessors :: [Int] -> [Int] -> [Int]
countPredecessors _ [] = []
countPredecessors fullList (x:xs) = (addNumsBefore left right x) : countPredecessors fullList xs
	where (left, right) = splitAt (length(fullList) - length(x:xs)) fullList


-- Calculates each individual element in the ending BFS list
addNumsBefore :: [Int] -> [Int] -> Int -> Int
addNumsBefore [] [] num = 1
addNumsBefore [] (y:ys) num
	| y < num = 1 + addNumsBefore [] ys num
	| otherwise = addNumsBefore [] ys num
addNumsBefore (x:xs) (y:ys) num
	| x <= num = 1 + addNumsBefore xs (y:ys) num
	| otherwise = addNumsBefore xs (y:ys) num


-- Gets the list of the BFS traversal ints
getBFSNums :: Tree a -> [Int]
getBFSNums t = countPredecessors (distance t 0) (distance t 0)


-- Traverses a tree in DFS order with a list of BFS numbers
bfsWithDfs :: Tree a -> [Int] -> (Tree Int, [Int])
bfsWithDfs (E) xs = (E, xs)
bfsWithDfs (T a lt rt) (x:xs) =
	let (lt', ln) = bfsWithDfs lt xs
	    (rt', rn) = bfsWithDfs rt ln
	in (T x lt' rt', rn)


tree1 :: Tree Char
tree1 = (T 'a' (T 'b' (T 'c' E E) (T 'd' E E)) (T 'e' (T 'f' E E) (T 'g' E E)))

tree2 :: Tree Char
tree2 = (T 'a' (T 'b' (T 'c' E E) E) E)

tree3 :: Tree Char
tree3 = (T 'a' (T 'b' E (T 'c' E E)) (T 'd' E E))


--5.2 Expression Trees
type Identifier = String

data Expr = Num Integer
          | Var Identifier
          | Let {var :: Identifier, value :: Expr, body :: Expr}
          | Add Expr Expr
          | Sub Expr Expr
          | Mul Expr Expr
          | Div Expr Expr
          deriving (Eq)

instance Show Expr where
  show (Let var value body) = "let " ++ var ++ " = " ++ show value ++ " in " ++ show body ++ " end"
  show (Num num) = show num
  show (Var var) = var
  show (Add op1 op2) = (show op1) ++ " + " ++ (show op2)
  show (Sub op1 op2) = (show op1) ++ " - " ++ (show op2)
  show (Mul op1 op2) = "(" ++ (show op1) ++ " * " ++ (show op2) ++ ")"
  show (Div op1 op2) = "(" ++ (show op1) ++ " / " ++ (show op2) ++ ")"

type Env = Identifier -> Integer

emptyEnv :: Env
emptyEnv = \s -> error ("unbound: " ++ s)

extendEnv :: Env -> Identifier -> Integer -> Env
extendEnv oldEnv s n s' = if s' == s then n else oldEnv s'

evalInEnv :: Env -> Expr -> Integer
evalInEnv env e =
  case e of
    -- The "Let" clause means that we want to create an environment. We do This
    -- using the extendEnv method
    Let var value body -> evalInEnv (extendEnv emptyEnv var (eval value)) body
    Var x -> env x -- env x does a lookup of Identifier -> Integer
    Num x -> x
    Add e1 e2 -> (evalInEnv env e1) + (evalInEnv env e2)
    Sub e1 e2 -> (evalInEnv env e1) - (evalInEnv env e2)
    Mul e1 e2 -> (evalInEnv env e1) * (evalInEnv env e2)
    Div e1 e2 -> (evalInEnv env e1) `div` (evalInEnv env e2)


eval :: Expr -> Integer
eval e = evalInEnv emptyEnv e


--5.3 Infinite Lists
diag :: [[a]] -> [a]
diag xss = diagHelper xss 1

-- As diagHelper recurses, it takes more and more lists to take the head of
diagHelper :: [[a]] -> Int -> [a]
diagHelper xss howMany = map (head) (take howMany xss) ++ (diagHelper tailLists (howMany + 1))
  where tailLists = map (tail) (take howMany xss) ++ (r)
        (l, r) = splitAt howMany xss


-- The standard table of all positive rationals, in three forms:
-- (1) as floats
rlist = [ [i/j | i<-[1..]] | j <- [1..] ]
-- (2) as strings, not reducd
qlist1 = [ [show i ++ "/" ++ show j | i<-[1..]] | j <- [1..] ]
-- (3) as strings, in reduced form
qlist2 = [ [fracString i j | i <- [1..]] | j <- [1..] ]
-- take a numerator and denominator, reduce, and return as string
fracString num den = if denominator == 1
                then show numerator
                else show numerator ++ "/" ++ show denominator
    where c = gcd num den
          numerator = num `div` c
          denominator = den `div` c
-- Take an n-by-n block from the top of a big list of lists
block n x = map (take n) (take n x)


\end{code}
