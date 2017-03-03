import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayQueue {
  private int head = 0;
  private int tail = 0;
  private String[] array;
  public ArrayQueue() {
    array = new String[1];
  }

  private String[] resize(int size) {
    String[] newArray = new String[size];

    for (int i = head; i < (tail - head); i++) {
      newArray[i] = array[i];
    }

    array = newArray;

    return array;
  }

  public String enqueue(String value) {
    if ((tail - head) == array.length - 1) {
      resize(array.length * 2);
    }

    array[tail++] = value;
    return value;
  }

  public String dequeue() {
    if ((tail - head) < (array.length - 1) / 4) {
      resize((array.length - 1) / 2);
    }

    String value = array[head];
    array[head] = null;
    head++;

    return value;
  }

  public boolean empty() {
    return head == 0 && array[0] == null;
  }

  public int size() {
    return array.length;
  }

  public static void main(String[] args) {
    ArrayQueue stack = new ArrayQueue();

    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.println(String.format("Dequeuing %s", stack.dequeue()));
      } else {
        StdOut.println(String.format("Enqueuing %s", s));
        stack.enqueue(s);
      }
    }
  }
}
