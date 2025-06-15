FROM eclipse-temurin:17-jre
COPY target/WorkflowDemo-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
