# Используем официальный образ для Java
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR-файл из локальной машины в контейнер
COPY target/MaslyakBank_client-0.0.1-SNAPSHOT.jar /app/MaslyakBank_client-0.0.1-SNAPSHOT.jar

# Открываем порт 8080 для доступа к приложению

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/MaslyakBank_client-0.0.1-SNAPSHOT.jar"]
