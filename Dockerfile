# Build stage
FROM maven:3.8.7 AS build
WORKDIR /app
COPY . .
RUN mvn -B clean package -DskipTests

# Run stage
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar StudentDetails.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","StudentDetails.jar"]
