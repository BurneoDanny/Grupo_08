
package ec.edu.espol.model;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
import javafx.scene.control.Label;


public class CircularLinkedList<E> implements List<E> {
    private CircularNode<E> last;
    private CircularNode<E> header;


    public CircularLinkedList() {
        last = null;
        header = null;

    }

    public CircularNode<E> getLast() {
        return last;
    }

    public void setHeader(CircularNode<E> header) {
        this.header = header;
    }
    
    public CircularNode<E> getFirst(){
        return header;
    }

    public void setLast(CircularNode<E> last) {
        this.last = last;
    }
   

    @Override
    public boolean addFirst(E e) {    
        if(e == null){
            return false;
        }
        else if(isEmpty()){
            CircularNode<E> newNode = new CircularNode<>(e);
            header = newNode;
            last = newNode;
            last.setNext(newNode);
            

        }
        else{
            CircularNode<E> newNode = new CircularNode<>(e);
            newNode.setNext(header);
            setHeader(newNode);
            last.setNext(header);
            
            return true;
        
        }

        return true;
    }

    
    
    @Override
    public boolean addLast(E e) {
        if(e!=null && isEmpty()){
            CircularNode<E> newNode = new CircularNode<>(e);
            last = newNode;
            header = newNode;
            last.setNext(header);
            return true;
        }  
        else if(e!=null){
            CircularNode<E> newNode = new CircularNode<>(e);
            last.setNext(newNode);
            newNode.setNext(header);
            last = newNode;
            return true;
        }    

        return false;
    }
    
    public CircularNode<E> getPrevious (CircularNode<E> node){
        CircularNode<E> previous = null;
        CircularNode<E> n;
        for(n = header; n!=node ; n = n.getNext()){
            previous = n;
        }
        return previous; 
    }


    @Override
    public E removeFirst() {
        CircularNode<E> nodo = header;
        if(size() == 1){
            header = null;
            last = null;
            return nodo.getContent();
        }
        else{
            /*
            header = header.getNext();
            getPrevious(header).setNext(null);
            last.setNext(header);           
            */
            last.setNext(header.getNext());
            header.setNext(null);
            header = last.getNext();
            return nodo.getContent();
        }
    }
    
 
    @Override
    public E removeLast() {
        CircularNode<E> nodo = last;
        if(size() == 1){
            last.setNext(null);
            header = null;
            last = null;
            return nodo.getContent();
        }
        else{
            last.setNext(null);
            last = getPrevious(last);
            last.setNext(header);
            return nodo.getContent();
        }
    }
    
    public void mostrar(){        
        CircularNode<E> n= header;   
        do{
            System.out.println(n.getContent());
            n=n.getNext();            
        }while(n!=header);
    } 

    
    
    public CircularLinkedList<E> moveRight(){
        addFirst(removeLast());   
        return this;
    }

    @Override
    public int size() {   
        int out = 0;
        if (!isEmpty()) {
            if (last == last.getNext()) {
                out = 1;
            } 
            else {
                CircularNode<E> tmp = last;
                do {
                    tmp = tmp.getNext();
                    out++;
                } while (tmp != last);
            }
        }
        return out;
    
    }
    
    @Override
    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {     
        if(index >= 0 && index < size()){
            // Consulta si la posicion es el inicio de la lista.
            if (index == 0) {
                // Retorna el valor del inicio de la lista.
                System.out.println("Paso por aqui 1");
                return header.getContent();
            }
            else{
                // Crea una copia de la lista.
                CircularNode<E> nodo = header;
                // Recorre la lista hasta la posición ingresada.
                for (int i = 0; i < index; i++) {
                    nodo = nodo.getNext();
                }
                // Retorna el valor del nodo.
                System.out.println("Paso por aqui 2");
                return nodo.getContent();
            }
        // Crea una excepción de Posicion inexistente en la lista.
        }
        System.out.println("Paso por aqui 3 :c");
        return null;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> findAll(Comparator<E> cmp, E anotherElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            CircularNode<E> cursor = header;
            @Override
            public boolean hasNext(){
                return cursor != null;
            }
            @Override
            public E next(){
                E content = cursor.getContent();
                cursor = cursor.getNext();
                return content;
            
            }
        };
        return it;
    }
    
   
    /*
    
    
    public Stack<Label> mostrarStack(){  
        Stack<Label> leibleable = new Stack();
        CircularNode<E> n= header;  
        Label label = (Label)n.getContent();
        do{
            leibleable.add(label);
            n = n.getNext();            
        }while(n!=header);
        return leibleable;
    } 
    
    public CircularLinkedList<E> moveRight(){
        CircularNode<E> n = last;
        header = n;
        CircularLinkedList<E> newList = new CircularLinkedList<>();
        do{
            
            newList.addLast(n.getContent());
            newList.tamaño += 1;
            n = n.getNext();

        }while(n!=last);   
        return newList;
    }
    
     
    public void addElement(E e){        
        if(e!=null && last==null){
            CircularNode<E> n= new CircularNode<>(e);
            last=n;  
            header = n;
            last.setNext(header);
        }else if(e!=null){
            CircularNode<E> n= new CircularNode<>(e);                        
            //n.setPrevious(last);
            last.setNext(n);
            n.setNext(header);                                               
            last=n;     
            //header.setPrevious(last);
        }

    }*/
   
    


}
    
    

