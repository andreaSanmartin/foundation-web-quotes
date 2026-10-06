# ---------- Etapa 1: compilar el .jar con Maven ----------
FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /build

# Descargar dependencias primero (se cachean si pom.xml no cambia)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ---------- Etapa 2: imagen final, solo con Java ----------
FROM eclipse-temurin:8-jre

# Usuario sin privilegios. Los documentos de beneficiarios se guardan en
# ~/Documents/DocumentosBeneficiarios (ver DocumentosBeneficiarioController).
RUN useradd --create-home --home-dir /home/app app \
 && mkdir -p /home/app/Documents/DocumentosBeneficiarios \
 && chown -R app:app /home/app
USER app
WORKDIR /home/app

COPY --from=build /build/target/*.jar app.jar

# La configuracion se pasa con variables de entorno (ver .env.example)
ENV PORT=3000
EXPOSE 3000
VOLUME /home/app/Documents/DocumentosBeneficiarios

ENTRYPOINT ["java", "-jar", "app.jar"]
