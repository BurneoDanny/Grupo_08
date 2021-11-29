module ec.edu.espol.grupo_08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;
    requires java.logging;

    opens ec.edu.espol.grupo_08 to javafx.fxml;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.grupo_08;
    exports ec.edu.espol.controller;
}
