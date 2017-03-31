import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int[] unsorted = new int[n];
    for (int i = 0; i < n; i++) {
      unsorted[i] = StdRandom.uniform(1000);
    }
    // If you are testing the performance of this algorithm, don't include these
    // print statements. They have a significant negative impact on running time.
    // System.out.println("Unsorted array is");
    // System.out.println(Arrays.toString(unsorted));

    int[] sorted = sort(unsorted);
    // System.out.println("Sorted array is");
    // System.out.println(Arrays.toString(sorted));
  }

  public static int[] sort(int[] array) {
    int length = array.length;

    if (length == 1) {
      return array;
    } else {
      int[][] halves = halve(array);
      int[] sortedFirstHalf = sort(halves[0]);
      int[] sortedSecondHalf = sort(halves[1]);

      return merge(sortedFirstHalf, sortedSecondHalf);
    }
  }

  private static int[][] halve(int[] array) {
      int length = array.length;
      int endFirstHalf = (length / 2) - 1;
      int[] firstHalf = new int[endFirstHalf + 1];
      int[] secondHalf = new int[length - endFirstHalf - 1];

      for (int i = 0; i < length; i++) {
        if (i <= endFirstHalf) {
          firstHalf[i] = array[i];
        } else {
          secondHalf[i - endFirstHalf - 1] = array[i];
        }
      }

      return new int[][]{ firstHalf, secondHalf };
  }

  private static int[] merge(int[] a, int[] b) {
    int lengthA = a.length;
    int lengthB = b.length;
    int i = 0;
    int j = 0;
    int k = 0;
    int[] sorted = new int[lengthA + lengthB];
    while (k < (lengthA + lengthB)) {
      if (i >= lengthA) {
        sorted[k++] = b[j++];
      } else if (j >= lengthB) {
        sorted[k++] = a[i++];
      } else if (a[i] >= b[j]) {
        sorted[k++] = b[j++];
      } else {
        sorted[k++] = a[i++];
      }
    }

    return sorted;
  }
}
