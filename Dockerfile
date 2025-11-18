FROM amazoncorretto:21

WORKDIR /app

# Copiamos tudo o que o Maven precisa
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

# Permissão de execução
RUN chmod +x mvnw

# Instalar tar + gzip (necessários para mvnw)
RUN yum install -y tar gzip

# Build da aplicação dentro da imagem
RUN ./mvnw clean package -DskipTests

# Copiar o jar gerado pelo Maven dentro da imagem
RUN cp target/finances-ms-transactions-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
