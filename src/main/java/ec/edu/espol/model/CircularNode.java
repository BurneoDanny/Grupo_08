
package ec.edu.espol.model;

public class CircularNode<E>  {
    private E content;
    private CircularNode<E> next;
    
    public CircularNode(E content){
        this.content = content;
        this.next = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularNode<E> getNext() {
        return next;
    }

    public void setNext(CircularNode<E> next) {
        this.next = next;
    }

    
    
}
