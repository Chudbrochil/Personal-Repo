def answer(x, y):
    # We need to analyze the diagonal that is at position (x + y) - 1
    # We can see this via inspection of the example.
    # element (2,3) and (3,2) can be accessed via the 4th diagonal
    diagonal = (x + y) - 1

    # The diagonal's numbering will begin at the sum of the previous integers diagonals.
    # The sum equation is given by (n(n-1))/2
    sequenceStartingValue = (diagonal * (diagonal - 1)) / 2

    # We now need to count diagonally to get our true value.
    # As a guess (via writing it out on paper) it looks like the startingValue
    # + x-coord will give us what we want. The problem counts from left to right
    # via the x-axis from the starting diagonal value
    cellNumber = sequenceStartingValue + x

    return str(cellNumber)
