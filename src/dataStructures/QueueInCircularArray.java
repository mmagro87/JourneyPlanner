package dataStructures;

import dataStructures.exceptions.EmptyQueueException;
import dataStructures.exceptions.FullQueueException;

public class QueueInCircularArray<E> implements Queue<E>{

    // Default capacity of the queue.
    static final int DEFAULT_CAPACITY = 1000;

    // Memory of the queue: a circular array.
    private E[] array;

    // Index of the element at the front of the queue.
    private int front;

    // Number of elements in the queue.
    private int currentSize;

    @SuppressWarnings("unchecked")
    public QueueInCircularArray( int capacity ) {
        // Compiler gives a warning.
        array = (E[]) new Object[capacity];
        front = 0;
        currentSize = 0;
    }

    public QueueInCircularArray( ) {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns true iff the queue contains no elements.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Inserts the specified element at the rear of the queue.
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        if(currentSize == array.length) {
            throw new FullQueueException();
        }
        int index = (front + currentSize) % array.length;
        array[index] = element;
        currentSize++;
    }

    /**
     * Returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E peek() {
        return array[front];
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        E  oldFront = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        currentSize--;
        return oldFront;
    }

    //TODO: Left as an exercise.
}
