# Funcionários API

## Visão Geral da Arquitetura
A Funcionários API é um microsserviço RESTful construído sobre o ecossistema Spring Boot 3 e Java 21, projetado para o gerenciamento do ciclo de vida cadastral de colaboradores corporativos. A solução adota princípios de arquitetura stateless, persistência transacional com Spring Data JPA/Hibernate sobre banco relacional PostgreSQL, controle evolutivo de schema via migrações automatizadas com Flyway, validações em camada de entrada via Bean Validation e controle de acesso baseado em tokens JWT assinados criptograficamente. Toda a especificação de contratos de interface é exposta via OpenAPI 3 (Swagger), provendo documentação viva e testes com suporte nativo a esquemas de autenticação Bearer.

---

## Stack Tecnológica
- Plataforma de Execução: OpenJDK 21
- Framework Central: Spring Boot 3
- Camada de Segurança: Spring Security 6 (Stateless Session Policy, BCrypt Password Encoder, JWT)
- Camada de Acesso a Dados: Spring Data JPA, Hibernate ORM
- Banco de Dados Relacional: PostgreSQL 17
- Versionamento e Migração de Esquema: Flyway Migration
- Especificação de Contratos: OpenAPI 3 / Swagger UI (springdoc-openapi)
- Orquestração de Ambientes: Docker Engine & Docker Compose
- Ferramenta de Build: Apache Maven (com Maven Wrapper integrado)

---

## Requisitos Operacionais
A aplicação suporta duas estratégias distintas de execução. Escolha apenas uma conforme o objetivo:

### Estratégia A: Orquestração em Contêineres (Docker Compose)
- Requisito exclusivo: Docker Engine e Docker Compose instalados no host.
- Dispensa instalação local de Java, Maven ou servidores de banco de dados.

### Estratégia B: Execução Bare-Metal Local
- Java Development Kit (JDK) 21 instalado e exportado na variável de ambiente `JAVA_HOME`.
- Instância do PostgreSQL 17 ativa na rede local (`localhost:5432`).

---

## Parametrização de Ambiente (.env e .env.local)
O projeto adota segregação de ambientes baseada em dois arquivos de configuração distintos localizados na raiz da aplicação:

### 1. Arquivo `.env` (Ambiente Docker Compose)
Utilizado automaticamente pelo Docker Compose para resolver o hostname interno do contêiner do banco de dados (`postgres-db`):

```properties
DB_NAME=funcionario_api
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=c2V1X2p3dF9zZWNyZXRfc3VwZXJfc2VjcmV0b19hbGdvcml0bW9faHM1MTI=
DB_URL=jdbc:postgresql://postgres-db:5432/funcionario_api

2. Arquivo .env.local (Ambiente Bare-Metal / Execução Local)
Utilizado em inicializações locais diretas pela IDE ou terminal, direcionando as conexões para o host local (localhost):

Properties
DB_NAME=funcionario_api
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=c2V1X2p3dF9zZWNyZXRfc3VwZXJfc2VjcmV0b19hbGdvcml0bW9faHM1MTI=
DB_URL=jdbc:postgresql://localhost:5432/funcionario_api
Procedimentos de Build e Execução
Opção 1: Execução Isolada via Docker Compose (.env)
Compilação dos fontes e empacotamento do artefato JAR:

PowerShell
# Windows
.\mvnw.cmd clean package -DskipTests

# Linux / Unix
./mvnw clean package -DskipTests
Inicialização dos serviços (API e PostgreSQL) em segundo plano:

Bash
docker compose up -d --build
Inspeção do fluxo de logs da aplicação:

Bash
docker logs -f funcionarios-api
Interrupção e remoção dos recursos de rede e contêineres:

Bash
docker compose down
A interface HTTP responderá no host via porta padronizada: http://localhost:8080.

Opção 2: Execução Local no Sistema Operacional (.env.local)
Provisionamento do banco de dados relacional:
Conecte-se ao terminal de comandos do PostgreSQL (psql) e execute a criação da base:

SQL
CREATE DATABASE funcionario_api;
Configuração da execução com .env.local:

Execução via IDE (ex.: IntelliJ IDEA): Nas opções de Run/Debug Configurations, aponte o arquivo de variáveis para .env.local.

Execução via terminal: Garanta que as variáveis do .env.local estejam exportadas no ambiente do terminal antes da inicialização.

Inicialização do servidor embutido Apache Tomcat:

PowerShell
# Windows
.\mvnw.cmd spring-boot:run

# Linux / Unix
./mvnw spring-boot:run
Documentação Técnica Interativa (OpenAPI / Swagger)
A especificação interativa da API é gerada dinamicamente com base nas anotações de contrato expostas nos controladores. Para acessar a interface gráfica do Swagger UI e validar os esquemas de autenticação e rotas:

Swagger UI: http://localhost:8080/swagger-ui/index.html

OpenAPI JSON Spec: http://localhost:8080/v3/api-docs

Fluxo para consumo de rotas autenticadas no Swagger UI:

Submeta uma requisição com credenciais válidas ao endpoint /usuario/login.

Obtenha o valor da chave token retornado no corpo da resposta HTTP 200.

Clique no botão "Authorize" localizado na seção superior do Swagger UI.

Insira o token recebido no campo de texto e confirme a autorização.

Contratos da Interface RESTful (Autenticação)
Endpoints de Autenticação e Gestão de Usuários (Acesso Anônimo / PermitAll)
Método	Endpoint	Acesso	Descrição	Status de Retorno
POST	/usuario/cadastro	Público	Registra novo usuário com hash BCrypt	201 Created / 400 Bad Request
POST	/usuario/login	Público	Valida credenciais e gera token JWT	200 OK / 400 Bad Request / 403 Forbidden
Payload de Cadastro (POST /usuario/cadastro)
JSON
{
  "login": "operador_sistema",
  "senha": "SenhaForte@2026"
}
Payload de Login (POST /usuario/login)
JSON
{
  "login": "operador_sistema",
  "Senha": "SenhaForte@2026"
}
Política de Licenciamento
Este software é disponibilizado sob os termos da Licença MIT.
