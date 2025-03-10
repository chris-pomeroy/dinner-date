FROM amazoncorretto:21-alpine

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Copy source code
COPY src/ src/

# Run Spring Boot with hot reloading
CMD ["./mvnw", "spring-boot:run"]