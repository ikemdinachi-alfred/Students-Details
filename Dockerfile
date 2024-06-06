FROM maven:3.8.7 as build
COPY src/test/java .
RUN mvn -B clean package -DskipTests
FROM openjdk:17
COPY --from=build ./target/*.jar StudentDetails.jar
EXPOSE = 8082
ENTRYPOINT ["java","-jar","StudentDetails.jar"]
