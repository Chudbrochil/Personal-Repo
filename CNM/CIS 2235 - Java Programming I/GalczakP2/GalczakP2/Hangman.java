// Anthony Galczak agalczak@cnm.edu
// Java I Program 2
// Hangman

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Hangman
{
	private String[] words = {"anamoly", "keyboard", "pleasure", "wheel", "textures", 
	"miracle", "regulate", "technical", "happily", "candles"};
	private DecimalFormat formatter;
	private StringBuilder guessedWord;
	private StringBuilder missedLetters;
	private static String name; // static so it persists between hangman games
	private String hiddenWord;
	private String goodByeString;
	private String gameOverString;
	private static int gamesWon = 0;
	private static int gamesLost = 0;
	private int numberOfMisses = 0;
	private int numberOfGuesses = 0;
	private int attemptsRemain = 0;
// 1 is letter has been guessed already, 2 is correct, 3 is missed letter, 4 is loss/win
	private int result = 0;
	
	
	// Default constructor for Hangman
	public Hangman(){
		formatter = new DecimalFormat("##.#");
		
		// Getting the random int from 0-9 to pick a word, then assigning the hiddenWord
		int wordIndex = (int) (Math.random() * 10);
		hiddenWord = words[wordIndex];
		
		// Creating a new stringbuilder object to hold the missed letters by the user
		missedLetters = new StringBuilder();
		
		// Assembling the guessedWord stringbuilder from hiddenWord and then making it *'s
		guessedWord = new StringBuilder(hiddenWord);
		for (int i = 0; i < hiddenWord.length(); ++i){
			guessedWord.setCharAt(i, '*');
		}
		
		
	}
	
	// Logic of checking the user's letter against the guessedWord (ultimately hiddenWord)
	public int checkGuess(String letter){
		
		String winString = "";
		// Converting the user's guess (letter) to a char for manipulation
		char guessedChar = letter.charAt(0);
		numberOfGuesses++;
		
		// If the user guessed a letter that is already missed or correct
		if ((missedLetters.toString()).contains(letter) || 
		(guessedWord.toString()).contains(letter)){
			numberOfGuesses--;
			result = 1;
		}
		
		// If the user guesses a correct letter that is in hiddenWord
		else if (hiddenWord.contains(letter)){
			
			// Setting result to 2 as the letter is correct
			result = 2;
			ArrayList<Integer> letterIndices = new ArrayList<Integer>(10);
			int charIndex = hiddenWord.indexOf(guessedChar);
			int j = 0;
			
			// Checking how many times the letter appears in the word
			while (charIndex >= 0)
			{
				letterIndices.add(j, charIndex);
				charIndex = hiddenWord.indexOf(guessedChar, charIndex + 1);
				j++;
			}
			
			// For each index I gathered, assign that letter to the guessedWord
			for (int i = 0; i < letterIndices.size(); ++i){
				guessedWord.setCharAt(letterIndices.get(i), guessedChar);
			}
			
			// Checking if the player has won,
			if ((guessedWord.toString()).indexOf('*') == -1)
			{
				gamesWon++;
				result = 4;
				winString = "won!";
			}
			
		}
			
		// Does not contain the char.... (User guessed wrong)
		else{
			result = 3;
			missedLetters.append(guessedChar);
			numberOfMisses++;
			
			
			// Checking if the player has lost
			if (numberOfMisses > hiddenWord.length()){
				gamesLost++;
				result = 4;
				winString = "lost!";
			}
			
			
		}	
		
		// If the user won or lost
		if (result == 4){
			int correctGuesses = numberOfGuesses - numberOfMisses;
			double correctGuessPct = 100.0 * ((float)correctGuesses / (float)numberOfGuesses);
			
			// Creating a single game summary for the user
			gameOverString = "You " + winString + "\nYou correctly guessed " + correctGuesses + 
			" letter(s).\nYou incorrectly guessed " + numberOfMisses + 
			" letter(s).\nYour success percentage was " + formatter.format(correctGuessPct) + 
			"%";
			
			// Creating the entire end game summary for the user (all games played)
			goodByeString = "Hello, " + name + "\nYou won: " + gamesWon + 
			" game(s).\nYou lost: " + gamesLost + " game(s).\nThanks for playing!";
		
		}
		

		
		return result;
		
	}
	
	// Setting user's name into the hangman object as a static variable
	public void setName(String n){
		name = n;
	}
	
	// Getters for info to goto the UI
	public String getGuessingWord(){
		return guessedWord.toString();
	}
	
	public String getMissedString(){
		return missedLetters.toString();
	}
	
	public String getGameOverString(){
		return gameOverString;
	}
	
	public String getGoodBye(){
		return goodByeString;
	}
	
	// Extra methods for slightly more functionality
	public int getAttemptsRemaining(){
		// Calculating the remaining attempts for the user
		attemptsRemain = (guessedWord.length() - missedLetters.length()) + 1;
		return attemptsRemain;
	}
	
	public String getHiddenWord(){
		return hiddenWord;
	}

}