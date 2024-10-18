module es.aritzherrero.ejercicioc {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.aritzherrero.ejercicioc to javafx.fxml;
    exports es.aritzherrero.ejercicioc;
    exports es.aritzherrero.ejercicioc.controlador;
    opens es.aritzherrero.ejercicioc.controlador to javafx.fxml;
    opens es.aritzherrero.ejercicioc.modelo to javafx.fxml, javafx.base;
}