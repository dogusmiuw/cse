
import java.util.Arrays;

public class MinHeap 
{ 
	  private int capacity;
	  private int size = 0;
	  private int[] heap;

	  public MinHeap(int capacity) {
	    this.capacity = capacity;
	    this.heap = new int[capacity];
	  }

	  private int getLeftChildIndex(int parentIndex) {
	    return 2 * parentIndex + 1;
	  }

	  private int getRightChildIndex(int parentIndex) {
	    return 2 * parentIndex + 2;
	  }

	  private int getParentIndex(int childIndex) {
	    return (childIndex - 1) / 2;
	  }

	  private boolean hasLeftChild(int index) {
	    return getLeftChildIndex(index) < size;
	  }

	  private boolean hasRightChild(int index) {
	    return getRightChildIndex(index) < size;
	  }

	  private boolean hasParent(int index) {
	    return getParentIndex(index) >= 0;
	  }

	  private int leftChild(int parentIndex) {
	    return heap[getLeftChildIndex(parentIndex)];
	  }

	  private int rightChild(int parentIndex) {
	    return heap[getRightChildIndex(parentIndex)];
	  }

	  private int parent(int childIndex) {
	    return heap[getParentIndex(childIndex)];
	  }

	  private void swap(int index1, int index2) {
	    int element = heap[index1];
	    heap[index1] = heap[index2];
	    heap[index2] = element;
	  }

	  private void ensureCapacity() {
	    if (size == capacity) {
	      heap = Arrays.copyOf(heap, capacity * 2);
	      capacity = capacity * 2;
	    }
	  }

	  // Time Complexity : O( Log n)
	  public int poll() {
	    if (size == 0) {
	      System.exit(1);
	    }

	    int element = heap[0];

	    heap[0] = heap[size - 1];
	    size--;
	    heapifyDown();
	    return element;
	  }

	  // Time Complexity : O( Log n)
	  public void add(int item) {
	    ensureCapacity();
	    heap[size] = item;
	    size++;
	    heapifyUp();
	  }

	  private void heapifyUp() {
	    int index = size - 1;

	    while (hasParent(index) && parent(index) > heap[index]) {
	      swap(getParentIndex(index), index);
	      index = getParentIndex(index);
	    }
	  }

	  private void heapifyDown() {
	    int index = 0;

	    while (hasLeftChild(index)) {
	      int smallestChildIndex = getLeftChildIndex(index);

	      if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
	        smallestChildIndex = getRightChildIndex(index);
	      }

	      if (heap[index] < heap[smallestChildIndex]) {
	        break;
	      } else {
	        swap(index, smallestChildIndex);
	      }
	      index = smallestChildIndex;
	    }
	  }
  
	  public boolean isEmpty() {
  	return size==0;
  }
}