FROM openjdk:11-jre-slim
MAINTAINER by.maksimovich
COPY target/application-0.0.1-SNAPSHOT.jar application-1.0.jar
ENTRYPOINT ["java","-jar","/application-1.0.jar"]