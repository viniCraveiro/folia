FROM eclipse-temurin:21-jdk-alpine AS final

WORKDIR /app

COPY /target/folia.jar ./folia.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "folia.jar"]
