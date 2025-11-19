package es.cifpcarlos3.dao.impl;

import es.cifpcarlos3.dao.profesorDAO;
import es.cifpcarlos3.model.Profesor;
import es.cifpcarlos3.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implementar los métodos de la interfaz profesorDAO que contiene la lógica y entra en la base de datos.
 * Utiliza JDBC y el metodo DatabaseConnection.getConnection() para obtener conexiones a MariaDB.
 */
public class profesorDAOImpl implements profesorDAO {


//Metodo para insertar a un nuevo Profesor:

    @Override
    public void insertarProfesor(Profesor profesor) {
        String sql = "Insert into t_profesor (apellidos, dni, nombre, telefono) values (?, ?, ?, ?)";   //Primero la consulta sql.

        try(Connection con = DatabaseConnection.getConnection();  //Intento establecer mi conexión
            PreparedStatement ps = con.prepareStatement(sql)){ //PreparedStatement porque utilizo parámetros.
            ps.setString(1, profesor.getApellidos());   //Sustituyo ? por el dato que corresponde.
            ps.setString(2, profesor.getDni());
            ps.setString(3, profesor.getNombre());
            ps.setString(4, profesor.getTelefono());
            ps.executeUpdate(); //Ejecuto la consulta.
            System.out.println("Profesor insertado exitosamente");

        }catch (SQLException e) {
            //Aqui compruebo que el DNI no esté duplicado, ya que si lo está saltará un error.
            if (e.getMessage().contains("Duplicate") || e.getMessage().contains("duplicate")) {
                System.out.println("No se pudo insertar el profesor: DNI duplicado.");
            } else {
                System.out.println("Error al insertar profesor.");
            }
        }
    }

//Metodo para obtener un profesor a partir de un DNI
    @Override
    public Profesor obtenerProfesorPorDNI(String dni) {
        String sql = "Select * from t_profesor where dni = ?"; //Primero la consulta sql.

        try(Connection con = DatabaseConnection.getConnection(); //Intento establecer mi conexión
        PreparedStatement ps= con.prepareStatement(sql)){ //PreparedStatement porque utilizo parámetros.

            ps.setString(1, dni);  //Sustituyo ? por el dato que corresponde.

            try(ResultSet rs = ps.executeQuery()){ //Intento ejecutar la consulta y buscar un resultado.
        //Si existe el profesor, construyo el objeto con los valores del ResultSet
                if(rs.next()){
                    Profesor profesor = new Profesor();
                    profesor.setApellidos(rs.getString("apellidos"));
                    profesor.setDni(rs.getString("dni"));
                    profesor.setNombre(rs.getString("nombre"));
                    profesor.setTelefono(rs.getString("telefono"));
                    return profesor; //Una vez construido lo devolvemos.
                }
            }
            //Si se produce un error, salta el siguiente mensaje:
        }catch (SQLException e) {
            System.out.println("Error al obtener profesor.");

        }
        return null; //Si finalmente no existe ese DNI en la base de datos, nos devuelve un null.
    }



    @Override
    public int eliminarProfesorPorDNI(String dni) {
        String sql = "Delete from t_profesor where dni = ?"; //Primero la consulta sql.

        try(Connection con = DatabaseConnection.getConnection(); //Intento establecer mi conexión
        PreparedStatement ps= con.prepareStatement(sql)){ //PreparedStatement porque utilizo parámetros.
            ps.setString(1, dni); //Sustituyo ? por el dato que corresponde.
            int filasAfectadas= ps.executeUpdate(); // executeUpdate devuelve cuántas filas se han modificado
            System.out.println("Eliminado correctamente el profesor con dni: "+ dni); //Imprimo este mensaje como trazabilidad de que dni se ha eliminado.
            return filasAfectadas; //Devuelvo cuantas filas se han eliminado.

        }catch (SQLException e) {
            System.out.println("Error al eliminar profesor.");
            return 0; //Si no ha sido posible eliminar devuelvo 0.
        }
    }

//Metodo para listar todos los profesores.

    @Override
    public List<Profesor> listarProfesores() {
        List<Profesor> profesores = new ArrayList<>(); //Creo un objeto lista donde voy a meter a todos los profesores de la base de datos.
        String sql = "select * from t_profesor"; //Consulta sql.

        try(Connection con = DatabaseConnection.getConnection(); //Intento establecer mi conexión
            Statement st = con.createStatement(); //Statement porque no utilizo parámetros, solo quiero mostrar lo que se encuentre en la consulta. No introduzco nada.
            ResultSet result = st.executeQuery(sql)){

            while (result.next()) { //Recorro cada fila del ResultSet
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
        return profesores; //Devuelvo mi lista de profesores.
    }
}
