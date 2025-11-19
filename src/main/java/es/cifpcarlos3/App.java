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

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        profesorDAO profesorDAO = new profesorDAOImpl();

        int opcion;
        do{
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
                case 1:
                    List<Modulo> modulos = cargarModulos();
                    for(Modulo mod : modulos) {
                        Profesor p = profesorDAO.obtenerProfesorPorDNI(mod.getDni());

                        System.out.print("Módulo: " + mod.getNombreModulo() + " (" + mod.getHoras() + "h)"
                                + " | DNI profe: " + mod.getDni() + " | Profesor: ");

                        if (p != null) {
                            System.out.println(p.getNombre() + " " + p.getApellidos());
                        } else {
                            System.out.println("FALTA EN BD");
                        }
                    }

                    break;
                case 2:
                    System.out.println("Apellidos:");
                    String apellidos = sc.nextLine();
                    System.out.println("DNI:");
                    String dni = sc.nextLine();
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    System.out.println("teléfono:");
                    String telefono = sc.nextLine();

                    Profesor nuevo = new Profesor();
                    nuevo.setApellidos(apellidos);
                    nuevo.setDni(dni);
                    nuevo.setNombre(nombre);
                    nuevo.setTelefono(telefono);

                    profesorDAO.insertarProfesor(nuevo);
                    break;
                case 3:
                    System.out.println("Introduzca el DNI del profesor a eliminar: ");
                    String DNI = sc.nextLine();
                    profesorDAO.eliminarProfesorPorDNI(DNI);
                    break;
                case 4:
                        for(Profesor p: profesorDAO.listarProfesores()){
                            System.out.println(p);
                        }
                    break;
                case 0:
                    System.out.println("Saliendo de la app...");
            }
        }while(opcion != 0);
    }

        public static List<Modulo> cargarModulos(){
            List<Modulo> modulos = new ArrayList<>();
            File archivo = Path.of("datos_modulos.txt").toFile();

            try(BufferedReader br = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))){
                String linea;

                while((linea = br.readLine()) != null){
                    String[] partes = linea.split(",");
                    if(partes.length == 3){
                        String nombre = partes[0].trim();
                        int horas = Integer.parseInt(partes[1].trim());
                        String dni = partes[2].trim();
                            modulos.add(new Modulo(nombre, horas, dni));

                    }
                }
            }catch (Exception e){
                System.out.println("Error" + e.getMessage());
            }
            return modulos;
        }
    }
