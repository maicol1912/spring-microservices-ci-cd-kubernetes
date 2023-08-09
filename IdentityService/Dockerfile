FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} identityservice.jar

ENTRYPOINT ["java","-jar","/identityservice.jar"]

EXPOSE 8085