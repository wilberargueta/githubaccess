FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY application.yml application.yml
EXPOSE 9191
ENTRYPOINT ["java","-jar","/app.jar"]