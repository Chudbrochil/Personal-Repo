// Anthony Galczak - agalczak@cnm.edu - Program 8
// Enigma.java

package galczakp8;

import java.util.Random;

/**
 *
 * @author Anthony
 */
public class Enigma {
    
    private String message, codedMessage;
    private int key;
    
    public Enigma(){
        message = "";
        codedMessage = "";
        key = 1;
    }
    
    public void setMessage(String m, int k){
        message = m;
        
        // Validating key is between 1-50
        if (k > 50 || k < 1){
            key = 1;
        }
        
        key = k;
        
        Encode();
    }
    
    public void setMessage(String m){
        message = m;
        
        Random rand = new Random();
        int randomNum = (rand.nextInt(50) + 1);
        
        key = randomNum;
        
        
        Encode();
    }
    
    public void setCodedMessage(String cm, int k){
        codedMessage = cm;
        key = k;
        Decode();
    }
    
    public String getCodedMessage(){
        return codedMessage;
    }
    
    public String getMessage(){
        return message;
    }
    
    public int getKey(){
        return key;
    }
    
    private void Encode(){
        StringBuilder messageArray = new StringBuilder();
        StringBuilder codedMessageArray = new StringBuilder();
        messageArray.append(message);
        
        for (int i = 0; i < messageArray.length(); ++i){
            int asciiValue = messageArray.charAt(i);
            
            int derp = messageArray.length();
            
            asciiValue += key;
            
            // Altering the key in case it goes out of bounds of 32-126
            if(asciiValue > 126){
                asciiValue -= 95;
            }
            
            codedMessageArray.insert(i, (char)asciiValue);
        }
        
        codedMessage = codedMessageArray.toString();
        
        
    }
    
    private void Decode(){
        StringBuilder messageArray = new StringBuilder();
        StringBuilder codedMessageArray = new StringBuilder();
        codedMessageArray.append(codedMessage);
        
        for (int i = 0; i < codedMessageArray.length(); ++i){
            int asciiValue = codedMessageArray.charAt(i);
            
            asciiValue -= key;
            
            // Altering the key in case it goes out of bounds of 32-126
            if (asciiValue < 32){
                asciiValue += 95;
            }
            
            messageArray.insert(i, (char)asciiValue);
            
        }
        message = messageArray.toString();
        
    }
    
    
}
