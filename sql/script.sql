CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

-- Tabla de salas
CREATE TABLE sala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    capacidad INT,
    recursos TEXT
);

-- Tabla de empleados
CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    departamento VARCHAR(100)
);

-- Tabla de reservas
CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT,
    id_sala INT,
    fecha DATE,
    hora_inicio TIME,
    hora_fin TIME,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id),
    FOREIGN KEY (id_sala) REFERENCES sala(id)
);

-- Datos de prueba
INSERT INTO sala (nombre, capacidad, recursos) VALUES
('Sala A', 10, 'Proyector, Pizarra'),
('Sala B', 5, 'TV, WiFi');

INSERT INTO empleado (nombre, email, departamento) VALUES
('Ana Pérez', 'ana@empresa.com', 'Ventas'),
('Luis Gómez', 'luis@empresa.com', 'TI');
