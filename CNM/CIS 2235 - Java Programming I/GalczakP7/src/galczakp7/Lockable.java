// Anthony Galczak - agalczak@cnm.edu - Program 7
// Lockable.java
package galczakp7;

/**
 *
 * @author Anthony
 */
public interface Lockable {
    public void setAccountKey(int key);
    public void lock(int key) throws WrongKeyException;
    public void unlock(int key) throws WrongKeyException;
    public boolean getIsLocked();
}
