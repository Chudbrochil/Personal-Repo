// Anthony Galczak - agalczak@cnm.edu - Program 7
// OverdraftException.java
package galczakp7;

/**
 *
 * @author Anthony
 */
public class OverdraftException extends Exception {
    public OverdraftException(){
	super("You are overdrawing your account!");
    }
}
