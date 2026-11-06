package dataStructures;

public class SetLinkedList<E> extends LinkedList<E> implements Set<E> {

    public SetLinkedList(){
        super();
    }

    @Override
    void addElem(E element) {
        //TODO: Left as an exercise.
        addFirstNode(new SinglyListNode<>(element));
    }

    /**
     * If there is an element in the set equals to a specified element,
     * returns the element; otherwise, returns null.
     *
     * @param element
     * @return an element in the set equals to a specified element,
     * or null otherwise
     */
    @Override
    public E get(E element) {
        //TODO: Left as an exercise.
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E current = iterator.next();
            if (current.equals(element)) {
                return current;
            }
        }
        return null;
    }

    /**
     * If there is an element in the set equals to a specified element,
     * returns true; otherwise, returns false.
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        if (isEmpty())
            return false;
        return get(element)!=null;
    }

    /**
     * Inserts the element,
     * if there is not an element in the set equals to the specified element and return true.
     * otherwise returns false
     * @param element to be added
     * @return true if insert the element, otherwise false
     */
    public boolean put(E element){
        //TODO: Left as an exercise.
        if(!contains(element)){
            addElem(element);
            return true;
        }
        return false;
    }

    /**
     * If there is an element in the set equals to the specified element
     * replace the element and return old element.
     * otherwise returns null
     *
     * @param element to be added
     * @return old element, otherwise null
     */
    @Override
    public E replace(E element) { //DÚVIDAAAA- A ordem de inserção não interessa
        //ou preciso de trocar o elemento dentro do nó(sem mudar a posição?)
        //TODO: Left as an exercise.
        E oldElement = remove(element);
        if(oldElement == null){
            return null;
        }
        addElem(element);
        return oldElement;
    }

    /**
     * If there is an element in the set equals to the specified element,
     * removes it from the set and returns the element;
     * otherwise, returns null.
     *
     * @param element to be removed
     * @return element removed,
     * or null if the set does not an element equals
     */
    @Override
    public E remove(E element) {
        //TODO: Left as an exercise.
        pairNode<E> pair = nodeOf(element);
        if(contains(element)){
            remove(pair.node().getElement());
        }
        return null;
    }


    private void invariant(){
        assert allDif();
    }

    private boolean allDif() {

        return true;
    }
}
