
package ec.edu.espol.controller;

import ec.edu.espol.model.Celda;
import ec.edu.espol.model.CircularLinkedList;
import ec.edu.espol.util.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javafx.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
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
    private int filas;
    private int columnas;
    private final String colorRed = "-fx-background-color: #d93b4d;";
    private GridPane gridPanelSopa = new GridPane();
    @FXML
    private Pane panelSopa;
    private CircularLinkedList<Celda> listaDeCeldasPorFila = new CircularLinkedList();
    //private CircularLinkedList<CircularLinkedList> CeldasPorColumna = new CircularLinkedList();
    private CircularLinkedList CeldasPorColumna[];
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
     public void cargar(int filas, int columnas)
    {  
        gridPanelSopa.alignmentProperty().setValue(Pos.CENTER);
        panelSopa.getChildren().add(gridPanelSopa);     
        this.filas = filas;
        this.columnas = columnas;
        setMatrix(filas,columnas); 
        colocarPalabras();
        llenarDeLetras();
        
        
    }
    
     private void setMatrix(int filas, int columnas){ //10 FILAS 5 COLUMNAS         
        celdas= new Celda[filas][columnas];
        for (int i = 0; i< filas; i++) 
        {
            for (int j = 0; j < columnas; j++) {
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
                
                gridPanelSopa.add(celdas[i][j], j, i);     
            }
        }
        
        for (int n = 0; n < filas; n++) {
            for (int m = 0; m < columnas; m++) {
                celdas[n][m].setNeighbours();
            } 
        }

    }
     
     // MANDO UNA K DEPENDIENDO DE LA COLUMNA QUE QUIERA CONVERTIR A LISTA CIRCULAR, CADA CELDA DE ESA COLUMNA SE INCLUYE EN UNA LISTA CIRCULAR
    private CircularLinkedList<Celda> convertirACircularLinkedList(int k){
        CircularLinkedList<Celda> listaDeCeldasPorColumna = new CircularLinkedList();
        CeldasPorColumna = new CircularLinkedList[gridPanelSopa.getColumnCount()]; // ARRAY DE TAMA??O X
        for (int n = 0; n < filas; n++) { // SE MUEVE DE LA FILA X HASTA LA FILA Y
           
            listaDeCeldasPorColumna.addElement(celdas[n][k]);          
        }
        return listaDeCeldasPorColumna;     
    }
    
    
     
    private void clickLetra(Celda celda){   
        for (int i=0;i<celdas.length;i++){ // celdas.length almacena la cantidad de filas de la matriz 
            for(int j=0;j<celdas[0].length;j++){ // celdas[f].length cuando f vale cero accedemos a la cantidad de elementos de la fila cero
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
     
    
    private void colocarPalabras(){  
        Queue<String> palabras = Util.readFile("src/main/resources/Palabras/palabras.txt");
        Stack<String> finalWords =  new Stack();
        while(!palabras.isEmpty()){
            if(palabras.peek().length()<= celdas.length || palabras.peek().length()<= celdas[0].length){
                finalWords.push(palabras.poll());         
            }
            else{
                palabras.poll();
            }
        }
        String finalPalabras[] = new String[finalWords.size()];     
        int i = 0;
        while(finalWords.size()>filas-1 && i < finalPalabras.length){ 
           finalPalabras[i] = finalWords.pop(); 
           i++;
        }
        int numero[] = NumeroAleatorioSinRepeticion(finalPalabras.length); // IMAGINEMOS QUE SON 4 PALABRAS ENTONCES PUEDE DAR EL NUMERO : 0, 1,2 ,3
        int x = 0;
        for(int k = 0; k<finalPalabras.length; k++){
            x = numero[k];
            formaHorizontalNormal(finalPalabras[k], x);
        }
        
    }
    
    private void formaHorizontalNormal(String palabra, int filaUnica){
        Random posicionAleatoria = new Random();
        int filaRandom = posicionAleatoria.nextInt(celdas.length); // NUMERO RANDOM DE FILA
        int columnaRandom = posicionAleatoria.nextInt(celdas[0].length);  // NUMERO RANDOM DE columna
        System.out.println(palabra +" "+filaUnica);
        int i=0; // INTEGER IGUAL A CERO
        if (columnaRandom+palabra.length()< columnas) { // COMPRUEBA QUE LA POSICION DE DONDE EMPEZARA LA PALABRA SEA MENOR QUE EL ESPACIO TOPE
            for (int j = columnaRandom; j < columnaRandom+palabra.length(); j++) {
                celdas[filaUnica][j].setText(palabra.substring(i, i+1));
                celdas[filaUnica][j].setLetra(new JLabel(Character.toString(palabra.charAt(i))));
                celdas[filaUnica][j].getLetra().setName("1");
            }
        }
        else if (columnaRandom-palabra.length()>0){
            for (int j = columnaRandom; j > columnaRandom-palabra.length() ; j--) {
                celdas[filaUnica][j].setText(Character.toString(palabra.charAt(i)));
                celdas[filaUnica][j].setLetra(new JLabel(Character.toString(palabra.charAt(i))));
                celdas[filaUnica][j].getLetra().setName("1");
            }
        }   
    } 
    
    public static int[] NumeroAleatorioSinRepeticion(int repeticiones) {  
        Set<Integer> set1 =  new TreeSet<>((int1, int2)->{ 
                Random random=new Random();
                return (random.nextInt()*int1);
        });
        for(int i = 0; i<repeticiones; i++){
            set1.add(i); 
        } 
        Iterator<Integer> iterator1 = set1.iterator();
        int numeroRandom[] = new int[repeticiones];
        int k = 0;
        while(iterator1.hasNext()){
            Integer e = iterator1.next();
            numeroRandom[k] = e;
            k++;
        }
        return numeroRandom;
    }
 
    
      
    private void llenarDeLetras() {
        //este arreglo ayuda a poner las letras del abecedario
        String abc[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","??","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (celdas[i][j].getText().equals("")) { //si la casilla esta vacia pongale una letra del arreglo abc
                    String aleatorio = abc[(int)(random.nextDouble()*abc.length-1)];
                    /*
                    celdas[i][j].setText(aleatorio);
                    celdas[i][j].setLetra(new JLabel (aleatorio));
                    */
                    celdas[i][j].setText("F");
                    celdas[i][j].setLetra(new JLabel ("F"));
                }
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
     
    
    
    
    
    
}
