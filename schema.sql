-- crear la base de datos
CREATE DATABASE IF NOT EXISTS peliculas_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- crear usuario
CREATE USER IF NOT EXISTS 'peliculas_user'@'localhost' 
IDENTIFIED BY 'peliculas_pass';

-- dar todos los permisos de la base de datos al usuario
GRANT ALL PRIVILEGES ON peliculas_db.* TO 'peliculas_user'@'localhost';

-- aplicar cambios
FLUSH PRIVILEGES;

USE peliculas_db;

-- tabla peliculas
CREATE TABLE IF NOT EXISTS peliculas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    director VARCHAR(100),
    anio INT,
    genero VARCHAR(50),
    INDEX idx_titulo (titulo),
    INDEX idx_director (director),
    INDEX idx_anio (anio)
);

-- datos iniciales 
INSERT INTO peliculas (titulo, director, anio, genero) VALUES
('El Padrino', 'Francis Ford Coppola', 1972, 'Drama'),
('Pulp Fiction', 'Quentin Tarantino', 1994, 'Crimen'),
('El Senor de los Anillos', 'Peter Jackson', 2001, 'Fantasia'),
('Matrix', 'Hermanos Wachowski', 1999, 'Ciencia Ficcion'),
('Forrest Gump', 'Robert Zemeckis', 1994, 'Drama');
