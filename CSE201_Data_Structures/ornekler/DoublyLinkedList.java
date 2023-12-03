
public class DoublyLinkedList<E> {
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    
    public DoublyLinkedList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    
    public int getSize() {return this.size;}
    public boolean isEmpty() {return this.size == 0;}
    
    public E getFirst() {
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }
    public E getLast() {
        if(isEmpty()) return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst() {
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast() {
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }
    
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }
    
    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        
        /**
         * @param e element
         * @param p previous node
         * @param n next node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            this.element = e;
            this.prev = p;
            this.next = n;
        }
        
        public E getElement() {return this.element;}
        public Node<E> getPrev() {return this.prev;}
        public Node<E> getNext() {return this.next;}
        public void setPrev(Node<E> p) {this.prev = p;}
        public void setNext(Node<E> n) {this.next = n;}
        
    }
}
