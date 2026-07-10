# Step 1: Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Runtime Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the compiled JAR file
COPY --from=build /app/target/spring-chatbot-0.0.1-SNAPSHOT.jar app.jar

# CRUCIAL FOR JSP: Copy the webapp directory into the runtime container
COPY --from=build /app/src/main/webapp ./src/main/webapp

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]