import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int tail = 0;
  private int head = 0;
  private Item[] array;
  public RandomizedQueue() {
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
    if (value == null) {
      throw new NullPointerException("Cannot enqueue null values.");
    }

    if (tail == array.length - 1) {
      resize(array.length * 2);
    }

    array[tail] = value;
    tail++;
    return value;
  }

  // Only grab random elements between head and tail so that we don't grab a null value
  private int randomIndex() {
    return StdRandom.uniform(head, tail);
  }

  public Item dequeue() {
    if (empty()) {
      throw new NoSuchElementException("The queue is empty.");
    }

    if (size() < array.length / 4) {
      resize(array.length / 2);
    }

    int index = randomIndex();
    Item value = array[index];

    // Swap the random element and the head element so that we keep all non-null
    // values between head and tail, allowing us to properly resize the array.
    Item oldHead = array[head];
    array[index] = oldHead;
    array[head] = null;
    head++;

    return value;
  }

  public Item sample() {
    if (empty()) {
      throw new NoSuchElementException("The queue is empty.");
    }

    return array[randomIndex()];
  }

  public boolean empty() {
    return head == tail && array[head] == null;
  }

  public int size() {
    return tail - head;
  }

  public Iterator<Item> iterator() {
    // Shuffle slice of array from head to tail. Modifies in place, doesn't make
    // a copy. This is OK because we dequeue() in random order anyway, so order
    // doesn't need to be preserved for non-iterator operations.
    StdRandom.shuffle(array, head, tail);
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int index = head;
    public void remove() {
      throw new UnsupportedOperationException("remove() is not supported.");
    }

    public boolean hasNext() {
      return index <= array.length && array[index] != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements to iterate over.");
      }

      Item value = array[index];
      index++;

      return value;
    }
  }

  public static void main(String[] args) {
    RandomizedQueue q = new RandomizedQueue();

    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.println(String.format("Dequeuing %s", q.dequeue()));
      } else {
        StdOut.println(String.format("Enqueuing %s", s));
        q.enqueue(s);
      }
    }
  }
}
