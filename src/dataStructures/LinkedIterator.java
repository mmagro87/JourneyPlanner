package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of an Iterator for Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */

class LinkedIterator<E>  implements Iterator<E> {
    //TODO: Left as an exercise.

    /**
     * First node of the list.
     */
    private final LinkedNode<E> first;
    /**
     * Node with the next element in the iteration.
     */
    LinkedNode<E> nextToReturn;

    /**
     * SinglyIterator constructor
     * @param first - Node with the first element of the iteration
     */
    public LinkedIterator(LinkedNode<E> first) {
        this.first=first;
        nextToReturn=first;
    }

    /**
     * Returns true if next would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return  nextToReturn != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    @Override
    public E next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        E element = nextToReturn.getElement();
        nextToReturn = nextToReturn.getNext();
        return element;
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     */
    @Override
    public void rewind() {
        nextToReturn = first;
    }
}
