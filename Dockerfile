## Dependencies stage
FROM eclipse-temurin:21-jdk-alpine AS dependencies

RUN apk add --no-cache maven

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

## Build stage
FROM dependencies AS builder
COPY src ./src
RUN mvn clean package -DskipTests

## Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
