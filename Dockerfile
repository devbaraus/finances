FROM gradle:jdk-21-and-22-jammy AS build

WORKDIR /app

COPY build.gradle settings.gradle /app/

COPY src /app/src

RUN gradle bootJar

FROM openjdk:21-bullseye

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]