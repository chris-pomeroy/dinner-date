FROM amazoncorretto:21-alpine3.21-jdk

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ["./mvnw", "dependency:resolve"]

# Copy source code
COPY src/ src/

# Build JAR file using Maven
RUN ["./mvnw", "clean", "install"]

# Start the service
ENTRYPOINT ["java", "-jar", "target/*.jar"]