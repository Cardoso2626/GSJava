## WorkClean - global solutions
📌 Sobre o Projeto

O WorkClean é uma aplicação desenvolvida em Java com Spring Boot, focada em fornecer um ambiente seguro, escalável e inteligente para gerenciamento de operações.
O sistema implementa autenticação via token Bearer, comunicação assíncrona usando RabbitMQ, integração com IA por meio do Ollama (executado em máquina virtual) e uma API REST robusta construída com Spring Web e JPA

O objetivo do projeto é demonstrar uma arquitetura moderna e profissional, integrando segurança, mensageria e inteligência artificial.

- LINK DO DEPLOY: 
- LINK DO VIDEO EXPLICATIVO:
- LINK DO PITCH: 

👥 Integrantes

- RM555160 	Pedro Cardoso
- RM557825	Heitor Ortega
- RM556496	Marcos Lourenço


## Tecnologias Utilizadas
- Java 21
- Gradle 
- Spring Boot

- Spring Web + JPA → Construção de endpoints REST

- Spring Security (Bearer Token)

- Proteção de rotas e filtros personalizados

- RabbitMQ (CloudAMQP compatível)

- Spring AI + Ollama (Conexão com modelo executado em máquina virtual na Azure)
- Exception Handler (tratamento de erros)

- Deploy da aplicação (render)
  
- CACHE para otimização da API

- Bean validation

- Paginação

## INFRAESTRUTURA: 

Spring Security: controla acesso às rotas com tokens JWT.

RabbitMQ: processa tarefas de forma assíncrona (producers e consumers).

Ollama: provê análises inteligentes e geração de conteúdo via IA.

REST API: disponibiliza endpoints bem estruturados para consumo externo.

## Mensageria com RabbitMQ

O projeto envia e consome mensagens usando filas RabbitMQ:

Producer envia mensagens para uma fila específica

Consumer recebe e processa de forma assíncrona

A integração funciona em ambiente cloud (CloudAMQP)

# Integração com IA (Ollama via Spring AI)

O sistema se conecta a um modelo Ollama executado em uma VM externa por meio de propriedades como:

spring.ai.ollama.base-url=http://IP_DA_VM:11434
spring.ai.ollama.chat.model=gemma3:1b

## Como rodar o projeto (LOCAL E DEPLOY)
## local
- Para rodar o projeto, é necessário, após a clonagem ou upload do zip (necessário extrair pasta do repositório), abrir o projeto em uma IDE, como o InteliJi, abrir as camadas src - main - java - br.com.fiap.gsjava (pacote)
- Clique no icone "CurrentFile" que está na esquerda do icone de run (canto superior direito indo para o meio)
- Clique em "Edit configurations" e clique em "+"
Clique em "Application", coloque um nome que você quer, onde está -cp selecione "GSJava.main", e onde está escrito "Main class", clique no icone de livro que fica um pouco mais a direita e selecione a primeira opção que aparecer
- Antes de rodar você precisa colocar algumas variáveis de ambientes, que são as variáveis de ambiente da conexão com o banco de dados, que são {db.user} e {db.passwd}, para modifica-las, clique em "modify options" e selecione a opção "Environment variables", após isso, essa opção aparecerá na sua tela, e você deve clicar no "livrinho" na direita para configura-la. Clique em + para adicionar uma variavel, coloque como "name" o "db.user", e o valor sendo "rm555160", e a mesma coisa com "db.passwd" mas o valor sendo "fiap25", após isso crie outra variavel sendo "CLOUDAMQP_URL" em name e no value coloque esta url "amqps://rkuerfga:xyh1AGjedLEvs3zCSDO2t-jUYc8Y1N76@leopard.lmq.cloudamqp.com/rkuerfga" (url do rabbitmq)

Pronto, agora você pode rodar o projeto no icone de play

(Antes você deve verificar sua versão java para que o projeto rode da maneira correta, para isso va no build.gradle e verifique que o java está em 22, para mudar,  se necessário, vá no sanduíche, canto superior esquerdo, "project structure", e mude o SDK para 22)

# end point local e deploy
- Você pode testar os endpoints pelo link do deploy, coloque o link em um app de requisição (Postman por exemplo) e faça as requisições, coloque o tipo de requisição que você quer e se for necessário um body, por exemplo um DTO, coloque, nos métodos POST e PUT por exemplo, mas você pode usar o postman para fazer requisições com o link local que é http://localhost:8082/entidade
- Todas as requisições são baseadas em um DTO que criamos, para esconder e isolar informações, por exemplo uma "EntidadeRequest" vai ser sempre um POST de alguma entidade, uma "EntidadeRequestDTO" vai ser um put para alguma entidade, essa regra só não se aplica a entidade Login, que conseguimos dar um POST com um DTO "LoginRequestDTO"
- Alguns exemplos de entidades e como devem ser requisitadas
    
    USUARIO:
    - /usuario/auth/register (cria usuario) - POST
    - /usuario/auth/login (loga usuario) - POST
    -  /usuario/{id} (pega usuario por id) - GET
    -  /usuario/{id} (deleta usuario por id) - DELETE
    -  /usuario/{id} (atualiza usuario por id) - PUT
    É IMPORTANTE DIZER QUE, OS MÉTODOS DE DELETE E PUT PARA TODAS AS ENTIDADES SÃO CONTROLADAS COM SECURITY A PARTIR DO TOKEN, PARA CONSEGUILO UTILIZE O AUTH/REGISTER E DEPOIS AUTH/LOGIN COM O MESMO EMAIL E SENHA QUE COLOCOU NO /REGISTER, LEMBRE-SE DE COLOCAR O "ROLE" COMO "ADMIN". PARA USAR O TOKEN, CLIQUE EM "AUTHENTICATION" COLOQUE "BEARER TOKEN" EM "AUTH TYPES" E COLOQUE SEU TOKEN NO CAMPO AO LADO QUE ESTÁ VAZIO, PRONTO.

    Mensagem
    - /mensagem (cria mensagem) - Post
    - /mensagem/{id} (pega mensagem por id) - GET
    - /mensagem/{id} (deleta mensagem por id) - DELETA - necessário o token
    - /mensagem/paginacao/mensagens (paginação de mensagens) - GET
    - /mensagem/{id} (atualiza mensagem por ID) - PUT
    - /mensagem/listar (lista as mensagens) - GET

    RABBITMQ
    - /rabbit/mensagem - (cria MensagemRequest para mensageria) - POST

Para ver mais exemplos, entre nesse código, va em alguma EntidadeController e veja o Mapping de cada entidade, que sempre começara com o nome da entidade, então, /usuario, /mensagem e etc.



