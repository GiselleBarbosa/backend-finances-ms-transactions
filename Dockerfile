FROM amazoncorretto:21

WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
COPY src ./src

RUN yum install -y tar gzip

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

RUN ls -l target/

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
