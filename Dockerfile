FROM openjdk:8
ADD target/*.jar pauta-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pauta-api.jar"]