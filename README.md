# manhwa-library-api-spring

Projeto SpringBoot maven com PostgreSQL

Este é um projeto Spring Boot que utiliza o PostgreSQL como banco de dados e o maven para configuração.

## Requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Java Development Kit (JDK)
- Maven
- PostgreSQL

## Configuração do Banco de Dados

Certifique-se de ter o PostgreSQL instalado e configurado em sua máquina. Você precisará criar um banco de dados para o projeto e configurar as credenciais de acesso.

1. Crie um banco de dados PostgreSQL utilizando o seguinte comando:

```bash
createdb nome_do_banco
```
2. Abra o arquivo *src/main/resources/application.properties* e configure as propriedades spring.datasource.url, spring.datasource.username e spring.datasource.password de acordo com as credenciais do seu banco de dados.

## Executando o Projeto

Para executar o projeto, siga estas etapas:

Clone este repositório:

```bash
git clone https://github.com/seu_usuario/seu_projeto.git
```
Navegue até o diretório do projeto:

```bash
cd seu_projeto
```
Compile o projeto usando o Maven:

```bash
mvn clean package
```
Execute o JAR gerado:

```bash
java -jar target/nome_do_arquivo.jar
```
O projeto será iniciado e estará acessível em http://localhost:8080