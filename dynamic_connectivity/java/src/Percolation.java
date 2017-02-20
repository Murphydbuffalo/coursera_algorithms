import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private WeightedQuickUnionUF UnionFind;
   private int n;
   private int[] open;

   // create n-by-n grid, with all sites blocked
   public Percolation(int n) {
      n = n;
      open = new int[n];
      UnionFind = new WeightedQuickUnionUF((n * n) + 2);
   }

   // Includes offset of 1 for the "virtual" site at the top of the grid.
   private int arrayIndexFromRowAndColumn(int row, int column) {
     return (n * (row - 1)) + column + 1;
   }

   private boolean checkIndexIsInBounds(int row, int col) {
     if (row < 1 || row > n || col < 1 || col > n) {
       throw new IndexOutOfBoundsException("Row and column arguments must be in bounds.");
     }
   }

   // open site (row, col) if it is not open already
   public void open(int row, int col) {
     checkIndexIsInBounds(row, col);

     int index = arrayIndexFromRowAndColumn(row, col);
     open[index] = 1;

     int indexTop = arrayIndexFromRowAndColumn(row + 1, col);
     int indexBottom = arrayIndexFromRowAndColumn(row - 1, col);
     int indexRight = arrayIndexFromRowAndColumn(row, col + 1);
     int indexLeft = arrayIndexFromRowAndColumn(row, col - 1);

     if (isOpen(row + 1, col)) {
        UnionFind.union(index, indexTop);
     }

     if (isOpen(row - 1, col)) {
        UnionFind.union(index, indexBottom);
     }

     if (isOpen(row, col + 1)) {
        UnionFind.union(index, indexRight);
     }

     if (isOpen(row, col - 1)) {
        UnionFind.union(index, indexBottom);
     }
   }

   // is site (row, col) open?
   public boolean isOpen(int row, int col) {
     checkIndexIsInBounds(row, col);
     int index = arrayIndexFromRowAndColumn(row, col);

     return open[index] == 1;
   }

   // is site (row, col) full?
   public boolean isFull(int row, int col) {
     return !isOpen(row, col);
   }

   // number of open sites
   public int numberOfOpenSites() {
     int count = 0;

     for (int i = 1; i < n + 1; i++) {

     }
   }

   // does the system percolate?
   public boolean percolates() {
     return UnionFind.connected(0, (n * n) + 2);
   }

   // test client (optional)
   public static void main(String[] args) {

   }
}
