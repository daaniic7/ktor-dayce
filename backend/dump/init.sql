-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS dbUsuario;

-- Crear la tabla usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255),
    nombre VARCHAR(100),
    token VARCHAR(255)
);

-- Crear la tabla Avisos
CREATE TABLE IF NOT EXISTS Aviso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    direction VARCHAR(255) NOT NULL,
    date VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image VARCHAR(1000) NOT NULL
);

-- Insertar datos en la tabla Avisos
INSERT INTO Aviso (id, name, direction, date, description, image) VALUES
(1, 'Alberto', 'Calle Rueda 123', '2024-11-29', 'Lavadora no echa agua', 'https://es.tiching.com/uploads/contents/2011/10/13/48765_1318527387.jpg'),
(2, 'Juan', 'Av. Principal 456', '2024-11-30', 'Lavavajillas no enciende', 'https://cdn.wallapop.com/images/10420/ho/51/__/c10420p1068471206/i5235666411.jpg?pictureSize=W640'),
(3, 'Marcos', 'Plaza Central 789', '2024-12-01', 'Instalación de Nevera.', 'https://cdn0.uncomo.com/es/posts/9/2/1/por_que_mi_nevera_no_enfria_51129_600.jpg'),
(4, 'Alejandro', 'Calle Luna 321', '2024-12-02', 'Caldera no funciona', 'https://www.atuairesabadell.com/cache/8/5/3/3/0/8533094d17f26ad3161934b8b74b75280a05c370.jpg'),
(5, 'Santiago', 'Av. Sol 654', '2024-12-03', 'Campana de Gas no se lleva el humo', 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/...'),
(6, 'María', 'Barrio Nuevo 987', '2024-12-04', 'Vitrocerámica no calienta', 'https://ollerstocks.com/wp-content/uploads/2021/11/VITROCERAMICA-ELECTRICA-TEKA-INDUCCION-IR640-3-FUEGOS-1024x768.jpg'),
(7, 'Mario', 'Calle Vieja 741', '2024-12-05', 'Nevera no enfría', 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/...'),
(8, 'Carlos', 'Av. Verde 852', '2024-12-06', 'Revisión de Lavadora', 'https://live.staticflickr.com/5777/29981984200_8dd750dae7_b.jpg'),
(9, 'Jaime', 'Calle Roja 963', '2024-12-07', 'Garantía nevera', 'https://images.milanuncios.com/api/v1/ma-ad-media-pro/images/dad7471c-bb2d-4956-b2f9-5314043cbc07?rule=detail_640x480'),
(10, 'Luis', 'Plaza Amarilla 258', '2024-12-08', 'Congelador no enfría.', 'https://cdn.wallapop.com/images/10420/g1/6g/__/c10420p969440381/i4678753984.jpg?pictureSize=W640');

-- Insertar datos en la tabla usuario (corregir la duplicación de "password")
INSERT INTO Usuario (id, dni, email, password, nombre, token) VALUES
(1, '50643065D', 'dani@gmail.com', '$2a$10$xqtID2Reqw4h2Gb8mMOGaOpqD9f.TOTQKMHVirVj23lLCUw03PRIe', 'dani', ''),
(2, '51218769Z', 'marcos@gmail.com', '$2a$10$Ns8V/y1iP7sOkDb.kLyJS.vUW6N7.XWWxRAbvotClZTwypkLNb1/u', 'marcos', ''),
(3, '12345678E', 'copado@gmail.com', '$2a$10$6dHfX5YK0wXxXih93u8pseXEVQj/9MoD96hP0qPeHvP5rjA6z9Ya2', 'copado', ''),
(4, '45678912P', 'santi@gmail.com', '$2a$10$3ThqPjzBMT0gqJYd19pQ3uXNEseVhzQxs7l.1OTnau1Aw7Jm/AaF.', 'santi', ''),
(5, '78945612T', 'juangu@gmail.com', '$2a$10$U7RVcxFA4s2EZRX/ebG6NOcfOTAhYDbG8fG0dPiNi0PtIBHc7Jo96', 'juangu', ''),
(6, '65983214C', 'pedro@gmail.com', '$2a$10$CpFD1vI3N4fKlfCPKv6JS.buA62kFl1v18HLYzQyYklF8lgYMyCuq', 'pedro', ''),
(7, '42753698S', 'luis@gmail.com', '$2a$10$GpJwBlF7MXhbLx5N4XoLrOj8KfY3pVQbLOK0lfTYbALCJ4XfE6FVe', 'luis', ''),
(8, '98412653J', 'carlos@gmail.com', '$2a$10$vlPdRMDF3.eHZJGIC7uG.e2CdZ3Lsb/8pUmcvOQ/E7z.Gn8Mk9KNO', 'carlos', ''),
(9, '36251482U', 'juanjo@gmail.com', '$2a$10$QyE2hfbLfDzjAIU5Gh8ofe0EjHiLoxM1b6Db4mIQdUlYI9Tyslt4G', 'juanjo', '');
