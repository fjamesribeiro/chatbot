FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/chatbot-0.0.1.jar chatbot.jar
ENTRYPOINT ["java", "-jar", "chatbot.jar"]