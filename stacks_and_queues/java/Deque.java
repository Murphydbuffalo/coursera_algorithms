import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Must implement via linked lists to meet performance requirement, which is that
// each operation should take constant time *in the worst case* (so no expensive
// array resize operations).
public class Deque<Item> implements Iterable<Item> {
  private Node head;
  private Node tail;
  private int numberOfNodes = 0;
  private class Node {
    private Node next;
    private Node prev;
    private Item value;
  }

  public Deque() {

  }

  public boolean isEmpty() {
    return numberOfNodes == 0;
  }

  public int size() {
    return numberOfNodes;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException("Can't add a null item to the deque.");
    }

    Node oldHead = head;
    head = new Node();
    head.next = oldHead;
    head.value = item;

    if (oldHead != null) {
      oldHead.prev = head;
    }

    if (tail == null) {
      tail = head;
    }

    numberOfNodes++;
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException("Can't add a null item to the deque.");
    }

    Node oldTail = tail;
    tail = new Node();
    tail.value = item;
    tail.prev = oldTail;

    if (oldTail != null) {
      oldTail.next = tail;
    }

    if (head == null) {
      head = tail;
    }

    numberOfNodes++;
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty.");
    }

    Item value = head.value;
    head = head.next;
    numberOfNodes--;

    return value;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty.");
    }

    Item value = tail.value;
    tail = tail.prev;

    if (tail != null) {
      tail.next = null;
    }

    numberOfNodes--;

    return value;
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = head;
    public void remove() {
      throw new UnsupportedOperationException("remove() is not supported.");
    }

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements to iterate over.");
      }

      Item value = current.value;
      current = current.next;

      return value;
    }
  }

  public static void main(String[] args) {
    Deque<String> deque = new Deque<String>();

    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();

      if (s.equals("-first")) {
        StdOut.println(String.format("Removing %s from the front of the deque", deque.removeFirst()));
      } else if (s.equals("-last")) {
        StdOut.println(String.format("Removing %s from the back of the deque", deque.removeLast()));
      } else if (StdRandom.uniform(2) == 1) {
        StdOut.println(String.format("Adding %s to the front of the deque", s));
        deque.addFirst(s);
      } else {
        StdOut.println(String.format("Adding %s to the back of the deque", s));
        deque.addLast(s);
      }
    }
  }
}
