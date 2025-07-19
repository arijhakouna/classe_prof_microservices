#!/bin/bash
set -e

echo " Démarrage du classe-service avec shared library..."

# Vérifier que la shared library est disponible
if [ -f "/app/shared-library/target/shared-library-1.0.0.jar" ]; then
    echo " Shared library trouvée, installation en cours..."
    
    # Créer le répertoire Maven local
    mkdir -p /root/.m2/repository/com/microservice/shared-library/1.0.0/
    
    # Copier les fichiers
    cp /app/shared-library/target/shared-library-1.0.0.jar /root/.m2/repository/com/microservice/shared-library/1.0.0/
    cp /app/shared-library/pom.xml /root/.m2/repository/com/microservice/shared-library/1.0.0/shared-library-1.0.0.pom
    
    echo " Shared library installée avec succès !"
else
    echo " Shared library non trouvée, utilisation des DTOs locaux..."
fi

# Démarrer l'application
echo " Démarrage de l'application Spring Boot..."
exec mvn spring-boot:run 