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

- Relacionamento entre pessoas da mesma família:
  Foi um desafio interessante ter que relacionar usuários de maneira automática ao inserir, primeiro pensamos na abordagem de ter um método que funcionaria como uma espécie de job varrendo todos os usuários relacionados ao usuário que tinha a relação
  e a cada inserção o método pesquisaria cada relação e devolveria relação por relação, no início pareceu uma boa ideia, mas do meio para o fim tornou-se algo inviável e pensando em grande escala pouco performático por ter a ideia de
  varrer usuário a usuário relacionado, mas tentamos de várias formas fazer funcionar até que pesquisando muito o Lucas teve a ideia de uma tabela hierárquica (árvore) e pesquisando mais um pouco acabou vendo o conceito de closure table,
  foi um grande desafio, entender o conceito e aplicar, mas a técnica de modelagem denominada closure table é usada para representar estruturas hierárquicas, como árvores, grafos ou categorias aninhadas. A ideia fundamental é criar uma tabela
  adicional que armazena informações sobre as relações hierárquicas entre os elementos da estrutura. Esta tabela de fechamento geralmente é preenchida com dados de forma que cada linha represente uma relação pai-filho entre elementos na hierarquia.
  A tabela inclui,pelo menos, duas colunas principais: uma coluna para o elemento pai e uma coluna para o elemento filho. E a promessa ao utilizar uma closure table é que seja possível consultar hierarquias complexas de forma eficiente.
  E ainda as consultas podem ser feitas de maneira fácil podendo recuperar todos os filhos de um elemento, todos os pais de um elemento ou até mesmo para navegar na hierarquia de cima para baixo e de baixo para cima, de maneira geral era o que precisávamos.
  Nunca tínhamos ouvido falar desta técnica, sendo assim é mais um aprendizado que vamos levar durante a nossa jornada como desenvolvedores.

## Tecnologias

- Como linguagem base usamos Java na versão 17 LTS.
- O Framework utilizado foi o Spring Boot que simplifica a configuração e o desenvolvimento.
    - Spring Web MVC para tratar as requisições HTTP, fazer o mapeamento de URLs e a comunicação entre a camada de apresentação e a camada de negócio.
    - O Spring Validation foi utilizado para validar os dados de entrada da API, para assegurar a integridade dos dados.
    - O Spring Data JPA foi utilizado para facilitar a persistência dos dados.
- A biblioteca Springdoc OpenAPI foi utilizada para facilitar a geração da documentação do projeto.
- O SQL foi utilizado para buscas e ações mais avançadas não disponíveis diretamente no Spring Data JPA.

## Ferramentas

- IntelliJ IDEA
- Postman
- Git
- Maven
- Copilot
- Spring initializr
- MySQL

## Documentacao das APIs

- ### API de Usuários:

  - #### Cadastrar um usuário:
    - POST: http://localhost:8080/users/
      - Request:
        ```bash
          curl -X POST 'localhost:8080/users' -H 'Content-Type: application/json' \
          --data '{
              "name": "Sò Mais um Silva",
              "birthDate": "1975-06-25",
              "gender": "MALE"
          }'
        ```
      - Response 201:
        ```json
          {
            "id": 1,
            "name": "Sergio Bezerra da Silva",
            "birthDate": "1974-05-25",
            "gender": "MALE"
          }
        ```
      - Response 400:
        ```json
          {
            "name": "tamanho deve ser entre 1 e 255",
            "birthDate": "não deve ser nulo"
          }
        ```
  - #### Retorna um usuário específico
    - GET: http://localhost:8080/users/{id}
      - Request:
        ```bash
          curl -X GET 'http://localhost:8080/users/2'
        ```
      - Response 200:
        ```json
          {
            "id": 1,
            "name": "Sergio Bezerra da Silva",
            "birthDate": "1974-05-25",
            "gender": "MALE"
          }
        ```
      - Response 404:
        ```json
          {
            "error": "User id: 1 not found."
          }
        ```
  - #### Retorna uma lista de usuários
    - GET: http://localhost:8080/users
      - Request:
        ```bash
          curl -X GET 'http://localhost:8080/users'
        ```
      - Response 200:
        ```json
          [
            {
              "id": 1,
              "name": "Sergio Bezerra da Silva",
              "birthDate": "1974-05-25",
              "gender": "MALE"
            }
          ]
        ```
  - #### Atualiza um usuário na base de dados
    - PUT: http://localhost:8080/users/{id}
      - Request:
        ```bash
          curl -X PUT 'localhost:8080/users/3' \
          -H 'Content-Type: application/json' \
          --data '{  
                  "name": "Sergio Oliveira da Silva",
                  "birthDate": "1974-05-25",
                  "gender": "MALE"
                }'
        ```
      - Response 200:
        ```json
          {
            "id": 1,
            "name": "Sergio da Silva",
            "birthDate": "1974-05-25",
            "gender": "MALE"
          }
        ```
      - Response 400:
        ```json
          {
            "name": "tamanho deve ser entre 1 e 255",
            "birthDate": "não deve ser nulo"
          }
        ```
      - Response 404:
        ```json
          {
            "error": "User id: 2 not found."
          }
        ```
    
  - #### Deleta um usuário na base de dados
    - DELETE: http://localhost:8080/users/{id}
      - Request
        ```bash
          curl -X DELETE 'http://localhost:8080/users/3'
        ```
      - Response 204
        ```json
          {}
        ```
      - Response 404:
        ```json
          {
            "error": "User id: 3 not found."
          }
        ```
- ### API de Endereços:

    - Cadastrar um endereço:
        - POST: http://localhost:8080/addresses/
        - Exemplo de requisição:
          ```bash
          curl -X POST 'localhost:8080/addresses' \
          -H 'Content-Type: application/json' \
          --data '{
              "userId": 1,
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
        - GET: http://localhost:8080/addresses/{id}
            - {id}: id do endereço buscado
        - Exemplo de requisição:
          ```bash
          curl -X GET 'localhost:8080/addresses/1'
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
        - GET: http://localhost:8080/addresses
        - Exemplo de requisição:
          ```bash
          curl -X GET 'localhost:8080/addresses'
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

  - Pesquisar os endereços por item:

      - POST: http://localhost:8080/addresses/search
      - Exemplo de requisição:
        ```bash
        curl -X POST 'localhost:8080/addresses/search' \
        -H 'Content-Type: application/json' \
        --data '{
            "street": "bobos",
            "number": "",
            "neighborhood": "",
            "city": "",
            "state": ""
        }'
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
        - PUT: http://localhost:8080/addresses/{id}
            - {id}: id do endereço a ser atualizado
        - Exemplo de requisição:
      ```bash
      curl -X PUT 'localhost:8080/addresses/1' \
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
        - DELETE: http://localhost:8080/addresses/{id}
            - {id}: id do endereço a ser deletado
        - Exemplo de requisição:
      ```bash
      curl -X DELETE 'localhost:8080/addresses/1'
      ```
        - Exemplo de retorno em caso de sucesso:
      ```json
      // status 204
      ```

- ### API de Eletrodomésticos:

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
          "voltage": "ONE_HUNDRED_TWENTY_SEVEN",
          "addressId": 1
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
      curl -X GET 'localhost:8080/appliances'
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
    - Pesquisar os eletrodomésticos por item:
    - POST: http://localhost:8080/appliances/search
    - Exemplo de requisição:
      ```bash
      curl -X POST 'localhost:8080/appliances/search' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Refrigerador",
          "brand": "",
          "model": "UVNANO"
      }'
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

- ### API de Pessoas:

    - Cadastrar uma pessoa:
        - POST: http://localhost:8080/people/
        - Exemplo de requisição:
      ```bash
      curl -X POST 'localhost:8080/people' \
      -H 'Content-Type: application/json' \
      --data '{
          "name": "Sergio Bezerra da Silva",
          "birthDate": "1974-05-25",
          "gender": "MALE",
          "addressesIds": [1],
          "userId": 1
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
        - GET: http://localhost:8080/people/{id}
            - {id}: id da pessoa buscada
        - Exemplo de requisição:
      ```bash
      curl -X GET 'localhost:8080/people/1'
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
        - GET: http://localhost:8080/people
        - Exemplo de requisição:
      ```bash
      curl -X GET 'localhost:8080/people'
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
    - Pesquisar pessoas por item:
    - POST: http://localhost:8080/people/search
    - Exemplo de requisição:
      ```bash
      curl -X POST 'localhost:8080/people/search' \
      -H 'Content-Type: application/json' \
      --data '{
          "userId": 1,
          "name": "",
          "gender": "MALE"
      }'
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
        - Atualizar uma pessoa:
            - PUT: http://localhost:8080/people/{id}
                - {id}: id da pessoa a ser atualizada
            - Exemplo de requisição:
          ```bash
          curl -X PUT 'localhost:8080/people/1' \
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
            - DELETE: http://localhost:8080/people/{id}
                - {id}: id da pessoa a ser deletada
            - Exemplo de requisição:
          ```bash
          curl -X DELETE 'localhost:8080/people/1'
          ```
            - Exemplo de retorno em caso de sucesso:
          ```json
          // status 204
          ```
        - Definir responsável:
            - POST: http://localhost:8080/people/{personId}/parent/{parentId}
                - {personId}: id do filho
                - {parentId}: id do responsavel
            - Exemplo de requisição:
          ```bash
          curl -X POST 'http://localhost:8080/people/2/parent/1'
          ```

- Exemplo de retorno em caso de sucesso:

  ```json
  {
    "id": 2,
    "name": "Luiz Inacio",
    "birthDate": "1971-09-03",
    "gender": "MALE",
    "relatedUser": {
      "id": 1,
      "name": "Sergio Bezerra da Silva",
      "birthDate": "1974-05-25",
      "gender": "MALE"
    },
    "addresses": [
      {
        "id": 1,
        "street": "Rua dos Bobos",
        "number": "0",
        "neighborhood": "Vila Pompéia",
        "city": "São Paulo",
        "state": "SP"
      }
    ]
  }
  ```

- Buscar descendentes:

    - POST: http://localhost:8080/people/{id}/descendants
    - {id}: id do responsavel
    - Exemplo de requisição:

  ```bash
    curl -X GET 'http://localhost:8080/people/1/descendants'
  ```

    - Exemplo de retorno em caso de sucesso:

  ```json
  {
    "id": 1,
    "name": "Sergio Bezerra da Silva",
    "birthDate": "1974-05-25",
    "gender": "MALE",
    "familyMembers": {
      "SON": [
        {
          "id": 2,
          "name": "Luiz Inacio",
          "birthDate": "1971-09-03",
          "gender": "MALE"
        }
      ]
    }
  }
  ```

- Buscar ancestrais:
    - POST: http://localhost:8080/people/{id}/ancestors
    - {id}: id do descendente
    - Exemplo de requisição:
  ```bash
    curl -X GET 'http://localhost:8080/people/2/ancestors'
  ```
- Exemplo de retorno em caso de sucesso:

  ```json
  {
    "id": 2,
    "name": "Luiz Inacio",
    "birthDate": "1971-09-03",
    "gender": "MALE",
    "familyMembers": {
      "PARENT": [
        {
          "id": 1,
          "name": "Sergio Bezerra da Silva",
          "birthDate": "1974-05-25",
          "gender": "MALE"
        }
      ]
    }
  }
  ```

- Buscar irmãos:
    - POST: http://localhost:8080/people/{id}/brothers
    - {id}: id da pessoa
    - Exemplo de requisição:
  ```bash
    curl -X GET 'http://localhost:8080/people/2/brothers'
  ```
- Exemplo de retorno em caso de sucesso:
  ```json
  {
    "id": 2,
    "name": "Luiz Inacio",
    "birthDate": "1971-09-03",
    "gender": "MALE",
    "familyMembers": {
      "BROTHER": [
        {
          "id": 3,
          "name": "Luiz Terceiro",
          "birthDate": "1971-09-13",
          "gender": "MALE"
        }
      ]
    }
  }
  ```
