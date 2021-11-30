
package ec.edu.espol.model;

public class ListaCircular<E>{
    private NodoC<E> last;
    
    
    public NodoC<E> getLast(){
        return last;
    }

    public void setLast(NodoC<E> last) {
        this.last = last;
    }
    
    
    public NodoC<E> getHeader(){
        return last.getNext();
    }
    
    public boolean isEmpty(){
        return last==null;
    }
    
    public void addElement(E e){        
        if(e!=null && last==null){
            NodoC<E> n= new NodoC<>(e);
            last=n;         
        }else if(e!=null){
            NodoC<E> n= new NodoC<>(e);                        
            n.setPrevious(last);
            n.setNext(getHeader());                        
            last.setNext(n);                        
            last=n;     
            getHeader().setPrevious(last);
        }       
    }
    
    public void moveRight(ListaCircular lc){
        NodoC<E> n= lc.last;
        do{
            //Aqui va para cambiar los botones
            System.out.println(n.getContent());
            n=n.getNext();
        }while(n!=lc.last);
    }
 
    public void moveLeft(ListaCircular lc){
        NodoC<E> n=lc.getHeader();        
        do{
            NodoC<E> u=n.getNext();
            System.out.println(u.getContent()); 
            n=n.getNext();
        }while(n!=lc.getHeader());
    }
    
    public void mostrar(){        
        NodoC<E> n= getHeader();        
        do{
            System.out.println(n.getContent());
            n=n.getNext();            
        }while(n!=getHeader());
    }    
    
    /*public String toString(){
        NodoC<E> n=getHeader();
        do{
            
        }while();
    }*/
    public static void main(String[] args){
        ListaCircular lc= new ListaCircular();
        lc.addElement("a");
        lc.addElement("v");
        lc.addElement("l");
        lc.addElement("o");
        lc.addElement("n");
        lc.mostrar();
        System.out.println("");
        System.out.println("");
        lc.moveLeft(lc);
        System.out.println("");
        System.out.println("");
        lc.moveRight(lc);
    }
    
}
