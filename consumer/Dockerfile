FROM openjdk:10-jdk
COPY target/consumer.jar /opt/consumer.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/consumer.jar"]
EXPOSE 9091
