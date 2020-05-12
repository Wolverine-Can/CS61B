/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        int maxLength = 0;
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);
        for (String s : asciis) {
            maxLength = Math.max(maxLength, s.length());
        }
        for (int i = 0; i < maxLength; i++) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }
    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] count = new int[256];
        int[] start = new int[256];
        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            if (s.length() - index <= 0) {
                count[s.charAt(0)] += 1;
            } else {
                count[s.charAt(s.length() - 1 - index)] += 1;
            }
        }
        start[0] = count[0];
        for(int i = 1; i < count.length; i++) {
            start[i] = count[i] + start[i - 1];
        }
        for (int i = asciis.length - 1; i >= 0; i--) {
            String s = asciis[i];
            if ((s.length() - index <= 0)) {
                start[s.charAt(0)] -= 1;
                sorted[start[s.charAt(0)]] = s;
            } else {
                start[s.charAt(s.length() - 1 - index)] -= 1;
                sorted[start[s.charAt(s.length() - 1 - index)]] = s;
            }
        }
        System.arraycopy(sorted, 0, asciis, 0, sorted.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
