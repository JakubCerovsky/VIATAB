# Build app
FROM maven:3-eclipse-temurin-19-alpine AS build
RUN apk update && apk add binutils
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -B package --file pom.xml -DskipTests

# Create small run time image
FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
COPY --from=build /app/target/backend-1.0.jar backend-1.0.jar
ENTRYPOINT ["java","-jar","backend-1.0.jar"]