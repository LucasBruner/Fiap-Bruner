# LunchTech - TechChallenge FIAP

## Sobre o Projeto
O Tech Challenge é o projeto da fase que englobará os conhecimentos obtidos em todas as disciplinas da fase. Esta é uma atividade que foi desenvolvida em grupo.
Este projeto é, inicialmente, uma API para gerenciamento de usuários, desenvolvida por estudantes da FIAP. Ele permite criar, listar, atualizar e excluir informações de usuários, além de possuir validação de login e troca de senha.
Como objetivo final, o projeto deve ser um sistema robusto que permita a restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar informações, deixar avaliações e fazer pedidos online. 

## Tecnologias Utilizadas
- Java
- Spring
- Spring Data JDBC
- MySQL
- Docker
- Lombok

## Como Configurar
### Pré-requisitos
- Java JDK 24
- MySQL 
- Maven

### Instalação
1. Clone o repositório:

git clone https://github.com/LucasBruner/Fiap-Bruner.git

2. Instale as dependências:

cd fiap-bruner
mvn clean package

A API estará disponível em `http://localhost:8080`.

## Como Contribuir
Contribuições são sempre bem-vindas! Veja como:

1. Fork o projeto
2. Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request


### Exemplos de Uso
Para buscar usuários por nome:

GET /usuarios/{name}

# Docker
* ### Para utilizar a aplicação via docker é necessário gerar o .jar da aplicação para isso faça os seguintes passos:
#### 1. mvn clean package ou  ./mvnw clean package -DskipTests
#### 2. Acessar o diretório LunchTech/docker
#### 3. Rodar o comando docker-compose up(caso queira acompanhar os logs) ou docker-compose up -d
#### 4. A aplicação estará rodando na porta http://localhost:8080

* ###  Caso queira rodar a aplicação local e utilizar o banco via docker:
#### 1. Acessar o diretório LunchTech/docker
#### 2. Rodar o comando docker compose -f docker-compose-mysql.yml up(caso queira acompanhar os logs) ou docker compose -f docker-compose-mysql.yml up -d
#### 3. Iniciar a aplicação manualmente ou via IDE.

## Contato
Lucas Bruner - lucasbrunerbruner@gmail.com
Brenda Bernat - brendalouisebernat@gmail.com
Fernanda Beber - nandaa_k@hotmail.com

## Licença
Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.
