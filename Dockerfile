FROM openjdk:24-ea-21-jdk-slim

WORKDIR /app

# Копируем JAR-файл в контейнер
COPY ./target/MaslyakBank_client-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
