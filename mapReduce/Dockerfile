FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY src src
COPY pom.xml .
RUN ls
RUN mvn clean install

#
# Package stage
#
FROM openjdk:11-jre-slim
#RUN sh -c 'touch mapReduce-0.0.1-SNAPSHOT.jar'
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
COPY --from=build /app/target/mapReduce-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 6969

