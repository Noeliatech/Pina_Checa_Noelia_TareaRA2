# 📚 Proyecto Acceso a Datos — Gestión de Profesores y Módulos (DAM 2º)

Este proyecto forma parte de la asignatura **Acceso a Datos** del ciclo formativo **2º DAM**.  
El objetivo principal es realizar una aplicación Java que **combine información procedente de un archivo TXT** con datos almacenados en una **base de datos MariaDB**, utilizando **JDBC** y el **patrón DAO**.

---

## 📌 Funcionalidades

- Leer módulos desde un archivo TXT (`datos_modulos.txt`)
- Consultar profesores desde la base de datos
- Relacionar módulos (TXT) con profesores (BD)
- Insertar profesores
- Eliminar profesores por DNI
- Listar todos los profesores

## 🛠️ Tecnologías usadas

- Java 17+
- JDBC
- MariaDB / MySQL
- Maven
- Lombok
- Patrón DAO

## 📁 Estructura

- `App.java` → Menú principal
- `model/` → Clases `Profesor` y `Modulo`
- `dao/` → Interfaz `profesorDAO`
- `dao.impl/` → Implementación JDBC (`profesorDAOImpl`)
- `util/DatabaseConnection.java` → Conexión a la BD
- `datos_modulos.txt` → Archivo de módulos

## 📄 Base de datos
- La base de datos contiene la tabla: fp_profesores

## 🧮 Funcionamiento del menú principal
### 🔹 Opción 1 – Listar módulos con profesor
- Lee el archivo TXT
- Busca al profesor según el DNI
- Si existe → muestra su nombre
- Si no existe → “FALTA EN BD”

### 🔹 Opción 2 – Añadir profesor
Solicita por consola apellidos, DNI, nombre y teléfono.

### 🔹 Opción 3 – Eliminar profesor
Elimina un profesor mediante su DNI.

### 🔹 Opción 4 – Listar profesores
Muestra todas las entradas de la tabla `t_profesor`.

### 🔹 Opción 0 – Salida del programa
Una vez seleccionada la opción 0, salimos del bucle.

## ‍💻 Autora
Noelia Piña Checa
