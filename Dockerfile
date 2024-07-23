# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven executable to the container image
COPY mvnw .
COPY .mvn .mvn

# Copy the project files to the container image
COPY pom.xml .
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-jar","/app/target/demo_tomcat.war"]