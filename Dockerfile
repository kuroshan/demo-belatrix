FROM openjdk:8-jdk-alpine
LABEL maintainer="americosierra@hotmail.com"
WORKDIR /workspace

ADD target/notificaciones*.jar app.jar

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8080