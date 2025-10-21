CREATE TABLE USUARIO (
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

insert into USUARIO (nome, login, senha, email, data_atualizacao, rua, cep, numero, cidade, tipo_usuario)
values ('Brenda', 'blbernat', 'password', 'brenda@fiap.com', '2025-10-15', 'Rua 2 de Setembro', 89055555, 111, 'Blumenau', 'USUARIO');
