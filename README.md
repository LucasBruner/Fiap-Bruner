# LunchTech - TechChallenge FIAP

## Sobre o Projeto
O Tech Challenge é o projeto da fase que englobará os conhecimentos obtidos em todas as disciplinas da fase. Esta é uma atividade que foi desenvolvida em grupo.
Este projeto é, inicialmente, uma API para gerenciamento de usuários, desenvolvida por estudantes da FIAP. Ele permite criar, listar, atualizar e excluir informações de usuários, além de possuir validação de login e troca de senha.
Como objetivo final, o projeto deve ser um sistema robusto que permita a restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar informações, deixar avaliações e fazer pedidos online. 

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JDBC
- Maven
- MySQL
- Docker e Docker Compose
- Swagger
- Postman

## Como Configurar
### Pré-requisitos
- Java JDK 24
- Maven
- MySQL 
- Docker
- Postman

### Instalação
1. Clone o repositório: `git clone https://github.com/LucasBruner/Fiap-Bruner.git`
2. Instale as dependências e rodar o projeto com o docker, conforme passo a passo do próximo tópico:

### Docker
* #### Para utilizar a aplicação via docker é necessário gerar o `.jar` da aplicação. Para isso faça os seguintes passos:
1. Acessar o diretório `LunchTech/docker`
2. Rodar o comando `docker-compose up` (caso queira acompanhar os logs) ou `docker-compose up -d`
3. A aplicação estará rodando na porta http://localhost:8080

* ####  Caso queira rodar a aplicação local e utilizar o banco via docker:
1. Acessar o diretório `LunchTech/docker`
2. Rodar o comando `docker compose -f docker-compose-mysql.yml up` (caso queira acompanhar os logs) ou `docker compose -f docker-compose-mysql.yml up -d`
3. Iniciar a aplicação manualmente ou via IDE

## Documentação da API (Swagger)
- Após iniciar o projeto, acesse a documentação Swagger em: http://localhost:8080/swagger-ui/index.html
- O controle de versionamento também pode ser visualizado pela documentação no Swagger. Para melhor entendimento, leia o exemplo: https://dzone.com/articles/versioning-rest-api-with-spring-boot-and-swagger

## Exemplos de Uso

Para verificar se a aplicação está rodando corretamente e ter acesso aos endpoints, utilize as Collections no Postman, conforme os seguintes passos:
1. Salve num arquivo local o conteúdo do arquivo `collections/Lunchtech.postman_collection.json`
2. Abra o Postman via desktop ou pela web (https://www.postman.com/)
3. Na aba "Collections", clique na opção "Import" e selecione o arquivo `.json` salvo no primeiro passo
4. Cada endpoint possui testes válidos e inválidos que já estão prontos para serem executados!

## Como Contribuir
Contribuições são sempre bem-vindas! Veja como:

1. Fork o projeto
2. Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Contato
- Lucas Bruner - lucasbrunerbruner@gmail.com
- Brenda Bernat - brendalouisebernat@gmail.com

## Licença
Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.
