FROM openjdk:8-jdk-slim

ADD /home/ubuntu/jenkins/workspace/Build_test/Career_Solutions-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","docker_demo-0.0.1-SNAPSHOT.jar"]
