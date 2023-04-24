FROM openjdk:17-jdk-slim

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY ./target/InternshipTask-0.0.1-SNAPSHOT.jar /usr/src/app

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8888

CMD ["java", "-jar", "InternshipTask-0.0.1-SNAPSHOT.jar"]