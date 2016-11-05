/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author tswinson
 */


public class FXMLDocumentController implements Initializable {
    
    
    private Card p1Cards[] = new Card[3];
    private Card p2Cards[] = new Card[3];
    private CardDeck deck = new  CardDeck();
    private int hands = 0;
    private String p1Name = "";
    private String p2Name = "";
    private ThreeCardBrag brag = new ThreeCardBrag();
    String p1CardName[] = new String[3];
    String p2CardName[] = new String[3];
    
    @FXML private ImageView titleImg;
    @FXML private ImageView p1Card1;
    @FXML private ImageView p2Card1;
    @FXML private ImageView p1Card2;
    @FXML private ImageView p2Card2;
    @FXML private ImageView p2Card3;
    @FXML private ImageView p1Card3;
    @FXML private Button dealBttn;
    @FXML private Button winnerBttn;
    @FXML private Button closeBttn;
    @FXML private Label rulesLbl;
    @FXML private TextField player1Txt;
    @FXML private TextField player2Txt;

    
    
   
    Image title = new Image(this.getClass().getResource("/Images/brag.png").toExternalForm());
    Image placeHolder = new Image(this.getClass().getResource("/Images/b2fv.png").toExternalForm());
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        rulesLbl.setText(brag.getRules());
        
        //Initialize images
        titleImg.setImage(title);
        p1Card1.setImage(placeHolder);
        p1Card2.setImage(placeHolder);
        p1Card3.setImage(placeHolder);
        p2Card1.setImage(placeHolder);
        p2Card2.setImage(placeHolder);
        p2Card3.setImage(placeHolder);
        
    }
    
    @FXML
    private void handleDealBttnAction(ActionEvent event) {
        if(hands < 8)
        {
            //Save player name
            if(hands < 1)
            {
                p1Name = player1Txt.getText();
                p2Name = player2Txt.getText();
            }
            
            dealTheCards();
            ++hands;
                
            //Set player name back
            player1Txt.setFont(Font.font("System", 12));
            player1Txt.setText(p1Name);
            player2Txt.setFont(Font.font("System", 12));
            player2Txt.setText(p2Name);
        }
        
    }
    
    @FXML
    private void handleWinnerBttnAction(ActionEvent event) {
        //Set the players hand into the brag method
        brag.setPlayer1Hand(p1Cards);
        brag.setPlayer2Hand(p2Cards);
        int winner = brag.getWinningHand();
        
        switch(winner)
        {
            //No winner
            case 0:
            {
                player1Txt.setFont(Font.font("Arial", 20));
                player2Txt.setFont(Font.font("Arial", 20));
                
                player1Txt.setText("No Winner");
                player2Txt.setText("No Winner");
                break;
            }
            //Player 1 wins
            case 1:
            {
                player1Txt.setFont(Font.font("Arial", 20));
                player1Txt.setText("Winner");
                break;
            }
            //Player 2 wins
            case 2:
            {
                player2Txt.setFont(Font.font("Arial", 20));
                player2Txt.setText("Winner");
                break;
            }
            //Tie
            case 3:
            {
                player1Txt.setFont(Font.font("Arial", 20));
                player2Txt.setFont(Font.font("Arial", 20));
                
                player1Txt.setText("Tie");
                player2Txt.setText("Tie");
                break;
            }
        }
        
    }
    
    private void dealTheCards()
    {
        String p1String;
        String p2String;
        
        for(int i = 0; i < 3; ++i)
        {
            //Get the hands
            p1Cards[i] = deck.getNextCard();
            p2Cards[i] = deck.getNextCard();
            
            p1CardName[i] = "/" + p1Cards[i].getImageName();
            p2CardName[i] = "/" + p2Cards[i].getImageName();
        }
        //Set the card images to the players hand
        p1Card1.setImage(new Image(this.getClass().getResource(p1CardName[0]).toExternalForm()));
        p1Card2.setImage(new Image(this.getClass().getResource(p1CardName[1]).toExternalForm()));
        p1Card3.setImage(new Image(this.getClass().getResource(p1CardName[2]).toExternalForm()));
        p2Card1.setImage(new Image(this.getClass().getResource(p2CardName[0]).toExternalForm()));
        p2Card2.setImage(new Image(this.getClass().getResource(p2CardName[1]).toExternalForm()));
        p2Card3.setImage(new Image(this.getClass().getResource(p2CardName[2]).toExternalForm()));
    }
    
    @FXML
    private void closeBttnAction(ActionEvent event)
    {
        Platform.exit();
    }
      
    
}
