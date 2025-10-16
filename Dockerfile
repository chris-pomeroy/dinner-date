FROM amazoncorretto:25.0.0-alpine3.22

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ["./mvnw", "dependency:go-offline"]

# Copy source code
COPY src/ src/

# Build JAR file using Maven
RUN ["./mvnw", "clean", "install"]

# Rename the JAR file
RUN mv target/*.jar service.jar

# Start the service
ENTRYPOINT ["java", "-jar", "service.jar"]