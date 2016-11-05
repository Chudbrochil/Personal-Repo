// Anthony Galczak agalczak@cnm.edu
// CIS 2235 Program 1 - Hangman

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Hangman{
	
	public static void main(String[] args){
		// Words to be guessed
		String[] words = {"anamoly", "keyboard", "pleasure", "wheel", "textures",
		"miracle", "regulate", "technical", "happily", "candles"};
		
		// Character for if the user wants to play another game
		int anotherGame;
		
		// Game variables for use at end of game report / manipulated during game losses/wins
		int gamesWon = 0, gamesLost = 0;
		String usersName;
		
		// Class header
		JOptionPane.showMessageDialog(null, "Welcome to my Hangman Game.", "CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Instructions dialog box
		JOptionPane.showMessageDialog(null, "Enter a letter to guess at the word.\nYou will get as many guesses as there are letters in the word.",
		"CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Asking user for their name with a JOptionPane
		usersName = JOptionPane.showInputDialog(null, "Enter your name please.", "CIS 2235 - GalczakP1", JOptionPane.INFORMATION_MESSAGE);
		
		// Starting a do while loop to setup a hangman game for play
		do{
			// Generating a random int from 0 to 9, will be assigned to a word in the words array
			int wordIndex = (int) (Math.random() * 10);
			
			// Assigning the true hidden word from the words array
			String hiddenWord = words[wordIndex];
			
			// Initializing the guessed word that the user is going to guess against as well as the missed letter string
			StringBuilder guessedWord = new StringBuilder(hiddenWord);
			StringBuilder missedLetters = new StringBuilder();
			for (int i = 0; i < hiddenWord.length(); ++i)
			{
				guessedWord.setCharAt(i, '*');
			}
			
			// Variables being declared to be used in next loop, -1 = null, 0 = lose, 1 = win for didTheyWin
			int numberOfMisses = 0, numberOfGuesses = 0, didTheyWin = -1;
			String guessedString;
			
			// A nested do while for actually playing the hangman game
			do{
				// Adding functionality for user to know how many guesses they have left...
				int attemptsRemain = (guessedWord.length() - missedLetters.length()) + 1;
				
				// Asking user to input a letter and then converting it to a char
				guessedString = JOptionPane.showInputDialog(null, "Attempts remaining: " + attemptsRemain + "\nGuess a letter in this word: " + guessedWord, 
				missedLetters.length() + " Letters missed: " + missedLetters.toString(), JOptionPane.INFORMATION_MESSAGE);
				char guessedChar = guessedString.charAt(0);
				numberOfGuesses++;
				
				// If the user has guessed a letter that has been guessed already, tell them to try again
				if ((missedLetters.toString()).contains(guessedString) || (guessedWord.toString()).contains(guessedString))
				{
					JOptionPane.showMessageDialog(null, "You have already guessed that letter.", "Please try again.", JOptionPane.INFORMATION_MESSAGE);
					
					// This isn't a valid guess, taking one value off numberofguesses
					numberOfGuesses--;
				}
				// If the user guesses a correct letter that is in hiddenWord
				else if (hiddenWord.contains(guessedString))
				{
					
					// Initializing an array to hold the index ints of the word letters
					ArrayList<Integer> letterIndices = new ArrayList<Integer>(10);
					
					// Starting a while loop so that I can collect all of the letters in the hiddenWord
					int charIndex = hiddenWord.indexOf(guessedChar);
					int j = 0;
					while (charIndex >= 0)
					{
						letterIndices.add(j, charIndex);
						charIndex = hiddenWord.indexOf(guessedChar, charIndex + 1);
						j++;
					}
					
					// Assigning the letters into guessed word so the user can see their success
					for (int i = 0; i < letterIndices.size(); ++i)
					{
						// Setting the indices for the letter guessed at the index in guessedWord
						guessedWord.setCharAt(letterIndices.get(i), guessedChar);
					}
					
					// Checking if the player has won, if guessedWord doesn't have *'s anymore...
					if ((guessedWord.toString()).indexOf('*') == -1)
					{
						JOptionPane.showMessageDialog(null, "You have won! The word was: " + hiddenWord, "Winner!", JOptionPane.INFORMATION_MESSAGE);
						didTheyWin = 1;
						gamesWon++;
					}
				}
				
				// If their guess was incorrect
				else
				{
					// Adding the guessed letter to the missedLetters stringbuilder and incrementing how many misses they have
					missedLetters.append(guessedChar);
					numberOfMisses = numberOfMisses + 1;
					
					// Telling the user that they guessed an incorrect letter and to try again
					JOptionPane.showMessageDialog(null, "Sorry, your letter \"" + guessedChar + "\" is not in the word.", 
					"Try again.", JOptionPane.INFORMATION_MESSAGE);
					
					// If the player loses...
					if (numberOfMisses > hiddenWord.length())
					{
						JOptionPane.showMessageDialog(null, "The word was: " + hiddenWord, "You have lost!", JOptionPane.INFORMATION_MESSAGE);
						gamesLost++;
						didTheyWin = 0;
					}
					
				}
				
				

			// do while ends when user either loses or wins
			} while (didTheyWin == -1);
			
			// Calculating number of correctGuesses and correctGuessPct and "win string"
			int correctGuesses = numberOfGuesses - numberOfMisses;
			double correctGuessPct = 100.0 * ((float)correctGuesses / (float)numberOfGuesses);
			String winString;
			if (didTheyWin == 0)
			{
				winString = "lost!";
			}
			else
			{
				winString = "won!";
			}
			
			// Creating a decimal format object to make a percentage display correctly
			DecimalFormat pctFormat = new DecimalFormat("##.#");
			
			// Dialogbox displaying stats the for the game they just lost or won
			JOptionPane.showMessageDialog(null, "You " + winString + "\nYou correctly guessed " + correctGuesses + 
			" letter(s).\nYou incorrectly guessed " + numberOfMisses + " letter(s).\nYour success percentage was " + pctFormat.format(correctGuessPct) + 
			"%", "Game Stats", JOptionPane.INFORMATION_MESSAGE);
			
			// Dialogbox asking if user wants to play another game
			anotherGame = JOptionPane.showConfirmDialog(null, "Would you like to play another game?", "More Games?", JOptionPane.YES_NO_OPTION);
			
		} while (anotherGame == JOptionPane.YES_OPTION);
		
		
		JOptionPane.showMessageDialog(null, "Hello, " + usersName + "\nYou won: " + gamesWon + " game(s).\nYou lost: " + 
		gamesLost + " game(s).\nThanks for playing!", "Session Stats", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
	
	
}