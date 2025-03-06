package PriorityQueue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class PriorityQueue<E> implements AbstractQueue<E> {
    private final Comparator<E> compar; 
    private final ArrayList<E> heap;    
    private final HashSet<E> set;      

    // Constructor: Creates a new priority queue with the given comparator.
    public PriorityQueue(Comparator<E> comparator) {
        this.compar = comparator;
        this.heap = new ArrayList<>();
        this.set = new HashSet<>();
    }

    // Determines if the queue is empty.
    @Override
    public boolean empty() {
        return heap.isEmpty();
    }

    // Adds an element to the priority queue. 
    @Override
    public boolean push(E e) {
        if (e == null || set.contains(e)) {
            return false;
        }
        heap.add(e);
        set.add(e);
        heapUp(heap.size() - 1);
        return true;
    }

    // Returns the top-priority element without removing it. 
    @Override
    public E top() {
        if (empty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        return heap.get(0);
    }

    // Removes the top-priority element from the queue. 
    @Override
    public void pop() {
        if (empty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        E deleted = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        set.remove(deleted);
        heapDown(0);
    }

    // Removes a specific element from the queue. 
    @Override
    public boolean remove(E e) {
        if (!set.contains(e)) {
            return false;
        }
        int index = heap.indexOf(e);
        swap(index, heap.size() - 1);
        heap.remove(heap.size() - 1);
        set.remove(e);
        heapDown(index);
        return true;
    }

    // Checks if a given element is in the priority queue.
    @Override
    public boolean contains(E e) {
        return set.contains(e);
    }

    // Restores the heap property upwards starting from a given index.
    private void heapUp(int i) {
        while (i > 0) {
            int parent = parentIndex(i);
            if (compar.compare(heap.get(i), heap.get(parent)) < 0) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    // Restores the heap property downwards starting from a given index.
    private void heapDown(int i) {
        int smallest = i;
        int leftChild = leftChildIndex(i);
        int rightChild = rightChildIndex(i);

        if (leftChild < heap.size() && compar.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
            smallest = leftChild;
        }

        if (rightChild < heap.size() && compar.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapDown(smallest);
        }
    }

    // Swaps two elements in the heap.
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Returns the left child index of a given index.
    private int leftChildIndex(int i) {
        return 2 * i;
    }

    // Returns the right child index of a given index.
    private int rightChildIndex(int i) {
        return 2 * i + 1;
    }

    // Returns the parent index of a given index.
    private int parentIndex(int i) {
        return i / 2;
    }
}
