import edu.princeton.cs.algs4.WeightedUnionFindUF;

public class Percolation {
   private WeightedUnionFindUF UnionFind;

   // create n-by-n grid, with all sites blocked
   public Percolation(int n) {
      UnionFind = new WeightedUnionFindUF((n * n) + 2);
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   public boolean isFull(int row, int col)  // is site (row, col) full?
   public     int numberOfOpenSites()       // number of open sites
   public boolean percolates()              // does the system percolate?

   public static void main(String[] args)   // test client (optional)
}
