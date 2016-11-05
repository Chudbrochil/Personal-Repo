// Anthony Galczak agalczak@cnm.edu
// Java I Program 2
// MainP2

public class MainP2
{
	// Creating a reference to a Hangman object
	HangmanUI ui;
	
	// "Main" within MainP2
	public static void main(String[] args)
	{
		MainP2 app = new MainP2();
	}
	
	// Default constructor for MainP2
	public MainP2()
	{
		ui = new HangmanUI();
		ui.Play();
		System.exit(0);
	}
}