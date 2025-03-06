package PriorityQueue;

import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

// Unit tests for the custom PriorityQueue class
public class PriorityQueueTest {
    private PriorityQueue<Integer> queue;

    // Test to check if the queue is empty
    @Test
    public void testEmpty() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestEmpty()");
        assertTrue(queue.empty());
    }

    // Test to check the push operation in the queue
    @Test
    public void testPushElement(){
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestPushElement()");
        queue.push(1);
        assertFalse(queue.empty());
        assertTrue(queue.contains(1));
    }

    // Test to check pushing multiple elements into the queue
    @Test
    public void testPushMultipleElements() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestPushMultipleElements()");
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        assertFalse(queue.contains(5));
        assertTrue(queue.contains(1));
    }

    // Test to check if contains() works as expected
    @Test
    public void testContainsElement(){
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestContainsElement()");
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
    }

    // Test to check the pop operation
    @Test
    public void testPopElement() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestPopElement()");
        queue.push(1);
        queue.pop();
        assertTrue(queue.empty());
    }

    // Test to check the pop operation when multiple elements are in the queue
    @Test
    public void testPopMultipleElements() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestPopMultipleElements()");
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.pop();
        assertTrue(queue.contains(4));
        assertTrue(queue.contains(3));
        assertTrue(queue.contains(2));
        assertFalse(queue.contains(1));
    }

    // Test the remove operation
    @Test
    public void testRemoveElement() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestRemoveElement()");
        queue.push(1);
        queue.push(2);
        queue.remove(2);
        assertTrue(queue.contains(1));
        assertFalse(queue.contains(2));
    }

    // Test the remove operation when multiple elements are involved
    @Test
    public void testRemoveMultipleElements() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestRemoveMultipleElements()");
        queue.push(1);
        queue.push(2);
        queue.remove(1);
        queue.remove(2);
        assertTrue(queue.empty());
    }

    // Test the combination of push, remove, and top operations
    @Test
    public void testPushRemoveTopOperations() {
        queue = new PriorityQueue<>(new IntegerComparator());
        System.out.print("\ntestPushRemoveTopOperations()");
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        queue.push(0);
        queue.remove(1);
        assertEquals(queue.top().intValue(), 0);
        queue.remove(2);
        assertEquals(queue.top().intValue(), 0);
        queue.remove(5);
        assertEquals(queue.top().intValue(), 0);
    }

    // IntegerComparator is a custom class implementing Comparator for Integer comparison
    static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }
}