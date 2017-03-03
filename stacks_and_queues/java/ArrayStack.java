import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayStack {
  private int head = 0;
  private String[] array;
  public ArrayStack() {
    array = new String[1];
  }

  private String[] resize(int size) {
    String[] newArray = new String[size];

    for (int i = 0; i < array.length; i++) {
      newArray[i] = array[i];
    }

    array = newArray;

    return array;
  }

  public String push(String value) {
    if (head == array.length - 1) {
      resize(array.length * 2);
    }

    array[++head] = value;
    return value;
  }

  public String pop() {
    if (head < (array.length - 1) / 4) {
      resize((array.length - 1) / 2);
    }

    String value = array[head];
    array[head] = null;
    head--;

    return value;
  }

  public boolean empty() {
    return head == 0 && array[0] == null;
  }

  public int size() {
    return array.length;
  }

  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack();

    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.println(String.format("Popping %s", stack.pop()));
      } else {
        StdOut.println(String.format("Pushing %s", s));
        stack.push(s);
      }
    }
  }
}
