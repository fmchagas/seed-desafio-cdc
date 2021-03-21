# API casa do código
  Neste desafio preciso desenvolver uma API para suportar parte do funcionamento da casa do código.
  A ideia é trabalhar o design do código, criando código que seja suficiente para a funcionalidade e questionar de maneira propositiva os padrões já estabelecidos na minha mente e no mercado.

## Começando
Para executar o projeto, será necessário instalar os seguintes programas:

- [Java 11+](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3+](https://maven.apache.org/download.cgi)
- [Postman](https://www.postman.com/downloads/) ou [Imsominia](https://insomnia.rest/download)

## Observação
* Projeto usa ecossistema Spring
* H2 como banco de dados em arquivo

## Desenvolvimento

* Para iniciar o desenvolvimento Tenha uma IDE(eclipse com STS) e clone o projeto do GitHub num diretório:

```shell
cd "<seu diretório(workspace)>"
git clone https://github.com/fmchagas/seed-desafio-cdc.git
```

* Rode a aplicação

```shell
cd "<diretório raiz da aplicação>"
./mvnw spring-boot:run
```

faça uma requisição para:
http://localhost:8080/autores