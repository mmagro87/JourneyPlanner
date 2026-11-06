package dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SepChain Hash Table
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class SepChainHashTable<K,V> extends HashTable<K,V> {

    //Load factors
    static final float IDEAL_LOAD_FACTOR =0.75f;
    static final float MAX_LOAD_FACTOR =0.9f;
    private static final int NUMCOLLISION = 3;

    // The array of set.
    private transient Set<Entry<K,V>>[] table;

    public SepChainHashTable( ){
        this(DEFAULT_CAPACITY);
    }

    public SepChainHashTable( int capacity ){
        super();
        table = createTable(capacity);
    }

    @SuppressWarnings("unchecked")
    private Set<Entry<K,V>>[] createTable(int capacity){
        Set<Entry<K,V>>[] aux;
        int arraySize = HashTable.nextPrime((int) (capacity / IDEAL_LOAD_FACTOR));
        maxSize = (int)(arraySize * MAX_LOAD_FACTOR);
        // Compiler gives a warning.
        aux = (Set<Entry<K,V>>[]) new Set[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            aux[i] = new SetLinkedList<>();
        return aux;
    }

    private void rehash() {
        //TODO: Left as an exercise.
        Set<Entry<K, V>>[] oldTable = table;
        table = createTable(oldTable.length);
        currentSize = 0;

        for (int i = 0; i < oldTable.length; i++) {
            Iterator<Entry<K, V>> iterator = oldTable[i].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                put(entry.key(), entry.value());
            }
        }
    }

    // Returns the hash value of the specified key.
    int hash( K key ){
        return Math.abs( key.hashCode() ) % table.length;
    }
    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     *
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V get(K key) {
        //TODO: Left as an exercise.
        int index = hash(key);
        Entry<K,V> element = new Entry<>(key, null);
        Entry<K,V> rightElement = table[index].get(element);
        if (rightElement == null) {
            return null;
        }
        return rightElement.value();
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with the key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V put(K key, V value) {

        //TODO: Left as an exercise.
        int index = hash(key);
        Entry<K,V> element = new Entry<>(key, value);
        Entry<K,V> rightElement = table[index].get(element);
        if (rightElement != null) {
            V oldValue = rightElement.value();
            table[index].replace(element);
            return oldValue;
        }
        if (isFull()) {
            rehash();
        }
        int newIndex = hash(key);
        table[newIndex].put(element);
        currentSize++;
        return null;
    }


    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     *
     * @param key whose entry is to be removed from the map
     * @return previous value associated with the key,
     * or null if the dictionary does not an entry with that key
     */
    @Override
    public V remove(K key) {
        //TODO: Left as an exercise.
        int index = hash(key);
        Entry<K,V> element = new Entry<>(key, null);
        Entry<K,V> rightElement = table[index].get(element);
        if(rightElement != null) {
            V oldValue = rightElement.value();
            table[index].remove(rightElement);
            currentSize--;
            return oldValue;
        }
        return null;
    }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new SepChainHTIterator<>(table);
    }

    void writeData(ObjectOutputStream oos)
            throws IOException {
        //TODO: Left as an exercise.
        oos.defaultWriteObject(); // write the normal attributes
    }

    void readData(ObjectInputStream ios)
            throws IOException, ClassNotFoundException {
        //TODO: Left as an exercise.
        ios.defaultReadObject(); // read the normal attributes
    }

}
