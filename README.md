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
varrer usuário a usuário relacionado, mas tentamos de várias formas fazer funcionar até que pesquisando muito o Lucas teve a ideia de uma tabela hierárquica(árvore) e pesquisando mais um pouco acabou vendo o conceito de closure table, 
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
- A biblioteca Springdoc OpenAPI foi utilizada para facilitar a geração da documentação do projeto.

## Ferramentas
- IntelliJ IDEA
- Postman
- Git
- Maven
- Copilot
- Spring initializr

## Documentacao das APIs
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
        "state": "SP",
        "cep": "00000-000"
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