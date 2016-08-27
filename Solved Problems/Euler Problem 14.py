def getChain(num):
    i = 0
    while num != 1:

        i = i + 1
        if num % 2 == 0:
            num = num / 2
        else:
            num = (3*num) + 1

    return(i + 1)

highestChainNum = 0
highestChain = 0

for x in range(1, 1000001):
    chainNum = getChain(x)
    if chainNum > highestChain:
        print(x)
        highestChain = chainNum
        highestChainNum = x



print("Starting number: " + str(highestChainNum) + " Highest Chain: " + str(highestChain))
