package es.cifpcarlos3.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

//Clase que representa a un profesor.
//Lombok genera automáticamente getters, setters, toString, equals, hashCode y los constructores.
//Le doy los mismos atributos que existen en la BD
public class Profesor {
    private String apellidos;
    private String dni;
    private String nombre;
    private String telefono;

}
