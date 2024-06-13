FROM maven:3.8.7 as build
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build ./target/*.jar studentdetails.jar
EXPOSE 9080
ENTRYPOINT ["java","-jar","studentdetails.jar"]