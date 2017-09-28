public class MutableBoolean
{
    // I wanted to pass a boolean by reference, but in Java that isn't a thing without the apache libraries...
    // So I made my own

    private boolean flag;

    /**
     * MutableBoolean()
     * This is MutableBoolean's constructor.
     * @param flag
     */
    public MutableBoolean(boolean flag) { this.flag = flag; }

    /**
     * get()
     * @return Boolean inside this data structure
     */
    public boolean get() { return flag; }

    /**
     * set()
     * @param flag Sets boolean inside this data structure
     */
    public void set(boolean flag) { this.flag = flag; }
}
