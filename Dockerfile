FROM openjdk:17-jdk-slim
COPY target/basketball-0.0.1-SNAPSHOT.jar basketball.jar
ENTRYPOINT ["java","-jar","/basketball.jar"]