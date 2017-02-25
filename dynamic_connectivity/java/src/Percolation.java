import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private int n;
   private int numberOfSites;
   private int[] open;
   private int numberOfOpenSites;
   private WeightedQuickUnionUF UnionFind;

   // create n-by-n grid of sites, with two additional "virtual" sites at the top
   // and bottom of the grid. Begin with all sites blocked (open[i] == 0).
   public Percolation(int gridDimension) {
      n = gridDimension;
      numberOfSites = (n * n) + 2;
      open = new int[numberOfSites];
      // Don't include the two virtual sites in this count.
      numberOfOpenSites = 0;
      UnionFind = new WeightedQuickUnionUF(numberOfSites);

      // Open the two virtual sites and connect virtual top site to all sites in
      // the top row and connect the virtual bottom site to all sites on the bottom row.
      open[0] = 1;
      open[numberOfSites - 1] = 1;

      for (int i = 1; i <= n; i++) {
        UnionFind.union(0, i);
        UnionFind.union(numberOfSites - 1, numberOfSites - 1 - i);
      }
   }

   // Includes offset of 1 for the "virtual" site at the top of the grid.
   // So this method returns values 1 to n^2, rather than 0 to n^2 - 1.
   private int arrayIndexFromRowAndColumn(int row, int column) {
     return (n * (row - 1)) + column;
   }

   private boolean indexIsInBounds(int row, int col) {
     return !(row < 1 || row > n || col < 1 || col > n);
   }

   private void checkIndexIsInBounds(int row, int col) {
     if (!indexIsInBounds(row, col)) {
       throw new IndexOutOfBoundsException("Row and column arguments must be in bounds.");
     }
   }

   // open site (row, col) if it is not open already
   public void open(int row, int col) {
     checkIndexIsInBounds(row, col);

     int index = arrayIndexFromRowAndColumn(row, col);
     open[index] = 1;
     numberOfOpenSites = numberOfOpenSites + 1;

     int indexTop = arrayIndexFromRowAndColumn(row + 1, col);
     int indexBottom = arrayIndexFromRowAndColumn(row - 1, col);
     int indexRight = arrayIndexFromRowAndColumn(row, col + 1);
     int indexLeft = arrayIndexFromRowAndColumn(row, col - 1);

     if (indexIsInBounds(row + 1, col) && isOpen(row + 1, col)) {
        UnionFind.union(index, indexTop);
     }

     if (indexIsInBounds(row - 1, col) && isOpen(row - 1, col)) {
        UnionFind.union(index, indexBottom);
     }

     if (indexIsInBounds(row, col + 1) && isOpen(row, col + 1)) {
        UnionFind.union(index, indexRight);
     }

     if (indexIsInBounds(row, col - 1) && isOpen(row, col - 1)) {
        UnionFind.union(index, indexLeft);
     }
   }

   // is site (row, col) open? Sites are either open or blocked.
   public boolean isOpen(int row, int col) {
     checkIndexIsInBounds(row, col);
     int index = arrayIndexFromRowAndColumn(row, col);

     return open[index] == 1;
   }

   // is site (row, col) full? "Full" means that the site is both open and connected
   // to the top row of sites by its connecte component.
   public boolean isFull(int row, int col) {
     int index = arrayIndexFromRowAndColumn(row, col);
     return isOpen(row, col) && UnionFind.connected(0, index);
   }

   // number of open sites
   public int numberOfOpenSites() {
     return numberOfOpenSites;
   }

   // does the system percolate?
   public boolean percolates() {
     return UnionFind.connected(0, numberOfSites - 1);
   }

   // test client (optional)
   public static void main(String[] args) {

   }
}
