public class Lab02 {
    public static void main(String[] args) throws Exception {
        System.out.println(isPalindrome("ababa"));
    }

    public static boolean isPalindrome(String value) {
        LinkedList myList = new LinkedList();
        for (char c : value.toCharArray()) {
            myList.insert(new Node(c));
        }

        Node left = myList.getHead();
        Node right = myList.getTail();
        while (left != right) {
            if (left.getValue() != right.getValue())
                return false;

            left = left.getNextNode();
            right = right.getPrevNode();
        }
        return true;
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.setNextNode(newNode);
        newNode.setPrevNode(tail);
        tail = newNode;

    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}

class Node {
    private char value;
    private Node prevNode;
    private Node nextNode;

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
