package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * SepChain Hash Table Iterator
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */

class SepChainHTIterator<K,V> implements Iterator<Map.Entry<K,V>> {

    //TODO: Left as an exercise.

    private Set<Map.Entry<K,V>>[] table;
    private int currentIndex;
    private Iterator<Map.Entry<K,V>> currentIterator;

    SepChainHTIterator(Set<Map.Entry<K,V>>[] table) {
        this.table = table;
        rewind();
    }

    /**
     * Returns true if next would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return currentIterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    @Override
    public Map.Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Map.Entry<K,V> entry = currentIterator.next();
        advance();
        return entry;
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     */
    @Override
    public void rewind() {
        currentIndex = 0;
        currentIterator = table[currentIndex].iterator();
        advance();
    }

    private void advance() {
        while (!currentIterator.hasNext() && currentIndex < table.length - 1) {
            currentIndex++;
            currentIterator = table[currentIndex].iterator();
        }
    }


}

