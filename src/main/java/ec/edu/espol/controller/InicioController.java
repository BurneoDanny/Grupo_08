
package ec.edu.espol.controller;

import ec.edu.espol.pruebasproyectoparcial.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class InicioController implements Initializable {

    @FXML
    private TextField numeroDeFilas;
    @FXML
    private TextField numeroDeColumnas;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Jugar(MouseEvent event) {
        int filas = Integer.parseInt(numeroDeFilas.getText());
        int columnas = Integer.parseInt(numeroDeColumnas.getText());
        try{

            FXMLLoader fxmlloader = App.loadFXMLLoader("prueba");  
            App.setRoot(fxmlloader);
            PruebaController pc = fxmlloader.getController();  
            pc.CargarJuego(filas, columnas);
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        } 
    }
    
    
    
}
