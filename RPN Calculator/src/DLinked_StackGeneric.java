/* Eli Tisor
 * DLinked_StackGeneric.java
 * CSCI-310 Data Structures
 * HW 11 - RPN Calculator
 */

/**
 *
 * @author Eli
 */
public class DLinked_StackGeneric<E> implements StackGeneric_Interface<E> {
    DNode_Generic<E> tos;
    int size;
    
    public DLinked_StackGeneric(){
        tos = null;
        size = 0;
    }
    
    @Override
    public void push(E element) {
        DNode_Generic<E> temp = new DNode_Generic<>(element);
        if (tos == null)
            tos = temp;
        else {
           tos.next = temp;
            temp.previous = tos;
            tos = temp; 
        }
        
        size++;
    }

    @Override
    public E pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        
        E result = tos.element;
        tos = tos.previous;
        if (tos != null){
           tos.next.previous = null;
            tos.next = null; 
        }
        
        size--;
        return result;
    }

    @Override
    public E peek() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        
        return tos.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 || tos == null;
    }

    @Override
    public void clear() {
        tos = null;
        size = 0;
    }
    
    @Override
    public String toString(){
        String s = "TOS [";
        boolean first = true;
        DNode_Generic<E> current = tos;
        
        for(int i = 0; i < size; i++){
            if(first){
                s += current.element;
                first = false;
            } else {
                s += ", " + current.element;
            }
            current = current.previous;
        }
        s += "]";
        return s;
    }
}
