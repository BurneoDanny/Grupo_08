
package ec.edu.espol.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Celda extends Button implements MouseListener, Runnable {
    public Celda up, down, left, right;
    //private static final Color color = new Color(255, 255, 255);
    private final String colorWhite = "-fx-background-color: #fff;";
    private final String colorRed = "-fx-background-color: #d93b4d;";
    private final String colorYellow = "-fx-background-color: #ebf21b;";
    private JLabel letra;
    private  int row;
    private  int column;
     private final Celda celdas[][];
    private boolean neighboursSet = false; // QUESTION MARK ?
    
    public Celda(int row, int column, JLabel letra, Celda[][] celdas) {
        this.row = row;
        this.column = column;
        this.setStyle(colorWhite);
        //this.setBackground(color);
        this.letra = letra;
        this.celdas = celdas;
        //this.addMouseListener(this);
        this.setNeighbours();
    }
    
    public void setNeighbours() {
        try {
            up = celdas[row - 1][column];
        } catch (Exception e) {
            up = null;
        }
        try {
            down = celdas[row + 1][column];
        } catch (Exception e) {
            down = null;
        }
        try {
            left = celdas[row][column - 1];
        } catch (Exception e) {
            left = null;
        }
        try {
            right = celdas[row][column + 1];
        } catch (Exception e) {
            right = null;
        }
    }
    

    public JLabel getLetra() {
        return letra;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void setLetra(JLabel letra){
        this.letra = letra;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    private boolean isPainted(Color color) {
        return this.getBackground().equals(color);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) { 
    }
    
 

    @Override
    public void mouseEntered(MouseEvent e) {
        /*
        if(!e.getComponent().getBackground().equals(colorRed)){
            e.getComponent().setBackground(colorYellow);
        }*/
        if(!this.getStyle().equals(colorRed)){
            this.setStyle(colorYellow);
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /*
        if(!e.getComponent().getBackground().equals(colorRed)){
            e.getComponent().setBackground(colorYellow);
        }*/
        if(!this.getStyle().equals(colorRed)){
            this.setStyle(colorYellow);
        }
        
    }

    @Override
    public void run() {   
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
}
