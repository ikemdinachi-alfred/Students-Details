# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/studentdetails-1.0.0.jar /app/studentdetails.jar

# Expose the port the application runs on
EXPOSE 9080

# Run the jar file
CMD ["java", "-jar", "studentdetails.jar"]
