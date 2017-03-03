import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListStack {
  private Node head = null;
  private int numberOfNodes;

  private class Node {
    private Node next;
    private String value;
  }

  public LinkedListStack() {}

  public Node push(String value) {
    numberOfNodes++;

    Node oldHead = head;
    head = new Node();
    head.value = value;
    head.next = oldHead;

    return head;
  }

  public String pop() {
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
    LinkedListStack stack = new LinkedListStack();

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
