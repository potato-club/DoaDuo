#FROM openjdk:17-jdk AS build
#WORKDIR /app
#COPY . /app
#RUN chmod +x ./gradlew
#
#RUN microdnf install -y findutils
#RUN ./gradlew bootJar
#
#ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar"]
#
FROM openjdk:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy the application files
COPY . /app

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Install findutils and build the application
RUN microdnf install -y findutils && microdnf clean all && ./gradlew bootJar

# Second stage: create a smaller image without build tools
FROM openjdk:17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar

# Set the entrypoint for the container
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/build/libs/doaduo-0.0.1-SNAPSHOT.jar"]
