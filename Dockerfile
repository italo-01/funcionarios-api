FROM  maven:3.10.0-eclipse-temurin-21-alpine AS build

WORKDIR /app

copy target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

