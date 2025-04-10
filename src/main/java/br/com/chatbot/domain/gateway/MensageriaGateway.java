package br.com.chatbot.domain.gateway;

public interface MensageriaGateway {
    void enviarMensagem(String numero, String texto);
}
