FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/scala-3.5.0/contacthub-scala.jar /app/contacthub-scala.jar

ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=contacthub
ENV DB_USER=contacthub
ENV DB_PASSWORD=contacthub
ENV HTTP_HOST=0.0.0.0
ENV HTTP_PORT=8081

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/contacthub-scala.jar"]
