package fifo;

public interface FIFOQueue {
    /**
     * Adds the given item to the rear of the queue and reuturns the modified queue.
     */
    FIFOQueue enqueue(char zeichen);
    /**
     * Removes the front item from the queue and returns the modified queue..
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    FIFOQueue dequeue();
    /**
     * Returns the front item from the queue without popping it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    char front();
    /**
     * Returns whether the queue is empty or not.
     */
    boolean isEmpty();

}