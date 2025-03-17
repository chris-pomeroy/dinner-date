FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY pom.xml ./

# Copy source code
COPY src/ src/

# Run Spring Boot with hot reloading
RUN ["mvn", "clean", "install"]

FROM amazoncorretto:21-alpine3.21

WORKDIR /app

COPY --from=build /app/target/*.jar dinner-date.jar

RUN ["java", "-jar", "dinner-date.jar"]