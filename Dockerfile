# =========================
# Stage 1 — Build the app
# =========================
FROM eclipse-temurin:21-jdk AS builder

# Set working directory
WORKDIR /app

# Copy Maven/Gradle wrapper & build files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (improves build caching)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Package the Spring Boot app
RUN ./mvnw clean package -DskipTests

# =========================
# Stage 2 — Run the app
# =========================
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from builder
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render will override with $PORT)
EXPOSE 8080

# Use environment variable PORT for Render
ENTRYPOINT ["java", "-jar", "app.jar"]
