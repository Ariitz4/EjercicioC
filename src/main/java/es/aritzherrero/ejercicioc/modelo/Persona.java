package es.aritzherrero.ejercicioc.modelo;

import java.util.Objects;

/**
 * Clase que representa una Persona con nombre, apellidos y edad.
 * Proporciona métodos para acceder y modificar los atributos de la persona.
 * Además, sobreescribe los métodos equals() y hashCode() para facilitar la comparación entre objetos Persona.
 *
 * @author Aritz
 */
public class Persona {

    // Variables de clase (atributos)
    private String nombre;
    private String apellidos;
    private int edad;

    /**
     * Constructor que inicializa una nueva instancia de la clase Persona con los valores proporcionados.
     *
     * @param nom
     * @param ape
     * @param edad
     */
    public Persona(String nom, String ape, int edad) {
        this.nombre = nom;
        this.apellidos = ape;
        this.edad = edad;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la persona.
     *
     * @param sNombre
     */
    public void setNombre(String sNombre) {
        this.nombre = sNombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return Los apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece nuevos apellidos para la persona.
     *
     * @param sApellidos
     */
    public void setApellidos(String sApellidos) {
        this.apellidos = sApellidos;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return La edad de la persona.
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Establece una nueva edad para la persona.
     *
     * @param nEdad
     */
    public void setEdad(int nEdad) {
        this.edad = nEdad;
    }

    /**
     * Genera el código hash para una instancia de Persona, utilizando los atributos edad, apellidos y nombre.
     *
     * @return El valor hash de la persona.
     */
    @Override
    public int hashCode() {
        return Objects.hash(edad, apellidos, nombre);
    }

    /**
     * Compara dos objetos Persona para verificar si son iguales.
     * Dos personas se consideran iguales si tienen el mismo nombre, apellidos y edad.
     *
     * @param obj
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        return edad == other.edad && Objects.equals(apellidos, other.apellidos)
                && Objects.equals(nombre, other.nombre);
    }

}