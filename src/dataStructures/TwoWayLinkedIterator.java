package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of Two Way Iterator for DLList
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
class TwoWayLinkedIterator<E> extends LinkedIterator<E>
        implements TwoWayIterator<E> {
    /**
     * Node with the last element in the iteration.
     */
    private final LinkedNode<E> lastNode;
    /**
     * Node with the previous element in the iteration.
     */
    private LinkedNode<E> prevToReturn;

    /**
     * DoublyLLIterator constructor
     *
     * @param first - Node with the first element of the iteration
     * @param last  - Node with the last element of the iteration
     */
    public TwoWayLinkedIterator(LinkedNode<E> first, LinkedNode<E> last) {
        super(first);
        prevToReturn=null;
        lastNode=last;
    }

    @Override
    public E next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        prevToReturn = nextToReturn;
        return super.next();
    }

    /**
     * Returns true if previous would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements in the reverse direction
     */
    @Override
    public boolean hasPrevious() {
        return prevToReturn != null ;
    }

    /**
     * Returns the previous element in the iteration.
     *
     * @return previous element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    @Override
    public E previous() throws NoSuchElementException {
        if(!hasPrevious()){
            throw new NoSuchElementException();
        }
        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn;
        prevToReturn = ((DoublyListNode<E>) prevToReturn).getPrevious();
        return element;
    }

    @Override
    public void rewind(){
        prevToReturn = null;
        super.rewind();
    }

    /**
     * Restarts the iteration in the reverse direction.
     * After fullForward, if iteration is not empty,
     * previous will return the last element
     */
    @Override
    public void fullForward() {
        prevToReturn = lastNode;
        nextToReturn = null;
    }

    //TODO: Left as an exercise.



}
