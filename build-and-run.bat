@echo off
echo =============================
echo 🚀 Gerando novo .jar com Maven
echo =============================

call mvn clean package -DskipTests

if exist target\chatbot-0.0.1.jar (
    echo ✅ JAR gerado com sucesso.
    echo Renomeando para chatbot.jar...

    copy /Y target\chatbot-0.0.1.jar target\chatbot.jar
) else (
    echo ❌ ERRO: chatbot-0.0.1.jar não foi encontrado!
    exit /b 1
)

echo =============================
echo 🐳 Derrubando containers antigos
echo =============================
docker compose down

echo =============================
echo 🐳 Subindo com nova build
echo =============================
docker compose up --build
