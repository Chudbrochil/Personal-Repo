Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
Dominos Game - Lab 1 CS 351

README for Dominos Project:
This project was assigned from CS 351 and due Sept. 14, 2017.
The goal was to create a "single-threaded" GUI-driven Dominos game
using Java and JavaFX.


Rules:
A player selects 7 dominos
First player starts the game by placing a domino face-up on the board
Play alternates to the next player to place a domino face-up on the board
The placing domino's tile must match the corresponding tile it is placed against
If the player does not have a domino to play, then they draw a new domino from
    the boneyard until they find one or the boneyard is empty
The game ends when a player has run out of dominos or they have no plays and no
    dominos left to draw from the boneyard
Th last player to place a domino wins


How to Play this Dominos Game:
The game will start with 7 dominos automatically drawn into your hand and the
    computer player's hand.
By default, the first domino in your hand is selected and is represented(displayed)
    by the upper left "Selected Domino" window.
In order to select a new Domino to place or rotate, just click on it.
This will update the "Selected Domino" window again..
After selecting a domino you can attempt to place the domino on the left, on the right,
    or rotate it.
If you are unable to play a piece, the draw button will light up and allow you to
    draw a new domino from the boneyard.
After your play, the computer will automatically make a move onto the board.
The remaining boneyard and dominos in the computer's hand are displayed on the left.
Play continues until someone runs out of dominos or a player has no plays left and
    the boneyard is empty.


Technical Game Flow Explanation:

Main executes the Controller class which instantiates the GameManager.
GameManager holds all of the objects in the program and handles all of the state.
Objects created are Player, Boneyard, Board.
The Domino class is a data-type that is used in these 3 previous classes.
There is GUI configuration and styling in the FXMLDocumentController.fxml

The primary talking point about my design is bringing UI elements down into the
    GameManager. This was a design decision that was made to force a hierarchy of
    top-down management. The alternative was to bring the Player, Board, Boneyard
    objects upto the Controller and bypass a GameManager. I decided to keep all
    state and UI element population in the GameManager class to centralize game
    flow and decisions.


Known Bugs:

The playBuzzer method will not work within a jar. I spent countless hours trying to
    configure resources and various configurations and ultimately was unable to get
    the sound to work in the jar.
    
Under some very, very specific circumstances the canvas for painting the board will
    flip. This does not change the game logic or play. It is slightly confusing when it
    flips however. I changed from ImageView's to painting on the day this project was
    due per request.


Possible future features and optimizations:
- Adding capability for a player vs. player Dominos game
    My game actually started out as a player vs. player Dominos game.
    Since I have a Player class and even a constructor to take a player's name,
    this would not be that difficult to implement. Some dependencies would have to 
    be removed in the game logic of GameManager.
    
- Setting a delay for the computer player for game effect
    This is a relatively easy feature to add. It, however, would break the "single-threaded"
    requirement of this project.
    
- Allowing for the amount of starting dominos to be changed
    This is a relatively easy change with my design. My hand initialize method has a 
    parameter coming in for the number of starting dominos.

    