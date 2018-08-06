package algorithms;

public class BinarySearchTree {

    class Node {
        int key;
        String value;
        Node left, right;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node min() {
            if (left == null) {
                return this;
            } else {
                return left.min();
            }
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Find
    public String find(int key) {

        // First find the node
        Node node = find(root, key);

        // Then return the value
        return node == null ? null : node.value;
    }

    private Node find(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        } else if (key < node.key) {
            return find(node.left, key);
        } else if (key > node.key) {
            return find(node.right, key);
        }
        return null; // Recursion

        // Node: We don't compare key == root.key because duplicate keys are not allowed
    }

    // Insert
    public void insert(int key, String value) {
        root = insertItem(root, key, value);
    }

    public Node insertItem(Node node, int key, String value) {

        // Create a new node
        Node newNode = new Node(key, value);

        // If null
        // Set it here. We are done.
        if (node == null) {
            node = newNode;
            return node;
        }

        // Else
        // Walk until you find a node
        // that is null and set it there
        if (key < node.key) {
            node.left = insertItem(node.left, key, value);
        } else {
            node.right = insertItem(node.right, key, value);
        }

        // We have returned from the bottom.
        // Return our fully constructed tree. We are done!
        return node;
    }

    public Node findMin(Node node) {
        return node.min();
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node delete(Node node, int key) {
        if (node == null) {
            return null;
        } else if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else { // Woohoo! Found you. This is the node we want to delete.

            // Case 1: No child
            if (node.left == null && node.right == null) {
                node = null;
            }

            // Case 2: One child
            else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            }

            // Case 3: Two children
            else {
                // Find the minimum node on the right (could also max the left...)
                Node minRight = findMin(node.right);

                // Duplicate it by copying its values here
                node.key = minRight.key;
                node.value = minRight.value;

                // Now go ahead and delete the node we just duplicated (same key)
                node.right = delete(node.right, node.key);
            }
        }
        return node;
    }

    // Print
    public void printInOrderTraversal() { inOrderTraversal(root); }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.key);
            inOrderTraversal(node.right);
        }
    }

    public void printPreOrderTraversal() { PreOrderTraversal(root); }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void printPostOrderTraversal() { postOrderTraversal(root); }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.key);
        }
    }
}

