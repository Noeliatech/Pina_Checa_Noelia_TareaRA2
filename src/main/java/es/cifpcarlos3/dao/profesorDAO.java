package es.cifpcarlos3.dao;

import es.cifpcarlos3.model.Profesor;

import java.util.List;

public interface profesorDAO {
    void insertarProfesor(Profesor profesor);
    Profesor obtenerProfesorPorDNI(String dni);
    int eliminarProfesorPorDNI(String dni);
    List<Profesor> listarProfesores();

}
