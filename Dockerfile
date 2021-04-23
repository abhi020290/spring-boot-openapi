  
FROM openjdk/8-jdk-alpine	
COPY ./target/ecosystem-user-service.jar /spring-boot-openapi.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "./spring-boot-openapi.jar"]
