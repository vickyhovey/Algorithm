public boolean isEmpty() { return head == null; }

public int peek() { return head.data; }

public void push(int data) {
    // Create new node
    // Set it's next to be head
    // Set head to be the new node

    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;

}

public int pop() {
    // Store the value you want to return
    // Set the current head.next to be the new head
    // return the value

    if (head == null) throw new EmptyStackException();

    int data = head.data;
    head = head.next;
    return data;
}
