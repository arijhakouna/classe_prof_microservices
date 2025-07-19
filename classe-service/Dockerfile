# Dockerfile pour classe-service (Développement avec volumes montés)
FROM openjdk:17-jdk-slim

# Installer Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Exposer le port
EXPOSE 8081

# Copier le script d'entrée
COPY entrypoint.sh /app/
RUN chmod +x /app/entrypoint.sh

# Commande par défaut (sera surchargée par docker-compose)
CMD ["/app/entrypoint.sh"]