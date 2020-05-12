import static org.junit.Assert.*;
import org.junit.Test;

public class RadixSortTester {
    String[] original1 = {"abc", "abd", "acd", "aad", "bac", "abc", "xyz"};
    String[] expected1 = {"aad", "abc", "abc", "abd", "acd", "bac", "xyz"};
    String[] original2 = {"abc", "abd", "ac", "bac", "abc", "candeng", "xyz"};
    String[] expected2 = {"ac", "abc", "abc", "abd", "bac", "xyz","candeng",};
    String[] original3 = {"39", "232", "90", "56", "104", "120", "1", "118"};
    String[] expected3 = {"1", "39", "56", "90", "104", "118", "120", "232", "118"};

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
        String[] test = new String[]{"cat", "elephant", "ball", "fuck", "apple", "giant", "dick"};
        String[] sorted = RadixSort.sort(test);
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }
}
