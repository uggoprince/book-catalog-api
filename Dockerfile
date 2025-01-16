# Use an official Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code into the image
COPY pom.xml ./
COPY src ./src

# Build the application and create a JAR file
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
