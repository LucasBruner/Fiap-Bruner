CREATE TABLE IF NOT EXISTS USUARIO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    login VARCHAR(255),
    senha VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    data_atualizacao DATE,
    rua VARCHAR(255),
    cep INT,
    numero INT,
    cidade VARCHAR(255),
    tipo_usuario VARCHAR(255)
);