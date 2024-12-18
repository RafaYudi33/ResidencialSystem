
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

---

<h2 id="started">🚀 Como Começar</h2>

### Pré-requisitos

Garanta que você tem instalado:

- Java 17 ou superior
- Maven para gerenciamento de dependências
- MySQL para o banco de dados

### Clonando o Projeto

Clone o repositório:

```bash
git clone https://github.com/RafaYudi33/ResidencialSystem.git
cd ResidencialSystem
```

### Configuração do Banco de Dados

Antes de rodar o aplicativo, configure o banco de dados no arquivo **`application.properties`**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/condominio
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executando a Aplicação

1. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

2. Execute a aplicação:
   ```bash
   java -jar target/ResidencialSync.jar
   ```

---

<h2 id="features">📍 Funcionalidades</h2>

> ### 🚀 **1. Gerenciamento de Áreas de Lazer**
> - Agendamento de **áreas de lazer** por períodos de até **5 horas**.  
> - Impede **sobreposição** de reservas no mesmo horário.  
> - Valida se o agendamento é feito por **moradores ou proprietários** através do CPF.  
> - **Gera comprovantes em PDF** contendo:  
>   - Nome do responsável  
>   - Local reservado  
>   - Data e hora da reserva  

---

> ### 📋 **2. Relatórios de Disponibilidade**
> - Gera **relatórios das áreas disponíveis** para agendamento em uma data e hora específica.

---

> ### 💵 **3. Emissão e Gestão de Boletos**
> - Permite emitir **boletos de cobrança** das taxas condominiais para as propriedades, calculando:  
>   - **Rateio da conta de água** entre unidades residenciais.  
>   - Taxa base definida pelo condomínio.  
>   - Taxa fixa de **R$ 10,00** por agendamento de área de lazer nos últimos **30 dias**.  
> - **Download automático** de todos os boletos gerados no sistema.
> - Calcula **multas** aplicáveis a boletos vencidos com base nas regras do condomínio.
> - Exclui os terrenos do rateio de agua.

---

> ### 🏠 **5. Relatórios de Moradores**
> - Gera relatórios detalhados dos moradores de **uma unidade residencial específica**, incluindo:  
>   - Nome  
>   - CPF  
>   - RG  
>   - Data de Nascimento  
>   - Sexo  
> - Permite gerar **relatórios a partir da placa de veículos** associados a moradores.

---

> ### 📅 **6. Agendamento de Visitas**
> - Agendamento de visitas com geração de **QR Codes únicos** para:  
>   - Controle de entrada e saída  
>   - Validade de **1 dia** após a data agendada.  
> - **Dados necessários:**  
>   - CPF do visitante  
>   - Placa do veículo  
>   - Dia da visita e telefone de contato.  
> - O QR Code é **salvo na máquina** e pode ser compartilhado com o visitante.

---

> ### 🔄 **7. Inicialização e Carregamento Completo de Dados**
> - Durante a **inicialização do sistema**, todos os **dados essenciais** são carregados, incluindo:  
>   - Dados do condomínio (nome, agência, convênio, endereço, CNPJ, etc.).  
>   - Dados das unidades residenciais e terrenos.  
>   - Dados dos moradores e proprietários.  
>   - Dados das reservas e agendamentos existentes.  
>   - Informações dos veículos e suas associações com os moradores.  

---

<h2 id="libraries">🔌 Bibliotecas Externas</h2>

Para aprimorar as funcionalidades e desempenho do sistema, as seguintes bibliotecas externas foram integradas:

- **Stella-Boleto**: Utilizada para a geração de boletos bancários.
- **Apache PDFBox**: Empregada para a criação e manipulação de documentos PDF, como comprovantes de reserva.
- **ZXing ("Zebra Crossing")**: Usada para a geração de QR Codes que facilitam o controle de visitas.
