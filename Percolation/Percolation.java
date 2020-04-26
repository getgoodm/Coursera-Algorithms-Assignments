/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF uf;
    private boolean[] states;
    private int openSites;
    public Percolation(int n) {
        if (n<=0) throw new IllegalArgumentException();
        this.n=n;
        uf = new WeightedQuickUnionUF(n*n+2);
        states = new boolean[n*n+2];
        for (int i=0; i<n; i++) {
            uf.union(0, i+1);
            uf.union(n*n+1, n*n-i);
        }
        states[0] = true;
        states[n*n+1] = true;
    }
    private int xyTo1D(int row, int column) {
        return this.n*(row-1)+column;
    }
    private void validate(int row, int column) {
        if (row<1 || column<1 || row>n || column>n)
            throw new IllegalArgumentException("Array indices are out of bounds");
    }
    public void open(int row, int col) {
        validate(row, col);
        int pos = xyTo1D(row, col);
        states[pos] = true;
        if (col-1>0 && states[pos-1])
            uf.union(pos, pos-1);
        if (col+1<=n && states[pos+1])
            uf.union(pos, pos+1);
        if (row-1>0 && states[pos-n])
            uf.union(pos, pos-n);
        if (row+1<=n && states[pos+n])
            uf.union(pos, pos+n);
        openSites++;
    }
    public boolean isOpen(int row, int col) {
        validate(row, col);
        // System.out.println("row " + row + " col " + col + " pos " + xyTo1D(row, col) + " res " + states[xyTo1D(row, col)]);
        return states[xyTo1D(row, col)];
    }
    public boolean isFull(int row, int col) {
        validate(row, col);
        int pos = xyTo1D(row, col);
        return states[pos] && uf.connected(pos, 0);
    }
    public int numberOfOpenSites() {
        return openSites;
    }
    public boolean percolates() {
        return openSites>0 && uf.connected(0, n*n+1);
    }
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.open(1, 2);
        // System.out.println(Arrays.toString(perc.states));
    }
}
