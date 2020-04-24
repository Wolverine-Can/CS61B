package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double [] x;
    private double meanX;
    private double stdDev;
    private int times;
    private int siteN;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        times = T;
        siteN = N * N;
        x = new double[T];
        int n, row, col;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                n = StdRandom.uniform(siteN);
                row = n / N;
                col = n % N;
                p.open(row, col);
            }
            x[i] = (double) p.numberOfOpenSites() / siteN;
        }
        meanX = mean();
        stdDev = stddev();
    }
    public double mean() {
        meanX = StdStats.mean(x);
        return meanX;
    }
    public double stddev() {
        stdDev = StdStats.stddev(x);
        return stdDev;
    }
    public double confidenceLow() {
        return meanX + 1.96 * stdDev / Math.sqrt(times);
    }
    public double confidenceHigh() {
        return meanX + 1.96 * stdDev / Math.sqrt(times);
    }
}
