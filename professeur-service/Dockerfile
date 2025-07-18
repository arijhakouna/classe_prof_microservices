# Dockerfile pour professeur-service (Développement avec volumes montés)
FROM openjdk:17-jdk-slim

# Installer Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Exposer le port
EXPOSE 8082

# Commande par défaut (sera surchargée par docker-compose)
CMD ["mvn", "spring-boot:run"]