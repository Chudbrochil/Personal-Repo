// Anthony Galczak - agalczak@cnm.edu - Program 7
// BankAccount.java
package galczakp7;

/**
 *
 * @author Anthony
 */
public class BankAccount implements Lockable {
    
    private double balance;
    private double deposit;
    private double withdrawal;
    private String accountName;
    private int acctNumber;
    private int accountKey;
    private boolean isLocked;
    
    // Constructor with default initialization
    public BankAccount(){
        balance = 0.00;
        deposit = 0.00;
        withdrawal = 0.00;
        accountName = "Bob";
        acctNumber = 123456;
        accountKey = 42;
        isLocked = false;
    }
    
    // Setters for variables within this class
    public void setBalance(double b){
        if(!isLocked){
            balance = b; 
        }
    }
    public void setDeposit(double d){
        // If the account isn't locked, apply the deposit math to the balance
        if(!isLocked){
           deposit = d;
           balance += deposit;
        }   

    }
    public void setWithdrawal(double w) throws OverdraftException{
        // If the account isn't locked, apply the withdrawal math to the balance
        if(!isLocked){
           withdrawal = w;
           if(withdrawal <= balance){
               balance = balance - withdrawal;
           }
           else{
               throw new OverdraftException();
           }
        }
        
        
    }
    public void setAccountName(String a){
        accountName = a;
    }
    public void setAccountNumber(int n){
        acctNumber = n;
    }
    public void setAccountKey(int key){
        accountKey = key;
    }
    
    // Getters for variables within this class    
    public double getBalance(){
        return balance;
    }
    public double getDeposit(){
        return deposit;
    }
    public double getWithdrawal(){
        return withdrawal;
    }
    public String getAccountName(){
        return accountName;
    }
    public int getAccountNumber(){
        return acctNumber;
    }
    public int getAccountKey(){
        return accountKey;
    }
    public boolean getIsLocked(){
        return isLocked;
    }
    
    
    
    // Lock/Unlock methods
    public void lock(int key) throws WrongKeyException{
        // If the key brought into lock method is right, then lock the account
        if(key == accountKey){
            isLocked = true;
        }
        else{
            throw new WrongKeyException();
        }
    }
    public void unlock(int key) throws WrongKeyException{   
        // If the key brought into unlock method is right, then unlock the account
        if(key == accountKey){
            isLocked = false;
        }
        else{
            throw new WrongKeyException();
        }
    }

    
    
    
    
    
}



