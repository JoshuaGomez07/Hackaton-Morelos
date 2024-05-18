CREATE DATABASE DonaSangre;
CREATE TABLE usuarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  INDEX (email),
  INDEX (usuario_id)
);

CREATE TABLE direcciones (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  calle VARCHAR(100) NOT NULL,
  numero INT NOT NULL,
  ciudad VARCHAR(50) NOT NULL,
  codigo_postal VARCHAR(10) NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  INDEX (usuario_id),
  INDEX (ciudad)
);

