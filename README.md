# ModeloDatos-ParqueaderoDB

## 1. Nombre del Proyecto

**ModeloDatos-ParqueaderoDB**

Aplicación de escritorio en Java (Swing) que simula el sistema CRUD de un parqueadero: cuando un vehículo llega se registra su placa, propietario y teléfono; al momento de la entrada se crea un registro con hora de ingreso, y al salir se liquida el cobro calculado según el tiempo parqueado. El proyecto tuvo como objetivo aplicar en un contexto real el proceso CRUD (Crear, Leer, Actualizar, Eliminar) sobre una base de datos relacional, usando MySQL sobre un servidor local (XAMPP).

## 2. Características

- Registro de vehículos (placa, propietario, teléfono) con validación de campos vacíos.
- Registro de entrada de vehículos, evitando duplicar el ingreso de un vehículo ya parqueado.
- Liquidación de salida: calcula automáticamente los minutos parqueados y el valor a pagar (tarifa fija por minuto), y actualiza el estado del registro a "salido".
- Actualización de datos del propietario y teléfono de un vehículo ya registrado.
- Eliminación de un vehículo junto con sus registros de entrada/salida y cobros asociados (elimina primero las tablas dependientes para respetar las llaves foráneas).
- Panel de consola en la interfaz que muestra mensajes de estado de cada operación (creado, actualizado, eliminado, errores, etc.).
- Conexión a base de datos MySQL mediante JDBC, con métodos genéricos de `SELECT` y `UPDATE`/`INSERT`/`DELETE` centralizados en una clase de acceso a datos.

## 3. Tecnologías Utilizadas

1. **Java 20** — Lenguaje principal de la aplicación.
2. **Swing** — Interfaz gráfica de escritorio (paneles, botones, campos de texto).
3. **JDBC** — Conexión y ejecución de consultas SQL desde Java.
4. **MySQL** (driver `mysql-connector-java 5.1.23`) — Motor de base de datos relacional.
5. **XAMPP** — Servidor local para levantar MySQL/phpMyAdmin en el entorno de desarrollo.
6. **NetBeans + Apache Ant** — IDE y sistema de construcción del proyecto.

## 4. Instalación

### Requisitos Previos

1. **Java JDK 20** instalado.
2. **XAMPP** (o cualquier servidor MySQL) con el módulo de MySQL activo.
3. **NetBeans** (recomendado, ya que el proyecto trae su configuración de Ant/NetBeans lista para abrir).
4. **Git** para clonar el repositorio.

### Pasos de Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/juandiegogalindo/ModeloDatos-ParqueaderoDB.git
```

2. Crear la base de datos `Parqueadero` en MySQL con las tablas que usa la aplicación:
```sql
CREATE DATABASE Parqueadero;
USE Parqueadero;

CREATE TABLE Vehiculo (
    placa VARCHAR(10) PRIMARY KEY,
    propietario VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE Registro (
    idRegistro INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10),
    fecha DATE,
    horaEntrada VARCHAR(10),
    horaSalida VARCHAR(10),
    estado VARCHAR(20),
    FOREIGN KEY (placa) REFERENCES Vehiculo(placa)
);

CREATE TABLE Cobro (
    idCobro INT AUTO_INCREMENT PRIMARY KEY,
    idRegistro INT,
    minutosTotales INT,
    valorCalculado FLOAT,
    valorFinal FLOAT,
    fechaCobro DATE,
    FOREIGN KEY (idRegistro) REFERENCES Registro(idRegistro)
);
```

3. Revisar la configuración de conexión en `src/mundo/MySql.java` (usuario `root`, sin contraseña, `localhost:3306`) y ajustarla si tu instalación de XAMPP usa credenciales distintas.

4. Abrir el proyecto en NetBeans (usa Ant, así que también puede compilarse con `ant build` desde la raíz del proyecto).

5. Ejecutar la clase principal `interfaz.InterfazApp`.

## 5. Estructura del Proyecto

```
ModeloDatos-ParqueaderoDB/
├── driver/
│   └── mysql-connector-java-5.1.23-bin.jar   # Driver JDBC de MySQL
├── script/
│   └── UnoAMuchos.sql                        # Script de otro ejercicio (no usado por esta app)
├── src/
│   ├── controlador/
│   │   └── Controlador.java                  # Intermediario entre la interfaz y el acceso a datos
│   ├── interfaz/
│   │   ├── InterfazApp.java                  # Ventana principal (JFrame) y punto de entrada (main)
│   │   ├── PanelCrud.java                    # Selector de operación (Create/Read/Update/Delete)
│   │   ├── PanelConsole.java                 # Consola de mensajes de estado
│   │   └── PanelVehicule.java                # Formulario y lógica CRUD del vehículo
│   └── mundo/
│       └── MySql.java                        # Conexión JDBC y ejecución de sentencias SQL
├── build.xml                                  # Script de construcción con Apache Ant
└── manifest.mf
```

## 6. Fundamento Teórico

- **Arquitectura por capas:** el proyecto separa la interfaz (`interfaz`), el controlador (`controlador`) y el acceso a datos (`mundo`), de forma que `PanelVehicule` nunca habla directo con JDBC, sino a través de `Controlador`, que a su vez delega en `MySql`.
- **JDBC (Java Database Connectivity):** `MySql.java` usa `DriverManager.getConnection(...)` para abrir la conexión y `Statement` para ejecutar tanto consultas (`executeQuery`, en el método `select`) como sentencias de modificación (`executeUpdate`, en el método `update`), que es el mecanismo estándar de Java para hablar con una base de datos relacional.
- **CRUD sobre un modelo relacional:** cada operación del panel (Create, Read, Update, Delete) arma una sentencia SQL como texto y la envía a través del controlador, ilustrando cómo una interfaz gráfica se traduce en operaciones sobre tablas relacionadas por llaves foráneas (`Vehiculo` → `Registro` → `Cobro`).
- **Integridad referencial:** al eliminar un vehículo, la aplicación borra primero los `Cobro`, luego los `Registro` y finalmente el `Vehiculo`, respetando el orden que exigen las llaves foráneas para no violar restricciones de integridad.

## 8. Autor

**Juan Diego Galindo**
Estudiante de Ingeniería de Sistemas - Sexto Semestre
 
- GitHub: [@juandiegogalindo](https://github.com/juandiegogalindo)
- LinkedIn: [Juan Diego Galindo - Full Stack](https://linkedin.com/in/jdgalindo6)
