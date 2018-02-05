'''
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS362 - Algorithms & Data Structures II
Homework 1 - Quick Search and Median of Medians Algorithms
'''

import random
Verbosity = 1 # 0 for none, 1 for minimal, 2 for verbose


# quickSearch
# Takes a random array and calculates using the "quickSearch" algorithm the i'th
# smallest element from the sorted array.
# Returns the element found to be the i'th smallest element
def quickSearch(array, index):
    # Getting a random number from the array
    pivot = array[random.randint(0, len(array) - 1)]
    return partitionAndCall(quickSearch, index, array, pivot)


# momSearch
# Takes a random array and calculates using the deterministic "median-of-medians" algorithm
# the i'th smallest element from the sorted array.
# Returns the element found to be the i'th smallest element
def momSearch(array, index):

    # Chopping our array into blocks of 5
    blockSize = 5
    blockOfElements = []
    for element in range(0, len(array), blockSize):
        blockOfElements.append(array[element:element+blockSize])

    # Find median in each block of 5
    listOfMedians = []
    for block in blockOfElements:
        median = sorted(block)[len(block)/2]
        listOfMedians.append(median)
        addComparisons(block)

    if len(listOfMedians) <= blockSize:
        medianOfMedians = sorted(listOfMedians)[len(listOfMedians)/2]
        addComparisons(listOfMedians)
    else:
        medianOfMedians = momSearch(listOfMedians, len(listOfMedians)/2)

    return partitionAndCall(momSearch, index, array, medianOfMedians)


# partitionAndCall
# One slick observation is that the end part of median-of-medians and quick search is the same.
# Therefore, I abstracted their ends into the same algorithm. The only difference is how the
# algorithms select their pivot.
def partitionAndCall(selectAlgo, index, array, pivot):
    leftSide = []
    rightSide = []

    for element in array:
        if element < pivot:
            selectAlgo.counter += 1
            leftSide.append(element)
        elif element > pivot:
            selectAlgo.counter += 1
            rightSide.append(element)

    if index < len(leftSide) + 1:
        return selectAlgo(leftSide, index)
    elif index > len(leftSide) + 1:
        return selectAlgo(rightSide, index - len(leftSide) - 1)
    else:
        return pivot


# addComparisons
# Adds the corresponding "correct" amount of comparisons to a given sort on an array size <= 5
# This is used anytime I used python's "sorted" in Median-of-Medians
#
# The comparisons here are educated guesses based upon Knuth's Art of Programming algorithmsxsxxxsxsxsxsxssssssssssssssssss
# This is in lieu of manually implementing this "comparison sort", which I was told isn't necessary
def addComparisons(array):
    if len(array) == 5:
        momSearch.counter += 7
    elif len(array) == 4:
        momSearch.counter += 3
    elif len(array) == 3:
        momSearch.counter += 2
    elif len(array) == 2:
        momSearch.counter += 1


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
    for x in range(firstListValue, lastListValue + 1):
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
            requestedElement = quickSearch(shuffledList, index)
            if Verbosity > 1:
                print("QS pivot returned: %d" % requestedElement)
            comparisonsPerList.append(quickSearch.counter)

        quickSearchComparisons.append(comparisonsPerList)

    return quickSearchComparisons


# momSearchRun
# Runs momSearch on all of our shuffled lists.
# Returns a list of comparisons for each list we processed.
def momSearchRun(listOfShuffledLists, index):

    if Verbosity > 0:
        print("Running median-of-median search on %d lists looking for %d'th smallest element with block size of 5."
              % (len(listOfShuffledLists), index))

        momSearchComparisons = []
    for list in listOfShuffledLists:
        momSearch.counter = 0
        requestedElement = momSearch(list, index)
        if Verbosity > 1:
            print("MoM pivot returned: %d" % requestedElement)
        momSearchComparisons.append(momSearch.counter)

    return momSearchComparisons


# generateStatistics
# Runs statistics and uses data from the two algorithm runs to produce
# ratios and comparisons between quickSearch and momSearch
def generateStatistics(quickSearchComparisons, momSearchComparisons):

    listIndex = 0
    sumOfQSAvgs = 0
    sumOfRatios = 0
    qsFasterTotal = 0
    sum10th = 0
    sum90th = 0
    iterations = len(quickSearchComparisons[0])
    qsOverallMin = 1000000
    qsOverallMax = 0
    qsMinLessThanMom = 0
    qs10thLessThanMom = 0
    qsAvgLessThanMom = 0
    qs90thLessThanMom = 0
    qsMaxLessThanMom = 0

    if len(quickSearchComparisons) != len(momSearchComparisons):
        raise Exception("A different amount of lists were ran for quick-search and median-of-medians. Crashing...")

    listSize = len(quickSearchComparisons)

    if Verbosity > 0:
        print("\nQuick-Search and MoM Comparison Individual List Statistics")

    # Processing statistics for each list of quick search comparisons
    for qsList in quickSearchComparisons:
        qsFaster = 0
        avgOfQSList = sum(qsList) / (len(qsList) * 1.0)
        sumOfQSAvgs += avgOfQSList
        momComparison = momSearchComparisons[listIndex]
        qsMomRatio = avgOfQSList / momComparison
        sumOfRatios += qsMomRatio

        percentile10th = sorted(qsList)[9]
        percentile90th = sorted(qsList)[89]
        sum10th += percentile10th
        sum90th += percentile90th

        for qsRun in qsList:
            if qsRun < momComparison:
                qsFaster += 1

        qsFasterTotal += qsFaster

        if min(qsList) < qsOverallMin:
            qsOverallMin = min(qsList)
        if max(qsList) > qsOverallMax:
            qsOverallMax = max(qsList)

        if min(qsList) < momComparison:
            qsMinLessThanMom += 1
        if percentile10th < momComparison:
            qs10thLessThanMom += 1
        if avgOfQSList < momComparison:
            qsAvgLessThanMom += 1
        if percentile90th < momComparison:
            qs90thLessThanMom += 1
        if max(qsList) < momComparison:
            qsMaxLessThanMom += 1

        if Verbosity > 0:
            print("#%04d Comparisons Q-S Min:%03d Max:%03d Avg:%0.2f 10th:%d 90th:%d | Mom-Search:%d QS/MoM Ratio:%0.4f QS-Faster:%d/%d"
                  % (listIndex, min(qsList), max(qsList), avgOfQSList, percentile10th, percentile90th,
                     momComparison, qsMomRatio, qsFaster, len(qsList)))

        listIndex += 1

    # Roll up of all the data for all lists
    qsAvg = sumOfQSAvgs / listSize
    qsMomAvgRatio = sumOfRatios / listSize
    qsAvg10th = sum10th / (listSize * 1.0)
    qsAvg90th = sum90th / (listSize * 1.0)
    mom10th = sorted(momSearchComparisons)[9]
    mom90th = sorted(momSearchComparisons)[89]
    momAvg = sum(momSearchComparisons) / listSize * 1.0

    if Verbosity > 0:
        print("\nTotal run statistics:\n%d*%d(%d) quick-search iterations, %d total lists."
              % (iterations, listSize, iterations*listSize, listSize))
        print("Average over all Q-S runs:%0.2f Min:%d Avg 10th:%0.2f Avg 90th:%0.2f Max:%d"
              % (qsAvg, qsOverallMin, qsAvg10th, qsAvg90th, qsOverallMax))
        print("Average over all MoM runs:%0.2f Min:%d 10th:%d 90th:%d Max:%d"
              % (momAvg, min(momSearchComparisons), mom10th, mom90th, max(momSearchComparisons)))
        print("Quick Search is fastest %d time(s), Mom Search fastest %d time(s). Ratio between QS/MoM:%0.4f"
              % (qsFasterTotal, iterations*listSize - qsFasterTotal, qsMomAvgRatio))
        print("Over all lists, how many are below MoM? QS-Min:%d/%d QS-10th:%d/%d QS-Avg:%d/%d QS-90th:%d/%d QS-Max:%d/%d " 
              % (qsMinLessThanMom, listSize, qs10thLessThanMom, listSize, qsAvgLessThanMom, listSize, qs90thLessThanMom, listSize, qsMaxLessThanMom, listSize))



def main():
    # Initial values to main, could be passed in as arguments from CLI
    firstListValue = 1 # Value we are starting our random lists at
    lastListValue = 100 # Value we are ending our random lists at
    howManyLists = 100 # How many random lists to make
    iterations = 100 # How many times to run quickSearch to normalize stats
    indexToFind = 50 # Index we were told to find

    listOfShuffledLists = createListOfRandomLists(firstListValue, lastListValue, howManyLists)
    quickSearchComparisons = normalizedQuickSearchRun(listOfShuffledLists, iterations, indexToFind)
    momSearchComparisons = momSearchRun(listOfShuffledLists, indexToFind)
    generateStatistics(quickSearchComparisons, momSearchComparisons)

if __name__ == "__main__":
    main()

