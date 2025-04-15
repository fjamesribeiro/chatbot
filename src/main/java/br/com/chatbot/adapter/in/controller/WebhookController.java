package br.com.chatbot.adapter.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.chatbot.adapter.in.dto.evolution.MensagemRequestDto;
import br.com.chatbot.adapter.in.factory.MensagemUsuarioFactory;
import br.com.chatbot.application.service.AtendimentoService;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private MensagemUsuarioFactory mensagemUsuarioFactory;
	
	private static final Logger log = LoggerFactory.getLogger(WebhookController.class);	

	@PostMapping
	public ResponseEntity<Void> receber(@RequestBody MensagemRequestDto request) {
		var data = request.getData();
		var key = data.getKey();

		try {
			if (key.getParticipant() == null) {

				String nome = data.getPushName();
				String texto = data.getMessage().getConversation();
				String numero = key.getRemoteJid().split("@")[0];

				log.debug("📥 " + nome + " (" + numero + ") disse: 💬 \"" + texto + "\"");

				if (numero.contains("558585235024")) {
					log.debug("📥 Mensagem recebida no webhook");
					log.debug("Enviando de volta");
					log.debug("👤 Nome: " + nome + " | 📱 Número: " + numero);
					log.debug("💬 Texto: " + texto);
					log.debug("───────────────────────────────────────");

					var mensagem = mensagemUsuarioFactory.fromRequest(request);
					log.debug(mensagem.getNumero());
					log.debug(mensagem.getTexto());
					atendimentoService.processarMensagem(mensagem);

					return ResponseEntity.ok().build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.noContent().build(); // não esquece de retornar algo fora do if
	}
}
