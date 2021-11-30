
package ec.edu.espol.controller;

import ec.edu.espol.model.Celda;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javafx.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

// CREA LA MATRIZ PERO CON ALGUNOS PROBLEMAS
public class JuegoController implements Initializable {

   
 
 
    private Celda celdas[][];
    private final Stack<Celda> firstCeldaClicked = new Stack<>();
    private String filas;
    private String columnas;
    private final String colorRed = "-fx-background-color: #d93b4d;";
    private GridPane gridPanelSopa = new GridPane();
    
    @FXML
    private Pane panelSopa;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
     public void cargar(String filas, String columnas)
    {
        
        gridPanelSopa.alignmentProperty().setValue(Pos.CENTER);
        panelSopa.getChildren().add(gridPanelSopa);     
        this.filas = filas;
        this.columnas = columnas;
        setMatrix(filas,columnas);
        llenarDeLetras();
    }
    
     private void setMatrix(String filas, String columnas){       
        int filas1 = Integer.parseInt(filas);
        int columnas1 = Integer.parseInt(columnas);    
        celdas= new Celda[filas1][columnas1];
        for (int i = 0; i < filas1; i++) 
        {
            for (int j = 0; j < columnas1; j++) {
                JLabel letra = new JLabel("",SwingConstants.CENTER);
                letra.setName("");
                letra.setOpaque(true);         
                letra.setFont(new Font("Calibri", Font.PLAIN, 14)); 
                letra.setForeground(Color.BLACK); 
                Celda celda = new Celda(i, j,letra, celdas); 
                
                celdas[i][j] = celda;
                celda.setText(letra.getText());
                celda.setOnAction((ActionEvent e) -> {
                    clickLetra(celda);
                });
                gridPanelSopa.add(celdas[i][j], i, j);
            }
        }
        for (int m = 0; m < filas1; m++) {
            for (int n = 0; n < columnas1; n++) {
                celdas[m][n].setNeighbours();
            }
        }
    }
     
     private void clickLetra(Celda celda){   
        for (int i=0;i<celdas.length;i++){
            for(int j=0;j<celdas[0].length;j++){ 
                firstCeldaClicked.push(celdas[i][j]);
            }
        }
        while(!firstCeldaClicked.isEmpty() && !firstCeldaClicked.peek().getStyle().equals(colorRed)){   
            firstCeldaClicked.pop(); 
        }
        if(firstCeldaClicked.isEmpty()){
            celda.setStyle(colorRed);
           
        }
        
        else{
            int filaPrimeraCelda = firstCeldaClicked.peek().getRow();
            int columnaPrimeraCelda = firstCeldaClicked.peek().getColumn();
            int Segundafila = celda.getRow();
            int SegundaColumna = celda.getColumn();
            int restaEntreCeldasFila = filaPrimeraCelda-Segundafila;
            int restaEntreColumnasColumna = columnaPrimeraCelda-SegundaColumna;  
            Stack<Celda> finalWord = new Stack<>();
            finalWord.push(celda); 
            if(restaEntreCeldasFila == 0 || restaEntreColumnasColumna == 0){
                if(restaEntreCeldasFila == 0){ // se movio entre columnas          
                    Stack<Celda> s = new Stack<>();
                    String color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getStyle();
                    if (!celda.getStyle().equals(color)) {
                        s.push(celda); // push de 2da celda
                    }
                    while (!s.isEmpty()) {                      
                        Celda currentCelda = s.pop(); 
                        if (!currentCelda.getStyle().equals(color)) {
                            currentCelda.setStyle(color);  
                        }    
                        if(restaEntreColumnasColumna<0){
                            if (currentCelda.left != null && !currentCelda.left.getStyle().equals(color)) {
                                s.push(currentCelda.left); 
                                finalWord.push(currentCelda.left);
                                
                            }                         
                        }
                        else{
                            if (currentCelda.right != null && !currentCelda.right.getStyle().equals(color)) {
                                s.push(currentCelda.right);
                                finalWord.push(currentCelda.right);
                            }
                        }     
                    }
                }
                else{ // se movio entre filas         
                    Stack<Celda> s = new Stack<>();
                    String color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getStyle();
                    if (!celda.getStyle().equals(color)) {
                        s.push(celda); // push de 2da celda
                    }
                    while (!s.isEmpty()) {                      
                        Celda currentCelda = s.pop(); 
                        if (!currentCelda.getStyle().equals(color)) {
                            currentCelda.setStyle(color);                      
                        }    
                        if(restaEntreCeldasFila<0){                      
                            if (currentCelda.up != null && !currentCelda.up.getStyle().equals(color)) {
                                s.push(currentCelda.up); 
                                finalWord.push(currentCelda.up);
                            }                         
                        }
                        else{
                            if (currentCelda.down != null && !currentCelda.down.getStyle().equals(color)) {
                                s.push(currentCelda.down); 
                                finalWord.push(currentCelda.down);
                            }
                        }     
                    }
                }
                finalWord.push(celdas[filaPrimeraCelda][columnaPrimeraCelda]);
                ComprobarPalabra(finalWord);
            }
            else if(Math.abs(restaEntreCeldasFila) == Math.abs(restaEntreColumnasColumna)){
                Stack<Celda> s = new Stack<>();
                String color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getStyle();
                if (!celda.getStyle().equals(color)) {
                    s.push(celda); 
                }
                while (!s.isEmpty()) {
                    Celda currentCelda = s.pop(); 
                    if (!currentCelda.getStyle().equals(color)) {
                        currentCelda.setStyle(color);                      
                    } 
                    if(restaEntreCeldasFila < 0 && restaEntreColumnasColumna < 0){
                        if (currentCelda.up.left != null && !currentCelda.up.left.getStyle().equals(color)) {
                            s.push(currentCelda.up.left);
                            finalWord.push(currentCelda.up.left);
                        } 
                    }
                    else if(restaEntreCeldasFila > 0 && restaEntreColumnasColumna < 0){
                        if (currentCelda.down.left != null && !currentCelda.down.left.getStyle().equals(color)) {
                            s.push(currentCelda.down.left); 
                            finalWord.push(currentCelda.down.left);
                        } 
                    }
                    else if(restaEntreCeldasFila < 0 && restaEntreColumnasColumna > 0){
                        if (currentCelda.up.right != null && !currentCelda.up.right.getStyle().equals(color)) {
                            s.push(currentCelda.up.right);
                            finalWord.push(currentCelda.up.right);
                        } 
                    }
                    else{
                        if (currentCelda.down.right != null && !currentCelda.down.right.getStyle().equals(color)) {
                            s.push(currentCelda.down.right); 
                            finalWord.push(currentCelda.down.right);
                        }  
                    }         
                }
                finalWord.push(celdas[filaPrimeraCelda][columnaPrimeraCelda]);
                ComprobarPalabra(finalWord);
            }         
        }      
    }
     
      private void ComprobarPalabra(Stack<Celda> palabra){
        while(!palabra.isEmpty()){
            System.out.println(palabra.pop().getText());
        }
        // DESPUES DE COMPROBAR LA PALABRA DEBERIA SUCEDER LO SIGUIENTE
        /*
        for (int m = 0; m < Integer.parseInt(filas); m++) {
            for (int n = 0; n < Integer.parseInt(columnas); n++) {
                celdas[m][n].setBackground(new Color(255, 255, 255));
            }
        }  
        */
    }
      
    public void llenarDeLetras() {
        //este arreglo ayuda a poner las letras del abecedario
        String abc[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random random = new Random();
        for (int i = 0; i < Integer.parseInt(filas); i++) {
            for (int j = 0; j < Integer.parseInt(columnas); j++) {
                if (celdas[i][j].getText().equals("")) { //si la casilla esta vacia pongale una letra del arreglo abc
                    String aleatorio = abc[(int)(random.nextDouble()*abc.length-1)];
                    celdas[i][j].setText(aleatorio);
                    celdas[i][j].setLetra(new JLabel (aleatorio));
                }
            }
        }
    }
     
    
    
    
    
    
}
