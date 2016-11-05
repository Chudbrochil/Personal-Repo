// Anthony Galczak - agalczak@cnm.edu - Program 7
// FXMLDocumentController.java
package galczakp7;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Anthony
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField jtfName;
    @FXML private TextField jtfAccountNum;
    @FXML private Button jbtSetInfo;
    @FXML private Button jbtDeposit;
    @FXML private Button jbtWithdrawal;
    @FXML private Button jbtBalance;
    @FXML private TextField jtfAmount;
    @FXML private TextField jtfLockKey;
    @FXML private Button jbtSetKey;
    @FXML private Button jbtUnlock;
    @FXML private Button jbtLock;
    @FXML private TextArea jtaResults;
    @FXML private Label lblLockStatus;
    
    BankAccount acct = new BankAccount();
    
    DecimalFormat dec = new DecimalFormat("$#.00 USD");
    
    String maskedAcctNum = "";
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Initializing the lock/unlock label
        boolean isLocked = acct.getIsLocked();
        
        if(isLocked){
            lblLockStatus.setText("Locked");
        }
        else{
            lblLockStatus.setText("Unlocked");
        }
    }
    
    @FXML
    private void setInfoClick(ActionEvent event) {
        String name = "";
        int acctNum = 0;

        try{
            // Getting the name and the account number from the form
            name = jtfName.getText();
            acctNum = Integer.parseInt(jtfAccountNum.getText());
            
            // Setting the name and account number into the class
            acct.setAccountNumber(acctNum);
            acct.setAccountName(name);
        
            // Telling user that their data was saved
            jtaResults.setText("Personal info set!");
        
            // Masking the account number for future use
            maskedAcctNum = maskNum(acct.getAccountNumber());
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        

    }
    
    @FXML
    private void balanceClick(ActionEvent event) {
        
        // Displaying balance information to user in results window
        if(acct.getIsLocked() == false){
            jtaResults.setText("Hello, " + acct.getAccountName() +
                "\nYour account number is: " + maskedAcctNum + 
                "\nYour balance is: " + dec.format(acct.getBalance()));  
        }
        else{
            jtaResults.setText("Account is locked!");
        }
  
    }
    
    @FXML
    private void withdrawalClick(ActionEvent event) {
        
        try{
            // Converting the withdrawal amount to a string and setting it into the account
            Double withdrawalAmount = Double.parseDouble(jtfAmount.getText());
            acct.setWithdrawal(withdrawalAmount);
            
            // Outputting successful withdrawal to user
            if(acct.getIsLocked() == false){
                jtaResults.setText("You have withdrawn " + dec.format(withdrawalAmount) +
                " from account: " + maskedAcctNum + "\nYour remaining balance is: " + dec.format(acct.getBalance()));
                jtfAmount.clear();
            }
            else{
                jtaResults.setText("Account is locked!");
            }
            
        }
        // Exception handling
        catch(OverdraftException oe){
            jtaResults.setText("Error message: " + oe.getMessage() + 
                    "\nYour attempted withdrawal amount: " + dec.format(jtfAmount.getText()));
        }
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
        
        
    }
    
    @FXML
    private void depositClick(ActionEvent event) {
        
        try{
            // Converting the deposit amount to a string and setting it into the account
            Double depositAmount = Double.parseDouble(jtfAmount.getText());
            acct.setDeposit(depositAmount);
            
            // Outputting successful withdrawal to user
            if(acct.getIsLocked() == false){
                jtaResults.setText("You have deposited " + dec.format(depositAmount) +
                " into account: " + maskedAcctNum + "\nYour balance is: " + dec.format(acct.getBalance()));
                jtfAmount.clear();
            }
            else{
                jtaResults.setText("Account is locked!");
            }
        }
        // Exception handling
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
    
    }
    
    @FXML
    private void setKeyClick(ActionEvent event) {
        try{
            // Capturing the key from the text field and setting it into the account class
            int key = Integer.parseInt(jtfLockKey.getText());
            acct.setAccountKey(key);
        }
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
        finally{
            jtfLockKey.clear();
        }
    }
    
    @FXML
    private void setLockClick(ActionEvent event) {
        int key = 0;
        
        try{
            // Capturing the key from the text field and setting it into the account class
            key = Integer.parseInt(jtfLockKey.getText());   
        }
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
        
        // Trying to lock the account
        try{
            acct.lock(key);
            lblLockStatus.setText("Locked");
        }
        catch(WrongKeyException wke){
            jtaResults.setText("Error Message: " + wke.getMessage());
        }
        finally{
            jtfLockKey.clear();
        }
    }
    
    @FXML
    private void setUnlockClick(ActionEvent event) {
        int key = 0;
        
        try{
            // Capturing the key from the text field and setting it into the account class
            key = Integer.parseInt(jtfLockKey.getText());   
        }
        catch(NumberFormatException nfe){
            jtaResults.setText("Error Message: " + nfe.getMessage() + "\nThat isn't a number!");
        }
        catch(NullPointerException npe){
            jtaResults.setText("Error Message: " + npe.getMessage() + "\nEnter a value!");
        }
        
        // Trying to unlock the account
        try{
            acct.unlock(key);
            lblLockStatus.setText("Unlocked");
        }
        catch(WrongKeyException wke){
            jtaResults.setText("Error Message: " + wke.getMessage());
        }
        finally{
            jtfLockKey.clear();
        }
    }
    
    
    // Masking the account number for customer safety
    private String maskNum(int n){
        StringBuilder acctString = new StringBuilder();
        acctString.append(Integer.toString(n));
        
        for(int i = 0; i < (acctString.length() - 4); ++i){
            acctString.setCharAt(i, '*');
        }
         
        return acctString.toString();
    }
    
}
