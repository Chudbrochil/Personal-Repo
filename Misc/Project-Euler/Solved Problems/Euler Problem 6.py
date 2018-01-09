# Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

# Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

import math

magicNum = 100

sumOfSquares = 0
squareOfSums = 0

def sum(magicNum):
  global sumOfSquares
  for x in range(1, magicNum + 1):
    sumOfSquares += math.pow(x, 2)

def square(magicNum):
  global squareOfSums
  sumOfNums = 0
  for x in range(1, magicNum + 1):
    sumOfNums += x

  squareOfSums = math.pow(sumOfNums, 2)

sum(magicNum)
square(magicNum)


print(squareOfSums - sumOfSquares)
  
