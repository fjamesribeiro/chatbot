package br.com.chatbot.adapter.in.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.chatbot.adapter.in.dto.MensagemRequestDto;
import br.com.chatbot.application.service.AtendimentoService;
import br.com.chatbot.domain.model.MensagemUsuario;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Void> receber(@RequestBody MensagemRequestDto request) {
		var mensagem = modelMapper.map(request, MensagemUsuario.class);
		atendimentoService.processarMensagem(mensagem);
		return ResponseEntity.ok().build();
	}
}
