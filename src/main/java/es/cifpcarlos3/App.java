package es.cifpcarlos3;


import es.cifpcarlos3.dao.impl.profesorDAOImpl;
import es.cifpcarlos3.dao.profesorDAO;
import es.cifpcarlos3.model.Modulo;
import es.cifpcarlos3.model.Profesor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación.
 * Contiene un menú que permite realizar los metodos creados anteriormente.
 */
public class App {
    public static void main(String[] args) {

//Clase Scanner para poder meter los datos que se me pidan, y seleccionar la opción que quiero.
        Scanner sc = new Scanner(System.in);
        profesorDAO profesorDAO = new profesorDAOImpl();  //DAO que gestiona todas las operaciones.

        int opcion;
        do{                             //Menú principal que se va a repetir hasta que pulse 0
            System.out.println("""
                    Elige una opción:
                      1) Listar módulos con profesor
                      2) Añadir profesor
                      3) Eliminar profesor por DNI
                      4) Listar todos los profesores
                      0) Salir
                      Opción:""");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:   // Opción 1: Listar los módulos junto con los profesores.
                    List<Modulo> modulos = cargarModulos();  //Creo una lista en la que voy a meter lo que se encuentre en el metodo cargarModulos.(Aquí leo todos los modulos del TXT)
                    for(Modulo mod : modulos) { //Para cada modulo que haya, busco al profesor por su dni.
                        Profesor p = profesorDAO.obtenerProfesorPorDNI(mod.getDni());
                            //Lo imprimo con el siguiente formato dado:
                        System.out.print("Módulo: " + mod.getNombreModulo() + " (" + mod.getHoras() + "h)"
                                + " | DNI profe: " + mod.getDni() + " | Profesor: ");
                    //Si el profesor existe, se muestra el nombre y los apellidos.
                        if (p != null) {
                            System.out.println(p.getNombre() + " " + p.getApellidos());
                        } else { //Si no existe le pongo lo siguiente: FALTA EN BD.
                            System.out.println("FALTA EN BD");
                        }
                    }

                    break;

                case 2:   // Opción 2: Insertar profesor en BD.
                    //Me pide los datos por consola, y los voy metiendo gracias a la clase escáner.
                    System.out.println("Apellidos:");
                    String apellidos = sc.nextLine();
                    System.out.println("DNI:");
                    String dni = sc.nextLine();
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    System.out.println("teléfono:");
                    String telefono = sc.nextLine();

                    Profesor nuevo = new Profesor();  // Creamos un objeto profesor con los datos introducidos.
                    nuevo.setApellidos(apellidos);
                    nuevo.setDni(dni);
                    nuevo.setNombre(nombre);
                    nuevo.setTelefono(telefono);

                    profesorDAO.insertarProfesor(nuevo);   //Inserto el profesor mediante la función.
                    break;
                case 3: //Opción 3: Eliminar un profesor por DNI
                    System.out.println("Introduzca el DNI del profesor a eliminar: "); //Solicito el DNI del profe a eliminar.
                    String DNI = sc.nextLine();
                    profesorDAO.eliminarProfesorPorDNI(DNI); //Elimino el profesor mediante la función.
                    break;
                case 4: //Opción 4: Listar todos los profesores almacenados en la BD.
                        for(Profesor p: profesorDAO.listarProfesores()){
                            System.out.println(p);
                        }
                    break;
                case 0:
                    System.out.println("Saliendo de la app...");
            }
        }while(opcion != 0);
    }

    //Metodo para cargar los modulos desde el arhivon TXT

        public static List<Modulo> cargarModulos(){
        //Creo una lista donde voy a guardar los modulos que encuentre en el TXT.
            List<Modulo> modulos = new ArrayList<>();
        //Ruta del archivo.
            File archivo = Path.of("datos_modulos.txt").toFile();
        //Intento leer el TXT.
            try(BufferedReader br = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))){
                String linea;

                while((linea = br.readLine()) != null){
                    String[] partes = linea.split(","); //Separo las lineas por comas.
                    if(partes.length == 3){ //Si encuentro 3 partes, le asigno los atributos.
                        String nombre = partes[0].trim();
                        int horas = Integer.parseInt(partes[1].trim());
                        String dni = partes[2].trim();
                            modulos.add(new Modulo(nombre, horas, dni));  //Creo el modulo y lo añado a la lista.

                    }
                }
            }catch (Exception e){
                System.out.println("Error" + e.getMessage());
            }
            return modulos; //Me devuelve la lista de modulos.
        }
    }
