package br.com.chatbot.domain.gateway;

import br.com.chatbot.domain.model.IntencaoDetectada;

public interface InteligenciaArtificial {
    IntencaoDetectada classificarMensagem(String texto);
    String humanizarResposta(String base);
}
