
##
##Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.
##
##Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.
##
##We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.
##
##Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below one-thousand (525) are bouncy. In fact, the least number for which the proportion of bouncy numbers first reaches 50% is 538.
##
##Surprisingly, bouncy numbers become more and more common and by the time we reach 21780 the proportion of bouncy numbers is equal to 90%.
##
##Find the least number for which the proportion of bouncy numbers is exactly 99%.



# Make this more efficient to reduce time...
def checkBouncyPercent(rangeNum):
    bouncy = 0
    nonBouncy = 0
    for num in range(1, rangeNum + 1):
        if num < 100:
            nonBouncy += 1
        else:
            numAsStr = str(num)
            sortedNumAsStr = ''.join(sorted(numAsStr))
            reversedSortedNumAsStr = ''.join(reversed(sortedNumAsStr))
            # If num is increasing or decreasing
            if sortedNumAsStr == numAsStr or reversedSortedNumAsStr == numAsStr:
                nonBouncy += 1
            else:
                bouncy +=1

    return (bouncy / float(rangeNum))




def main():
    # Doing a binary search for which number will give 0.99 bouncy percent
    rangeNum = 1000000 # Initial number to start searching at
    amountToShift = 10000 # How much to increase the number while searching
    hit99 = False
    lowNum = 0
    highNum = 0

    while (hit99 == False):

        bouncyPercent = checkBouncyPercent(rangeNum)

        print("rangeNum:%s has bouncyPercent:%s" % (rangeNum, bouncyPercent))

        currentTooLow = None
        if bouncyPercent == 0.99:
            print(rangeNum)
            hit99 = True
            break
        elif bouncyPercent > 0.99:
            currentTooLow = False
            highNum = rangeNum
        elif bouncyPercent < 0.99:
            currentTooLow = True
            lowNum = rangeNum

        # Not a perfect binary search, but works reasonably well to pinpoint the number
        if highNum == 0:
            amountToShift *= 3
        else:
            amountToShift = (highNum - lowNum) / 2

        # If we were too low, increase the number
        if currentTooLow == True:
            rangeNum += amountToShift
        else:
            rangeNum -= amountToShift


if __name__ == "__main__":
    main()




    
