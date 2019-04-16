import java.util.HashSet;
import org.junit.Assert;


class q1 {
    public static void main(String[] args)
    {
        // Test cases
        Assert.assertEquals(false, isUnique("test"));
        Assert.assertEquals(false, isUnique("unique"));
        Assert.assertEquals(true, isUnique("abcdefghjkil"));
    }

    // Determines if a string has all unique characters or not.
    public static boolean isUnique(String s)
    {
        HashSet<Character> chars = new HashSet<>();

        for(int i = 0; i < s.length(); ++i)
        {
            if (chars.contains(s.charAt(i)))
            {
                return false;
            }
            else
            {
                chars.add(s.charAt(i));
            }
        }

        return true;
    }
}

