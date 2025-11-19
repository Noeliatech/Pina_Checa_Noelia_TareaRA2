package es.cifpcarlos3.dao.impl;

import es.cifpcarlos3.dao.profesorDAO;
import es.cifpcarlos3.model.Profesor;
import es.cifpcarlos3.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class profesorDAOImpl implements profesorDAO {

    @Override
    public void insertarProfesor(Profesor profesor) {
        String sql = "Insert into t_profesor (apellidos, dni, nombre, telefono) values (?, ?, ?, ?)";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, profesor.getApellidos());
            ps.setString(2, profesor.getDni());
            ps.setString(3, profesor.getNombre());
            ps.setString(4, profesor.getTelefono());
            ps.executeUpdate();
            System.out.println("Profesor insertado exitosamente");

        }catch (SQLException e) {
            if (e.getMessage().contains("Duplicate") || e.getMessage().contains("duplicate")) {
                System.out.println("No se pudo insertar el profesor: DNI duplicado.");
            } else {
                System.out.println("Error al insertar profesor.");
            }
        }
    }

    @Override
    public Profesor obtenerProfesorPorDNI(String dni) {
        String sql = "Select * from t_profesor where dni = ?";

        try(Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(sql)){

            ps.setString(1, dni);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Profesor profesor = new Profesor();
                    profesor.setApellidos(rs.getString("apellidos"));
                    profesor.setDni(rs.getString("dni"));
                    profesor.setNombre(rs.getString("nombre"));
                    profesor.setTelefono(rs.getString("telefono"));
                    return profesor;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al obtener profesor.");

        }
        return null;
    }

    @Override
    public int eliminarProfesorPorDNI(String dni) {
        String sql = "Delete from t_profesor where dni = ?";

        try(Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1, dni);
            int filasAfectadas= ps.executeUpdate();
            System.out.println("Eliminado correctamente el profesor con dni: "+ dni);
            return filasAfectadas;

        }catch (SQLException e) {
            System.out.println("Error al eliminar profesor.");
            return 0;
        }
    }

    @Override
    public List<Profesor> listarProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "select * from t_profesor";

        try(Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(sql)){

            while (result.next()) {
                Profesor profesor = new Profesor();
                profesor.setApellidos(result.getString("apellidos"));
                profesor.setDni(result.getString("dni"));
                profesor.setNombre(result.getString("nombre"));
                profesor.setTelefono(result.getString("telefono"));
                profesores.add(profesor);
            }

        }catch (SQLException e) {
            System.out.println("Error al listar profesores.");
        }
        return profesores;
    }
}
