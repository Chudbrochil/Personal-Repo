# POSSIBLY NEEDS A SIEVE? Optimization needed as this takes FOREVER to run

import math

num = 600851475143

#num = 13195

numbers = []


for x in range(1, num//2):
    if num % x == 0:
      print(x)
      isPrime = True
      
      for i in range(2, int((math.sqrt(x)))):
        if x % i == 0:
            isPrime = False
            break

      if isPrime == True:
        numbers.append(x)


print(numbers[len(numbers)-1])
