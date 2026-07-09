# Step 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run stage
FROM eclipse-temurin:21-jre
# This line safely finds whatever .war or .jar file Maven built and renames it to app.war
COPY --from=build /target/*.w* app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]