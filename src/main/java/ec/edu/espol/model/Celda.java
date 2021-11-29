
package ec.edu.espol.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Celda extends JPanel implements MouseListener, Runnable {
    public Celda up, down, left, right;
    long startTime;
    private static final Color color = new Color(255, 255, 255);
     private JLabel letra;
    private final int row;
    private final int column;
 
    private boolean neighboursSet = false; // QUESTION MARK ?
    Stack<Celda> celdasRecorridas = new Stack<>();
    
    public Celda(int row, int column, JLabel letra) {
        this.row = row;
        this.column = column;
        this.letra = letra;
        this.setSize(new Dimension(20, 20));
        this.setBackground(color);
        this.addMouseListener(this);
        //this.setNeighbours();
    }
    
    private boolean isPainted(Color color) {
        return this.getBackground().equals(color);
    }

    public JLabel getLetra() {
        return letra;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        Stack<Celda> celdasRecorridas = new Stack<>();
        if(e.getClickCount() == 1){
            e.getComponent().setBackground(new java.awt.Color(147, 207, 250));
            celdasRecorridas.push(this);
        }
        else if(e.getClickCount() == 2){ // E LLEGUEMOS A E        
            System.out.println("Double clicked");

            while (!celdasRecorridas.peek().equals(this)) { 
                if (this.up != null && this.up.equals(this)) {
                    celdasRecorridas.push(this.up); // recordando
                }
                if (this.down != null && this.down.equals(this)) {
                    celdasRecorridas.push(this.down); // recordando
                }
                if (this.left != null && this.left.equals(this)) {
                    celdasRecorridas.push(this.left); // recordando
                }
                if (this.right != null && this.right.equals(this)) {
                    celdasRecorridas.push(this.right); // recordando
                }
            }
        }
        */  
    }
    
    

    

    @Override
    public void mousePressed(MouseEvent e) {
        e.getComponent().setBackground(new java.awt.Color(147, 207, 250));
        celdasRecorridas.push(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        while (!celdasRecorridas.peek().equals(this)) { // NO FUNCIONA CON DIAGONALES
            Celda currentCelda = celdasRecorridas.pop();
            if (currentCelda.up != null && !currentCelda.up.equals(this)) {
                celdasRecorridas.push(this.up); // recordando
            }
            if (currentCelda.down != null && currentCelda.down.equals(this)) {
                celdasRecorridas.push(this.down); // recordando
            }
            if (currentCelda.left != null && currentCelda.left.equals(this)) {
                celdasRecorridas.push(this.left); // recordando
            }
            if (currentCelda.right != null && currentCelda.right.equals(this)) {
                celdasRecorridas.push(this.right); // recordando
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {       
    }

    @Override
    public void mouseExited(MouseEvent e) {       
    }

    @Override
    public void run() {   
        
    }
    
}
