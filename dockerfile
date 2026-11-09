
# ETAPA 1: CONSTRUCCIÓN (BUILD)

FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

ENV MAVEN_OPTS="-Xmx512m -XX:MaxMetaspaceSize=256m"

WORKDIR /app

# 1. Copiar solo el pom.xml primero (aprovecha la caché de Docker)
COPY pom.xml .

# 2. Descargar dependencias (esto separa la descarga de la compilación)
RUN mvn dependency:go-offline -B

# 3. Copiar el código fuente
COPY src ./src

# 4. Compilar en modo "Batch" (-B) para reducir el uso de memoria y logs
RUN mvn clean package -DskipTests -B


# ETAPA 2: IMAGEN FINAL LIGERA (RUNTIME)

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar únicamente el archivo .jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-XX:MaxMetaspaceSize=256m", "-jar", "app.jar"]