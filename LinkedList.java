public class LinkedList {

    private static class Node {
        int data;
        Node next;

        public Node(int data) { this.data = data; }
    }

    private Node head;
    private int size;

    public void addFront(int data) {

        // Create new node
        Node newNode = new Node(data);

        // if head...
        if (head == null) {
            head = newNode;
            return;
        }

        // Set it's next to the current head
        newNode.next = head;

        // Set current head be the new head
        head = newNode;

        size++;
    }

    public int getFirst() {
        return head.data;
    }

    public int getLast() {
        if (head == null) {
            throw new IllegalStateException("Empty List!");
        }

        Node current = head;

        // while we are not at the tail
        while (current.next != null) {
            current = current.next; // O(n)
        }

        // We at the tail
        return current.data;
    }

    public void addBack(int data) {
        Node newNode = new Node(data);

        // if head... set and return
        if (head == null) {
            head = newNode;
            return;
        }

        // Start at the head
        Node current = head;

        // Walk back node = null
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        newNode.next = null;
    }

    public int size() {

        if (head == null) {
            return 0;
        }

        int count = 1;
        Node current = head;

        while (current.next != null) {
            current = current.next;
            count++;
        }

        return count;
    }

}