FROM openjdk:8-jdk-alpine
LABEL maintainer "Test running BaseProject"

RUN mkdir /jar
COPY target/gui-0.0.1-SNAPSHOT.jar /jar 
RUN chmod +x /jar/gui-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","/jar/gui-0.0.1-SNAPSHOT.jar"]

USER root


