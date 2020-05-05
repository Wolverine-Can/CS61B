import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class BubbleGrid {
    private int[][] grid;
    private int N, M;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        M = this.grid.length;
        N = this.grid[0].length;
    }
    private int corToN(int[] xy) {
        return xy[0] * N + xy[1];
    }

    public int[] gridToN() {
        int[] gridN = new int[N * M];
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                gridN[m * N + n] = grid[m][n];
            }
        }
        return gridN;
    }


    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    private boolean isAdjacent(int a, int b){
        if (Math.abs(a - b) == 1 && a / N == b /N && a >= 0 && a < (N * M) && b >= 0 && b < (N * M)) {
            return true;
        }
        if (Math.abs(a - b) == N && a >= 0 && a < (N * M) && b >= 0 && b < (N * M)) {
            return true;
        }
        return false;
    }

    private void unionAdjacent(WeightedQuickUnionUF union, int i, int[] gridN) {
        if (isAdjacent(i, i + 1) && gridN[i + 1] == 1 && !union.connected(i + 1, gridN.length)) {
            union.union(i + 1, gridN.length);
            unionAdjacent(union, i + 1, gridN);
        }
        if (isAdjacent(i, i - 1) && gridN[i - 1] == 1 && !union.connected(i - 1, gridN.length)) {
            union.union(i - 1, gridN.length);
            unionAdjacent(union, i - 1, gridN);
        }
        if (isAdjacent(i, i + N) && gridN[i + N] == 1 && !union.connected(i + N, gridN.length)) {
            union.union(i + N, gridN.length);
            unionAdjacent(union, i + N, gridN);
        }
        if (isAdjacent(i, i - N) && gridN[i - N] == 1 && !union.connected(i - N, gridN.length)) {
            union.union(i - N, gridN.length);
            unionAdjacent(union, i - N, gridN);
        }
    }


    private WeightedQuickUnionUF getUnion(int[] gridN) {
        WeightedQuickUnionUF union = new WeightedQuickUnionUF(gridN.length + 1);
        for (int n = 0; n < N; n++) {
            if (gridN[n] == 1) {
                union.union(n, gridN.length);
                unionAdjacent(union, n, gridN);
            }
        }
        return union;
    }

    public int[] popBubbles(int[][] darts) {
        int[] gridN = gridToN();
        int[] popN = new int[darts.length];
        WeightedQuickUnionUF gridUnionStart;
        WeightedQuickUnionUF gridUnionEnd;
        for (int i = 0; i < darts.length; i++) {
            gridUnionStart = getUnion(gridN);
            if (gridN[corToN(darts[i])] == 0) {
                popN[i] = 0;
            } else {
                gridN[corToN(darts[i])] = 0;
                gridUnionEnd = getUnion(gridN);
                popN[i] = gridUnionEnd.count() - gridUnionStart.count() - 1;
                for (int j = 0; j < M * N; j++) {
                    gridN[j] = (gridUnionEnd.connected(gridN.length, j)? 1: 0);
                }
            }
        }
        return popN;
    }
}
