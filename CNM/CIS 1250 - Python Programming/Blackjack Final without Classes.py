#GalczakFinal
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Have fun playing blackjack.

#Importing Libraries
import wx
import random
from copy import deepcopy
    
#Deal and Hit Function
def deal(deck):
    singleCard = deck.pop(0)
    return singleCard

def hit(event):
    pCard = deal(currentDeck)
    playersCards.append(pCard)
    pjCards = ' '.join(playersCards)
    playerGUI.SetValue(pjCards)
    playerScore = pscore()
    playerScoreGUI.SetValue(str(playerScore))
##    if playerScoreGUI == 'Bust!':
##        wx.CallAfter(hitButton.Disable)
##        hitButton.Refresh()

def stand(event):
    hitButton.Disable()
    dCard = deal(currentDeck)
    dealersCards.append(dCard)
    djCards = ' '.join(dealersCards)
    dealerGUI.SetValue(djCards)
    dealerScore = dscore()
    dealerScoreGUI.SetValue(str(dealerScore))

#---Database of Cards---
cardList = ['Ad','Ac','Ah','As','2d','2c','2h','2s','3d','3c','3h','3s','4d',
            '4c','4h','4s','5d','5c','5h','5s','6d','6c','6h','6s','7d','7c',
            '7h','7s','8d','8c','8h','8s','9d','9c','9h','9s','10d','10c','10h',
            '10s','Jd','Jc','Jh','Js','Qd','Qc','Qh','Qs','Kd','Kc','Kh','Ks']

#---Init---
#Creating a deepcopy of CardList assigned to currentDeck
currentDeck = deepcopy(cardList)
#Initializing playersCards as an empty list
playersCards = []
#Initializing dealersCard as an empty list
dealersCards = []
#Shuffling this newly created deck for randomization
random.shuffle(currentDeck)

#---GUI---
GUI = wx.App()
#Display Main Window
win = wx.Frame(None, title = 'Blackjack!', size = (900, 600))
#Display Dealer's Cards
dealerGUI = wx.TextCtrl(win, pos = (100, 200), size = (200, 25))
dealerLabel = wx.StaticText(win, pos = (100, 175), size = (200, 25), label = 'Dealers Cards')
#Display Dealer's Score
dealerScoreGUI = wx.TextCtrl(win, pos = (25, 200), size = (50, 25))
dealerScoreLabel = wx.StaticText(win, pos = (25, 160), size = (50, 50), label = 'Dealers Score')
#Display Player's Cards
playerGUI = wx.TextCtrl(win, pos = (100, 400), size = (200, 25))
playerLabel = wx.StaticText(win, pos = (100, 375), size = (200, 50), label = 'Players Cards')
#Display Player's Score
playerScoreGUI = wx.TextCtrl(win, pos = (25, 400), size = (50, 25))
playerScoreLabel = wx.StaticText(win, pos = (25, 360), size = (50, 50), label = 'Players Score')
#Hit Button
hitButton = wx.Button(win, label = 'Hit', pos = (400, 400), size = (100, 25))
hitButton.Bind(wx.EVT_BUTTON, hit)
#Stand Button
standButton = wx.Button(win, label = 'Stand', pos = (550, 400), size = (100, 25))
standButton.Bind(wx.EVT_BUTTON, stand)

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

#Show main window
win.Show()
#GUI Loop
GUI.MainLoop()
