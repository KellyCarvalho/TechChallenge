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


