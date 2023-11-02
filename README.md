# Projeto TodoList com Spring Boot

![Rocketseat](https://img.shields.io/badge/Curso%20de%20Java-Rocketseat-blue)
![Snarloff](https://img.shields.io/badge/Aluno-Snarloff-red)

![TechThrowBack](https://github.com/Snarloff/todolist-java/assets/46792575/318274d5-9537-450e-8604-ffece66c7d53)

Este é um projeto de uma REST API de lista de tarefas (TodoList) desenvolvida com Spring Boot e Maven. A aplicação utiliza o Spring JPA para interagir com o banco de dados H2, que armazena os dados em memória.

## Visão Geral

Este projeto tem como objetivo criar uma API para gerenciar tarefas de uma lista de afazeres (TodoList). As principais funcionalidades incluem:

- Criação, leitura, atualização e exclusão de tarefas.
- Armazenamento dos dados em um banco de dados H2 em memória.
- Exposição de endpoints RESTful para manipulação de tarefas.

## Tecnologias Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot): Framework para desenvolvimento de aplicativos Java.
- [Maven](https://maven.apache.org/): Gerenciador de dependências e construção de projetos.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Parte do projeto Spring Data que facilita o acesso a bancos de dados relacionais.
- [H2 Database](https://www.h2database.com/): Banco de dados em memória para desenvolvimento e testes.

## Execução do Projeto

Certifique-se de ter o [Java](https://www.oracle.com/java/technologies/javase-downloads.html) e o [Maven](https://maven.apache.org/download.cgi) instalados em sua máquina. Em seguida, siga os passos abaixo:

1. Clone este repositório:

   ```shell
   git clone https://github.com/Snarloff/todolist-java.git
   ```

2. Navegue até o diretório do projeto:

   ```shell
   cd nome-do-repositorio
   ```

3. Execute a aplicação com Maven:

   ```shell
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

<!--## Documentação da API-->

<!--A documentação da API estará disponível em `http://localhost:8080/swagger-ui.html` após iniciar o aplicativo. Lá, você poderá explorar os endpoints e testar a API.-->

## Contribuições

Contribuições são bem-vindas! Se você deseja contribuir para este projeto, siga as diretrizes de contribuição e envie um pull request.

## Problemas e Sugestões

Se você encontrar algum problema ou tiver sugestões para melhorar este projeto, por favor, abra uma issue neste repositório.

Agradecemos à [Rocketseat](https://rocketseat.com.br/) por fornecer o conhecimento e os recursos para desenvolver este projeto.
