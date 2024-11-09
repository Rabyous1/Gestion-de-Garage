FROM openjdk:17
EXPOSE 8081
ADD target/stock-dk.jar stock-dk.jar
ENTRYPOINT ["java","-jar","stock-dk.jar"]
