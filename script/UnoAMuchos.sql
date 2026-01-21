DROP DATABASE IF EXISTS UnoAMuchos;
CREATE DATABASE UnoAMuchos;
USE UnoAMuchos;

/*
 Relación uno a muchos - Relacion no-identificatoria
 */
CREATE TABLE TipoProducto(
 ID_CodTipo int AUTO_INCREMENT PRIMARY KEY,
 nombreTipo varchar(30)
);

CREATE TABLE Productos(
 CodProducto char(4) PRIMARY KEY,
 descripcion varchar(20),
 precioCompra float,
 precioVenta float,
 ID_CodTipo int,
 FOREIGN KEY (ID_CodTipo) REFERENCES TipoProducto(ID_CodTipo) 
);
