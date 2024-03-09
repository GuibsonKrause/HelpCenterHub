# Usar a imagem oficial do Java 17 como base
FROM eclipse-temurin:17-jdk-alpine

# Copiar o jar compilado da aplicação Spring Boot para o contêiner
ADD backend/helpcenterhub/target/helpcenterhub-0.0.1-SNAPSHOT.jar app.jar

# Informar ao Docker para executar a aplicação quando o contêiner iniciar
ENTRYPOINT ["java","-jar","app.jar"]
