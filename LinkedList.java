public class LinkedList {

    private static class Node {
        int data;
        Node next;

        public Node(int data) { this.data = data; }
    }

    private Node head;

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

}