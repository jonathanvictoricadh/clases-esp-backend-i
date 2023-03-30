FROM openjdk:17-alpine3.12
ARG JAR_FILE=target/api-course.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9002
