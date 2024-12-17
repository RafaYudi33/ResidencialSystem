<h1 align="center" style="font-weight: bold;">ResidencialSync 🏠</h1>

<div align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="hibernate">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</div>

<br> 
<p align="center">
 <a href="#started">Como Começar</a> • 
 <a href="#features">Funcionalidades</a> •
 <a href="#libraries">Bibliotecas Externas</a>
</p>

<br>

<p align="center">
  <b>Um sistema desktop para gerenciamento de condomínios residenciais.</b>
</p>

<h2 id="started">🚀 Como Começar</h2>

### Pré-requisitos

Garanta que você tem instalado:

- Java 17 ou superior
- Maven para gerenciamento de dependências

### Clonando

Clone o repositório:

```bash
git clone seu-projeto-url-no-github
```

### Banco de Dados

Antes de rodar o aplicativo, certifique-se de configurar o banco de dados necessário. Use o arquivo `application.properties.example` como referência para criar seu arquivo `application.properties` com a sua configuração.

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_nome_de_banco
spring.datasource.username=nome_de_usuario_do_banco
spring.datasource.password=senha_do_banco
```


<h2 id="features">📍 Funcionalidades</h2>

- **Gerenciamento de Áreas de Lazer**: Agende e gerencie as áreas de lazer, evitando sobreposição de reservas.
- **Validação de Moradores**: Certifique-se de que apenas moradores ou proprietários façam reservas, validando pelo CPF.
- **Relatórios e Comprovantes**: Gere relatórios sobre as áreas disponíveis e produza comprovantes em PDF das reservas.
- **Cobranças Condominiais**: Emita boletos de cobrança com juros e multas aplicáveis.
- **Agendamento de Visitas**: Permita agendamentos de visitas com geração de QR code para controle de acesso.
- **Relatórios por Placa de Veículo**: Produza relatórios detalhados dos moradores a partir das placas de veículos.

<h2 id="libraries">🔌 Bibliotecas Externas</h2>

Para aprimorar as funcionalidades e desempenho do sistema, as seguintes bibliotecas externas foram integradas:

- **Stella-Boleto**: Utilizada para a geração de boletos bancários.
- **Apache PDFBox**: Empregada para a criação e manipulação de documentos PDF, como comprovantes de reserva.
- **ZXing ("Zebra Crossing")**: Usada para a geração de QR Codes que facilitam o acesso e controle de visitas.
