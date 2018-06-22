# -*- coding: utf-8 -*-
##
##Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
##If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
##
##For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
##
##Evaluate the sum of all the amicable numbers under 10000.

import math

def genProperDivisorsSum(num):

    properDivisors = [1]

    maxSearch = int(math.ceil(math.sqrt(num)))
    for possibleFactor in range(2, maxSearch):
        
        if num % possibleFactor == 0:
            properDivisors.append(possibleFactor)
            if possibleFactor * possibleFactor != num:
                properDivisors.append(num / possibleFactor)

    return(sum(properDivisors))


amicableNums = []
for possibleAmicableNum in range(3, 10000):

    if possibleAmicableNum not in amicableNums:
        firstSum = genProperDivisorsSum(possibleAmicableNum)
        secondSum = genProperDivisorsSum(firstSum)

        # Exclude primes and where "a = b"
        if possibleAmicableNum == secondSum and firstSum != 1 and possibleAmicableNum != firstSum:
            amicableNums.append(possibleAmicableNum)
            amicableNums.append(firstSum)


print(sum(amicableNums))


