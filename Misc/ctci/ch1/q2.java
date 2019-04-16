import org.junit.Assert;

public class q2 {
    public static void main(String[] args)
    {
        Assert.assertEquals("gnirts A", reverseIterative("A string"));
        Assert.assertEquals("gnirts A", reverseRecursive("A string"));
    }


    public static String reverseIterative(String s)
    {
        StringBuilder newString = new StringBuilder();

        for(int i = s.length() - 1; i >= 0; --i)
        {
            newString.append(s.charAt(i));
        }

        return newString.toString();
    }

    public static String reverseRecursive(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }
        return reverseRecursive(s.substring(1)) + s.charAt(0);
    }
}
