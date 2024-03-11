PROJETO SESP - Cadastro de Pessoas e Endereços

Este é o README.txt para o projeto SESP (Sistema de Cadastro de Pessoas e Endereços).
Aqui você encontrará informações sobre como instalar, configurar e utilizar o projeto.

1. Descrição do Projeto:
   O projeto SESP é uma aplicação web desenvolvida para o cadastro e gerenciamento de pessoas e seus respectivos endereços.
   Ele oferece recursos para criar, visualizar, atualizar e excluir registros de pessoas e endereços.

2. Pré-requisitos:
   - Java JDK 17 instalado
   - Maven
   - Conexão com o servidor ElephanteSQL(PostgresSQL)

3. Configuração do Banco de Dados:
   - Certifique-se de ter uma conexão com o servidor ElephanteSQL(PostgresSQL).
   Verifique e atualize as configurações do banco de dados no arquivo `application.yml` do projeto que esta dentro da pasta resources.

4. Uso:
   - faça o download do projeto.
   - Navegue até o diretório do projeto.
   - Execute o aplicativo:
   - Uma vez que o aplicativo estiver em execução, você pode acessá-lo em: http://localhost:8080

5. Documentação da API:
   - Após iniciar o aplicativo, a documentação da API estará disponível em: http://localhost:8080/swagger-ui.html

6. Solicitações HTTP CRUD usando o Insomnia ou similar:
   - Para realizar operações CRUD (Create, Read, Update, Delete) em endereços, você pode usar aplicativos como Insomnia, Postman ou similar.
   - Abra o aplicativo Insomnia (ou similar) e crie uma nova solicitação HTTP.
   - Especifique o método HTTP adequado (GET, POST, PUT, DELETE) e a URL correspondente para acessar os endpoints CRUD do seu aplicativo.
   - Insira os dados necessários no corpo da solicitação, se aplicável, e envie a solicitação para interagir com o sistema de cadastro de endereços.

   Exemplos de URLs para solicitações CRUD:

   - Obter todos os endereços:
     GET http://localhost:8080/enderecos

   - Obter endereço por ID:
     GET http://localhost:8080/enderecos/{id}

   - Criar um novo endereço:
     POST http://localhost:8080/enderecos/inserir
     Corpo da solicitação (JSON):
     {
         "pessoa": {
             "id": 26
         },
         "logradouro": "Rua ABC",
         "numero": 123,
         "bairro": "Centro",
         "cidade": "Exemplo",
         "estado": "EX",
         "cep": "12345-678"
     }

   - Atualizar um endereço existente:
     PUT http://localhost:8080/enderecos/{id}
     Corpo da solicitação (JSON):
     {
         "logradouro": "Rua XYZ",
         "numero": 456,
         "bairro": "Centro",
         "cidade": "Exemplo",
         "estado": "EX",
         "cep": "54321-987"
     }

   - Deletar um endereço:
     DELETE http://localhost:8080/enderecos/{id}

7. Solicitações HTTP CRUD usando o Insomnia ou similar:
   - Para realizar operações CRUD (Create, Read, Update, Delete) em pessoas, você pode usar aplicativos como Insomnia, Postman ou similar.
   - Abra o aplicativo Insomnia (ou similar) e crie uma nova solicitação HTTP.
   - Especifique o método HTTP adequado (GET, POST, PUT, DELETE) e a URL correspondente para acessar os endpoints CRUD do seu aplicativo.
   - Insira os dados necessários no corpo da solicitação, se aplicável, e envie a solicitação para interagir com o sistema de cadastro de pessoas.

   Exemplos de URLs para solicitações CRUD em Pessoas:

   - Obter todas as pessoas paginadas:
     GET http://localhost:8080/api/pessoas?page=1&size=5

   - Buscar pessoas por nome, data de nascimento e nome da mãe:
     GET http://localhost:8080/api/pessoas/buscar?nome=John&dataNascimento=2023-01-01&nomeMae=Mary

   - Obter pessoa por CPF:
     GET http://localhost:8080/api/pessoas/cpf/12345678900

   - Inserir uma lista de pessoas:
     POST http://localhost:8080/api/pessoas/inserir
     Corpo da solicitação (JSON):
      [
         {
            "nome": "João Silva",
            "rg": "123456789",
            "cpf": "12345678901",
            "dataNascimento": "1990-05-15T00:00:00.000+00:00",
            "telefone": "999999999",
            "nomeMae": "Maria Silva",
            "nomePai": "José Silva",
         },
         {
            "nome": "Maria Souza",
            "rg": "987654321",
            "cpf": "98765432109",
            "dataNascimento": "1985-10-20T00:00:00.000+00:00",
            "telefone": "888888888",
            "nomeMae": "Ana Souza",
            "nomePai": "Pedro Souza",
         }
      ]

   - Criar ou atualizar uma pessoa:
     POST http://localhost:8080/api/pessoas
     Corpo da solicitação (JSON):
     {
         "nome": "João",
         "dataNascimento": "1990-01-01",
         "nomeMae": "Maria"
     }

   - Atualizar uma pessoa existente:
     PUT http://localhost:8080/api/pessoas/atualizar/1
     Corpo da solicitação (JSON):
     {
         "nome": "João da Silva",
         "dataNascimento": "1990-01-01",
         "nomeMae": "Maria Silva"
     }

   - Deletar uma pessoa pelo ID:
     DELETE http://localhost:8080/api/pessoas/deletar/1

   - Buscar pessoas pelo nome:
     GET http://localhost:8080/api/pessoas/por-nome/João
