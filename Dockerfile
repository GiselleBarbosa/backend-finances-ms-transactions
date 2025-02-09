FROM amazoncorretto:21

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

