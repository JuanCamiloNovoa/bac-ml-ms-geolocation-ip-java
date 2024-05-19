FROM eclipse-temurin:21-jdk
ENV JAVA_HOME=/opt/java/openjdk
WORKDIR /opt/java
EXPOSE 8090
COPY target/bac-ml-ms-geolocation-ip-java-1.0-SNAPSHOT.jar /opt/java/app.jar
CMD ["java", "-jar", "app.jar"]