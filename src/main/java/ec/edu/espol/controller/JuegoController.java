
package ec.edu.espol.controller;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javax.swing.JLabel;

// CREA LA MATRIZ PERO CON ALGUNOS PROBLEMAS
public class JuegoController implements Initializable {

    @FXML
    private AnchorPane principalPane;
    private GridPane gridPrueba;
    private static final String textAdjusment = "-fx-font-size: 18; -fx-border-color: #adadad; ";
 
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
    public void setMatrix(String filas, String columnas){
        int filas1 = Integer.parseInt(filas);
        int columnas1 = Integer.parseInt(columnas);
        gridPrueba = new GridPane();
        principalPane.getChildren().add(gridPrueba);
        //gridPrueba.setMinSize(600, 370);
        //gridPrueba.setAlignment(Pos.CENTER);
        gridPrueba.setLayoutY(30);
        gridPrueba.setGridLinesVisible(false);
        
        for (int i = 0; i < filas1; i++) 
        {
            for (int j = 0; j < columnas1; j++) {
                Label texto = new Label("A");
                
                gridPrueba.setHgap(15);
                gridPrueba.setVgap(10);
                texto.setStyle(textAdjusment);
                gridPrueba.add(texto, i, j);
                
                
              
            }
        }
    }
    
    
    
}
