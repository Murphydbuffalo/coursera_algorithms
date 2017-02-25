import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  // For each trial, what percentage of sites needed to be open for percolation to occur?
  private int[] results;

  // Number of trials
  private int t;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException("Both n and trials must be greater than 0.");
    }

    t = trials;
    results = new int[t];

    for (int i = 0; i < t; i++) {
      Percolation perc = new Percolation(n);

      while(!perc.percolates()) {
        int randomRow = StdRandom.uniform(n) + 1;
        int randomColumn = StdRandom.uniform(n) + 1;

        perc.open(randomRow, randomColumn);
      }

      results[i] = perc.numberOfOpenSites();
    }
  };

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(results);
  };

  // sample standard deviation of percolation threshold
  public double stddev() {
    if (t == 1) {
      return Double.NaN;
    }

    return StdStats.stddev(results);
  };

  private double confidenceFactor() {
    return (1.96 * stddev()) / Math.sqrt(t);
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - confidenceFactor();
  };

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + confidenceFactor();
  };

  // test client (described below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int t = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(n, t);

    StdOut.printf(String.format("mean                    = %f\n", stats.mean()));
    StdOut.printf(String.format("stddev                  = %f\n", stats.stddev()));
    StdOut.printf(String.format("95 confidence interval  = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi()));
  };
}
