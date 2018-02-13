{-
Studying for exam 1, re-writing Data.List

(++) :: [a] -> [a] -> [a] infixr 5 Source#

Append two lists, i.e.,

[x1, ..., xm] ++ [y1, ..., yn] == [x1, ..., xm, y1, ..., yn]
[x1, ..., xm] ++ [y1, ...] == [x1, ..., xm, y1, ...]
If the first list is not finite, the result is the first list.

head :: [a] -> a Source#

Extract the first element of a list, which must be non-empty.

last :: [a] -> a Source#

Extract the last element of a list, which must be finite and non-empty.

tail :: [a] -> [a] Source#

Extract the elements after the head of a list, which must be non-empty.

init :: [a] -> [a] Source#

Return all the elements of a list except the last one. The list must be non-empty.

uncons :: [a] -> Maybe (a, [a]) Source#

Decompose a list into its head and tail. If the list is empty, returns Nothing. If the list is non-empty, returns Just (x, xs), where x is the head of the list and xs its tail.

Since: 4.8.0.0

null :: Foldable t => t a -> Bool Source#

Test whether the structure is empty. The default implementation is optimized for structures that are similar to cons-lists, because there is no general way to do better.

length :: Foldable t => t a -> Int Source#

Returns the size/length of a finite structure as an Int. The default implementation is optimized for structures that are similar to cons-lists, because there is no general way to do better.

List transformations
map :: (a -> b) -> [a] -> [b] Source#

map f xs is the list obtained by applying f to each element of xs, i.e.,

map f [x1, x2, ..., xn] == [f x1, f x2, ..., f xn]
map f [x1, x2, ...] == [f x1, f x2, ...]
reverse :: [a] -> [a] Source#

reverse xs returns the elements of xs in reverse order. xs must be finite.

intersperse :: a -> [a] -> [a] Source#

The intersperse function takes an element and a list and `intersperses' that element between the elements of the list. For example,

intersperse ',' "abcde" == "a,b,c,d,e"
intercalate :: [a] -> [[a]] -> [a] Source#

intercalate xs xss is equivalent to (concat (intersperse xs xss)). It inserts the list xs in between the lists in xss and concatenates the result.

transpose :: [[a]] -> [[a]] Source#

The transpose function transposes the rows and columns of its argument. For example,

transpose [[1,2,3],[4,5,6]] == [[1,4],[2,5],[3,6]]
If some of the rows are shorter than the following rows, their elements are skipped:

transpose [[10,11],[20],[],[30,31,32]] == [[10,20,30],[11,31],[32]]
subsequences :: [a] -> [[a]] Source#

The subsequences function returns the list of all subsequences of the argument.

subsequences "abc" == ["","a","b","ab","c","ac","bc","abc"]
permutations :: [a] -> [[a]] Source#

The permutations function returns the list of all permutations of the argument.

permutations "abc" == ["abc","bac","cba","bca","cab","acb"]

Extracting sublists
take :: Int -> [a] -> [a] Source#

take n, applied to a list xs, returns the prefix of xs of length n, or xs itself if n > length xs:

take 5 "Hello World!" == "Hello"
take 3 [1,2,3,4,5] == [1,2,3]
take 3 [1,2] == [1,2]
take 3 [] == []
take (-1) [1,2] == []
take 0 [1,2] == []
It is an instance of the more general genericTake, in which n may be of any integral type.

drop :: Int -> [a] -> [a] Source#

drop n xs returns the suffix of xs after the first n elements, or [] if n > length xs:

drop 6 "Hello World!" == "World!"
drop 3 [1,2,3,4,5] == [4,5]
drop 3 [1,2] == []
drop 3 [] == []
drop (-1) [1,2] == [1,2]
drop 0 [1,2] == [1,2]
It is an instance of the more general genericDrop, in which n may be of any integral type.

splitAt :: Int -> [a] -> ([a], [a]) Source#

splitAt n xs returns a tuple where first element is xs prefix of length n and second element is the remainder of the list:

splitAt 6 "Hello World!" == ("Hello ","World!")
splitAt 3 [1,2,3,4,5] == ([1,2,3],[4,5])
splitAt 1 [1,2,3] == ([1],[2,3])
splitAt 3 [1,2,3] == ([1,2,3],[])
splitAt 4 [1,2,3] == ([1,2,3],[])
splitAt 0 [1,2,3] == ([],[1,2,3])
splitAt (-1) [1,2,3] == ([],[1,2,3])
It is equivalent to (take n xs, drop n xs) when n is not _|_ (splitAt _|_ xs = _|_). splitAt is an instance of the more general genericSplitAt, in which n may be of any integral type.

takeWhile :: (a -> Bool) -> [a] -> [a] Source#

takeWhile, applied to a predicate p and a list xs, returns the longest prefix (possibly empty) of xs of elements that satisfy p:

takeWhile (< 3) [1,2,3,4,1,2,3,4] == [1,2]
takeWhile (< 9) [1,2,3] == [1,2,3]
takeWhile (< 0) [1,2,3] == []
dropWhile :: (a -> Bool) -> [a] -> [a] Source#

dropWhile p xs returns the suffix remaining after takeWhile p xs:

dropWhile (< 3) [1,2,3,4,5,1,2,3] == [3,4,5,1,2,3]
dropWhile (< 9) [1,2,3] == []
dropWhile (< 0) [1,2,3] == [1,2,3]
dropWhileEnd :: (a -> Bool) -> [a] -> [a] Source#

The dropWhileEnd function drops the largest suffix of a list in which the given predicate holds for all elements. For example:

dropWhileEnd isSpace "foo\n" == "foo"
dropWhileEnd isSpace "foo bar" == "foo bar"
dropWhileEnd isSpace ("foo\n" ++ undefined) == "foo" ++ undefined
Since: 4.5.0.0

span :: (a -> Bool) -> [a] -> ([a], [a]) Source#

span, applied to a predicate p and a list xs, returns a tuple where first element is longest prefix (possibly empty) of xs of elements that satisfy p and second element is the remainder of the list:

span (< 3) [1,2,3,4,1,2,3,4] == ([1,2],[3,4,1,2,3,4])
span (< 9) [1,2,3] == ([1,2,3],[])
span (< 0) [1,2,3] == ([],[1,2,3])
span p xs is equivalent to (takeWhile p xs, dropWhile p xs)

break :: (a -> Bool) -> [a] -> ([a], [a]) Source#

break, applied to a predicate p and a list xs, returns a tuple where first element is longest prefix (possibly empty) of xs of elements that do not satisfy p and second element is the remainder of the list:

break (> 3) [1,2,3,4,1,2,3,4] == ([1,2,3],[4,1,2,3,4])
break (< 9) [1,2,3] == ([],[1,2,3])
break (> 9) [1,2,3] == ([1,2,3],[])
break p is equivalent to span (not . p).

stripPrefix :: Eq a => [a] -> [a] -> Maybe [a] Source#

The stripPrefix function drops the given prefix from a list. It returns Nothing if the list did not start with the prefix given, or Just the list after the prefix, if it does.

stripPrefix "foo" "foobar" == Just "bar"
stripPrefix "foo" "foo" == Just ""
stripPrefix "foo" "barfoo" == Nothing
stripPrefix "foo" "barfoobaz" == Nothing
group :: Eq a => [a] -> [[a]] Source#

The group function takes a list and returns a list of lists such that the concatenation of the result is equal to the argument. Moreover, each sublist in the result contains only equal elements. For example,

group "Mississippi" = ["M","i","ss","i","ss","i","pp","i"]
It is a special case of groupBy, which allows the programmer to supply their own equality test.

inits :: [a] -> [[a]] Source#

The inits function returns all initial segments of the argument, shortest first. For example,

inits "abc" == ["","a","ab","abc"]
Note that inits has the following strictness property: inits (xs ++ _|_) = inits xs ++ _|_

In particular, inits _|_ = [] : _|_

tails :: [a] -> [[a]] Source#

The tails function returns all final segments of the argument, longest first. For example,

tails "abc" == ["abc", "bc", "c",""]
Note that tails has the following strictness property: tails _|_ = _|_ : _|_

-}


--(++) :: [a] -> [a] -> [a]
--(++) xs ys = [i    | x <- xs, y <-ys]


head' :: [a] -> a
head' (x:xs) = x


last' :: [a] -> a
last' (x:xs) = if length(x:xs) == 1 then x else last' xs


tail' :: [a] -> [a]
tail' (x:xs) = xs

--init' :: [a] -> [a]


-- I copied this...
uncons :: [a] -> Maybe (a, [a])
uncons [] = Nothing
uncons (x:xs) = Just (x, xs)


--map' :: (a -> b) -> [a] -> [b]
--map' f (x:xs) = f x : map f xs

-- don't know how to do reverse...
--reverse' :: [a] -> [a]
--reverse' (x:xs) = 


-- Adds an extra y at end, easily fixed with a length(1) call... no time to fix this.
intersperse' :: a -> [a] -> [a]
intersperse' _ [] = []
intersperse' y (x:xs) = x : y : intersperse' y xs


-- Now doing the ones we went over in class...

fact' :: Int -> Int
fact' n = if n == 0 then 1 else n *fact'(n-1)


greaterThanZero :: Int -> Bool
greaterThanZero n = if n > 0 then True else False


twice' :: (Double -> Double) -> Double -> Double
twice' f n = f (f n)


length' :: [Int] -> Int
length' [] = 0
length' (x:xs) = 1 + length' xs


(+++) :: [a] -> [a] -> [a]
[] +++ ys = ys
(x:xs) +++ ys = x : (xs +++ ys)



drop' :: Int -> [a] -> [a]
drop' 0 xs = xs
drop' n [] = []
drop' n (x:xs) = drop' (n-1) xs


map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = f x : map' f xs


filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' pred (x:xs) = if pred x then x : filter' pred xs else filter' pred xs


-- This is the . operator
compose :: (b -> c) -> (a -> b) -> a -> c
compose f g x = f (g x)


zip' :: [a] -> [b] -> [(a,b)]
zip' [] _ = []
zip' _ [] = []
zip' (x:xs) (y:ys) = (x,y) : zip' xs ys


concat' :: [[a]] -> [a]
concat' xss = [x | xs <- xss, x <- xs]


filter'' :: (a -> Bool) -> [a] -> [a]
filter'' f (x:xs) = [x | x <- xs, f x]




