
package ec.edu.espol.model;



import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;





public class Celda extends Button implements Runnable {
    public Celda up, down, left, right;
    private final String colorWhite = "-fx-background-color: #fff;";
    private final String colorRed = "-fx-background-color: #d93b4d;";
    private final String colorYellow = "-fx-background-color: #ebf21b;";
    private Label label;
    private  int row;
    private  int column;
    private final Celda celdas[][];
    private boolean neighboursSet = false; // QUESTION MARK ?
    
    public Celda(int row, int column, Label label, Celda[][] celdas) {
        this.row = row;
        this.column = column;
        this.setStyle(colorWhite);
        //this.setBackground(color);
        this.label = label;
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
    

    public Label getLabel() {
        return label;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void setLabel(Label label){
        this.label = label;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    /*
    private boolean isPainted(Color color) {
        return this.getBackground().equals(color);
    }*/

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
