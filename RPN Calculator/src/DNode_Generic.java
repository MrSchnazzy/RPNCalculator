/* Eli Tisor
 * DNode_Generic.java
 * CSCI-310 Data Structures
 * HW 11 - RPN Calculator
 */

/**
 *
 * @author Eli
 */
public class DNode_Generic<E> {
    protected E element;
    protected DNode_Generic<E> next;
    protected DNode_Generic<E> previous;
    
    protected DNode_Generic(E e){
        this.element = e;
        next = null;
        previous = null;
    }
}
