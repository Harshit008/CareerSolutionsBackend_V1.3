FROM openjdk:8-jdk-slim

ADD Career_Solutions-0.0.1-SNAPSHOT.jar latest_build.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","latest_build.jar"]
