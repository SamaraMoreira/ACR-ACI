# AiHackLogistics

Nossa solução não só aprimora a experiência dos pacientes, mas também melhora a eficiência e a eficácia do sistema de saúde como um todo, trazendo benefícios para hospitais e profissionais. Permitimos que pacientes agendem consultas de forma rápida e simplificada, diretamente pelo smartphone, desde que possuam cadastro prévio no hospital. Em casos de consultas realizadas, integramos um recurso avançado de prognóstico utilizando IA. Nosso modelo atual foi treinado para detectar sinais de COVID-19 e pneumonia. O exame é conduzido por um profissional de saúde designado pelo hospital, que captura imagens do pulmão do paciente. Essas imagens são analisadas automaticamente pelo nosso modelo, que identifica possíveis indícios de doenças respiratórias, permitindo aos médicos priorizar os casos mais graves e agilizar o tratamento. 

## Arquitetura da solução
![Minha Imagem](https://drive.google.com/uc?export=view&id=1KQOMrdjtcCEQWmXpJupmd9aSkYX9LIe6)

### Descrição da Arquitetura da Solução Proposta

1. **Desenvolvimento e Repositório:**
   - **Desenvolvedor:** Inicia o fluxo com o envio de código para um repositório Git.
   - **Git Repository:** Armazena o código fonte e utiliza GitHub Actions para automatizar os fluxos de CI/CD.

2. **Integração e Entrega Contínua:**
   - **GitHub Actions:** Automatiza o teste, a construção e o deploy do código para o ambiente Azure.

3. **Serviço de Hospedagem:**
   - **Azure App Service:** Recebe o código do GitHub Actions e hospeda a aplicação web.
   - **App Service Plan:** Define o dimensionamento e a capacidade de recursos para a aplicação web hospedada no Azure App Service.

4. **Back-end e Armazenamento:**
   - **Spring Boot REST API:** A aplicação principal, desenvolvida usando Spring Boot, serve como back-end, processando as requisições REST.
   - **Maven:** Gerencia dependências e automatiza a construção da aplicação Spring Boot.
   - **SQL Database:** Hospedado no Microsoft Azure, gerencia e armazena dados relacionais usados pela aplicação.

5. **Interface do Usuário e Interatividade:**
   - **Thymeleaf:** Utilizado para criar as telas da aplicação. Thymeleaf é um motor de template para Java, empregado com Spring Boot para desenvolver interfaces web dinâmicas. Ele permite a criação de páginas HTML no servidor, que são depois renderizadas no cliente. Este sistema facilita a implementação de funcionalidades interativas como formulários para cadastro e outras operações CRUD (Create, Read, Update, Delete).

6. **Fluxos de Informação:**
   - O código é empurrado do ambiente de desenvolvimento para o repositório Git.
   - O GitHub Actions puxa o código para automação e, após testes e construção, o deploy é feito no Azure App Service.
   - A aplicação no Azure interage com o banco de dados SQL para realizar operações de CRUD, armazenar e recuperar dados.
   - A interface do usuário, desenvolvida com Thymeleaf, permite que os usuários interajam diretamente com a aplicação, facilitando o cadastro e a gestão de informações através de um front-end intuitivo e responsivo.

7. **Conectividade:**
   - A aplicação é acessível via internet, permitindo interações de usuários ou outros sistemas externos.


## Benefícios a serem alcançados

Com a arquitetura proposta, espera-se alcançar os seguintes benefícios significativos para o negócio:

1. **Automatização e Eficiência:**  
   A automação contínua proporcionada pelo GitHub Actions permite testes rápidos, construção confiável e deploys automáticos para o Azure App Service. Isso reduz o tempo de ciclo de desenvolvimento e aumenta a eficiência operacional.

2. **Escalabilidade e Disponibilidade:**  
   O uso do Azure App Service e do App Service Plan permite escalar facilmente a aplicação conforme necessário, garantindo que a plataforma possa lidar com aumentos de tráfego sem comprometer o desempenho. Isso resulta em maior disponibilidade e capacidade de resposta para os usuários finais.

3. **Segurança e Confiabilidade:**  
   Utilizando serviços confiáveis da Microsoft Azure, como hospedagem segura de banco de dados SQL, a solução oferece um ambiente robusto para armazenamento e gerenciamento de dados sensíveis. Isso ajuda a garantir a segurança dos dados dos clientes e a conformidade com regulamentações.

4. **Desenvolvimento Ágil e Iterativo:**  
   O uso do Spring Boot e Maven facilita o desenvolvimento ágil de APIs REST, permitindo rápida iteração e adaptação às necessidades do mercado e dos usuários. Isso suporta a entrega contínua de novas funcionalidades e melhorias.

5. **Experiência do Usuário Aprimorada:**  
   A interface do usuário desenvolvida com Thymeleaf oferece uma experiência intuitiva e responsiva para os usuários finais. Isso facilita a interação com a aplicação, melhorando a usabilidade e a satisfação do usuário.

6. **Redução de Custos Operacionais:**  
   A combinação de ferramentas de automação e escalabilidade eficiente no Azure contribui para a redução dos custos operacionais, otimizando recursos e maximizando o retorno sobre o investimento em infraestrutura de TI.

## Scripts sql

Realize a criação das tabelas. Os inserts podem ser feitos através da versão web, no entanto, disponibilizamos aqui inserts para teste.

```sql
-- Comandos DDL
------------------------------------
-- Apagar a tabela de consultas
DROP TABLE tb_consultas;

-- Apagar a tabela de médicos
DROP TABLE tb_medicos;

-- Apagar a tabela de pacientes
DROP TABLE tb_paciente;

-- Apagar a tabela de endereços
DROP TABLE tb_endereco;

CREATE TABLE tb_consultas (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    data_hora TIMESTAMP,
    observacoes VARCHAR2(255),
    prescricao VARCHAR2(255),
    status NUMBER(1),
    medico_id NUMBER,
    paciente_id NUMBER,
    PRIMARY KEY (id),
    CHECK (status BETWEEN 0 AND 3)
);

-- Criação da tabela de endereços
CREATE TABLE tb_endereco (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    bairro VARCHAR2(255),
    cep VARCHAR2(255),
    cidade VARCHAR2(255),
    complemento VARCHAR2(255),
    estado VARCHAR2(255),
    numero VARCHAR2(255),
    rua VARCHAR2(255),
    PRIMARY KEY (id)
);

-- Criação da tabela de médicos
CREATE TABLE tb_medicos (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    crm VARCHAR2(255),
    especialidade NUMBER(1),
    nome VARCHAR2(255),
    telefone VARCHAR2(255),
    endereco_id NUMBER,
    PRIMARY KEY (id),
    CHECK (especialidade BETWEEN 0 AND 1),
    CONSTRAINT UQ_Medicos_Endereco UNIQUE (endereco_id)
);

-- Criação da tabela de pacientes
CREATE TABLE tb_paciente (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    data_nascimento DATE,
    nome VARCHAR2(255),
    telefone VARCHAR2(255),
    endereco_id NUMBER,
    PRIMARY KEY (id),
    CONSTRAINT UQ_Pacientes_Endereco UNIQUE (endereco_id)
);

-- Criação das constraints de chave estrangeira
ALTER TABLE tb_consultas 
    ADD CONSTRAINT FK_Consultas_Medico 
    FOREIGN KEY (medico_id) 
    REFERENCES tb_medicos (id);

ALTER TABLE tb_consultas 
    ADD CONSTRAINT FK_Consultas_Paciente 
    FOREIGN KEY (paciente_id) 
    REFERENCES tb_paciente (id);

ALTER TABLE tb_medicos 
    ADD CONSTRAINT FK_Medicos_Endereco 
    FOREIGN KEY (endereco_id) 
    REFERENCES tb_endereco (id);

ALTER TABLE tb_paciente 
    ADD CONSTRAINT FK_Pacientes_Endereco 
    FOREIGN KEY (endereco_id) 
    REFERENCES tb_endereco (id);
    
    
