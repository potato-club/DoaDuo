# First stage: build the application
FROM openjdk:17-jdk AS build

WORKDIR /app

# Copy the project files to the build container
COPY . /app

# Run the build process (adjust the build command as needed, e.g., using Gradle or Maven)
# For example, using Gradle:
RUN ./gradlew build

# Second stage: create the runtime image
FROM openjdk:17-jdk

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar"]