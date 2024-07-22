FROM openjdk:17-jdk AS build

WORKDIR /tmp

# Copy the project files to the container
COPY . /tmp

RUN chmod +x ./gradlew && ./gradlew clean bootJar

FROM openjdk:17-jdk

WORKDIR /tmp

COPY --from=build /tmp/build/libs/doaduo-0.0.1-SNAPSHOT.jar /tmp/doaduo.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /tmp/doaduo.jar"]



