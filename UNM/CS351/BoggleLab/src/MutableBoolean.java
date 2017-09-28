public class MutableBoolean
{
    // I wanted to pass a boolean by reference, but in Java that isn't a thing without the apache libraries...
    // So I made my own

    boolean flag;

    public MutableBoolean(boolean flag) { this.flag = flag; }
    public boolean get() { return flag; }
    public void set(boolean flag) { this.flag = flag; }
}
