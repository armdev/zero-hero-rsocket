FROM openjdk:11.0.1-jre-slim-sid
COPY target/producer.jar /opt/producer.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/producer.jar"]
EXPOSE 9090
