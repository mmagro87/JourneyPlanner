package dataStructures;

import dataStructures.exceptions.InvalidPositionException;
import dataStructures.exceptions.NoSuchElementException;

public class ArrayClass<E> implements TwoWayList<E> {
    private static final int SIZE = 50;

    /**
     * Array of elements E.
     */
    private E[] elems;

    /**
     * Number of elements on an array.
     */
    private int counter;

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayClass() {
        elems = (E[]) new Object[SIZE];
        counter = 0;
    }

    /**
     *
     * @param dimension
     */
    @SuppressWarnings("unchecked")
    public ArrayClass(int dimension) {
        elems = (E[]) new Object[dimension];
        counter = 0;
    }

    /**
     * Returns a two-way iterator of the elements in the list.
     *
     * @return Two-Way Iterator of the elements in the list
     */
    @Override
    public TwoWayIterator<E> twoWayiterator() {
        return new ArrayIterator<>(elems, counter);
    }

    /**
     * Returns true iff the list contains no elements.
     *
     * @return true if a list is empty
     */
    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    @Override
    public int size() {
        return counter;
    }

    /**
     * Returns an iterator of the elements in the list (in a a proper sequence).
     *
     * @return Iterator of the elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(elems, counter);
    }

    /**
     * Returns the first element of the list.
     *
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elems[0];
    }

    /**
     * Returns the last element of the list.
     *
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    @Override
    public E getLast() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return elems[counter - 1];
    }

    /**
     * Returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size()-1.
     * If the specified position is 0, it corresponds to getFirst.
     * If the specified position is size()-1, get corresponds to getLast.
     *
     * @param position - position of an element to be returned
     * @return element at position
     * @throws InvalidPositionException if position is not valid in the list
     */
    @Override
    public E get(int position) throws InvalidPositionException {
        if (position < 0 || position >= counter) {
            throw new InvalidPositionException();
        }
        return elems[position];
    }

    /**
     * Returns the position of the specified element in the list
     * if the list contains the element.
     * Otherwise, returns -1.
     *
     * @param element - element to be searched in the list
     * @return position of the element in the list (or -1)
     */
    @Override
    public int indexOf(E element) {
        for(int i = 0; i < counter; i++) {
            if(elems[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addLast(E element) {
        add(counter, element);
    }

    /**
     * Inserts the specified element at the specified position in the list.
     * Range of valid positions: 0, ..., size().
     * If the specified position is 0, add corresponds to addFirst.
     * If the specified position is size(), add corresponds to addLast.
     *
     * @param position - position where to insert the element
     * @param element  - element to be inserted
     * @throws InvalidPositionException - if position is not valid in the list
     */
    @Override
    public void add(int position, E element) throws InvalidPositionException {
        if(position < 0 || position > counter) {
            throw new InvalidPositionException();
        }
        if(counter == elems.length) {
            resize();
        }
        for(int i = counter; i > position; i--) {
            elems[i] = elems[i-1];
        }
        elems[position] = element;
        counter++;
    }

    /**
     * Removes and returns the element at the first position in the list.
     *
     * @return element removed from the first position of the list
     * @throws NoSuchElementException - if size() == 0
     */
    @Override
    public E removeFirst() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    /**
     * Removes and returns the element at the last position in the list.
     *
     * @return element removed from the last position of the list
     * @throws NoSuchElementException - if size() == 0
     */
    @Override
    public E removeLast() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(counter - 1);
    }

    /**
     * Removes and returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size()-1.
     * If the specified position is 0, remove corresponds to removeFirst.
     * If the specified position is size()-1, remove corresponds to removeLast.
     *
     * @param position - position of the element to be removed
     * @return element removed at position
     * @throws InvalidPositionException - if position is not valid in the list
     */
    @Override
    public E remove(int position) throws InvalidPositionException {
        if(position < 0 || position > counter - 1) {
            throw new InvalidPositionException();
        }
        E removed = elems[position];
        for(int i = position; i < counter - 1; i++) {
            elems[i] = elems[i+1];
        }
        counter--;
        elems[counter] = null;
        return removed;
    }

    public void resize() {
        E[] newElems  = (E[]) new Object[elems.length * 2];
        for(int i = 0; i < counter; i++) {
            newElems[i] = elems[i];
        }
        elems = newElems;
    }
    //TODO: Left as an exercise.
}
