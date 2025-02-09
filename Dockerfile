FROM amazoncorretto:21

WORKDIR /app

COPY . .

RUN yum install -y tar

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

COPY target/*.jar app.jar

