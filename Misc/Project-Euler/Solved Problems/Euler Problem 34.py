# Problem 34

##145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
##
##Find the sum of all numbers which are equal to the sum of the factorial of their digits.
##
##Note: as 1! = 1 and 2! = 2 are not sums they are not included.

# Hard coding factorial for 0-9
factorials = [1,1,2,6,24,120,720,5040,40320,362880]

magicNum = 9000000
digitFacts = []

for num in range(10, magicNum+1):

    remainNum = num
    power = 1
    factorialSum = 0
    while(remainNum != 0):
        # Get the digit and add the factorial of it to the factorial sum
        correctPlaceDigit = remainNum % (10 ** power)
        singlePlaceDigit = correctPlaceDigit / (10 ** (power-1))
        factorialSum += factorials[singlePlaceDigit]

        # Terminate early if we go over the number
        if factorialSum > num:
            break
        
        remainNum -= correctPlaceDigit
        power += 1

    if factorialSum == num:
        digitFacts.append(num)

print(digitFacts)
print(sum(digitFacts))
        

