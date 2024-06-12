FROM openjdk:17-jdk

WORKDIR /app

COPY target/studentdetails-1.0.0.jar /app/studentdetails.jar

EXPOSE 9080

CMD ["java", "-jar", "studentdetails.jar"]