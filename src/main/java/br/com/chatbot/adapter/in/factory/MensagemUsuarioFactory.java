package br.com.chatbot.adapter.in.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.chatbot.adapter.in.dto.evolution.MensagemRequestDto;
import br.com.chatbot.domain.model.MensagemUsuario;

@Component
public class MensagemUsuarioFactory {
	
	private static final Logger log = LoggerFactory.getLogger(MensagemUsuarioFactory.class);	

    public MensagemUsuario fromRequest(MensagemRequestDto request) {
    	
    	log.debug("Entrou em MensagemUsuarioFactory");
    	
        if (request == null || request.getData() == null) return null;

        var data = request.getData();
        var key = data.getKey();
        var msg = data.getMessage();

        if (key == null || msg == null) return null;

        if (Boolean.TRUE.equals(key.getFromMe())) {
            return null;
        }

        // ✅ Extrai o número sem o @
        String remoteJid = key.getRemoteJid();
        String numero = (remoteJid != null && remoteJid.contains("@")) ? remoteJid.split("@")[0] : null;

        // ✅ Extrai o texto da conversa
        String texto = msg.getConversation();

        // Se não tem conteúdo, não cria objeto
        if (numero == null || texto == null || texto.isBlank()) {
            return null;
        }

        return new MensagemUsuario(numero, texto);
    }
}
