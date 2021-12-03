module ec.edu.espol.pruebasproyectoparcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.pruebasproyectoparcial to javafx.fxml;
    exports ec.edu.espol.pruebasproyectoparcial;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.controller;
}
