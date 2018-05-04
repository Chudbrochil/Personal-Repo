import math


def answer(area):
    # This problem involves taking max squares from a number until the number is gone.
    solarPanels = []
    while area > 0:
        # Get the largest square out of the area
        biggestPanel = math.pow(math.floor(math.sqrt(area)), 2)
        solarPanels.append(biggestPanel)
        area = area - biggestPanel

    return solarPanels