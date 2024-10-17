# Utiliza una imagen base con Maven
FROM maven:3.9.5 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y otros archivos necesarios para la construcción
COPY pom.xml ./
COPY src ./src

# Construye tu aplicación con Maven
RUN mvn clean package -DskipTests

# Cambia a una imagen más ligera de OpenJDK 17 para la ejecución
FROM openjdk:17-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al directorio de trabajo
COPY --from=build /app/target/miTienditaSystem-0.0.1-SNAPSHOT.jar ./

# Exponer el puerto que utilizará la aplicación
EXPOSE 8081

# Define el comando de inicio de la aplicación
CMD ["java", "-jar", "miTienditaSystem-0.0.1-SNAPSHOT.jar"]
