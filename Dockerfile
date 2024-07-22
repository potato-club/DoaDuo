# First stage: build the application
FROM openjdk:17-jdk AS build

WORKDIR /tmp

# Install necessary packages (e.g., xargs)
RUN apt-get update && apt-get install -y xargs

# Copy the project files to the build container
COPY . /tmp

# Ensure gradlew is executable and build the project
RUN chmod +x ./gradlew && ./gradlew clean bootJar

# Second stage: create the runtime image
FROM openjdk:17-jdk

WORKDIR /tmp

# Copy the JAR file from the build stage
COPY --from=build /tmp/build/libs/doaduo-0.0.1-SNAPSHOT.jar /tmp/doaduo.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /tmp/doaduo.jar"]