# Desafio de Programação - Itaú Unibanco

Este projeto é uma solução para o desafio de programação proposto pelo Itaú Unibanco. Consiste em uma API REST desenvolvida em Java com Spring Boot para processar transações financeiras em tempo real e calcular estatísticas sobre elas.

##  Funcionalidades

- **Registro de Transações:** Permite o envio de transações contendo um valor e a data/hora do evento.
- **Cálculo de Estatísticas:** Retorna estatísticas (soma, média, mínimo, máximo e contagem) de todas as transações ocorridas nos últimos 60 segundos.
- **Limpeza de Dados:** Oferece um endpoint para apagar todas as transações armazenadas.
- **Interface Web:** Inclui um front-end simples para interagir com a API diretamente pelo navegador.
- **Testes Automatizados:** Cobertura de testes unitários para a lógica de negócio principal, garantindo a robustez e a corretude dos cálculos.

---

##  Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Maven**
- **JUnit 5** (para testes automatizados)
- **HTML5, CSS3, JavaScript** (para a interface web)

---

##  Endpoints da API

A API expõe os seguintes endpoints:

| Método HTTP | Endpoint       | Descrição                                                                 | Corpo da Resposta (Sucesso)                                  |
|-------------|----------------|---------------------------------------------------------------------------|--------------------------------------------------------------|
| `POST`      | `/transacao`   | Registra uma nova transação. A data não pode ser futura e o valor não pode ser negativo. | `201 Created` (sem corpo)                                    |
| `DELETE`    | `/transacao`   | Apaga todas as transações armazenadas em memória.                         | `200 OK` (sem corpo)                                         |
| `GET`       | `/estatistica` | Retorna as estatísticas das transações ocorridas nos últimos 60 segundos. | JSON com `count`, `sum`, `avg`, `min`, `max`.                |

---

##  Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

**Pré-requisitos:**
- JDK 17 ou superior instalado.
- Maven 3.8 ou superior instalado.

**Passos:**

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/itau-islno.git](https://github.com/SEU-USUARIO/itau-islno.git)
    cd itau-islno
    ```
    *(**Atenção:** Substitua `SEU-USUARIO` pelo seu nome de usuário do GitHub)*

2.  **Compile e execute os testes com o Maven:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    java -jar target/itau-islno-0.0.1-SNAPSHOT.jar
    ```
    *(O nome do arquivo `.jar` pode variar. Verifique o nome correto dentro da pasta `target` após o passo 2)*

4.  **Acesse a aplicação:**
    - A API estará disponível em `http://localhost:8080`.
    - A interface web pode ser acessada em `http://localhost:8080`.

---