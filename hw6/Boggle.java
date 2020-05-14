import edu.princeton.cs.algs4.MaxPQ;
import java.io.File;
import java.util.*;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
    private static char[][]charBoard;
    private static Boolean[][] marked;
    private static MyTrieSet dict = new MyTrieSet();
    private static MaxPQ<String> strings;

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        if (!new File(dictPath).exists()) {
            throw new IllegalArgumentException("dictionary does not exist");
        }

        In dictionary = new In(dictPath);
        for (String s : dictionary.readAllStrings()) {
            dict.add(s);
        }

        Comparator<String> compareString = (i, j) -> {
            if (i.length() != j.length()) {
                return i.length() - j.length();
            } else {
                return -i.compareTo(j);
            }
        };
        strings = new MaxPQ<>(compareString);
        List<String> result = new LinkedList<>();


        String[] board = new In(boardFilePath).readAllStrings();

        int m = board.length;
        int n = board[0].length();
        for (String s : board) {
            if (s.length() != n) {
                throw new IllegalArgumentException("board is not rectangle");
            }
        }

        charBoard = new char[m][n];
        marked = new Boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                charBoard[i][j] = board[i].charAt(j);
                marked[i][j] = false;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                findString("", marked, i, j);
            }
        }

        String topStr;
        String preStr = "";
        for (int i = 0; i < k;) {
            if (strings.isEmpty()) {
                break;
            }
            topStr = strings.delMax();
            if (!topStr.equals(preStr)) {
                result.add(topStr);
                preStr = topStr;
                i += 1;
            }
        }

        return result;
    }
    private static void findString(String prev, Boolean[][] nowMarked, int x, int y) {
        Boolean[][] currentMarked = nowMarked.clone();
        for (int i = 0; i < nowMarked.length; i++) {
            currentMarked[i] = nowMarked[i].clone();
        }
        String currentString = prev + charBoard[x][y];
        currentMarked[x][y] = true;
        if (dict.contains(currentString) && currentString.length() >= 3) {
            strings.insert(currentString);
        }
        if (!dict.hasPrefix(currentString)
                || unmarkedNeighbor(x, y, currentMarked).isEmpty()) {
            return;
        }
        for (int[] next: unmarkedNeighbor(x, y, currentMarked)) {
            findString(currentString, currentMarked, next[0], next[1]);
        }
    }


    private static Set<int[]> unmarkedNeighbor(int x, int y, Boolean[][] newMarked) {
        Set<int[]> unmarkedNeighbors = new HashSet<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inBound(i, j) && !newMarked[i][j]) {
                    unmarkedNeighbors.add(new int[]{i, j});
                }
            }
        }
        return unmarkedNeighbors;
    }

    private static Boolean inBound(int x, int y) {
        return x >= 0 && y >= 0 && x < charBoard.length && y < charBoard[0].length;
    }

}
