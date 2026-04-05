# 📚 Aluno Online — API REST com Spring Boot

Sistema de gerenciamento acadêmico desenvolvido em Java com Spring Boot, permitindo o cadastro de alunos, professores, disciplinas e matrículas, além da emissão de histórico escolar.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Estrutura de Pacotes](#estrutura-de-pacotes)
- [Detalhamento do Código](#detalhamento-do-código)
- [Endpoints da API](#endpoints-da-api)
- [Como Executar](#como-executar)

---

## Sobre o Projeto

O **Aluno Online** é uma API REST que simula operações básicas de um sistema acadêmico. Com ela é possível:

- Cadastrar, listar, atualizar e remover alunos
- Cadastrar professores e associar disciplinas a eles
- Matricular alunos em disciplinas
- Trancar matrículas
- Atualizar notas de alunos matriculados
- Emitir histórico escolar completo de um aluno

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.3 | Framework base da aplicação |
| Spring Data JPA | — | Persistência e mapeamento ORM |
| Spring Validation | — | Validação de dados de entrada |
| Spring Web MVC | — | Camada HTTP / REST |
| PostgreSQL | — | Banco de dados relacional |
| Lombok | — | Redução de código boilerplate |
| Maven | 3.9.12 | Gerenciamento de dependências e build |

---

## Arquitetura

O projeto segue a arquitetura **MVC em camadas (Layered Architecture)**, padrão amplamente utilizado em aplicações Spring Boot:

```
┌─────────────────────────────────────┐
│           Cliente (HTTP)            │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Controller Layer            │  ← Recebe requisições HTTP, delega ao Service
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│          Service Layer              │  ← Contém as regras de negócio
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│        Repository Layer             │  ← Acesso ao banco de dados via JPA
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Banco de Dados              │  ← PostgreSQL
└─────────────────────────────────────┘
```

### Camadas da Aplicação

**Controller**: Responsável por receber as requisições HTTP, mapear os endpoints REST e delegar o processamento para a camada de serviço. Não contém regras de negócio.

**Service**: Camada central onde ficam as regras de negócio da aplicação. Orquestra as operações entre controllers e repositories, aplicando validações e lógica de domínio.

**Repository**: Interface que estende `JpaRepository`, provendo os métodos de acesso ao banco de dados de forma automática via Spring Data JPA.

**Model**: Classes que representam as entidades do domínio, mapeadas para tabelas do banco de dados com anotações JPA.

**DTO (Data Transfer Object)**: Objetos usados para trafegar dados específicos entre camadas, como na atualização de notas e na emissão do histórico, sem expor diretamente as entidades.

---

## Estrutura de Pacotes

```
src/
└── main/
    └── java/
        └── br/com/gabriel/aluno_online/
            ├── AlunoOnlineApplication.java       # Classe principal (entry point)
            ├── MatriculaStatusEnum.java           # Enum de status de matrícula
            ├── controller/
            │   ├── AlunoController.java
            │   ├── DisciplinaController.java
            │   ├── MatriculaAlunoController.java
            │   └── ProfessorController.java
            ├── service/
            │   ├── AlunoService.java
            │   ├── DisciplinaService.java
            │   ├── MatriculaAlunoService.java
            │   └── ProfessorService.java
            ├── repository/
            │   ├── AlunoRepository.java
            │   ├── DisciplinaRepository.java
            │   ├── MatriculaAlunoRepository.java
            │   └── ProfessorRepository.java
            ├── model/
            │   ├── Aluno.java
            │   ├── Disciplina.java
            │   ├── MatriculaAluno.java
            │   └── Professor.java
            └── dto/
                ├── AtualizarNotasRequestDTO.java
                └── HistoricoAlunoResponseDTO.java
```

---

## Detalhamento do Código

### `MatriculaStatusEnum`

Enum que representa os possíveis estados de uma matrícula:

| Valor | Descrição |
|---|---|
| `MATRICULADO` | Aluno ativo na disciplina |
| `APROVADO` | Aluno aprovado ao final do período |
| `REPROVADO` | Aluno reprovado ao final do período |
| `TRANCADO` | Matrícula trancada pelo aluno |

---

### Controllers

#### `AlunoController` — `/alunos`

| Método | Endpoint | Ação | Status |
|---|---|---|---|
| POST | `/alunos` | Cria um novo aluno | 201 Created |
| GET | `/alunos` | Lista todos os alunos | 200 OK |
| GET | `/alunos/{id}` | Busca aluno por ID | 200 OK |
| PUT | `/alunos/{id}` | Atualiza aluno por ID | 204 No Content |
| DELETE | `/alunos/{id}` | Remove aluno por ID | 204 No Content |

#### `DisciplinaController` — `/disciplinas`

| Método | Endpoint | Ação | Status |
|---|---|---|---|
| POST | `/disciplinas` | Cria uma nova disciplina | 201 Created |
| GET | `/disciplinas/professor/{professorId}` | Lista disciplinas de um professor | 200 OK |

#### `MatriculaAlunoController` — `/matriculas`

| Método | Endpoint | Ação | Status |
|---|---|---|---|
| POST | `/matriculas` | Cria uma nova matrícula | 201 Created |
| POST | `/matriculas/trancar/{id}` | Tranca uma matrícula | 204 No Content |
| PATCH | `/matriculas/atualizar-notas/{id}` | Atualiza as notas de uma matrícula | 204 No Content |
| GET | `/matriculas/emitir-historico/{alunoId}` | Emite o histórico escolar do aluno | 200 OK |

#### `ProfessorController` — `/professores`

| Método | Endpoint | Ação | Status |
|---|---|---|---|
| POST | `/professores` | Cria um novo professor | 201 Created |
| GET | `/professores` | Lista todos os professores | 200 OK |
| GET | `/professores/{id}` | Busca professor por ID | 200 OK |
| PUT | `/professores/{id}` | Atualiza professor por ID | 204 No Content |
| DELETE | `/professores/{id}` | Remove professor por ID | 204 No Content |

---

### DTOs

**`AtualizarNotasRequestDTO`**: Carrega os dados de nota enviados na requisição PATCH de atualização de notas, desacoplando o payload da entidade `MatriculaAluno`.

**`HistoricoAlunoResponseDTO`**: Estrutura a resposta do histórico escolar, agregando as informações relevantes das matrículas do aluno para retorno ao cliente.

---

## Endpoints da API

A API está disponível em `http://localhost:8080` após a inicialização. Recomenda-se utilizar o **Postman** ou **Insomnia** para testar os endpoints.

Exemplo de criação de aluno:

```http
POST /alunos
Content-Type: application/json

{
  "nome": "Gabriel Amorim",
  "email": "gabriel@email.com"
}
```

---

## Como Executar

### Pré-requisitos

- Java 21+
- Maven 3.9+
- PostgreSQL rodando localmente

### Configuração do banco

Crie um banco de dados no PostgreSQL e configure as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Rodando a aplicação

```bash
# Clone o repositório
git clone https://github.com/Gabriel-Amorim-dev/Java-P2.git

# Entre na pasta do projeto
cd Java-P2

# Execute com Maven
./mvnw spring-boot:run
```

---

## Autor

Desenvolvido por **Gabriel Gonçalves Menezes de Amorim** — [GitHub](https://github.com/Gabriel-Amorim-dev)
Com base no projeto do professor **Kelson Almeida** - [GitHub](https://github.com/kelsonvictr)
