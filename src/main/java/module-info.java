module ec.edu.espol.grupo_08 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.grupo_08 to javafx.fxml;
    exports ec.edu.espol.grupo_08;
}
