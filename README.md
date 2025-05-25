# Desafio Técnico NgBilling

## Stack utilizada
- Java 17
- Spring Boot 3.5.0
- Postman
- JMeter

## Sobre o projeto
Este projeto consiste em uma API RESTful para gerenciamento de contas e transações bancárias, o projeto conta com dois endpoints: **/conta** e o **/transacao**. O endpoint **/conta** cria a conta  e ja fornece o saldo da mesma. O endpoint **/transacao** sera responsável por realizar diversas transações.

Há três formas de tyransações disponiveis: débito, credito e pix, cada transação recebe uma taxa especifica e essa taxa é acrescida na transação, ou seja, sera descontado o valor transferido + taxa.
- **Taxa Débito:** 3% sobre a operação.
- **Taxa Crédito:** 5% sobre a operação.
- **Taxa Pix:** sem custo.

## Como rodar o projeto

1. **Clone o repositório:**

- git clone https://github.com/Jose1-0/NgBilling-DesafioTecnico.git
- importe o projeto para a IDE uutilizada.

2. **Configuração do banco de dados:**

O projeto utiliza o banco de dados H2 em memória, as configurações do banco estão no `application.properties`. Ao subir o projeto acesse a URL: **http://localhost:8080/h2-console**.
- JDBC URL: jdbc:h2:mem:banco-teste
- User Name: sa
- **Após preencher esses dois campos clique em Connect**

## Estrutura do projeto

- `src/main/java/com/desafio/ngbilling/`
  - `controller/` — Controllers REST
  - `service/` — Regras de negócio
  - `dto/` — Data Transfer Objects
  - `exception/` — Tratamento de exceções
  - `model/` — Entidades do domínio
  - `repository/` — Interfaces de persistência
 
## Endpoints principais

- `POST /contas` — Criação de conta
- `POST /transacoes` — Realização de transação
- `GET /contas/{id}` — Consulta de conta

## Observações
- O projeto conta com uma pasta chamada Docs, dentro dessa está armazenada a **colection para usar a API** e as evidências de testes executados tanto via Postman quanto via JMeter.







