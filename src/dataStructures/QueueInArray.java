package dataStructures;

import dataStructures.exceptions.EmptyQueueException;
import dataStructures.exceptions.FullQueueException;

class QueueInArray<E> implements Queue<E> {
    // Default capacity of the stack.
    static final int DEFAULTCAPACITY = 1000;
    private final List<E> elements;
    private final int capacity;

    public QueueInArray( ){
        elements = new ArrayClass<>(DEFAULTCAPACITY);
        capacity=DEFAULTCAPACITY;
    }

    public QueueInArray(int capacity){
        elements = new ArrayClass<>(capacity);
        this.capacity=capacity;
    }

    /**
     * Returns true iff the queue contains no elements.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Inserts the specified element at the rear of the queue.
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        elements.addLast(element);
    }

    /**
     * Returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E peek() {
        return elements.getFirst();
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E dequeue() {
        return elements.removeFirst();
    }

    //TODO: Left as an exercise.
}
