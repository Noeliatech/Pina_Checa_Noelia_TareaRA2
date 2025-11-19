package es.cifpcarlos3.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

//Clase que representa un módulo.
//Lombok genera automáticamente getters, setters, toString, equals, hashCode y los constructores.
//Le doy los mismos atributos que existen en el TXT
public class Modulo {
    private String nombreModulo;
    private int horas;
    private String dni;

}
