package es.aritzherrero.ejercicioc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase principal de la aplicación JavaFX.
 * Carga la interfaz gráfica (FXML) y lanza la aplicación.

 *
 * @author Aritz
 */
public class HelloApplication extends Application {

    /**
     * Método principal de la aplicación JavaFX que configura y muestra la ventana principal.
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML que contiene la definición de la interfaz gráfica
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ejercicioc.fxml"));

        // Crear una nueva escena con el contenido cargado desde el archivo FXML
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        //Tamaño minimo de la ventana
        stage.setMinHeight(620);
        stage.setMinWidth(830);

        // Establecer el título e icono de la ventana
        stage.setTitle("Ejercicio C");
        Image icono= new Image(getClass().getResourceAsStream("/imagen/agenda.png"));
        stage.getIcons().add(icono);

        // Asignar la escena al stage y mostrar la ventana
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main que lanza la aplicación JavaFX.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Iniciar la aplicación
        launch();
    }
}