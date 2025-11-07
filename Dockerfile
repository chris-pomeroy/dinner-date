FROM amazoncorretto:25.0.0-alpine3.22 AS build

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ["./mvnw", "dependency:go-offline"]

# Copy source code
COPY src/ src/

# Build JAR file using Maven
RUN ["./mvnw", "clean", "install"]

FROM amazoncorretto:25.0.0-alpine3.22

# Copy JAR from build stage
COPY --from=build /app/target/*.jar service.jar

# Start the service
CMD ["java", "-jar", "service.jar"]