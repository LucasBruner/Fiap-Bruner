CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    ano INT,
    cor VARCHAR(255),
    valor_diaria decimal(10,2)
);

CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    telefone VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE aluguel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    veiculo_id BIGINT,
    veiculo_modelo varchar(255),
    pessoa_cpf VARCHAR(255),
    pessoa_nome VARCHAR(255),
    data_inicio date,
    data_fim date,
    valor_total DECIMAL(10,2),
    foreign key (pessoa_id) references pessoa(id),
    foreign key (veiculo_id) references veiculos(id)
);

insert into veiculos (marca, modelo, placa, ano, cor, valor_diaria) values ('PEUGEOT', '206', 'MBR-8819', 2006, 'Azul', 88.90);
insert into pessoa (nome, cpf, telefone, email) values ('João Pessoa', '255.534.552-24', '47596741353', 'joao@teste.com.br');
insert into aluguel (pessoa_Id, veiculo_id, data_inicio, data_fim, valor_total) values (1, 1, '2024-10-1', '2024-10-15', 1550.00);