package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private boolean [] siteStatus;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sitesWithoutBot;
    private int gridNumber;
    private int virtualTop;
    private int virtualBot;
    private int numberOfOpenSites = 0;
    public Percolation(int N) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException("N must be positive");
        }
        gridNumber = N;
        sites = new WeightedQuickUnionUF(N * N + 2);
        sitesWithoutBot = new WeightedQuickUnionUF(N * N + 1);
        siteStatus = new boolean[gridNumber * gridNumber];
        Arrays.fill(siteStatus, false);
        virtualTop = gridNumber * gridNumber;
        virtualBot = gridNumber * gridNumber + 1;
        for (int i = 0; i < N; i++) {
            sites.union(i, virtualTop);
            sitesWithoutBot.union(i, virtualTop);
            sites.union(gridNumber * gridNumber - 1 - i, virtualBot);
        }
    }
    private int sitesToN(int row, int col) {
        return gridNumber * row + col;
    }
    private boolean outOfBounds(int row, int col) {
        return row >= gridNumber || col >= gridNumber || row < 0 || col < 0;
    }

    public void open(int row, int col) {
        if (outOfBounds(row, col)) {
            throw new IllegalArgumentException("out of bounds");
        }
        if (isOpen(row, col)) {
            return;
        }
        if (!outOfBounds(row + 1, col) && isOpen(row + 1, col)) {
            sites.union(sitesToN(row, col), sitesToN(row + 1, col));
            sitesWithoutBot.union(sitesToN(row, col), sitesToN(row + 1, col));
        }
        if (!outOfBounds(row - 1, col) && isOpen(row - 1, col)) {
            sites.union(sitesToN(row, col), sitesToN(row - 1, col));
            sitesWithoutBot.union(sitesToN(row, col), sitesToN(row - 1, col));
        }
        if (!outOfBounds(row, col + 1) && isOpen(row, col + 1)) {
            sites.union(sitesToN(row, col), sitesToN(row, col + 1));
            sitesWithoutBot.union(sitesToN(row, col), sitesToN(row, col + 1));
        }
        if (!outOfBounds(row, col - 1) && isOpen(row, col - 1)) {
            sites.union(sitesToN(row, col), sitesToN(row, col - 1));
            sitesWithoutBot.union(sitesToN(row, col), sitesToN(row, col - 1));
        }
        siteStatus[sitesToN(row, col)] = true;
        numberOfOpenSites += 1;
    }
    public boolean isOpen(int row, int col) {
        if (outOfBounds(row, col)) {
            throw new IllegalArgumentException("out of bounds");
        }
        return siteStatus[sitesToN(row, col)];
    }
    public boolean isFull(int row, int col) {
        if (outOfBounds(row, col)) {
            throw new IllegalArgumentException("out of bounds");
        }
        return isOpen(row, col) && sitesWithoutBot.connected(sitesToN(row, col), virtualTop);
    }
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
    public boolean percolates() {
        return sites.connected(virtualTop, virtualBot);
    }
    public static void main(String[] args) {
        Percolation A = new Percolation(5);
        System.out.println(A.isOpen(0,0));
    }
}
