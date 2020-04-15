import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();
    static OffByOne off1 = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("non"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("can"));
        assertFalse(palindrome.isPalindrome("Noon"));
    }
    @Test
    public void testOffByOne() {
        assertTrue(palindrome.isPalindrome("", off1));
        assertTrue(palindrome.isPalindrome("a", off1));
        assertTrue(palindrome.isPalindrome("ab", off1));
        assertTrue(palindrome.isPalindrome("abb", off1));
        assertTrue(palindrome.isPalindrome("abcdedcb", off1));
        assertFalse(palindrome.isPalindrome("can", off1));
        assertFalse(palindrome.isPalindrome("Abb", off1));
    }
}
