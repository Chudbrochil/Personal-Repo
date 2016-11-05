// Anthony Galczak agalczak@cnm.edu
// Java I Program 3
// Hangman

import javax.swing.JFrame;

public class MainP3{
	
	// Creating a new reference to a hangframe object called frame
	private HangFrame frame;
	
	public static void main(String[] args){
		MainP3 app = new MainP3();
	}
	
	// Constructor
	public MainP3(){
		// Creating the frame details
		frame = new HangFrame();
		frame.setTitle("Hangman Game!");
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
}