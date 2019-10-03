FROM openjdk:10-jdk
COPY target/twitter.jar /opt/twitter.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/twitter.jar"]
EXPOSE 2018
