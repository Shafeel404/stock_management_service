# Stage 1: Build the JAR using Maven
FROM maven:3.9.3-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight JDK image to run the JAR
FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/stock-management-service-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]