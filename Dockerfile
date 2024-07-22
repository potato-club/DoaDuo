FROM openjdk:17-jdk

WORKDIR /app

# Copy the project files to the container
COPY . /app

# If the JAR file is already present in the project directory, no need for the additional copy
# Ensure that your JAR file path is correct
COPY /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar"]