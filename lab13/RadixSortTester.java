import static org.junit.Assert.*;
import org.junit.Test;

public class RadixSortTester {
    String[] original1 = {"abc", "abd", "acd", "aad", "bac", "abc", "xyz"};
    String[] expected1 = {"aad", "abc", "abc", "abd", "acd", "bac", "xyz"};
    String[] original2 = {"abc", "abd", "ac", "bac", "abc", "xyz", "candeng"};
    String[] expected2 = {"abc", "abc", "abd", "ac", "bac", "candeng", "xyz"};
    String[] original3 = {"cat", "dog", "apple", "pig", "smart", "dumb", "candeng"};
    String[] expected3 = {"apple", "candeng", "cat", "dog", "dumb", "pig", "smart"};

    @Test
    public void test() {
        String[] sorted1 = RadixSort.sort(original1);
        String[] sorted2 = RadixSort.sort(original2);
        String[] sorted3 = RadixSort.sort(original3);
        for (int i = 0; i < original1.length; i++) {
            assertEquals(sorted1[i], expected1[i]);
        }
        for (int i = 0; i < original2.length; i++) {
            assertEquals(sorted2[i], expected2[i]);
        }
        for (int i = 0; i < original3.length; i++) {
            assertEquals(sorted3[i], expected3[i]);
        }
    }
}
