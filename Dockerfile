FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /home/app
COPY pom.xml /home/app
RUN ["mvn", "verify", "clean", "--fail-never"]
COPY src /home/app/src
RUN mvn clean package


FROM openjdk:18-alpine
WORKDIR /home/lib
COPY --from=build /home/app/target/codeList-service.jar /home/lib/app.jar
ENTRYPOINT ["java", "-Ddb_username=$DB_USERNAME", "-Ddb_password=$DB_PASSWORD", "-Dserver_port=$SERVER_PORT", "-jar", "app.jar"]