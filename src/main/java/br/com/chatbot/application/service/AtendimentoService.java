package br.com.chatbot.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.chatbot.domain.gateway.InteligenciaArtificial;
import br.com.chatbot.domain.gateway.MensageriaGateway;
import br.com.chatbot.domain.model.MensagemUsuario;
import br.com.chatbot.domain.model.IntencaoDetectada;

@Service
public class AtendimentoService {

	@Autowired
    private InteligenciaArtificial ia;
	
	@Autowired
	private MensageriaGateway mensageria;

    public void processarMensagem(MensagemUsuario mensagem) {
        IntencaoDetectada intencao = ia.classificarMensagem(mensagem.getTexto());
        String resposta = ia.humanizarResposta("Detectado: " + intencao.getTipo());
        mensageria.enviarMensagem(mensagem.getNumero(), resposta);
    }
}
