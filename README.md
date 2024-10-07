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

-- Criação da tabela de consultas
CREATE TABLE tb_consultas (
    id BIGINT IDENTITY(1,1),
    data_hora DATETIME2(6),
    observacoes VARCHAR(255),
    prescricao VARCHAR(255),
    status TINYINT,
    medico_id BIGINT,
    paciente_id BIGINT,
    PRIMARY KEY (id),
    CHECK (status BETWEEN 0 AND 3)
);

-- Criação da tabela de endereços
CREATE TABLE tb_endereco (
    id BIGINT IDENTITY(1,1),
    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255),
    complemento VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    rua VARCHAR(255),
    PRIMARY KEY (id)
);

-- Criação da tabela de médicos
CREATE TABLE tb_medicos (
    id BIGINT IDENTITY(1,1),
    crm VARCHAR(255),
    especialidade TINYINT,
    nome VARCHAR(255),
    telefone VARCHAR(255),
    endereco_id BIGINT,
    PRIMARY KEY (id),
    CHECK (especialidade BETWEEN 0 AND 1),
    CONSTRAINT UQ_Medicos_Endereco UNIQUE (endereco_id)
);

-- Criação da tabela de pacientes
CREATE TABLE tb_paciente (
    id BIGINT IDENTITY(1,1),
    data_nascimento DATE,
    nome VARCHAR(255),
    telefone VARCHAR(255),
    endereco_id BIGINT,
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

-- Inserts para a tabela tb_endereco:
INSERT INTO tb_endereco (bairro, cep, cidade, complemento, estado, numero, rua)
VALUES ('Centro TESTE', '12345-678', 'Cidade Exemplo TESTE', 'Apto 101', 'Estado Exemplo', '100', 'Rua Exemplo');

INSERT INTO tb_endereco (bairro, cep, cidade, complemento, estado, numero, rua)
VALUES ('Vila Nova', '54321-987', 'Cidade Nova', 'Casa 2', 'Estado Nova', '200', 'Rua Nova');

INSERT INTO tb_endereco (bairro, cep, cidade, complemento, estado, numero, rua)
VALUES ('Jardins', '56789-123', 'Cidade Jardim', 'Bloco B', 'Estado Jardim', '300', 'Avenida Flores');

INSERT INTO tb_endereco (bairro, cep, cidade, complemento, estado, numero, rua)
VALUES ('Alphaville', '78945-321', 'Cidade Alphaville', 'Sala 301', 'Estado Rico', '400', 'Avenida Luxo');

INSERT INTO tb_endereco (bairro, cep, cidade, complemento, estado, numero, rua)
VALUES ('Pinheiros', '95123-654', 'Cidade Pinheiros', 'Apto 502', 'Estado São Paulo', '500', 'Rua Verde');

-- Inserts para a tabela tb_paciente:
INSERT INTO tb_paciente (data_nascimento, endereco_id, nome, telefone)
VALUES (CONVERT(DATE, '1985-08-15', 120), 1, 'Maria Souza', '(11) 98888-8888');

INSERT INTO tb_paciente (data_nascimento, endereco_id, nome, telefone)
VALUES (CONVERT(DATE, '1990-05-10', 120), 2, 'Carlos Silva', '(21) 97777-7777');

INSERT INTO tb_paciente (data_nascimento, endereco_id, nome, telefone)
VALUES (CONVERT(DATE, '1978-12-22', 120), 3, 'Ana Pereira', '(31) 96666-6666');

INSERT INTO tb_paciente (data_nascimento, endereco_id, nome, telefone)
VALUES (CONVERT(DATE, '2000-03-30', 120), 4, 'Bruno Costa', '(41) 95555-5555');

INSERT INTO tb_paciente (data_nascimento, endereco_id, nome, telefone)
VALUES (CONVERT(DATE, '1965-07-18', 120), 5, 'Luiza Almeida', '(51) 94444-4444');

-- Inserts para a tabela tb_medicos:
INSERT INTO tb_medicos (crm, endereco_id, especialidade, nome, telefone)
VALUES ('123456', 1, 0, 'Dr. João Silva', '(11) 99999-9999');

INSERT INTO tb_medicos (crm, endereco_id, especialidade, nome, telefone)
VALUES ('654321', 2, 1, 'Dra. Fernanda Mendes', '(21) 98888-8888');

INSERT INTO tb_medicos (crm, endereco_id, especialidade, nome, telefone)
VALUES ('789012', 3, 0, 'Dr. Ricardo Lopes', '(31) 97777-7777');

INSERT INTO tb_medicos (crm, endereco_id, especialidade, nome, telefone)
VALUES ('345678', 4, 1, 'Dra. Paula Souza', '(41) 96666-6666');

INSERT INTO tb_medicos (crm, endereco_id, especialidade, nome, telefone)
VALUES ('987654', 5, 0, 'Dr. Pedro Santos', '(51) 95555-5555');

-- Inserts para a tabela tb_consultas:
INSERT INTO tb_consultas (data_hora, observacoes, prescricao, status, medico_id, paciente_id)
VALUES ('2024-09-15 09:00:00', 'Consulta de rotina', 'Xarope 2x ao dia', 1, 1, 1);

INSERT INTO tb_consultas (data_hora, observacoes, prescricao, status, medico_id, paciente_id)
VALUES ('2024-09-16 10:30:00', 'Dor nas costas', 'Analgésico', 2, 2, 2);

INSERT INTO tb_consultas (data_hora, observacoes, prescricao, status, medico_id, paciente_id)
VALUES ('2024-09-17 14:45:00', 'Dor de cabeça frequente', 'Repouso e analgésico', 0, 3, 3);

INSERT INTO tb_consultas (data_hora, observacoes, prescricao, status, medico_id, paciente_id)
VALUES ('2024-09-18 08:15:00', 'Exame de rotina', 'Suplemento vitamínico', 1, 4, 4);

INSERT INTO tb_consultas (data_hora, observacoes, prescricao, status, medico_id, paciente_id)
VALUES ('2024-09-19 13:30:00', 'Revisão pós-cirúrgica', 'Anti-inflamatório', 2, 5, 5);

-- Selects:
SELECT * FROM tb_paciente;
SELECT * FROM tb_endereco;
SELECT * FROM tb_medicos;
SELECT * FROM tb_consultas;

-- Updates para a tabela tb_endereco:

-- Atualiza o registro com id = 1
UPDATE tb_endereco
SET bairro = 'Centro Atualizado', cep = '11111-111', cidade = 'Cidade Atualizada', complemento = 'Apto 202', estado = 'Estado Atualizado', numero = '101', rua = 'Rua Atualizada'
WHERE id = 1;

-- Atualiza o registro com id = 2
UPDATE tb_endereco
SET bairro = 'Vila Nova Atualizada', cep = '22222-222', cidade = 'Cidade Nova Atualizada', complemento = 'Casa 3', estado = 'Estado Nova Atualizada', numero = '201', rua = 'Rua Nova Atualizada'
WHERE id = 2;

-- Atualiza o registro com id = 3
UPDATE tb_endereco
SET bairro = 'Jardins Atualizado', cep = '33333-333', cidade = 'Cidade Jardim Atualizada', complemento = 'Bloco C', estado = 'Estado Jardim Atualizado', numero = '301', rua = 'Avenida Flores Atualizada'
WHERE id = 3;

-- Atualiza o registro com id = 4
UPDATE tb_endereco
SET bairro = 'Alphaville Atualizado', cep = '44444-444', cidade = 'Cidade Alphaville Atualizada', complemento = 'Sala 302', estado = 'Estado Rico Atualizado', numero = '401', rua = 'Avenida Luxo Atualizada'
WHERE id = 4;

-- Atualiza o registro com id = 5
UPDATE tb_endereco
SET bairro = 'Pinheiros Atualizado',

