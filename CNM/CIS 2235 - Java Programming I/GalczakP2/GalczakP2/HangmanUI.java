// Anthony Galczak agalczak@cnm.edu
// Java I Program 2
// HangmanUI

import javax.swing.JOptionPane;

public class HangmanUI
{
	private Hangman hang;
	private String usersName;
	private int result = 0;
	
	// Default constructor for HangmanUI
	public HangmanUI(){
		hang = new Hangman();
	}
	
	public void Play(){
		// Class header
		JOptionPane.showMessageDialog(null, "Welcome to my Hangman Game.", "CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Instructions dialog box
		JOptionPane.showMessageDialog(null, "Enter a letter to guess at the word.\nYou will get as many guesses as there are letters in the word.",
		"CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Asking user for their name with a JOptionPane
		usersName = JOptionPane.showInputDialog(null, "Enter your name please.", "CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Setting the users name into the hangman object
		hang.setName(usersName);
		
		int anotherGame;
		
		// Starting a nested do while loop for playing another game
		do{
			
			
			// Starting a do while loop, i.e. the game itself
			do{
				// Gathering what is already missed and the guess word from the object
				String guessedWord = hang.getGuessingWord();
				String missedString = hang.getMissedString();
				int attemptsRemain = hang.getAttemptsRemaining();
				
				// Having the user guess a letter in the word
				String guessedString = JOptionPane.showInputDialog(null, "Attempts remaining: " + 
				attemptsRemain + "\nGuess a letter in this word: " + guessedWord, missedString.length() + 
				" Letters missed: " + missedString.toString(), JOptionPane.INFORMATION_MESSAGE);
				
				// Entering the user's letter guess into the calculation method of the Hangman object
				result = hang.checkGuess(guessedString);
				
				// Getting the hiddenWord and gameOver to display to the user in case they win or lose.
				String hiddenWord = hang.getHiddenWord();
				String gameOver = hang.getGameOverString();
				
				// Word has already been guessed correctly or incorrectly
				if (result == 1){
					JOptionPane.showMessageDialog(null, "You have already guessed that letter.", 
					"Please try again.", JOptionPane.INFORMATION_MESSAGE);
				}
				// Incorrect guess
				else if (result == 3){
					JOptionPane.showMessageDialog(null, "Sorry, your letter \"" + guessedString + 
					"\" is not in the word.", "Try again.", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// Correct guess / result == 2 can be skipped for this loop as nothing needs 
				// to be presented to the user except continuing
				
				
				// Win/Loss
				else if (result == 4){				
					JOptionPane.showMessageDialog(null, "The word was: " + hiddenWord, 
					"Game Over.", JOptionPane.INFORMATION_MESSAGE);
					
					JOptionPane.showMessageDialog(null, gameOver, "Game Stats", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			} while (result != 4); // while user hasn't won or lost continue...
		
		
		// Asking user if they would like to play again
		anotherGame = JOptionPane.showConfirmDialog(null, "Would you like to play another game?", 
		"More Games?", JOptionPane.YES_NO_OPTION);
		
		// If they want to play again... make a new hangman object to loop back around to within Play()
		if (anotherGame == JOptionPane.YES_OPTION){
			hang = new Hangman();
		}
		
		// If the user selects yes, keep the show going
		} while (anotherGame == JOptionPane.YES_OPTION);
		
		// User is done with the game, sending them the goodbye msg
		String goodByeString = hang.getGoodBye();
		JOptionPane.showMessageDialog(null, goodByeString, "Session Stats", 
		JOptionPane.INFORMATION_MESSAGE);
		
	}

}