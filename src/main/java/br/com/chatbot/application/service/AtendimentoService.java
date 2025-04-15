package br.com.chatbot.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.chatbot.domain.gateway.InteligenciaArtificial;
import br.com.chatbot.domain.gateway.MensageriaGateway;
import br.com.chatbot.domain.model.IntencaoDetectada;
import br.com.chatbot.domain.model.MensagemUsuario;

@Service
public class AtendimentoService {

	@Autowired
	private InteligenciaArtificial ia;

	@Autowired
	private MensageriaGateway mensageria;

	private static final Logger log = LoggerFactory.getLogger(AtendimentoService.class);

	public void processarMensagem(MensagemUsuario mensagem) {
		log.debug("Entrou em AtendimentoService");
		IntencaoDetectada intencao = ia.classificarMensagem(mensagem.getTexto());
		log.debug("===detectando intencao===");
		log.debug("serviço: " + intencao.getServico());
		log.debug("tipo: " + intencao.getTipo());

		String resposta;

		switch (intencao.getTipo().toUpperCase()) {
		case "SAUDACAO" -> resposta = "👋 Olá! Seja muito bem-vindo(a) à iPoint. Em que posso te ajudar hoje?";
		case "LOCALIZACAO" ->
			resposta = "📍 Estamos na Av. Dom Luís, 300, loja 158 – Aldeota/ Fortaleza – CE.\n🕒 Atendimento: Seg-Sex 9h-18h, Sáb 9h-13h.";
		case "PRAZO" ->
			resposta = "⏱️ A maioria dos reparos são concluídos no mesmo dia. Troca de tela, por exemplo, leva apenas 30 minutos!";
		case "ORÇAMENTO" -> {
			String servico = intencao.getServico() != null ? intencao.getServico() : "o serviço solicitado";
			resposta = "💰 Você gostaria de saber o valor de " + servico + "? Já te passo mais detalhes!";
		}
		case "HUMANO" -> resposta = "👤 Ok! Encaminhando para um atendente humano. Só um instante...";
		default -> resposta = ia.humanizarResposta(mensagem.getTexto()); // fallback para IA
		}

		log.debug("===humanizando resposta===");
		log.debug("resposta: " + resposta);

		mensageria.enviarMensagem(mensagem.getNumero(), resposta);
	}
}
