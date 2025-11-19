package es.cifpcarlos3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase que gestiona la conexión con la base de datos mariadb
//Se utiliza en profesorDAO para acceder a la base de datos.
public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/fp_profesores";  //URL de conexión a la BD
    private static final String USER = "root";  //Usuario para la conexión
    private static final String PASS = "";      //Contraseña vacía, así la tengo configurada.


    //Si la conexión falla lanzo una SQLExcepción.
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
