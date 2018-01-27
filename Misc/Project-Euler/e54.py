'''
Euler Problem 54
Analyze the poker hands found in the text file and find how many times player 1 won
'''

handRank = {'royal flush': 1, 'straight flush':2, '4 of a kind':3, 'full house':3, 'flush':4, 'straight':5,
            'three of a kind': 6, 'two pair':7, 'pair':8, 'high card':9}

numToRank = { 'A': 1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T': 10, 'J': 11, 'Q': 12, 'K': 13 }

p1Wins = 0

def didP1Win(p1Hand, p2Hand):
    calculateHandScore(p1Hand)
    calculateHandScore(p2Hand)










    return False

# Analyze each hand and return it's score
def calculateHandScore(hand):
    
    nums = []
    suits = []

    flush = False
    straight = False

    for card in hand:
        nums.append(numToRank[card[0]])
        suits.append(card[1])

    #print(nums)
    #print(suits)

    # Checking for flush
    if len(set(suits)) == 1:
        flush = True

    # Checking for straight
    sortedNums = sorted(nums)

    



    


def main():

    pokerHandsFile = open("p054_poker.txt", 'r')
    for line in pokerHandsFile:
        line = line.strip("\n")
        tenCards = line.split(" ")
        p1Hand = tenCards[0:5]
        p2Hand = tenCards[5:10]

        if didP1Win(p1Hand, p2Hand):
            p1Wins += 1


if __name__ == "__main__":
    main()



