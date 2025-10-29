FROM openjdk:24-oraclelinux9 AS builder

WORKDIR /app

COPY mvnw ./
COPY .mvn ./.mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package -DskipTests

FROM openjdk:24-oraclelinux9
WORKDIR /app

WORKDIR /app

COPY --from=builder /app/target/lunchtech-0.0.1-SNAPSHOT.jar /app/lunchtech.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "lunchtech.jar"]