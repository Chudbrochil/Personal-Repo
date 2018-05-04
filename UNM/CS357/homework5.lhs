\begin{code}
import Data.List

--5.1 Trees
data Tree a = E
            | T a (Tree a) (Tree a)
            deriving (Eq, Show)

bfnum :: Tree a -> Tree Int
bfnum = undefined

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
