FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build /app/build

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# Первый слой: сборка зависимостей
# Первый слой: установка зависимостей (root)
#FROM gradle:8.5-jdk21-alpine AS dependencies
#WORKDIR /opt/app
#
#RUN apk add --no-cache dos2unix
#
#COPY build.gradle settings.gradle ./
#COPY gradlew gradlew
#COPY gradle gradle
#COPY gradle/wrapper gradle/wrapper
#
#RUN [ -f gradle/wrapper/gradle-wrapper.jar ] || (echo "gradle-wrapper.jar не найден, создаём..." && gradle wrapper)
#
#RUN dos2unix gradlew && chmod +x gradlew
#RUN ./gradlew dependencies --no-daemon
#
#---
#
## Второй слой: сборка и запуск (без root)
#FROM eclipse-temurin:21-jre-alpine AS builder
#WORKDIR /opt/app
#
## Создаем пользователя без root-прав
#RUN addgroup -S appgroup && adduser -S appuser -G appgroup
#
## Переключаемся на пользователя без root-прав
#USER appuser
#
## Копируем загруженные зависимости
#COPY --from=dependencies /opt/app /opt/app
#
## Копируем исходный код
#COPY src src
#
## Собираем приложение
#RUN ./gradlew clean build --no-daemon
#
## Копируем только собранный JAR в финальную папку
#RUN mkdir -p /opt/app/build_output && cp /opt/app/build/libs/*.jar /opt/app/build_output/app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "/opt/app/build_output/app.jar"]
