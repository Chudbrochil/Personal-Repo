// Anthony Galczak agalczak@cnm.edu
// Java I Program 3
// Hangman

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangFrame extends JFrame{
	private Hangman hang;
	private HangmanPanel canvas; // for drawing the hangman person
	private static int segment;
	
	// Swing variables
	JTextArea jtaAskLetter, jtaResults, jtaTitle, jtaHiddenWord;
	JTextField jtHiddenWord, jtName, jtAskLetter;
	JButton okButton, playAgainButton, doneButton;
	JLabel lblEnterName, lblAlreadyGuessed;
	JPanel p1;
	
	// Constructor
	public HangFrame(){
		hang = new Hangman();
		canvas = new HangmanPanel();
		initComponents();
		showGuessingWord();
		segment = 0;
		
		// Adding action listener classes to my buttons
		okButton.addActionListener(new OKButtonListener());
		doneButton.addActionListener(new doneButtonListener());
		playAgainButton.addActionListener(new againButtonListener());
		
	}
	
	// Drawing the initial frame
	private void initComponents(){
		// Creating a font to use with jtaAskLetter
		Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
		
		// Swing Text Areas
		jtaAskLetter = new JTextArea();
		jtaAskLetter.setFont(font1);
		jtaAskLetter.setEditable(false);
		jtaResults = new JTextArea();
		jtaResults.setEditable(false);
		jtaTitle = new JTextArea("Anthony Galczak, Java I. Hangman Game.\nThe hidden word will be shown as *****. Enter a single letter guess and press OK.\nThere are no capitals or abbreviations.");
		jtaTitle.setEditable(false);
		
		// Swing Text Fields
		jtHiddenWord = new JTextField("Enter guess here: ");
		jtName = new JTextField();	
		
		// Swing Buttons
		okButton = new JButton("OK");
		playAgainButton = new JButton(" Press here to guess another word");
		doneButton = new JButton("Done");
		playAgainButton.setVisible(false);
		
		// Swing Labels
		lblEnterName = new JLabel("Enter your name here =========>");
		lblAlreadyGuessed = new JLabel();
		
		// Creating a new layout and assigning items to locations
		setLayout(new BorderLayout(10,20));
		add(jtaTitle, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		add(jtaResults, BorderLayout.SOUTH);
		
		// Filling my panel with buttons, labels, etc.
		p1 = new JPanel();
		p1.setLayout(new GridLayout(5,2,5,5));
		p1.add(lblEnterName);
		p1.add(jtName);
		p1.add(jtaAskLetter);
		p1.add(lblAlreadyGuessed);
		p1.add(jtHiddenWord);
		p1.add(okButton);
		p1.add(playAgainButton);
		p1.add(doneButton);
		add(p1, BorderLayout.WEST);
	}
	
	// Telling the OK button what to do on action
	public class OKButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			checkResult();
		}
	}
	
	// Telling the Done button what to do on action
	public class doneButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			hang.setName(jtName.getText());
			jtaResults.setText(hang.getGoodBye());
		}
	}
	
	// Button listener for if user wants to play again
	public class againButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			hang = new Hangman();
			showGuessingWord();
			jtaResults.setText("New Word!");
			segment = 0;
		}
	}

	// Methods
	private void showGuessingWord(){
		jtaAskLetter.setText("Guess a letter in the word:  " + hang.getGuessingWord() + "\nEnter a letter below and press OK");
		lblAlreadyGuessed.setText("Letters already guessed: \n" + hang.getMissedString() );
	}
	
	// Logic of the hangman game which references hangman's checkguess
	private void checkResult(){
		
		// Setting the users name into the hangman class
		hang.setName(jtName.getText());
		
		// Running the logic in the class to get the 1,2,3,4 return value
		int gameResult = hang.checkGuess(jtHiddenWord.getText());
		
		// If the letter has already been guessed
		if(gameResult == 1){
			jtaResults.setText("You have already guessed \"" + jtHiddenWord.getText() + "\", try again!");
		}
		// If the letter is in the word
		else if (gameResult == 2){
			jtaResults.setText("Your letter: \"" + jtHiddenWord.getText() + "\" is in the word.");
		}
		// If the letter is not in the word
		else if (gameResult == 3){
			segment++;
			canvas.repaint();
			jtaResults.setText("Your letter: \"" + jtHiddenWord.getText() + "\" is not in the word. " + hang.getAttemptsRemaining() + " attempts remaining.");
		}
		
		// Player wins or loses, display gameoverstring and turn on the play again button
		else if (gameResult == 4){
			// If the player lost, increment a segment and do another repaint
			if(hang.getMissedString().length() > 9){
				segment ++;
				canvas.repaint();
			}
			
			jtaResults.setText("" + hang.getGameOverString());
			playAgainButton.setVisible(true);
		}
		
		// Updating the guessing word text area and letters already guessed label
		showGuessingWord();
		jtHiddenWord.setText("");	
	}
	
	//Inner class for JPanel / graphics
	public class HangmanPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			// Drawing the borders for the graphics panel
			g.drawLine(0, 0, 300, 0);
			g.drawLine(0, 0, 0, 300);
			g.drawLine(300, 0, 300, 300);
			g.drawLine(300, 300, 0, 300);
			
			// Setting color of the graphics
			g.setColor(Color.blue);
			
			if(segment == 1){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
			}
			else if(segment == 2){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
			}
			else if(segment == 3){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
			}
			else if(segment == 4){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
			}
			else if(segment == 5){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
			}
			else if(segment == 6){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
				// Body - 6
				g.drawLine(200, 125, 200, 200);
			}
			else if(segment == 7){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
				// Body - 6
				g.drawLine(200, 125, 200, 200);
				// Leg 1 - 7
				g.drawLine(200, 200, 150, 250);
			}
			else if(segment == 8){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
				// Body - 6
				g.drawLine(200, 125, 200, 200);
				// Leg 1 - 7
				g.drawLine(200, 200, 150, 250);
				// Leg 2 - 8
				g.drawLine(200, 200, 250, 250);
			}
			else if(segment == 9){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
				// Body - 6
				g.drawLine(200, 125, 200, 200);
				// Leg 1 - 7
				g.drawLine(200, 200, 150, 250);
				// Leg 2 - 8
				g.drawLine(200, 200, 250, 250);
				// Arm 1 - 9
				g.drawLine(200, 150, 150, 125);
			}
			else if(segment == 10){
				// Stand/Feet - 1
				g.drawArc(10, 250, 150, 60, 0, 180);
				// Pole - 2
				g.drawLine(85, 250, 85, 20);
				// Overhang - 3
				g.drawLine(85, 20, 200, 20);
				// Rope - 4
				g.drawLine(200, 20, 200, 75);
				// Head - 5
				g.drawOval(175, 75, 50, 50);
				// Body - 6
				g.drawLine(200, 125, 200, 200);
				// Leg 1 - 7
				g.drawLine(200, 200, 150, 250);
				// Leg 2 - 8
				g.drawLine(200, 200, 250, 250);
				// Arm 1 - 9
				g.drawLine(200, 150, 150, 125);
				// Arm 2 - 10
				g.drawLine(200, 150, 250, 125);
				// Un smiley face for losing...
				g.drawOval(190, 90, 5, 5);
				g.drawOval(210, 90, 5, 5);
				g.drawArc(192, 105, 20, 20, 0, 180);
			}

		}
	}

}