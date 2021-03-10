FROM adoptopenjdk:11-hotspot AS builder
VOLUME /tmp
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootWar

FROM adoptopenjdk:11-jdk-hotspot
COPY --from=builder build/libs/*.war app.war

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.war"]

