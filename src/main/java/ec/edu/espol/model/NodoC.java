
package ec.edu.espol.model;

public class NodoC<E> {
    private E content;
    private NodoC<E> next;
    private NodoC<E> previous;
    
    public NodoC(E content){
        this.content=content;
        this.next=this;
        this.previous=this;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodoC<E> getNext() {
        return next;
    }

    public void setNext(NodoC<E> next) {
        this.next = next;
    }

    public NodoC<E> getPrevious() {
        return previous;
    }

    public void setPrevious(NodoC<E> previous) {
        this.previous = previous;
    }
     
    
    
    
}
