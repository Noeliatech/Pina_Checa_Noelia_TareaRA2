package es.cifpcarlos3.dao;

import es.cifpcarlos3.model.Profesor;
import java.util.List;

//Interfaz DAO donde defino los métodos que se van a implementar

public interface profesorDAO {

    void insertarProfesor(Profesor profesor);
    Profesor obtenerProfesorPorDNI(String dni);
    int eliminarProfesorPorDNI(String dni);
    List<Profesor> listarProfesores();

}
