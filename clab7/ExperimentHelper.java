/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    private static double Log2(double logNumber) {
        return Math.log(logNumber) / Math.log(2);
    }
    public static int optimalIPL(int N) {
        int optimalIPl = 0;
        for (int i = 1; i <= N; i++) {
            optimalIPl += Log2(i);
        }
        return optimalIPl;
    }


    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        double optimalAverageDepth;
        if (N == 0) {
            throw new RuntimeException("N should be greater than 0");
        }
        optimalAverageDepth = (double) optimalIPL(N) / N;
        return optimalAverageDepth;
    }
}
