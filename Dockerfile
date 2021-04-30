FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]