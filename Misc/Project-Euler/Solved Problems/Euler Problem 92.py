# Problem 92

def endsWith(num, nums):

    newNum = generateSquaredNum(num)

    foundIt = False

    if newNum == 1:
        return False
    
    elif newNum == 89:
        # If there's another 89 in the list already, return true
        for num in nums:
            if num == 89:
                foundIt = True
                return True

        nums.append(newNum)
        foundIt = endsWith(newNum, nums)
    else:
        foundIt = endsWith(newNum, nums)
    
    return foundIt


def generateSquaredNum(num):
    sum = 0
    while num:
        sum += ((num % 10) * (num % 10))
        num //= 10
    return sum
        

# Need to figure out whether number ends with duplicates of 1 or 89
# if it is 89, ++ a counter




def main():

    sum89s = 0

    for i in range(1, 10000000):

        if (i % 100000) == 0:
            print(i)
        
        nums = []
        myBool = False
        myBool = endsWith(i, nums)
        if myBool == True:
            sum89s = sum89s + 1


    print("Number of 89s:" + sum89s)


if __name__ == "__main__":
    main()

    
