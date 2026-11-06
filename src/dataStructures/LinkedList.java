package dataStructures;

import java.io.*;

/**
 * Linked List
 * Includes description of general methods to be implemented by linked lists.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
abstract class LinkedList<E> implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     *  Node at the head of the list.
     */
    transient LinkedNode<E> head;
    /**
     * Node at the tail of the list.
     */
    transient LinkedNode<E> tail;
    /**
     * Number of elements in the list.
     */
    transient int currentSize;
    /**
     * Constructor of an empty singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public LinkedList(){
        head=null;
        tail=null;
        currentSize=0;
    }
    /**
     * Returns true iff the list contains no elements.
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        //TODO: Left as an exercise.
        return currentSize==0;
    }
    /**
     * Returns the number of elements in the list.
     * @return number of elements in the list
     */
    public int size() {
        //TODO: Left as an exercise.
        return currentSize;
    }

    /**
     * Returns an iterator of the elements in the list (in a proper sequence).
     * @return Iterator of the elements in the list
     */
    public Iterator<E> iterator() {
        return new LinkedIterator<>(head);
    }

    /**
     * Insert a node on the head of list
     * @param newNode
     */
    void addFirstNode(LinkedNode<E> newNode){ //head = newNode
        //TODO: Left as an exercise.
        if(isEmpty()){
            head  = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }

        currentSize++;
    }

    /**
     * Insert a node on the tail of list
     * @param newNode
     */
    void addLastNode(LinkedNode<E> newNode){
        //TODO: Left as an exercise.
        if(this.isEmpty()){
            addFirstNode(newNode);
        }else{
            tail.setNext(newNode);
            tail = newNode;
            currentSize++;
        }
    }

    /**
     * Record with two nodes (prev, node)
     * @param prev
     * @param node
     */
    record pairNode<E>(LinkedNode<E> prev, LinkedNode<E> node){

    }

    /**
     * Insert node (newNode) between pair.previous() and pair.node()
     * @pre: pair.previous()!=null && pair.node()!=null
     * @param newNode
     */
    void addMiddleNode(pairNode<E> pair,LinkedNode<E> newNode){
        //TODO: Left as an exercise.
        LinkedNode<E> prev = pair.prev();
        LinkedNode<E> node = pair.node();
        newNode.setNext(node);
        prev.setNext(newNode);
        currentSize++;
    }

    /**
     * Removes the first node in the list.
     * @pre: !isEmpty()
     * @return
     */
    E removeFirstNode(){
        //TODO: Left as an exercise.
        E element = head.getElement();
        if(size()==1){
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }
        currentSize--;
        return element;
    }

    /**
     * remove the last node (pair.node()) of the list
     * @return
     */
    E removeLastNode(pairNode<E> pair){
        //TODO: Left as an exercise.
        if(size()==1){
            return removeFirstNode();
        }
        LinkedNode<E> prev = pair.prev();
        LinkedNode<E> node = pair.node();
        E element = node.getElement();
        if (node == tail) {
            tail = prev;
            prev.setNext(null);
        }
        currentSize--;
        return element;
    }

    /**
     * remove the node pair.node()
     @pre: pair.previous()!=null && pair.node()!=null
      * @param pair
     */
    void removeMiddleNode(pairNode<E> pair) {
        //TODO: Left as an exercise.
        LinkedNode<E> prev = pair.prev();
        LinkedNode<E> node = pair.node();
        LinkedNode<E> next = node.getNext(); //nó que está ao lado direito do node que vai ser removido
        prev.setNext(next);
        currentSize--;
    }

    /**
     *
     * @param element
     * @return pair with the previous node and the element node, Or null if no element
     */
    pairNode<E>  nodeOf(E element){
        //TODO: Left as an exercise.
        LinkedNode<E> prevNode = null;
        LinkedNode<E> elemNode = head;
        while(elemNode != null ){
            if(elemNode.getElement().equals(element)){
                return new pairNode<>(prevNode,elemNode);
            }

            prevNode = elemNode;
            elemNode = elemNode.getNext();
        }

        return null;
    }

    LinkedNode<E> getFirstNode(){
        //TODO: Left as an exercise.
        return head;
    }

    LinkedNode<E> getLastNode(){
        //TODO: Left as an exercise.
        return tail;
    }

    // MANUAL SERIALIZATION
    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        //TODO: Left as an exercise.
        writeData(oos);
        oos.writeInt(currentSize); // write the size
        LinkedNode<E> node = head;
        while (node != null) {
            oos.writeObject(node.getElement()); // write each element
            node = node.getNext();
        }
    }

    // MANUAL DESERIALIZATION
    @SuppressWarnings("unchecked")
    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        //TODO: Left as an exercise.
        readData(ois);
        int tamanho = ois.readInt(); // read the size
        for (int i = 0; i < tamanho; i++) { // add each element on the list
            @SuppressWarnings("unchecked")
            E element = (E) ois.readObject();
            addElem(element);
        }
    }

    void writeData(ObjectOutputStream out) throws IOException{
    }

    void readData(ObjectInputStream in) throws IOException, ClassNotFoundException {
    }

    abstract void addElem(E element);
}
