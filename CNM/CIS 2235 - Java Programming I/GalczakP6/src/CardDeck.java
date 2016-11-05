/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Anthony
 */
public class CardDeck {
    private Card cardArray[] = new Card[52];
    private boolean cardDealt[] = new boolean[52];
    
    

    
    public CardDeck(){
    // Initializing the cardDealt array to false
        for(int i = 0; i < cardArray.length; ++i){
            cardDealt[i] = false;
        }
        
        // Initializing each spot of the array using the overloaded constructor
        for(int i = 0; i < cardArray.length; ++i){
            cardArray[i] = new Card(1, "Spades", "Images/1.png");
        }
        
        // Loading cards with suit, rank, image
        for(int j = 0; j < 4; ++j){
            // 4 j's represent 4 sets of suits Ace-King
            if(j == 0){
                for(int k = 0; k < 13; ++k){
                    cardArray[k].setSuit("Spades");
                    cardArray[k].setRank(k+1); // Ranks start at 1, not 0
                    String tempImageName = "Images/" + (k+1) + ".png";
                    cardArray[k].setImageName(tempImageName);
                }
            }
            else if(j == 1){
                for(int k = 0; k < 13; ++k){
                    int newK = k + 13; // New variable to exclude using a "magic number"
                    cardArray[newK].setSuit("Hearts");
                    cardArray[newK].setRank(k+1);
                    String tempImageName = "Images/" + (newK+1) + ".png";
                    cardArray[newK].setImageName(tempImageName);
                }
            }
            else if(j == 2){
                for(int k = 0; k < 13; ++k){
                    int newK = k + 26;
                    cardArray[newK].setSuit("Diamonds");
                    cardArray[newK].setRank(k+1);
                    String tempImageName = "Images/" + (newK+1) + ".png";
                    cardArray[newK].setImageName(tempImageName);
                }
            }
            else{
                for(int k = 0; k < 13; ++k){
                    int newK = k + 39;
                    cardArray[newK].setSuit("Clubs");
                    cardArray[newK].setRank(k+1);
                    String tempImageName = "Images/" + (newK+1) + ".png";
                    cardArray[newK].setImageName(tempImageName);
                }
            }
            
        }
        
    }
    
    // Method for getting a new random card from the deck
    public Card getNextCard(){
        // Generating a random index int to get a new card 
        int index = generateRandomCard();
        do{
            index = generateRandomCard();
        }while(cardDealt[index] == true);
        
        cardDealt[index] = true;
        
        // Returning the random card
        return cardArray[index];  
    }
    
    private int generateRandomCard(){
        // Generating a random number from the length of the card array
        int index = new Random().nextInt(cardArray.length);
        return index;
    }
    
    
    
    
    
    
    
}