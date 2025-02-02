// Like mergesort, quicksort is a recursive algorithm.
// Unlike mergesort, quicksort does most of its work before recursing.
//
// The bulk of that work is *partitioning* the array into 3 parts:
// A middle partition, where all elements are equal to a randomly chosen value
// from the array.
//
// A left partition, where all elements are less than the randomly chosen partitioning
// value.
//
// And a right partition, where all element are greater than the randomly chosen
// partitioning value.
//
// Once the array is partitioned, we recurse by calling quicksort again on the left
// and right partitions.
//
// Our base case is when we encounter an array of length 1, which is by definition
// sorted.
import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class ThreePartitionQuickSort {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int[] unsorted = new int[n];
    for (int i = 0; i < n; i++) {
      unsorted[i] = StdRandom.uniform(1000);
    }

    // If you are testing the performance of this algorithm, don't include these
    // print statements. They have a significant negative impact on running time.
    System.out.println("Unsorted array is");
    System.out.println(Arrays.toString(unsorted));

    int[] sorted = sort(unsorted);
    System.out.println("Sorted array is");
    System.out.println(Arrays.toString(sorted));
  }

  public static int[] sort(int[] array) {
    sort(array, 0, array.length - 1);
    return array;
  }

  private static void sort(int[] array, int start, int end) {
    int partitionSize = end - start;
    if (partitionSize < 1) {
      return;
    }

    int[] partitionIndexes = partition(array, start, end);
    sort(array, start, partitionIndexes[0]);
    sort(array, partitionIndexes[1], end);
  }

  private static int[] partition(int[] a, int start, int end) {
    int lt = start;
    int i = start;
    int gt = end;

    while (i <= gt) {
      if (a[lt] > a[i]) {
        swap(a, i++, lt++);
    } else if (a[lt] < a[i]) {
        swap(a, i, gt--);
      } else {
        i++;
      }
    }

    lt = lt == start ? start : lt - 1;
    gt = gt == end ? end : gt + 1;

    return new int[] { lt, gt };
  };

  private static void swap(int[] array, int a, int b) {
    int aVal = array[a];
    array[a] = array[b];
    array[b] = aVal;
  }
}
