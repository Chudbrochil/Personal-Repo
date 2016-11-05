/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Henry King
//hking5@cnm.edu
//ThreeCardBrag.java

public class ThreeCardBrag{

	private Card p1Cards[] = new Card[3];
	private Card p2Cards[] = new Card[3];
	private int result;
	private String rules;
	
	public ThreeCardBrag(){
	
		result = 0;
		rules = "3 cards are dealt to each player." 
		+ "\nA player who holds any of the following wins:"
		+ "\n\r1st : Three 3's or an Ace"
		+ "\n2nd: 2 or 3 of the same card or suit"
		+ "\n3rd : King, Queen or Jack";
	}
	//FXML will call getRules and assign to txtBox or txtField on form where rules go
	public String getRules(){
		
		return rules;
	}
	
	public void setPlayer1Hand(Card p1Hand[]){
		
		p1Cards = p1Hand;
	}
	
	public void setPlayer2Hand(Card p2Hand[]){
		
		p2Cards = p2Hand;
	}

	//card numbers are the following: 0-12 = A-K SPADES, 13-25 = A-K HEARTS, 26-38 = A-K DIAMONDS, 39-51 = A-K CLUBS
	//method will return result...0 = no winner, 1 = Player1 winner, 2 = Player2 winner, 3 = tie
	public int getWinningHand(){
		
		int p1WinLevel = 0;
		int p2WinLevel = 0;
	
		//for(int i=0; i<3; ++i)
		//{
			//three 3's
			if(p1Cards[0].getRank() == p1Cards[1].getRank() && p1Cards[1].getRank() == p1Cards[2].getRank() && p1Cards[0].getRank() == 3)
                        {
                            p1WinLevel = 3;
                        }
			//an Ace
			else if(p1Cards[0].getRank() == 1 || p1Cards[1].getRank() == 1 || p1Cards[2].getRank() == 1)
			{
                            p1WinLevel = 3;
			}
                        //2 or 3 of any combinations
			else if((p1Cards[0].getSuit() == p1Cards[1].getSuit()) || (p1Cards[0].getSuit() == p1Cards[2].getSuit()) || (p1Cards[1].getSuit() == p1Cards[2].getSuit()) || (p1Cards[0].getRank() == p1Cards[1].getRank()) || (p1Cards[0].getRank() == p1Cards[2].getRank()) || (p1Cards[1].getRank() == p1Cards[2].getRank()) )
			{
                            p1WinLevel = 2;
                        }
                        else if((p1Cards[0].getRank() > 10) || (p1Cards[1].getRank() > 10) || (p1Cards[2].getRank() > 10 ))
			{
                            p1WinLevel = 1;
			}
                        else{
                            p1WinLevel = 0;
                        }
                        
                        
                        
                        
                        
			if(p2Cards[0].getRank() == p2Cards[1].getRank() && p2Cards[1].getRank() == p2Cards[2].getRank() && p2Cards[0].getRank() == 3)
			{
                            p2WinLevel = 3;
			}
			else if(p2Cards[0].getRank() == 1 || p2Cards[1].getRank() == 1 || p2Cards[2].getRank() == 1)
			{
                            p2WinLevel = 3;
			}
                        else if((p2Cards[0].getSuit() == p2Cards[1].getSuit()) || (p2Cards[0].getSuit() == p2Cards[2].getSuit()) || (p2Cards[1].getSuit() == p2Cards[2].getSuit()) || (p2Cards[0].getRank() == p2Cards[1].getRank()) || (p2Cards[0].getRank() == p2Cards[2].getRank()) || (p2Cards[1].getRank() == p2Cards[2].getRank()) )
                        {
                            p2WinLevel = 2;
			}
                        else if((p2Cards[0].getRank() > 10) || (p2Cards[1].getRank() > 10) || (p2Cards[2].getRank() > 10 ))
			{
                            p2WinLevel = 1;
			}
                        else{
                            p2WinLevel = 0;
                        }




		//}
		//now, compare win levels
		//if noone wins
		if(p1WinLevel == p2WinLevel && p1WinLevel == 0)
		{
			result = 0;
		}
		//if Player1 wins
		else if(p1WinLevel > p2WinLevel)
		{
			result = 1;
		}
		//Player2 wins
		else if(p2WinLevel > p1WinLevel)
		{
			result = 2;
		}
		//tie
		else
		{
			result = 3;
		}
		
		return result;
	}	
}
