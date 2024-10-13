FROM amazoncorretto:21-alpine3.20
COPY build/libs/hermes-api-0.0.1-SNAPSHOT.jar hermes.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom -Xms 512 -Xmx 1024", "-jar", "hermes.jar"]