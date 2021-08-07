FROM openjdk:8-jdk-slim

ADD /home/ubuntu/jenkins/workspace/Build_test/Career_Solutions-0.0.1-SNAPSHOT.jar /home/ubuntu/jenkins/workspace/Build_test/latest_build.jar

ENTRYPOINT ["java","-jar","latest_build.jar"]
