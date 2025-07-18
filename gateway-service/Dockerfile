# Dockerfile pour gateway-service (Développement avec volumes montés)
FROM openjdk:17-jdk-slim

# Installer Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /app

EXPOSE 8080

# Commande par défaut (sera surchargée par docker-compose)
CMD ["mvn", "spring-boot:run"]