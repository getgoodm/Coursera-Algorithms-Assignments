/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholds;
    private final int trials;
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        thresholds = new double[trials];
        for (int i=0; i<trials; i++) {
            Percolation perc = new Percolation(n);
            int row, column;
            while (!perc.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                column = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(row, column))
                    perc.open(row, column);
            }
            thresholds[i] = (double) perc.numberOfOpenSites()/(n*n);
        }
    }
    public double mean() {
        return StdStats.mean(thresholds);
    }
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    public double confidenceLo() {
        return mean() - (1.96*stddev())/Math.sqrt(trials);
    }
    public double confidenceHi() {
        return mean() + (1.96*stddev())/Math.sqrt(trials);
    }
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
