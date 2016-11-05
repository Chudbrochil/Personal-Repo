#GalczakP4
#Programmer: Anthony Galczak
#EMail: agalczak@cnm.edu
#Purpose: Provides user capability to select a game and play it in a browser
import webbrowser
from time import sleep
games = ['chess', 'tic tac toe', 'tetris', 'count', 'global thermonuclear war']
response = raw_input('Would you like to play a game (yes/no)?')
while "yes" in response:
    print "Chess"
    print "Tic Tac Toe"
    print "Tetris"
    print "Count"
    print " "
    print "Global Thermonuclear War"
    usergame = raw_input()
    if "chess" in usergame.lower():
        webbrowser.open_new("http://pygame.org/tags/chess")
    elif "tic tac toe" in usergame.lower():
        webbrowser.open_new("http://www.pygame.org/tags/tictactoe")
    elif "tetris" in usergame.lower():
        webbrowser.open_new("http://www.pygame.org/project-Clone+of+Tetris-2125-.html")
    elif "count" in usergame.lower():
        countnum =  input("How high would you like me to count?")
        for num in range(countnum+1):
            print num
    elif "thermonuclear" in usergame.lower():
        print "Wouldn't you prefer a good game of chess?"
        sleep(1.0)
    else:
        print "I did not understand that!"
        response = raw_input('Would you like to play a game (yes/no)?')
if "no" in response:
    print "Okay, have a great day!"
else:
    print "I did not understand that!"
