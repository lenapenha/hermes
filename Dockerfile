FROM amazoncorretto:21-alpine3.20
COPY build/libs/hermes-api-0.0.1-SNAPSHOT.jar hermes.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hermes.jar"]