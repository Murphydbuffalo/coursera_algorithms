import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

// Must implement via linked lists to meet memory requirement, which is that each
// operation should take constant time *in the worst case* (so no expensive array
// resize operations).
public class Deque<Item> implements Iterable<Item> {
  private Node head;
  private Node tail;
  private int numberOfNodes;
  private class Node {
    private Node next;
    private Node prev;
    private Item value;
  }

  public Deque() {

  }

  public boolean isEmpty() {
    return head == null && tail == null;
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
    oldHead.prev = head;

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
    oldTail.next = tail;

    if (head == null) {
      head = tail;
    }

    numberOfNodes++;
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NullPointerException("Deque is empty.");
    }

    Item value = head.value;
    head = head.next;
    numberOfNodes--;

    return value;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NullPointerException("Deque is empty.");
    }

    Item value = tail.value;
    tail.prev.next = null;
    tail = tail.prev;
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
      Node next = current.next;

      return value;
    }
  }

  public static void main(String[] args) {

  }
}
