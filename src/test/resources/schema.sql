DROP TABLE IF EXISTS propietario;
DROP TABLE IF EXISTS red_social;
DROP TABLE IF EXISTS servicio;
DROP TABLE IF EXISTS habilidad;
DROP TABLE IF EXISTS proyecto;
DROP TABLE IF EXISTS experiencia_laboral;
DROP TABLE IF EXISTS educacion;

CREATE TABLE propietario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    descripcion VARCHAR(2000) NOT NULL,
    ubicacion VARCHAR(255),
    correo VARCHAR(255),
    telefono VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    texto_soy VARCHAR(255) NOT NULL,
    texto_quiero VARCHAR(255) NOT NULL,
    texto_hago VARCHAR(255) NOT NULL
);

CREATE TABLE red_social (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    enlace VARCHAR(255) NOT NULL,
    icono VARCHAR(255) NOT NULL,
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

CREATE TABLE servicio (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    icono VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

CREATE TABLE habilidad (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    porcentaje INT NOT NULL,
    imagen VARCHAR(255) NOT NULL,
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

CREATE TABLE proyecto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(2000) NOT NULL,
    imagen VARCHAR(255) NOT NULL,
    enlace VARCHAR(255),
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

CREATE TABLE experiencia_laboral (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    puesto VARCHAR(255) NOT NULL,
    empresa VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME,
    icono VARCHAR(255) NOT NULL,
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

CREATE TABLE educacion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    lugar VARCHAR(255) NOT NULL,
    descripcion VARCHAR(2000) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME,
    icono VARCHAR(255) NOT NULL,
    id_propietario BIGINT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);

