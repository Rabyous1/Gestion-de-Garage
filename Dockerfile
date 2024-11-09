FROM openjdk:17
EXPOSE 8082
ADD target/test-0.0.1-SNAPSHOT.jar reclamation-dk.jar
ENTRYPOINT ["java","-jar","reclamation-dk.jar"]