FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/todoapi.jar todoapi.jar
EXPOSE 8080
CMD ["java","-jar","todoapi.jar"]