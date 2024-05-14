FROM eclipse-temurin:21-jdk
RUN JAVA_HOME=/opt/java/openjdk

EXPOSE 8080

COPY /target/bac-ml-ms-geolocation-ip-java-1.0-SNAPSHOT.jar /opt/java
CMD ["java","-jar","/opt/java/ac-ml-ms-geolocation-ip-java-1.0-SNAPSHOT.jar"]
