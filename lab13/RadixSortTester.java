import org.junit.Test;
import static org.junit.Assert.*;

public class RadixSortTester {
    String[] original1 = {"abc", "abd", "acd", "aad", "bac", "abc", "xyz"};
    String[] expected1 = {"aad", "abc", "abc", "abd", "acd", "bac", "xyz"};
    String[] original2 = {"abc", "abd", "ac", "bac", "abc", "candeng", "xyz"};
    String[] expected2 = {"ac", "abc", "abc", "abd", "bac", "xyz","candeng",};

    @Test
    public void test() {
        String[] sorted1 = RadixSort.sort(original1);
        String[] sorted2 = RadixSort.sort(original2);
        for (int i = 0; i < original1.length; i++) {
            assertEquals(sorted1[i], expected1[i]);
        }
        for (int i = 0; i < original2.length; i++) {
            assertEquals(sorted2[i], expected2[i]);
        }
    }
}
