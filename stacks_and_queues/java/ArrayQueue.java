import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayQueue<Item> {
  private int head = 0;
  private int tail = 0;
  private Item[] array;
  public ArrayQueue() {
    array = (Item[]) new Object[1];
  }

  private Item[] resize(int size) {
    Item[] newArray = (Item[]) new Object[size];

    int newTail = 0;

    for (int i = 0; (head + i) <= tail; i++) {
      newArray[i] = array[head + i];
      newTail = i;
    }

    array = newArray;
    head = 0;
    tail = newTail;

    return array;
  }

  public Item enqueue(Item value) {
    if (tail == array.length - 1) {
      resize(array.length * 2);
    }

    array[tail++] = value;
    return value;
  }

  public Item dequeue() {
    if (size() < array.length / 4) {
      resize(array.length / 2);
    }

    Item value = array[head];
    array[head] = null;
    head++;

    if (head == tail && array[head] == null) {
      head = 0;
      tail = 0;
    }

    return value;
  }

  public boolean empty() {
    return head == tail && array[head] == null;
  }

  public int size() {
    return tail - head;
  }

  public static void main(String[] args) {
    ArrayQueue<String> stack = new ArrayQueue<String>();

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
