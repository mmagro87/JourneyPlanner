package dataStructures;

import dataStructures.exceptions.EmptyQueueException;

public class QueueInList<E> implements Queue<E> {

    // Memory of the queue: a list.
    private final List<E> list;

    public QueueInList( ){
        list = new SinglyLinkedList<>();
    }

    /**
     * Returns true iff the queue contains no elements.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Inserts the specified element at the rear of the queue.
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        list.addLast(element);
    }

    /**
     * Returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E peek() {
        return list.getFirst();
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new  EmptyQueueException();
        }
        return list.removeFirst();
    }

    //TODO: Left as an exercise.
}
