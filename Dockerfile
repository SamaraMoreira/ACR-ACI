# Usa uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Instala o Maven
RUN apt-get update && apt-get install -y maven && apt-get clean

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Compila o projeto sem rodar os testes
RUN mvn clean install -DskipTests

# Define o comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "target/aihack-0.0.1-SNAPSHOT.jar"]

# Expõe a porta da aplicação (ajuste a porta conforme necessário)
EXPOSE 8080


