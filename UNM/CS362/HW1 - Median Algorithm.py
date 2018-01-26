import random

Verbosity = 1 # 0 for none, 1 for minimal, 2 for verbose

# TODO: Write a pythonic shuffle method


# shuffle
# Takes in an array and returns it shuffled.
# NOTE: This is not pythonic, but it was requested to manually shuffle elements
def shuffle(array):
    shuffledArray = []
    baseArray = array[:]
    while baseArray:
        randomElement = baseArray.pop(random.randrange(len(baseArray)))
        shuffledArray.append(randomElement)
    return shuffledArray

# quickSearch
# Takes an array and calculates using the "quickSearch" algorithm the i'th
# smallest element from the sorted array.
# Returns the element found to be the i'th smallest element
def quickSearch(array, index):

    leftSide = [] # Less than index
    rightSide = [] # More than index

    # Getting a random number from the array
    pivot = array[random.randint(0, len(array) - 1)]

    # Ignores case for element == indexToFind
    for element in array:
        if element < pivot:
            quickSearch.counter += 1
            leftSide.append(element)
        elif element > pivot:
            quickSearch.counter += 1
            rightSide.append(element)

    # If the index is smaller than left side size then recurse on left side, otherwise recurse on
    # right side. Of course, if we found the element we want, return it.
    if index <= len(leftSide):
        return quickSearch(leftSide, index)
    elif index == len(leftSide) + 1:
        return pivot
    elif index > len(leftSide) + 1:
        return quickSearch(rightSide, index - (len(leftSide) + 1))


def momSearch(array, index, blockSize):
    # TODO: Not accounting for if we have modulus left over in the blocks
    choppableArray = array[:]
    blockList = []
    blockOfElements = []
    while choppableArray:
        if len(blockOfElements) == blockSize:
            blockList.append(blockOfElements)
            blockOfElements = []
        else:
            blockOfElements.append(choppableArray.pop())


    # Find median in each block of 5...

    # Find median of these medians...

    # Use this as pivot, see what's above and what's below, etc.



    print(blockList)




# HELPER METHODS FOR ORGANIZING MAIN FUNCTIONALITY
# createListOfRandomLists
# Creates howManyLists(int) of lists containing random values from firstListValue(int) to lastListValue(int)
def createListOfRandomLists(firstListValue, lastListValue, howManyLists):

    if Verbosity > 0:
        print("Generating %d shuffled lists of values between %d and %d" % (howManyLists, firstListValue, lastListValue))
    initialArray = []
    for x in range(firstListValue, lastListValue):
        initialArray.append(x)

    listOfShuffledLists = []
    for x in range(howManyLists):
        listOfShuffledLists.append(shuffle(initialArray))

    return listOfShuffledLists

# normalizedQuickSearchRun
# Runs quickSearch on all of our shuffled lists iterations(int) times.
# Returns a list of list of comparisons for use in statistics roll-up.
def normalizedQuickSearchRun(listOfShuffledLists, iterations, index):

    if Verbosity > 0:
        print("Running %d quick search runs on %d lists looking for %d'th smallest element."
          % (iterations, len(listOfShuffledLists), index))

    quickSearchComparisons = []

    for shuffledList in listOfShuffledLists:
        comparisonsPerList = []
        for x in range(iterations):
            quickSearch.counter = 0
            quickSearch(shuffledList, index)
            comparisonsPerList.append(quickSearch.counter)

        quickSearchComparisons.append(comparisonsPerList)

    return quickSearchComparisons


def momSearchRun(listOfShuffledLists, index, blockSize):
    for list in listOfShuffledLists:
        momSearch(list, index, blockSize)


def generateStatistics(quickSearchComparisons):
    # Generate stats per list
    listIndex = 1
    sumOfAvgs = 0

    for list in quickSearchComparisons:
        # sum(list, 0.0) makes this list into a floating point list, slick way to get more precise values without python 3
        avgOfList = sum(list, 0.0) / len(list)
        sumOfAvgs += avgOfList

        if Verbosity > 1:
            print("List#%d - Comparisons Max: %d Min: %d Avg: %0.2f" % (listIndex, max(list), min(list), avgOfList))
            listIndex += 1

    quickSearchAvg = sumOfAvgs / len(quickSearchComparisons)

    if Verbosity > 0:
        print("Average comparisons of all quick search runs on all %d lists: %0.2f" % (len(quickSearchComparisons), quickSearchAvg))





def main():
    # Initial values to main, could be passed in as arguments from CLI
    firstListValue = 1 # Value we are starting our random lists at
    lastListValue = 100 # Value we are ending our random lists at
    howManyLists = 100 # How many random lists to make
    iterations = 100 # How many times to run quickSearch to normalize stats
    indexToFind = 50 # Index we were told to find
    blockSize = 5 # How big we want our blocks on deterministic median-of-median search


    # List of all of the shuffled lists
    listOfShuffledLists = createListOfRandomLists(firstListValue, lastListValue, howManyLists)

    # List of all the lists of comparisons taken by quick search to find the given value
    quickSearchComparisons = normalizedQuickSearchRun(listOfShuffledLists, iterations, indexToFind)

    momSearchComparisons = momSearchRun(listOfShuffledLists, indexToFind, blockSize)

    generateStatistics(quickSearchComparisons)

if __name__ == "__main__":
    main()








