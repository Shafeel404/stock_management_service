# Use Maven image with Java installed
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Add JitPack repository to settings.xml (optional but safer)
COPY settings.xml /root/.m2/settings.xml

# Copy source code
WORKDIR /app
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller image for running the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=build /app/target/stock-management-service-0.0.1.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
