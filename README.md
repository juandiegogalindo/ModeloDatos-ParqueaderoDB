# EjercicioBaseDatos - Parqueadero

## 1. Nombre del Proyecto

**EjercicioBaseDatos - Parqueadero**

Proyecto académico desarrollado en **Java** que implementa una aplicación de gestión de un parqueadero apoyada en una base de datos relacional (por ejemplo MySQL), con el objetivo de registrar y administrar la entrada y salida de vehículos, así como los datos asociados. :contentReference[oaicite:0]{index=0}

## 2. Características

Este proyecto incluye:

- Integración de una aplicación en Java con una **base de datos relacional** para persistencia de información.
- Diagramas y scripts de base de datos para la creación de tablas y relaciones necesarias para gestionar el parqueadero.
- Implementación de operaciones de **CRUD** para registrar vehículos, clientes, entradas, salidas y otros datos pertinentes.
- Uso de JDBC u otro mecanismo de conexión para interactuar con la base de datos desde Java.
- Enfoque educativo que fortalece la comprensión de conceptos de bases de datos, normalización y programación orientada a datos. :contentReference[oaicite:1]{index=1}

## 3. Instalación

### Requisitos Previos

1. **Java JDK 8 o superior** instalado en el sistema.
2. **MySQL** u otro servidor de base de datos relacional instalado.
3. **Git** para clonar el repositorio.
4. IDE recomendado: **NetBeans**, **Eclipse** o **IntelliJ IDEA**.
5. Configurar usuario y contraseña de la base de datos.

### Pasos de Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/juandiegogalindo/EjercicioBaseDatos-Parqueadero.git
```

2. Crear la base de datos en tu servidor (por ejemplo, en MySQL):
```bash
CREATE DATABASE parqueadero;
USE parqueadero;
-- ejecutar scripts de creación de tablas incluidos en el repositorio
```

3. Configurar las credenciales de conexión en el código Java (URL, usuario, contraseña).

4. Abrir el proyecto en tu IDE.

5. Compilar y ejecutar la aplicación desde el IDE o mediante herramienta de construcción si aplica.

## 4. Tecnologías Utilizadas

1. Java — Lenguaje de programación principal para la lógica de la aplicación.
2. MySQL u otro gestor de base de datos relacional — Para persistencia de datos y consultas.
3. JDBC — API estándar para conectar Java con bases de datos.
4. Git / GitHub — Control de versiones y hospedaje del repositorio.
5. IDE Java — Entorno de desarrollo recomendado (NetBeans, IntelliJ, Eclipse).

## 5. Teoría del Juego en Base a la Programación

Un sistema de gestión de parqueadero con base de datos implica la interacción entre una aplicación y un modelo de datos estructurado, lo cual es un problema clásico en el desarrollo de software empresarial y académico.

Fundamentos Conceptuales

1. Modelo Relacional de Datos:
  - La base de datos se organiza en tablas, cada una representando entidades como vehículos, clientes, registros de entrada/salida y demás.
  - Cada tabla contiene atributos organizados en columnas y filas, y se establecen relaciones mediante claves primarias y foráneas para mantener integridad referencial.
2. Normalización:
  - Para evitar redundancia y asegurar consistencia de datos, se aplican principios de normalización que estructuran la base de datos en formas normales (1FN, 2FN, 3FN, etc.).
3. Conexión desde Java con JDBC:
  - La aplicación Java utiliza JDBC (Java Database Connectivity) para establecer una conexión con la base de datos, enviar consultas SQL y procesar resultados.
  - Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) se traducen a consultas SQL ejecutadas desde Java.
5. Integración entre Aplicación y Base de Datos:
  - La lógica de negocio en Java encapsula operaciones de acceso a datos, validación, gestión de errores y presentación de resultados, generando una arquitectura orientada a datos necesaria para un sistema de información real.

Este ejercicio permite consolidar conocimientos de programación orientada a datos, diseño de bases de datos y arquitectura cliente-servidor básica, competencias esenciales en ingeniería de software.

## 6 Imagen de Referencia:
<img width="865" height="874" alt="image" src="https://github.com/user-attachments/assets/d735a3c2-bac9-4263-b0a6-a7febd48dad0" />
