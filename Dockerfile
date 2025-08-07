# Stage 1: Build the application and create native binary
FROM ghcr.io/graalvm/graalvm-community:17-ol9-java17 as builder

# Install native-image component
RUN gu install native-image

WORKDIR /app

# Copy Maven wrapper and POM
COPY mvnw .mvn/ .mvn/
COPY pom.xml .

# Make Maven wrapper executable and install dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src/ src/

# Build the native binary with native profile
RUN ./mvnw -Pnative -DskipTests clean package

# Stage 2: Create minimal runtime image
FROM oraclelinux:9-slim

# Set the working directory
WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/target/stock-management-service .

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["./stock-management-service"]

FROM debian:bullseye-slim
RUN apt-get update && apt-get install -y --no-install-recommends libstdc++6 zlib1g && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=builder /app/target/stock-management-service /app/stock-management-service
RUN chmod +x /app/stock-management-service
EXPOSE 8080
USER 1000
ENTRYPOINT ["/app/stock-management-service"]