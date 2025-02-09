FROM amazoncorretto:21

WORKDIR /app

COPY . .

RUN ./mvnw package -DskipTests

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

