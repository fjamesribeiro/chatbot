# 🤖 WhatsApp Chatbot MVP (com Evolution API + IA)

Este é um MVP de um chatbot inteligente para WhatsApp, construído com foco em arquitetura hexagonal, integração com IA gratuita via OpenRouter (ChatGPT Free) e comunicação via Evolution API.

## ✨ Funcionalidades

- Recebe mensagens via Evolution API (Webhook)
- Detecta intenções e classifica mensagens (ex: orçamento, localização, etc)
- Gera respostas humanizadas usando IA gratuita
- Envia respostas de volta para o WhatsApp
- Pronto para escalar para diferentes ramos de negócio

---

## 🧠 Arquitetura

- Spring Boot 3.4 com arquitetura hexagonal (clean)
- Adapters para:
  - IA (`ChatGptFreeIaAdapter`)
  - WhatsApp (`EvolutionApiAdapter`)
- Separação por camadas: `domain`, `application`, `adapter.in`, `adapter.out`

---

## 🐳 Docker

### Pré-requisitos:
- Docker
- Docker Compose
- Java 17+
- Maven

### Build do projeto:
```bash
mvn clean package -DskipTests
