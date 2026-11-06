package dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Closed Hash Table
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class ClosedHashTable<K,V> extends HashTable<K,V> {

    //Load factors
    static final float IDEAL_LOAD_FACTOR =0.5f;
    static final float MAX_LOAD_FACTOR =0.8f;
    static final int NOT_FOUND=-1;

    // removed cell
    static final Entry<?,?> REMOVED_CELL = new Entry<>(null,null);

    // The array of entries.
    private transient Entry<K,V>[] table;


    /**
     * Constructors
     */

    public ClosedHashTable( ){
        this(DEFAULT_CAPACITY);
    }

    public ClosedHashTable( int capacity ){
        super();
        table=createTable(capacity);
    }

    @SuppressWarnings("unchecked")
    private Entry<K,V>[] createTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (capacity / IDEAL_LOAD_FACTOR));
        maxSize = (int)(arraySize * MAX_LOAD_FACTOR);
        // Compiler gives a warning.
        Entry<K,V>[] aux = (Entry<K,V>[]) new Entry<?,?>[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            aux[i] = null;
        return aux;
    }

    //USADO PARA CALCUÇAR A POSIÇÃO DA TABELA
    //Methods for handling collisions. Only one of them should be used!
    // Returns the hash value of the specified key.
    int hash( K key, int i ){
        return Math.abs( key.hashCode() + i) % table.length;
    }

    //USADO PARA QUANDO ADICIONAMOS(POR EXEMPLO), USAR O HASH, OBTEMOS A POSIÇÃO E VAMOS VER SE
    //ESSA POSIÇÃO ESTÁ OCUPADA OU NÃO
    //SE NÃO ESTIVER, ENTÃO ADICIONAMOS
    //SE ESTIVER, PASSAMOS PARA A PRÓXIMA(E VEMOS SE ESTÁ OCUPADA...)
    //SE ESTIVER DEL(REMOVED) AVANÇAMOS TAMBÉM
    /**
     * Linear Proving
     * @param key to search
     * @return the index of the table, where is the entry with the specified key, or null
     */
    int searchLinearProving(K key) {
        //TODO: Left as an exercise.
        for(int i = 0; i < table.length; i++){
            int index = hash(key, i);
            Entry<K,V> entry = table[index];

            if( entry == null ){
                return NOT_FOUND;
            }

            if(entry != REMOVED_CELL && entry.key().equals(key)){
                return index;
            }
        }
        return NOT_FOUND; // all cells were covered, the element was not found
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
        int index = searchLinearProving(key);
        if(index == NOT_FOUND){
            return null;
        }
        else{
            Entry<K,V> entry = table[index];
            return entry.value();
        }
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
        int index = searchLinearProving(key);

        if (index != NOT_FOUND) {
            V oldValue = table[index].value();
            table[index] = new Entry<>(key, value);
            return oldValue;
        }
        if (isFull())
            rehash();

        int i = 0;
        int position = hash(key, i);
        while(table[position] != null && table[position] != REMOVED_CELL){
            i++;
            position = hash(key, i);
        }
        table[position] = new Entry<>(key, value);
        currentSize++;
        return null;
    }


    private void rehash() {
        //TODO: Left as an exercise.
        Entry<K, V>[] oldTable = table;
        table = createTable(oldTable.length);
        currentSize = 0;

        for (int i = 0; i < oldTable.length; i++) {
            Entry<K, V> entry = oldTable[i];
            if (entry != REMOVED_CELL && entry != null) {
                put(entry.key(), entry.value());
            }
        }
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
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        //TODO: Left as an exercise.
        int index = searchLinearProving(key);
        if(index == NOT_FOUND){
            return null;
        }
        V oldValue = table[index].value();
        table[index] = (Entry<K, V>) REMOVED_CELL;
        currentSize--;
        return oldValue;
    }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new FilterIterator<>(
                new ArrayIterator<>(table,table.length),elem-> elem!=null && elem!= REMOVED_CELL);
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
