# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Construye la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia el archivo jar del build
# Asegúrate de reemplazar 'your-app.jar' con el nombre real de tu archivo JAR
COPY --from=build /app/target/DiagnosticoDeAdiccioAlAzucar-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto (ajusta según tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/app.jar"]
