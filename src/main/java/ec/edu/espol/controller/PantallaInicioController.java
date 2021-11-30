
package ec.edu.espol.controller;

import ec.edu.espol.grupo_08.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class PantallaInicioController implements Initializable {

    @FXML
    private TextField filas;
    @FXML
    private TextField columnas;
    @FXML
    private Button playButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void playClicked(MouseEvent event) {
        String filas1 = filas.getText();
        String columnas1 = columnas.getText();
        try{

            FXMLLoader fxmlloader = App.loadFXMLLoader("juego");  
            App.setRoot(fxmlloader);
            JuegoController jc = fxmlloader.getController();  
            jc.cargar(filas1, columnas1);
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }  
    }
    
}
