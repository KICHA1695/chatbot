# Step 1: Build the Maven application inside a Java 21 environment
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the compiled WAR using JDK 21 runtime
FROM eclipse-temurin:21-jre
COPY --from=build /target/*.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]