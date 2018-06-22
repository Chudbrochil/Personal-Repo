## Euler Problem 26
##
##A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
##
##    1/2	= 	0.5
##    1/3	= 	0.(3)
##    1/4	= 	0.25
##    1/5	= 	0.2
##    1/6	= 	0.1(6)
##    1/7	= 	0.(142857)
##    1/8	= 	0.125
##    1/9	= 	0.(1)
##    1/10	= 	0.1 
##
##Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
##
##Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

from decimal import *

# Decimal precision set to 100
getcontext().prec = 10000

numMap = {}


for divisor in range(2,1001):
    fraction = Decimal(1) / Decimal(divisor)
    fractionStr = str(fraction)
    fractionStr = fractionStr[2:]
    fractionStr = fractionStr.lstrip('0')

    # Chop the number into chunks that we will compare against "future" chunks in the number
    for chunk in range(2,4999):
        # If the number is smaller than 2 chunks, it can't be a recurring cycle worth finding
        if len(fractionStr) < chunk * 2:
            break
        # Eliminating numbers that are always repeating TODO:
        else:
            firstChunk = fractionStr[:chunk]
            secondChunk = fractionStr[chunk:chunk*2]

            # If we have a match...
            if firstChunk == secondChunk:
                if divisor not in numMap:
                    numMap[divisor] = [chunk]
                else:
                    # We need to check if existing chunk numbers are a factor of this
                    # new chunk. For example, if we have a recurring cycle of 6, we don't
                    # want to also add a recurring cycle of 12. 6 is truly the longest one.
                    chunks = numMap[divisor]
                    foundFactor = False
                    for possibleFactor in chunks:
                        if chunk % possibleFactor == 0:
                            foundFactor = True
                            break
                    
                    if foundFactor == False:    
                        numMap[divisor].append(chunk)

# Not the most efficient way to print these, but works for this set of numbers
for key in sorted(numMap):

    listOfValues = numMap[key]

    if max(listOfValues) > 800 and len(listOfValues) < 5:
        print("%s %s" % (key, numMap[key]))
            
