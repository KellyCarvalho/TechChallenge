# TechChallenge

## Desafios
- Tratamento de erros e exceções:
 Foi solicitado que fosse feito a validação dos dados e o tratamento de exceções retornando ao usuário uma mensagem informativa, 
este acabou sendo o primeiro desafio encontrado, pois existem muitas formas de tratar o erro e retornar ao usuário, a discussão foi sobre qual estratégia seguir.
A primeira tentativa foi utilizando o binding result que aparentemente era uma boa opção, mas começamos a notar muita repetição de código e já começamos a comparar alternativas.
A outra opção que pareceu melhor para o resultado que queríamos foi utilizar um handler geral para capturar erros com o @ExceptionHandler e o @RestControllerAdvice, 
que nos permitiu tratar as exceções de forma mais genérica, inclusive exceções de validação, em um único ponto.


- Padronização de estilo de código:
Outro desafio foi a padronização do código, pois cada um tem seu estilo de codificação, e isso pode gerar conflitos. Não foi um desafio grande, mas foi necessário uma discussão para definir um estilo.
Além do estilo de código também foi importante discutir padrões de nomenclatura para variáveis, métodos e classes, e também o idioma geral do sistema.


- Lógica no controller ou service:
Discussão sobre onde a lógica deveria estar surgiu na metade final do desenvolvimento, pois como ainda estamos trabalhando em um sistema pequeno a lógica é bem simples e curta, por isso estava direto no controller. 
Pensando que este sistema tende a crescer e sofrer alterações não é boa prática a lógica permanecer no controller, pois isso acaba gerando um débito técnico levando em consideração o princípio da responsabilidade única.

## Tecnologias
- Como linguagem base usamos Java na versão 17 LTS.
- O Framework utilizado foi o Spring Boot que simplifica a configuração e o desenvolvimento.
  - Spring Web MVC para tratar as requisições HTTP, fazer o mapeamento de URLs e a comunicação entre a camada de apresentação e a camada de negócio.
  - O Spring Validation foi utilizado para validar os dados de entrada da API, para assegurar a integridade dos dados.
- A biblioteca Springdoc OpenAPI foi utilizada para facilitar a geração da documentação do projeto.

## Ferramentas
- IntelliJ IDEA
- Postman
- Git
- Maven
- Copilot
- Spring initializr

## Documentação das APIs
- API de Endereços:
  - Cadastrar um endereço:
      - POST: http://localhost:8080/address/
      - Exemplo de requisição:
    ```bash
    curl -X POST 'localhost:8080/address' \
    -H 'Content-Type: application/json' \
    --data '{
        "street": "Rua dos Bobos",
        "number": "0",
        "neighborhood": "Vila Pompéia",
        "city": "São Paulo",
        "state": "SP"
    }'
    ```
      - Exemplo de retorno em caso de sucesso:
    ```json
    // status 201
    {
      "id": 1,
      "street": "Rua dos Bobos",
      "number": "0",
      "neighborhood": "Vila Pompéia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```
  - Buscar um endereço: 
    - GET: http://localhost:8080/address/{id}
      - {id}: id do endereço buscado
    - Exemplo de requisição:
    ```bash
    curl -X GET 'localhost:8080/address/1'
    ```
    - Exemplo de retorno em caso de sucesso:
    ```json
    // status 200
    {
      "id": 1,
      "street": "Rua dos Bobos",
      "number": "0",
      "neighborhood": "Vila Pompéia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```
  - Buscar todos os endereços:
      - GET: http://localhost:8080/address
      - Exemplo de requisição:
    ```bash
    curl 'localhost:8080/address'
    ```
      - Exemplo de retorno em caso de sucesso:
    ```json
    // status 200
    [
      {
        "id": 1,
        "street": "Rua dos Bobos",
        "number": "0",
        "neighborhood": "Vila Pompéia",
        "city": "São Paulo",
        "state": "SP"
      },
      {
        "id": 2,
        "street": "Rua dos dois",
        "number": "1",
        "neighborhood": "Vila Pompéia",
        "city": "São Paulo",
        "state": "SP"
      }
    ]
    ```
  - Atualizar um endereço:
    - PUT: http://localhost:8080/address/{id}
      - {id}: id do endereço a ser atualizado
    - Exemplo de requisição:
    ```bash
    curl -X PUT 'localhost:8080/address/1' \
    -H 'Content-Type: application/json' \
    --data '{
        "street": "Rua dos Bobos",
        "number": "00",
        "neighborhood": "Vila Pompéia",
        "city": "São Paulo",
        "state": "SP"
    }'
    ```
    - Exemplo de retorno em caso de sucesso:
    ```json
    // status 200
    {
      "id": 1,
      "street": "Rua dos Bobos",
      "number": "00",
      "neighborhood": "Vila Pompéia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```
  - Deletar um endereço:
    - DELETE: http://localhost:8080/address/{id}
        - {id}: id do endereço a ser deletado 
    - Exemplo de requisição:
    ```bash
    curl -X DELETE 'localhost:8080/address/1'
    ```
    - Exemplo de retorno em caso de sucesso:
    ```json
    // status 204
    ```

- API de Eletrodomésticos:
    - Cadastrar um eletrodoméstico:
        - POST: http://localhost:8080/appliances/
        - Exemplo de requisição:
      ```bash
      curl -X POST 'localhost:8080/appliances' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Refrigerador dummie",
          "brand": "alta fridge",
          "model": "UVNANO",
          "potencyInWatts":5200,
          "voltage": "ONE_HUNDRED_TWENTY_SEVEN"
      }'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 201
      {
          "id": 2,
          "name": "Refrigerador dummie",
          "brand": "alta fridge",
          "model": "UVNANO",
          "potencyInWatts": 5200,
          "voltage": "ONE_HUNDRED_TWENTY_SEVEN"
      }
      ```
    - Buscar um eletrodoméstico:
        - GET: http://localhost:8080/appliances/{id}
            - {id}: id do endereço buscado
        - Exemplo de requisição:
      ```bash
      curl -X GET 'localhost:8080/appliances/1'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      {
          "id": 1,
          "name": "Refrigerador dummie",
          "brand": "alta fridge",
          "model": "UVNANO",
          "potencyInWatts": 5200,
          "voltage": "ONE_HUNDRED_TWENTY_SEVEN"
      }
      ```
    - Buscar todos os eletrodomésticos:
        - GET: http://localhost:8080/appliances
        - Exemplo de requisição:
      ```bash
      curl 'localhost:8080/appliances'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      [
          {
              "id": 1,
              "name": "Refrigerador dummie",
              "brand": "alta fridge",
              "model": "UVNANO",
              "potencyInWatts": 5200,
              "voltage": "ONE_HUNDRED_TWENTY_SEVEN"
          },
          {
              "id": 2,
              "name": "Refrigerador dummie",
              "brand": "alta fridge",
              "model": "UVNANO",
              "potencyInWatts": 5200,
              "voltage": "ONE_HUNDRED_TWENTY_SEVEN"
          }
      ]
      ```
    - Atualizar um eletrodoméstico:
        - PUT: http://localhost:8080/appliances/{id}
            - {id}: id do eletrodoméstico a ser atualizado
        - Exemplo de requisição:
      ```bash
      curl -X PUT 'localhost:8080/appliances/1' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Lavadora Lavanada",
          "brand": "Suggatudo",
          "model": "LGPD",
          "potencyInWatts": 2000,
          "voltage": "BIVOLT"
      }'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      {
          "id": 1,
          "name": "Lavadora Lavanada",
          "brand": "Suggatudo",
          "model": "LGPD",
          "potencyInWatts": 2000,
          "voltage": "BIVOLT"
      }
      ```
    - Deletar um eletrodoméstico:
        - DELETE: http://localhost:8080/appliances/{id}
            - {id}: id do eletrodoméstico a ser deletado
        - Exemplo de requisição:
      ```bash
      curl -X DELETE 'localhost:8080/appliances/1'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 204
      ```

- API de Pessoas:
    - Cadastrar uma pessoa:
        - POST: http://localhost:8080/person/
        - Exemplo de requisição:
      ```bash
      curl -X POST 'localhost:8080/person' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Sergio Bezerra da Silva",
          "birthDate": "1974-05-25",
          "gender": "MALE"
      }'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 201
      {
          "id": 1,
          "name": "Sergio Bezerra da Silva",
          "birthDate": "1974-05-25",
          "gender": "MALE"
      }
      ```
    - Buscar uma pessoa:
        - GET: http://localhost:8080/person/{id}
            - {id}: id do pessoa buscado
        - Exemplo de requisição:
      ```bash
      curl -X GET 'localhost:8080/person/1'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      {
          "id": 2,
          "name": "Sergio Bezerra da Silva",
          "birthDate": "1974-05-25",
          "gender": "MALE"
      }
      ```
    - Buscar todas as pessoas:
        - GET: http://localhost:8080/person
        - Exemplo de requisição:
      ```bash
      curl 'localhost:8080/person'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      [
          {
              "id": 1,
              "name": "Sergio Bezerra da Silva",
              "birthDate": "1974-05-25",
              "gender": "MALE"
          },
         {
              "id": 2,
              "name": "Sergio Bezerra da Silva",
              "birthDate": "1974-05-25",
              "gender": "MALE"
         }
      ]
      ```
    - Atualizar uma pessoa:
        - PUT: http://localhost:8080/person/{id}
            - {id}: id da pessoa a ser atualizado
        - Exemplo de requisição:
      ```bash
      curl -X PUT 'localhost:8080/person/1' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Sergio Bezerra Oliveira",
          "birthDate": "1974-05-25",
          "gender": "MALE"
      }'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 200
      {
          "id": 2,
          "name": "Sergio Bezerra Oliveira",
          "birthDate": "1974-05-25",
          "gender": "MALE"
      }
      ```
    - Deletar uma pessoa:
        - DELETE: http://localhost:8080/person/{id}
            - {id}: id da pessoa a ser deletada
        - Exemplo de requisição:
      ```bash
      curl -X DELETE 'localhost:8080/person/1'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 204
      ```