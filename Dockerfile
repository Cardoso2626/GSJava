FROM eclipse-temurin:22-jdk AS BUILD
WORKDIR /usr/app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM eclipse-temurin:22-jre
WORKDIR /app

COPY --from=BUILD /usr/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
