FROM openjdk:11
ADD target/demo-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
