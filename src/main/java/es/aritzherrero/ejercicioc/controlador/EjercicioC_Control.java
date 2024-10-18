package es.aritzherrero.ejercicioc.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import es.aritzherrero.ejercicioc.modelo.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Controlador de la interfaz para gestionar personas en una tabla.
 * Implementa la interfaz Initializable para inicializar los componentes de la vista.
 */
public class EjercicioC_Control implements Initializable {
    //Agregar los elementos del fxml al controler
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableView<Persona> tblvTabla;

    @FXML
    private TableColumn<Persona, String> tblcApellidos;

    @FXML
    private TableColumn<Persona, Integer> tblcEdad;

    @FXML
    private TableColumn<Persona, String> tblcNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    // Lista observable para almacenar los datos de las personas
    private ObservableList<Persona> listaPersonas;
    private String camposNulos = "";

    /**
     * Inicializa los componentes de la vista, como la lista observable y las columnas de la tabla.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicialización de la lista observable
        listaPersonas = FXCollections.observableArrayList();

        // Configuración de las columnas de la tabla para asociarlas con los atributos de Persona
        tblcNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        tblcApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        tblcEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));

        // Asignar la lista observable a la tabla
        tblvTabla.setItems(listaPersonas);
    }

    /**
     * Procedimiento llamado cuando se presiona el botón "Agregar".
     * Valida los campos del formulario y agrega una nueva persona a la tabla si no está ya presente.
     *
     * @param event
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        String camposNulos = "";
        try {
            // Validar si el campo nombre está vacío
            if (txtNombre.getText().equals("")) {
                camposNulos = "El campo nombre es obligatorio\n";
            }
            // Validar si el campo apellidos está vacío
            if (txtApellidos.getText().equals("")) {
                camposNulos += "El campo apellidos es obligatorio\n";
            }
            // Validar si el campo edad está vacío
            if (txtEdad.getText().isEmpty()) {
                camposNulos += "El campo edad es obligatorio";
            }

            // Crear una nueva persona con los datos del formulario
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            Integer edad = Integer.parseInt(txtEdad.getText());
            Persona p = new Persona(nombre, apellidos, edad);

            // Verificar si la persona ya está en la lista
            if (!listaPersonas.contains(p)) {
                // Agregar la nueva persona a la lista y refrescar la tabla
                listaPersonas.add(p);
                tblvTabla.refresh();
                // Mostrar un mensaje de éxito
                ventanaAlerta("C", "Persona añadida correctamente");
            } else {
                // Mostrar un mensaje de error si la persona ya existe
                ventanaAlerta("E", "La persona ya existe");
            }
        } catch (NullPointerException e) {
            // Mostrar un mensaje de error si faltan campos obligatorios
            ventanaAlerta("E", camposNulos);
        } catch (NumberFormatException e) {
            // Mostrar un mensaje de error si la edad no es un número válido
            ventanaAlerta("E", "El valor de edad debe ser un número mayor que cero");
        }
    }

    /**
     * Muestra una ventana de alerta con un mensaje dado.
     *
     * @param tipoAlerta
     * @param mensaje
     */
    void ventanaAlerta(String tipoAlerta, String mensaje) {
        Alert alert = null;
        // Determinar el tipo de alerta según el parámetro
        if (tipoAlerta.equals("E")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        // Mostrar el mensaje en la alerta
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    /**
     * Procedimiento llamado al presionar el botón "Eliminar".
     * Elimina la persona seleccionada de la tabla.
     *
     * @param event Evento que dispara el método.
     */
    @FXML
    void eliminarPersona(ActionEvent event) {
        // Obtener la persona seleccionada y eliminarla de la lista
        String sNombreEliminado = tblvTabla.getSelectionModel().getSelectedItem().getNombre();
        String sApellidosEliminado = tblvTabla.getSelectionModel().getSelectedItem().getApellidos();
        Integer nEdadEliminado = tblvTabla.getSelectionModel().getSelectedItem().getEdad();
        listaPersonas.remove(new Persona(sNombreEliminado, sApellidosEliminado, nEdadEliminado));
        ventanaAlerta("I", "Persona eliminada correctamente");
        eliminarValores();
    }

    /**
     * Procedimiento llamado al presionar el botón "Modificar".
     * Modifica la persona seleccionada en la tabla si no existe otra con los mismos datos.
     *
     * @param event Evento que dispara el método.
     */
    @FXML
    void modificarPersona(ActionEvent event) {
        camposNulos = "";
        try {
            // Validar los valores del formulario
            comprobarValores();
            Persona p = new Persona(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtEdad.getText()));

            if (!listaPersonas.contains(p)) {
                // Eliminar la persona seleccionada y agregar la nueva versión
                String sNombreEliminado = tblvTabla.getSelectionModel().getSelectedItem().getNombre();
                String sApellidosEliminado = tblvTabla.getSelectionModel().getSelectedItem().getApellidos();
                Integer nEdadEliminado = tblvTabla.getSelectionModel().getSelectedItem().getEdad();
                listaPersonas.remove(new Persona(sNombreEliminado, sApellidosEliminado, nEdadEliminado));
                listaPersonas.add(p);
                ventanaAlerta("I", "Persona modificada correctamente");
                eliminarValores();
            } else {
                ventanaAlerta("E", "Persona existente");
            }
        } catch (NullPointerException e) {
            ventanaAlerta("E", camposNulos);
        } catch (NumberFormatException e) {
            ventanaAlerta("E", "El valor de edad debe ser un número entero mayor que cero");
        }
    }

    /**
     * Procedimiento llamado al hacer clic en una fila de la tabla.
     * Carga los datos de la persona seleccionada en los campos de texto.
     *
     * @param event Evento de clic de ratón.
     */
    @FXML
    void seleccionarPersona(MouseEvent event) {
        try {
            // Cargar los datos de la persona seleccionada en los campos de texto
            txtNombre.setText(tblvTabla.getSelectionModel().getSelectedItem().getNombre());
            txtApellidos.setText(tblvTabla.getSelectionModel().getSelectedItem().getApellidos());
            txtEdad.setText(tblvTabla.getSelectionModel().getSelectedItem().getEdad().toString());
        } catch (NullPointerException e) {
            ventanaAlerta("E", "Seleccione una persona para cargar. Si la tabla no contiene ninguna, agregue una nueva.");
        }
    }

    /**
     * Valida los valores de los campos de texto.
     * Lanza excepciones si los valores son incorrectos.
     */
    void comprobarValores() {
        if (txtNombre.getText().equals("")) {
            camposNulos = "El campo nombre es obligatorio\n";
        }
        if (txtApellidos.getText().equals("")) {
            camposNulos += "El campo apellidos es obligatorio\n";
        }
        if (txtEdad.getText().isEmpty()) {
            camposNulos += "El campo apellidos es obligatorio";
        }
        if (camposNulos != "") {
            throw new NullPointerException();
        }
        if (Integer.parseInt(txtEdad.getText()) < 1) {
            throw new NumberFormatException();
        }
    }

    /**
     * Limpia los valores de los campos de texto.
     */
    void eliminarValores() {
        txtNombre.clear();
        txtApellidos.clear();
        txtEdad.clear();
    }
}

