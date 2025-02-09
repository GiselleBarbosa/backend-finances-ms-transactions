FROM amazoncorretto:21

WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
COPY src ./src

RUN yum install -y tar gzip

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

RUN ls -l target/

COPY target/finances-ms-transactions-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
