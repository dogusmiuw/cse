
public class SinglyLinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    
    public int getSize() {return this.size;}
    public boolean isEmpty() {return this.size == 0;}
    
    public E getFirst() {
        if(isEmpty()) return null;
        return this.head.getElement();
    }
    
    public E getLast() {
        if(isEmpty()) return null;
        return this.tail.getElement();
    }
    
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if(size == 0)
            tail = head;
        size++;
    }
    
    public void addLast(E e) {
        // there is no next node, cuz it will be the last by itself.
        Node<E> newNode = new Node<>(e, null);
        if(isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        size++;
    }
    
    public E removeFirst() {
        if(isEmpty()) return null;
        
        E ret = head.getElement();
        head = head.getNext();
        size--;
        
        if(size == 0)
            tail = null;
        return ret;
    }

    private static class Node<E> {

        private E element;
        private Node<E> next;

        /**
         * @param e element
         * @param n next node
         */
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        public E getElement() {return this.element;}
        public Node<E> getNext() {return this.next;}
        public void setNext(Node<E> node) {this.next = node;}
    }
}
