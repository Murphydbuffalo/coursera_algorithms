import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
   public static void main(String[] args) {
      RandomizedQueue<String> q = new RandomizedQueue<String>();

      while (!StdIn.isEmpty()) {
        q.enqueue(StdIn.readString());
      }

      int k = Integer.parseInt(args[0]);
      Iterator<String> iterator = q.iterator();

      for (int i = 1; i < k; i++) {
        StdOut.println(iterator.next());
      }
   };
}
