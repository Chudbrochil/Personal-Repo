\begin{code}
{-# LANGUAGE BangPatterns #-}
import Data.List

--5.1 Trees
data Tree a = E
            | T a (Tree a) (Tree a)
            deriving (Eq, Show, Ord)

\end{code}

-- At the beginning of the semester we were given code for an efficient Queue
-- in Haskell.
type Queue a = ([a], [a])


push :: a -> Queue a -> Queue a
push x (fs, bs) = (fs, x:bs)

pop :: Queue a -> (a, Queue a)
pop ([],[]) = error "empty queue"
pop (f:fs, bs) = (f, (fs, bs))
pop ([], bs) = (b', (bs', []))
  where
    b':bs' = reverse bs

empty :: Queue a
empty = ([],[])

null :: Queue a -> Bool
null ([],[]) = True
null _ = False

q :: Queue Int
q = ([1,2,3,4,5], [])


--bfs :: Tree a -> Queue (Tree a) -> Int -> Queue (Tree a)
--bfs (E) q i = push (E) q
--bfs (T a t1 t2) q i = push (T i t1 t2) q


bfs :: Queue (Tree a) -> Int -> Queue (Tree Int)
bfs q i =
  | null q == False = push (parent i )

  where parent = (pop q)



{-}
bfHelper :: Tree a -> Int -> Tree Int
bfHelper E count = E
bfHelper (T a t1 E) count =
bfHelper (T a E t2) count =
bfHelper (T a t1 t2) count =
-}

{-bfHelper :: Tree a -> Tree Int
bfHelper tree = treeInt tree

getChildren :: Tree Int -> [Tree Int]
getChildren (E) = [E]
getChildren (T a t1 E) = [t1 (a + 1), E]
getChildren (T a E t2) = [E, t2 (a + 1)]
getChildren (T a t1 t2) = [t1 (a + 1), t2 (a + 2)]

-- We don't care about the values that come with our tree, just eliminate them
-- to simplify type checking and my sanity
treeInt :: Tree a -> Tree Int
treeInt (E) = E
treeInt (T a t1 t2) = T 1 (treeInt t1) (treeInt t2)




-}

\begin{code}

bfnum :: Tree a -> Tree Int
bfnum = undefined

--bfnum tree = bfHelper tree 1 0

-- First int is counter, second int is depth
--bfHelper :: Tree a -> Int -> Int -> Tree Int
--bfHelper (E) counter depth = E -- I guess E is empty
--bfHelper (T a t1 t2) counter depth = T counter child1 child2
--  where child1 = bfHelper t1 (counter + 1 + depth) (depth + 1)
--        child2 = bfHelper t2 (counter + 2 + depth) (depth + 1)




-- DFS depth assignment
assignDepth :: Tree a -> Int -> Tree (Int, Int)
assignDepth (E) depth = E
assignDepth (T a t1 t2) depth = T (1, depth) child1 child2
  where child1 = assignDepth t1 (depth + 1)
        child2 = assignDepth t2 (depth + 1)

-- Traverse tree to find max depth, this will determine how we do levels
depth :: Tree a -> Int
depth (E) = 0
depth (T a t1 t2) = max (1 + depth t1) (1 + depth t2)


-- Traverse the tree each time we want to count a particular level
bfsNumber :: Tree (Int, Int) -> Int -> Int -> Tree (Int, Int)
bfsNumber (E) targetDepth count = E
bfsNumber (T (a, d) t1 t2) targetDepth count
  | d < targetDepth = T (a, d) child1a child1b
  | d == targetDepth = T (count, d) child1a child2a
  | otherwise = T (a, d) child1b child2b
    where child1a = bfsNumber t1 targetDepth (count + 1)
          child2a = bfsNumber t2 targetDepth (count + 1)
          child1b = bfsNumber t1 targetDepth count
          child2b = bfsNumber t2 targetDepth count

dropDepth :: Tree (Int, Int) -> Tree Int
dropDepth (E) = E
dropDepth (T (a, d) t1 t2) = T a child1 child2
  where child1 = dropDepth t1
        child2 = dropDepth t2

-- TODO: Doing static 3 level tree for now... I'll figure out the compose/church
-- fanciness later
foo :: Tree a -> Tree Int
foo t = dropDepth (bfsNumber (bfsNumber (bfsNumber treeWithLvls 1 1) 2 1) 3 1)
  where maxDepth = depth t
        treeWithLvls = assignDepth t 0



church :: Int -> (c -> c) -> c -> c
church n = (\f -> foldr (.) id (replicate n f))




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

























-- CS 357 Homework 5 Test Code
-- Luke Hanks
-- I'm not going to bother with the module stuff. Just past this stuff into your solution file with proper attribution.

-- 5.1

exmplTree = T 'a' (T 'b' E (T 'c' E E)) (T 'd' E E)

infTreeFull = T () infTreeFull infTreeFull
infTreeL = T () infTreeL E
infTreeR = T () E infTreeR
infTreeOut = T () infTreeL infTreeR
infTreeZigZagL = T () infTreeZigZagR E
infTreeZigZagR = T () E infTreeZigZagL

-- Think of what you would do to cut an infinite tree short like how we cut infinite lists short.

test_bfnum = bfnum exmplTree == T 1 (T 2 E (T 4 E E)) (T 3 E E)

-- This makes a string of mermaid code. Remember to use putStrLn to print it.
toMermaid :: Show a => Tree a -> String
toMermaid rT@(T _ l r) = "graph TD" ++ connect rT l ++ connect rT r ++ "\n"
    where connect :: Show a => Tree a -> Tree a -> String
          connect (T p _ _)     E        = "\n" ++ show p ++ "-.->Empty_Tree"
          connect (T p _ _) cT@(T c l r) = line ++ connect cT l ++ connect cT r
              where line = "\n" ++ show p ++ "==>" ++ show c

-- To see the mermaid code built go to https://mermaidjs.github.io/mermaid-live-editor/ .

-- 5.2

exmplExp1 = Let
                "x" (Num 3)
                ((Var "x")`Add`(Num 5))
exmplExp2 = (Let
                "y" (Num (-9))
                (Let
                    "x" ((Num 1)`Sub`(Var "y"))
                    ((Var "x")`Add`(((Var "x")`Div`((Var "x")`Sub`(Num 5)))`Mul`(Num 10)))
                )
           )
exmplExp3 = (Num 2) `Add` (Num 3)

testExprShow = show exmplExp1 =="let x = 3 in x + 5 end"
            && show exmplExp2 == "let y = -9 in let x = 1 - y in x + ((x / (x - 5)) * 10) end end"

testEval =     eval exmplExp1 == 8
            && eval exmplExp2 == 30

-- 5.3

testDiag =  take 20 (diag qlist2) == ["1",
                                      "2", "1/2",
                                      "3", "1"  , "1/3",
                                      "4", "3/2", "2/3", "1/4",
                                      "5", "2"  , "1"  , "1/2", "1/5",
                                      "6", "5/2", "4/3", "3/4", "2/5"]


testQlist2 = (block 5 qlist2) == [["1"  , "2"  , "3"  , "4"  , "5"  ],
                                  ["1/2", "1"  , "3/2", "2"  , "5/2"],
                                  ["1/3", "2/3", "1"  , "4/3", "5/3"],
                                  ["1/4", "1/2", "3/4", "1"  , "5/4"],
                                  ["1/5", "2/5", "3/5", "4/5", "1"  ]]




\end{code}
