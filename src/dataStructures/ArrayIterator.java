package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class ArrayIterator<T> implements TwoWayIterator<T> {
	private static final int DIFF=2;

	private final T[] elems;
	private final int counter;
	private int currentN;
	private int currentP;

	public ArrayIterator(T[] elems, int counter) {
		this.elems = elems;
		this.counter = counter;
		rewind();
	}

	/**
	 * Returns true if previous would return an element
	 * rather than throwing an exception.
	 *
	 * @return true iff the iteration has more elements in the reverse direction
	 */
	@Override
	public boolean hasPrevious() {
		return currentP >= 0;
	}

	/**
	 * Returns the previous element in the iteration.
	 *
	 * @return previous element in the iteration
	 * @throws NoSuchElementException - if call is made without verifying pre-condition
	 */
	@Override
	public T previous() throws NoSuchElementException {
		if (!hasPrevious()) {
			throw new NoSuchElementException();
		}
		int current  = currentP;
		currentP = currentP - 1;
		return elems[current];
	}

	/**
	 * Restarts the iteration in the reverse direction.
	 * After fullForward, if iteration is not empty,
	 * previous will return the last element
	 */
	@Override
	public void fullForward() {
		currentP  = counter - 1;
		currentN = counter;
	}

	/**
	 * Returns true if next would return an element
	 * rather than throwing an exception.
	 *
	 * @return true iff the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return currentN <= counter - 1;
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 * @throws NoSuchElementException - if call is made without verifying pre-condition
	 */
	@Override
	public T next() throws NoSuchElementException {
		if (hasNext() == false){
			throw new NoSuchElementException();
		}

		int current = currentN;
		currentN = currentN + 1;
		return elems[current];
	}

	/**
	 * Restarts the iteration.
	 * After rewind, if the iteration is not empty, next will return the first element.
	 */
	@Override
	public void rewind() {
		currentP = -1;
		currentN = 0;
	}
	//TODO: Left as an exercise.
}
