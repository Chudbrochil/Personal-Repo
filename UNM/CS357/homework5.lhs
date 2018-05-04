\begin{code}
import Data.List

--5.1 Trees
data Tree a = E
            | T a (Tree a) (Tree a)
            deriving (Eq, Show)

\end{code}

I implemented the BFS traversal in the most simple way I could imagine.
"bfnum" seeds bfHelper with the initial level or depth of 1.
From there bfHelper processes from left to right doing a BFS traversal
level by level. The variable "counter" serves as a counter for what node that
we are traversing in order. This won't correspond directly to level, but I could've
just as easily done that by doing "depth+1" for left node and "depth+1" for right node.
This would result in a tree with numbers corresponding to the actual depth of
each node.

We are only visiting each node once (including "E") so this run-time would be O(M)
where M = # of edges. This is "linear" time for a graph.
To compare this to an iterative setting, O(M) is literally the fastest this could
perform. There is no faster traversal.
There would be time to pop off the list of trees, but we can assume this is done
in constant or O(1) time assuming Haskell is using an efficient data structure
behind the "data Tree".

TODO: Regular BFS runtime is O(M+N), is that the case here? I only see
processing O(M) as edges will dominate nodes anyways.

\begin{code}

bfnum :: Tree a -> Tree Int
bfnum tree = bfHelper tree 1

bfHelper :: Tree a -> Int -> Tree Int
bfHelper (E) counter = E -- I guess E is empty
bfHelper (T a t1 t2) counter = T counter (bfHelper t1 (counter+1)) (bfHelper t2 (counter+2))

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
  show (Mul op1 op2) = (show op1) ++ " * " ++ (show op2)
  show (Div op1 op2) = (show op1) ++ " / " ++ (show op2)

type Env = Identifier -> Integer

emptyEnv :: Env
emptyEnv = \s -> error ("unbound: " ++ s)

extendEnv :: Env -> Identifier -> Integer -> Env
extendEnv oldEnv s n s' = if s' == s then n else oldEnv s'

evalInEnv :: Env -> Expr -> Integer
evalInEnv env expr = eval expr

eval :: Expr -> Integer
eval e =
  case e of
    Let var value body -> evalInEnv (extendEnv emptyEnv var (eval value)) body
    Num x -> x
    Add e1 e2 -> eval e1 + eval e2
    Sub e1 e2 -> eval e1 - eval e2
    Mul e1 e2 -> eval e1 * eval e2
    Div e1 e2 -> eval e1 `div` eval e2

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
