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

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
