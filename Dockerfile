# Base image with JDK
FROM openjdk:17-jdk-alpine AS build
WORKDIR /tmp
COPY . /tmp
RUN chmod +x ./gradlew && ./gradlew clean bootJar

FROM openjdk:17-jdk-alpine
WORKDIR /tmp
COPY --from=build /tmp/build/libs/doaduo-0.0.1-SNAPSHOT.jar /tmp/doaduo.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /tmp/doaduo.jar"]