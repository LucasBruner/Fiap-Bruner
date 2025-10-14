FROM openjdk:24-rc-nanoserver-ltsc2025 # Escolha sua versão do JDK
WORKDIR /app

COPY target/app.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]