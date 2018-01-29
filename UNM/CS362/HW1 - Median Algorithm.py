'''
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS362 - Algorithms & Data Structures II
Homework 1 - Quick Search and Median of Medians Algorithms
'''


import random

Verbosity = 1 # 0 for none, 1 for minimal, 2 for verbose


# TODO LIST:
# The comparisons count done in momSearch is wrong. I need to also be counting the comparisons being done
# when getting medians of blocks and then the median of medians.
# Also clean up momSearch after fixing comparisons...
#
# Generate stats for:
#     (DONE? Maybe he wants an analysis of each run vs. momSearch)How often quickSearch is "faster" than momSearch()
#     (DONE)Average Ratio of QS/MoM
#     (DONE)Avg Cost for QS
#     10th percentile compare
#     90th percentile compare
#
# Ask about formal definition of a comparison, also some advice on implementing this in momsearch would help
#
# Second half of quickSearch and momSearch looks almost exactly the same...


# quickSearch
# Takes a random array and calculates using the "quickSearch" algorithm the i'th
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


# momSearch
# Takes a random array and calculates using the deterministic "median-of-medians" algorithm
# the i'th smallest element from the sorted array.
# blockSize - The size of the chunk we are chopping our list into
# Returns the element found to be the i'th smallest element
def momSearch(array, index, blockSize):
    leftSide = []
    rightSide = []

    # Chopping our array into blocks of blockSize (usually size 5)
    choppableArray = array[:]
    blockList = []
    blockOfElements = []
    while choppableArray:

        # If we are on our last chop of blockSize size of lower, then just add these into a block and stop
        if(len(choppableArray) <= blockSize and len(blockOfElements) == 0):
            blockList.append(choppableArray)
            break
        elif len(blockOfElements) == blockSize:
            blockList.append(blockOfElements)
            blockOfElements = []
        else:
            blockOfElements.append(choppableArray.pop())

    # TODO: Shelving this manual sort for now... going to use python's sorted()
    # for block in blockList:
    #     # "Sort" the list and find the median of each block and add it to listOfMedians
    #
    #     # TODO: ONLY DOING THIS FOR 5 OR LESS FOR NOW
    #     #if len(block) <= blockSize:
    #         # Breaking into pairs
    #         for x in range(0, len(block), 2):
    #             print("Blocksize: %d Index: %d" % (len(block), x))
    #             if x+1 < len(block) and block[x] > block[x+1]:
    #                 block[x], block[x+1] = block[x+1], block[x] # in place swap

    # Find median in each block of 5...
    listOfMedians = []
    for block in blockList:
        # TODO: I need to break this sort apart and count comparisons in here....
        median = sorted(block)[len(block)/2]
        listOfMedians.append(median)

    if Verbosity > 1:
        print("Full array: %s" % array[:])
        print("List of Medians: %s" % listOfMedians[:])

    # Inspired by https://brilliant.org/wiki/median-finding-algorithm/
    if len(listOfMedians) <= blockSize:
        medianOfMedians = sorted(listOfMedians)[len(listOfMedians)/2]
    else:
        medianOfMedians = momSearch(listOfMedians, len(listOfMedians)/2, blockSize)

    for element in array:
        if element < medianOfMedians:
            momSearch.counter += 1
            leftSide.append(element)
        elif element > medianOfMedians:
            momSearch.counter += 1
            rightSide.append(element)

    if index <= len(leftSide):
        return momSearch(leftSide, index, blockSize)
    elif index == len(leftSide) + 1:
        return medianOfMedians
    elif index > len(leftSide) + 1:
        return momSearch(rightSide, index - (len(leftSide) + 1) , blockSize)


# shuffle
# Takes in an array and returns it shuffled.
# NOTE: This is not pythonic, but it was requested to manually shuffle the elements
def shuffle(array):
    shuffledArray = []
    baseArray = array[:]
    while baseArray:
        randomElement = baseArray.pop(random.randrange(len(baseArray)))
        shuffledArray.append(randomElement)
    return shuffledArray


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


# momSearchRun
# Runs momSearch on all of our shuffled lists.
# Returns a list of comparisons for each list we processed.
def momSearchRun(listOfShuffledLists, index, blockSize):

    if Verbosity > 0:
        print("Running median-of-median search on %d lists looking for %d'th smallest element with block size of %d."
              % (len(listOfShuffledLists), index, blockSize))

        momSearchComparisons = []
    for list in listOfShuffledLists:
        momSearch.counter = 0
        momSearch(list, index, blockSize)
        momSearchComparisons.append(momSearch.counter)

    return momSearchComparisons


# generateStatistics
# Runs statistics and uses data from the two algorithm runs to produce
# ratios and comparisons between quickSearch and momSearch
def generateStatistics(quickSearchComparisons, momSearchComparisons):

    listIndex = 0
    sumOfQSAvgs = 0
    sumOfRatios = 0
    qsFaster = 0

    if len(quickSearchComparisons) != len(momSearchComparisons):
        raise Exception("A different amount of lists were ran for quick-search and median-of-medians. Crashing...")

    listSize = len(quickSearchComparisons)

    for qsList in quickSearchComparisons:
        # sum(qsList, 0.0) makes this list into a floating point list, slick way to get more precise values without python 3
        avgOfQSList = sum(qsList, 0.0) / len(qsList)
        sumOfQSAvgs += avgOfQSList
        momComparison = momSearchComparisons[listIndex]
        qsMomRatio = momComparison / avgOfQSList
        sumOfRatios += qsMomRatio

        if avgOfQSList < momComparison:
            qsFaster += 1


        if Verbosity > 0:
            print("#%d Comparisons Quick-Search Max: %d Min: %d Avg: %0.2f MoM-Search: %d QS/MoM Ratio: %0.2f"
                  % (listIndex, max(qsList), min(qsList), avgOfQSList, momComparison, qsMomRatio))
        listIndex += 1

    quickSearchAvg = sumOfQSAvgs / listSize
    qsMomAvgRatio = sumOfRatios / listSize

    if Verbosity > 0:
        print("Average comparisons of all quick search runs on all %d lists: %0.2f" % (listSize, quickSearchAvg))
        print("Average ratio between MoM Search and Quick Search on %d lists is: %0.2f" % (listSize, qsMomAvgRatio))
        print("Over %d lists, Quick Search is fastest %d times, Mom Search fastest %d times."
              % (listSize, qsFaster, listSize - qsFaster))


def main():
    # Initial values to main, could be passed in as arguments from CLI
    firstListValue = 1 # Value we are starting our random lists at
    lastListValue = 100 # Value we are ending our random lists at
    howManyLists = 100 # How many random lists to make
    iterations = 100 # How many times to run quickSearch to normalize stats
    indexToFind = 50 # Index we were told to find
    blockSize = 5 # How big we want our blocks on deterministic median-of-median search

    listOfShuffledLists = createListOfRandomLists(firstListValue, lastListValue, howManyLists)
    quickSearchComparisons = normalizedQuickSearchRun(listOfShuffledLists, iterations, indexToFind)
    momSearchComparisons = momSearchRun(listOfShuffledLists, indexToFind, blockSize)
    generateStatistics(quickSearchComparisons, momSearchComparisons)

if __name__ == "__main__":
    main()

