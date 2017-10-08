Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
Boggle Game - Lab 2 CS 351

This project was assigned from CS 351 and due Oct. 12, 2017.
The goal as to create a "single-threaded" GUI-driven Boggle game using
Java and JavaFX.

Rules:

Try to form as many words as possible in the allotted time.
-Letters are valid if:
-At least 3-letters long
-Are not capitalized or foreign words.
-Are visible on the tray via adjacent letters without using the same piece
-Are listed in the standard dictionary

Score is calculated as such: word length - 2, so "mouse" is 3 points
There is no penalty for short words or words not in dictionary.

How to Play:
Left-Click to select a game piece.
Keep in mind you must select an adjacent piece horizontally, vertically or diagonally.
Right-click to try to check the current built word.

Game Flow:
Main executes the Controller class which instantiates the GameManager.
GameManager holds all of the objects in the program and handles all of the state.
There are various objects created by GameManager.
BoggleDieSet and BoggleDie are only used if the player wants to use "real-dice".
BoardSetup does the initial board setup depending upon whether you want random or real dice.
Controller holds all of the UI elements and passes down the appropriate ones to
    the GameManager.
BogglePiece is the actual Piece that is drawn including it's character inside of it.

Known Bugs:
None so far! 

List of extra features:
-Capability to play on 6x6 board in addition to 4x4 and 5x5
-Very fine-tuned "U" adding probability after Q. This will make sure
    that the U is added in an appropriate spot to a Q
-Option to play with "real dice". This basically goes through a real set
    of Boggle dice for each game size and rolls a random die-face off from
    a die. This makes the layout of the characters 100% realistic.
-Sounds for placing a legitimate word or a bad word.
-Help MenuBar including Rules, Controls and About
-End game screen

Possible future features and optimizations:
Eventually, this project needed to stop being developed as I've spent a 
ton of time on it and I do have other classes. However, here is a list of possible
features to add.

- Dice being rolled one by one for effect for the "real dice" game type
- Combining the ChoiceDialog's at the beginning of the game into one
    This seemed to involve creating your own custom dialog. I spent some
    time looking at it, but ultimately wasn't worth it at the time.
- Option to restart the game
- Ability to print out all the available words at the end.
    I analyzed many different ways to do this and even started on it a bit.
    This is a pretty difficult problem considering the huge amount of
    possible words in a 6x6 board.
- Possibly a button to start the timer/game at the beginning. This would be
    very easy to do, but I'm not sure it adds anything to the functionality.
    It forces a user to just press another button to get going.
