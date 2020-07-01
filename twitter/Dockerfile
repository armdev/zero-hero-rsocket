FROM openjdk:11.0.1-jre-slim-sid
COPY target/twitter.jar /opt/twitter.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/twitter.jar"]
EXPOSE 2018
