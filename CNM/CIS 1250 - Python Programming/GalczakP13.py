#GalczakFinal
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Have fun playing blackjack.

#=================================================
#Importing Libraries
import wx
import random
from copy import deepcopy
import os

#Game Result Variable
gamelog = []
#=================================================
#Deal Function
def deal(deck):
#Popping a Card off the Deck
    singleCard = deck.pop(0)
#Returning the Popped Card
    return singleCard

#=================================================
#Hit Function
def hit(event):
#Dealing a Card From CurrentDeck
    pCard = deal(currentDeck)
#Adding that Card to Players Cards
    playersCards.append(pCard)
#Joining Cards Together
    pjCards = ' '.join(playersCards)
#Displaying All Cards
    playerGUI.SetValue(pjCards)
#Calculating Score
    playerScore = pscore()
#Displaying Score
    playerScoreGUI.SetValue(str(playerScore))
#Disabling Hit Button if Bust
    if playerScore > 21:
        hitButton.Disable()

#=================================================
#Stand Function
def stand(event):
#Disabling Hit Button
    hitButton.Disable()
#Removing Hit or Stand Text
    hitorStandLabel = wx.StaticText(win, pos = (25, 300), size = (270, 50), label = '')
#Getting Dealer's Score for Loss State Calculation
    dealerScore = dscore()
#Getting Player's Score for Loss State Calculation
    playerScore = pscore()
#Initializing Loss State
    lossState = ''
#Logic for Dealer Hitting on less than 16
    if dealerScore > 16 or dealerScore == 'Bust!':
#Disabling Stand Button when Score is over 16
        standButton.Disable()
#Logic for Displaying Loss, Win, or Push
        if playerScore == 'Bust!':
            lossState = 'Dealer wins!'
        elif dealerScore == 'Bust!':
            lossState = 'You win!'
        elif playerScore > dealerScore:
            lossState = 'You win!'
        elif dealerScore > playerScore:
            lossState = 'Dealer wins!'
        elif dealerScore == playerScore:
            lossState = 'Push!'
        else:
            lossState = ''
#Adding the Game Result to the Game Log
        gamelog.append(lossState)
#Checking for Player Bust
    else:
        if playerScore == 'Bust!':
            standButton.Disable()
            lossState = 'Dealer wins!'
#When score is less than 16 and player isn't Bust, deal more Cards
        else:
#Popping a Card
            dCard = deal(currentDeck)
#Adding popped card to hand
            dealersCards.append(dCard)
#Joining cards to hand
            djCards = ' '.join(dealersCards)
#Display Hand in GUI
            dealerGUI.SetValue(djCards)
#Calculating Score
            dealerScore = dscore()
#Displaying Score in GUI
            dealerScoreGUI.SetValue(str(dealerScore))       
#Displaying Loss State
    winFont = wx.Font(32, wx.ROMAN, wx.NORMAL, wx.BOLD)
    winorLoseLabel = wx.StaticText(win, pos = (25, 500), size = (100, 50), label = lossState)         
    winorLoseLabel.SetFont(winFont)
#Enabling Shuffle and View Button
    shuffleButton.Enable()
    viewButton.Enable()

#=================================================
#Start Game Function
def start(event):
#Enabling Stand and Hit Button
    standButton.Enable()
    hitButton.Enable()
#Disabling Start Button
    startButton.Disable()
#Dealing 2 Cards for Dealer Initial Hand
    card1 = deal(currentDeck)
    dealersCards.append(card1)
    card2 = deal(currentDeck)
    dealersCards.append(card2)
    djCards = ' '.join(dealersCards)
    dealerGUI.SetValue(djCards)
    dealerScore = dscore()
    dealerScoreGUI.SetValue(str(dealerScore))
#Dealing 2 Cards for Player Initial Hand
    card3 = deal(currentDeck)
    playersCards.append(card3)
    card4 = deal(currentDeck)
    playersCards.append(card4)
    pjCards = ' '.join(playersCards)
    playerGUI.SetValue(pjCards)
    playerScore = pscore()
    playerScoreGUI.SetValue(str(playerScore))
#Static Text for 'Would you like to hit or stand?'
    hitFont = wx.Font(14, wx.ROMAN, wx.NORMAL, wx.BOLD)
    hitorStandLabel = wx.StaticText(win, pos = (25, 300), size = (270, 50), label = 'Would you like to Hit or Stand?')
    hitorStandLabel.SetFont(hitFont)

#=================================================
#Shuffle and Play Function
def shuffleNPlay(event):
#Opening or Writing new Blackjack File
    f = open('Blackjack Results.txt','a')
#Write Gamelog
    f.write(str(gamelog) + '\n')
#Close Results File
    f.close()
#Quitting
    raise SystemExit

#=================================================
#View Function
def view(event):
    f1 = open('Blackjack Results.txt','a')
    f1.write(str(gamelog) + '\n')
    f1.close()
    os.system("notepad.exe " + "Blackjack Results.txt")
    raise SystemExit

#=================================================
#Database of Cards
cardList = ['Ad','Ac','Ah','As','2d','2c','2h','2s','3d','3c','3h','3s','4d',
            '4c','4h','4s','5d','5c','5h','5s','6d','6c','6h','6s','7d','7c',
            '7h','7s','8d','8c','8h','8s','9d','9c','9h','9s','10d','10c','10h',
            '10s','Jd','Jc','Jh','Js','Qd','Qc','Qh','Qs','Kd','Kc','Kh','Ks']

#=================================================
#Initialization
#Creating a deepcopy of CardList assigned to currentDeck
currentDeck = deepcopy(cardList)
#Initializing playersCards as an empty list
playersCards = []
#Initializing dealersCard as an empty list
dealersCards = []
#Shuffling this newly created deck for randomization
random.shuffle(currentDeck)

#=================================================
#GUI
GUI = wx.App()
#Display Main Window
win = wx.Frame(None, title = 'Blackjack!', size = (550, 600))
#Display Dealer's Cards
dealerGUI = wx.TextCtrl(win, pos = (100, 200), size = (150, 25))
dealerLabel = wx.StaticText(win, pos = (100, 175), size = (200, 25), label = 'Dealers Cards')
#Display Dealer's Score
dealerScoreGUI = wx.TextCtrl(win, pos = (25, 200), size = (50, 25))
dealerScoreLabel = wx.StaticText(win, pos = (25, 160), size = (50, 35), label = 'Dealers Score')
#Display Player's Cards
playerGUI = wx.TextCtrl(win, pos = (100, 400), size = (150, 25))
playerLabel = wx.StaticText(win, pos = (100, 375), size = (200, 25), label = 'Players Cards')
#Display Player's Score
playerScoreGUI = wx.TextCtrl(win, pos = (25, 400), size = (50, 25))
playerScoreLabel = wx.StaticText(win, pos = (25, 360), size = (50, 35), label = 'Players Score')
#Hit Button
hitButton = wx.Button(win, label = 'Hit', pos = (300, 350), size = (100, 25))
hitButton.Bind(wx.EVT_BUTTON, hit)
hitButton.Disable()
#Stand Button
standButton = wx.Button(win, label = 'Stand', pos = (300, 400), size = (100, 25))
standButton.Bind(wx.EVT_BUTTON, stand)
standButton.Disable()
#Shuffle and Play Again Button
shuffleButton = wx.Button(win, label = 'Write Result and Quit!', pos = (300, 300), size = (200, 25))
shuffleButton.Bind(wx.EVT_BUTTON, shuffleNPlay)
shuffleButton.Disable()
#Start Game Button
startButton = wx.Button(win, label = 'Start Game!', pos = (300, 450), size = (150, 25))
startButton.Bind(wx.EVT_BUTTON, start)
#View Results Button
viewButton = wx.Button(win, label = 'Write Result, View and Quit!', pos = (300, 250), size = (200, 25))
viewButton.Bind(wx.EVT_BUTTON, view)
viewButton.Disable()

#Rules of Blackjack Display
rulesLabel = wx.StaticText(win, pos = (25, 25), size = (250, 120),
label = 'Rules of Blackjack \n \
Dealer Must Hit on 16 or Below \n \
Press Start Game! in Order to Start \n \
Press Hit to Get Another Card \n \
Press Stand to Stop and Have Dealer Play \n \
Press Shuffle to Play Again!')

#=================================================
#Player Score Function
def pscore():
#Capturing the Cards from GUI
    rawCards = playerGUI.GetValue()
#Initializing empty list for cards
    cards = []
#Iterating through GUI cards and making them strings
    for rcard in rawCards:
        cards.append(str(rcard))
#Initializing Total Score as 0
    totalScore = 0
#Logic for Score Calculation, Using sets to determine math
    for card in cards:
        if card in set('KQJ1'):
            totalScore += 10
        elif card in set('23456789'):
            totalScore += int(card)
        elif card in set('A'):
            totalScore += 11
        else:
            totalScore += 0
#Logic for Busting
    if totalScore > 21:
        return "Bust!"
    else:
        return totalScore
    
#=================================================
#Dealer Score Function
def dscore():
#Capturing the Cards from GUI
    rawCards = dealerGUI.GetValue()
#Initializing empty list for cards
    cards = []
#Iterating through GUI cards and making them strings
    for rcard in rawCards:
        cards.append(str(rcard))
#Initializing Total Score as 0
    totalScore = 0
#Logic for Score Calculation, Using sets to determine math
    for card in cards:
        if card in set('KQJ1'):
            totalScore += 10
        elif card in set('23456789'):
            totalScore += int(card)
        elif card in set('A'):
            totalScore += 11
        else:
            totalScore += 0
#Logic for Busting
    if totalScore > 21:
        return "Bust!"
    else:
        return totalScore
    
#=================================================
#GUI Show and Loop
win.Show()
GUI.MainLoop()
