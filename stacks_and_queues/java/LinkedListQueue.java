import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListQueue {
  private Node head = null;
  private Node tail = null;
  private int numberOfNodes;

  private class Node {
    private Node next;
    private Node prev;
    private String value;
  }

  public LinkedListQueue() {}

  public Node enqueue(String value) {
    numberOfNodes++;

    Node oldTail = tail;
    tail = new Node();
    tail.value = value;
    tail.prev = oldTail;

    if (head == null) {
      head = tail;
    } else {
      oldTail.next = tail;
    }

    return tail;
  }

  public String dequeue() {
    if (head == null) {
      return null;
    }

    numberOfNodes--;

    String value = head.value;
    head = head.next;

    return value;
  }

  public boolean empty() {
    return head == null;
  }

  public int size() {
    return numberOfNodes;
  }

  public static void main(String[] args) {
    LinkedListQueue queue = new LinkedListQueue();

    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.println(String.format("Dequeuing %s", queue.dequeue()));
      } else {
        StdOut.println(String.format("Enqueuing %s", s));
        queue.enqueue(s);
      }
    }
  }
}
