<h1 align="center" style="font-weight: bold;">ResidencialSync 🏠</h1>

<div align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="hibernate">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/AWS%20S3-FF9900?style=for-the-badge&logo=amazon-aws&logoColor=white" alt="AWS S3">
  <img src="https://img.shields.io/badge/AWS%20SQS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" alt="AWS SQS">
</div>

<br> 
<p align="center">
 <a href="#started">Getting Started</a> • 
 <a href="#features">Features</a> •
 <a href="#libraries">External Libraries</a>
</p>

<br>

<p align="center">
  <b>A desktop system for managing residential condominiums.</b>
</p>

---

<h2 id="started">🚀 Getting Started</h2>

### Prerequisites

Make sure you have installed:

- Java 17 or later
- Maven for dependency management
- MySQL for the database

### Cloning the Project

Clone the repository:

```bash
git clone https://github.com/RafaYudi33/ResidencialSystem.git
cd ResidencialSystem
```

### Database Configuration

Before running the application, configure the database in the **`application.properties`** file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/condominio
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
aws.services.sqs.queue-url=yourSQSqueue
aws.services.ses.emailSender=yourEmailSender
```

### Running the Application

1. Compile the project with Maven:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   java -jar target/ResidencialSync.jar
   ```

---

<h2 id="features">📍 Features</h2>

> ### 💾 **1. Uploading Invoices to AWS S3 and Email Dispatch**
> - The system **generates condominium invoices** and automatically stores them in **AWS S3**.
> - Sends a message to the **AWS SQS queue** with the owner's email details and a signed link for invoice download.
> - The **[Email Microservice](https://github.com/RafaYudi33/EmailMicroservice/tree/microservice-servless)**, hosted in AWS Lambda, processes the queue message and sends the email.

---

> ### 🚀 **2. Leisure Area Management**
> - Scheduling **leisure areas** for periods of up to **5 hours**.  
> - Prevents **overlapping reservations** at the same time.  
> - Validates if the reservation is made by **residents or owners** using CPF.  
> - **Generates PDF receipts** containing:  
>   - Responsible person's name  
>   - Reserved location  
>   - Date and time of reservation  

---

> ### 📋 **3. Availability Reports**
> - Generates **reports of available areas** for booking at a specific date and time.

---

> ### 💵 **4. Invoice Issuance and Management**
> - Allows issuing **billing invoices** for condominium fees, calculating:  
>   - **Water bill sharing** among residential units.  
>   - Base fee defined by the condominium.  
>   - Fixed fee of **R$ 10.00** per leisure area booking in the last **30 days**.  
> - **Automatic download** of all invoices generated in the system.

---

<h2 id="libraries">🔌 External Libraries</h2>

To enhance the system's functionalities and performance, the following external libraries were integrated:

- **Stella-Boleto**: Used for generating bank invoices.
- **Apache PDFBox**: Used for creating and manipulating PDF documents, such as reservation receipts.
- **ZXing ("Zebra Crossing")**: Used for generating QR Codes to facilitate visitor control.

---

📌 *This system was developed to optimize condominium management.* 🚀

