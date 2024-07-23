FROM openjdk:17-jdk-slim

ARG FILE_JAR=target/*.jar

WORKDIR /app

COPY ${FILE_JAR} api-service.jar

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 8080