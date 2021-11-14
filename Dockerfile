FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

FROM openjdk:18-jdk-alpine

ARG JAR_FILE=aq-early-warning-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","/opt/app/aq-early-warning-0.0.1-SNAPSHOT.jar"]
