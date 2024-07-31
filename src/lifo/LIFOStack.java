package lifo;

public interface LIFOStack {
    /** Adds the given item to the top of the stack and returns the modified Stack. */
    public void push(char item);
    /**
     * Returns the top item from the stack without popping it.
     * @exception java.lang.IllegalStateException if the stack is empty.
     */
    public char top();
    /**
     * Removes the top item from the stack and returns the modified Stack.
     * @exception java.lang.IllegalStateException if the stack is empty.
     */
    public void pop();
    /** Returns whether the stack is empty or not. */
    public boolean isEmpty();

    // von Ahmad
    public boolean isFull();

}
