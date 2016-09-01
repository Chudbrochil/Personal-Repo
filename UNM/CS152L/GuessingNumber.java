/**
 * @version 2016-08-31
 * @author Anthony Galczak
 * Lab 2
 */

import java.util.Scanner;

public class GuessingNumber {
    
 /* NON FANCY WAY
    public static void main(String[] args){
        Scanner scanIn = new Scanner(System.in); 
        System.out.println("What is your name?");
        String name = scanIn.nextLine();
        System.out.println(name + ", pick a number 1 to 10 please.");
        Integer guess = scanIn.nextInt();
        Integer rand = (int)((Math.random() * 10) + 1);
        System.out.println("You guessed " + guess);
        System.out.println("I was thinking of " + rand);
        if (rand == guess){
          System.out.println("Congratulations, " + name + ". You guessed my number!");
        }
        else{
          System.out.println("Sorry, " + name + ". Your guess wasn't correct.");
        }
    }
}
*/
    
    // Instantiating class for referencing methods
    public static GuessingNumber gn = new GuessingNumber();

    public static void main(String[] args) {
        // Creating scanner object for use on command line
        Scanner scanIn = new Scanner(System.in); 
        System.out.println("What is your name?");
        String name = scanIn.nextLine();

        // Asking user if they'd like to try again
        boolean goAgain = true;
        while (goAgain){
            System.out.println(name + ", pick a number 1 to 10 please.");
            Integer guess = Integer.parseInt(scanIn.nextLine()); // Dangerous conversion
            
            System.out.println(gn.compareGuess(guess, name));
            System.out.println("Would you like to play again? (y/n)");
            
            String answer = scanIn.nextLine();
                   
            if (answer.equals("y") == false){
                goAgain = false;
            }
            
        }
        System.out.println("Thanks for playing my guessing game!");

    }
    
    // Encapsulated isCorrect
    private boolean isCorrect;
    public boolean isCorrect() {
        return isCorrect;
    }
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String compareGuess(Integer guess, String name){
        String userOutput;
        Integer rand = (int)((Math.random() * 10) + 1);
        System.out.println("You guessed " + guess);
        System.out.println("I was thinking of " + rand);
        if (rand.equals(guess)){
            userOutput = "Congratulations, " + name + ". You guessed my number!";
            setCorrect(true);
        }
        else{
            userOutput = "Sorry, " + name + ". Your guess wasn't correct.";
            setCorrect(false);
        }
        
        return userOutput;
    }
    
}


