FROM maven:3.9.9-amazoncorretto-21

WORKDIR /app

# Copy the Maven wrapper and DB configuration
COPY pom.xml ./

RUN ["mvn", "dependency:resolve"]

# Copy source code
COPY src/ src/

# Run Spring Boot with hot reloading
CMD ["mvn", "spring-boot:run"]