
# apiProdutos

Este é um projeto de exemplo para uma API RESTful de controle de produtos, desenvolvido com Spring Boot.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- MongoDB
- RabbitMQ
- Docker
## Configuração do Ambiente
# Pré-requisitos
- Java 21
- Docker
- Docker Compose
## Configuração do MongoDB e RabbitMQ
O projeto utiliza MongoDB e RabbitMQ, que podem ser configurados utilizando Docker Compose. Para iniciar os serviços, execute o comando:

docker-compose up -d

Endpoints da API
A API possui os seguintes endpoints:

- GET /api/produtos: Lista todos os produtos.
- GET /api/produtos/{id}: Obtém um produto pelo ID.
- POST /api/produtos: Cria um novo produto.
- PUT /api/produtos/{id}: Atualiza um produto existente.
- DELETE /api/produtos/{id}: Inativa um produto.

## Documentação da API
A documentação da API está disponível através do Swagger. Após iniciar a aplicação, acesse:

http://localhost:8084/swagger-ui.html

## Configurações
As configurações da aplicação estão no arquivo application.properties.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Como Contribuir

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (git checkout -b feature/nova-feature).
3. Commit suas mudanças (git commit -m 'Adiciona nova feature').
4. Faça o push para a branch (git push origin feature/nova-feature).
5. Abra um Pull Request.
