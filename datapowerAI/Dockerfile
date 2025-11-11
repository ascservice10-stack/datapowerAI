# Step 1: Use Maven image to build the jar
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Use lightweight JRE image for running the app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set environment variables (optional)
ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_OPTS=""

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]