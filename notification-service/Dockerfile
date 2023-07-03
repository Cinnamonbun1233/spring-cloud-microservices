FROM openjdk:15-jdk-alpine
EXPOSE 8989
COPY build/libs/notification-service-1.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "notification-service-1.0-SNAPSHOT"]