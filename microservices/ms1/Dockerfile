# Base image is pulled through the bank's Artifactory Docker virtual registry.
ARG BASE_REGISTRY=artifactory.corporate.io/docker-virtual
FROM ${BASE_REGISTRY}/eclipse-temurin:17-jre

WORKDIR /app
COPY target/app.jar app.jar

USER 1001
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
