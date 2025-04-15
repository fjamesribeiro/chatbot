package br.com.chatbot.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.chatbot.application.service.AtendimentoService;
import br.com.chatbot.domain.model.MensagemUsuario;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private AtendimentoService atendimentoService;

	@PostMapping
	public ResponseEntity<Void> receberSimples(@RequestBody MensagemUsuario mensagem) {
	    try {
	        atendimentoService.processarMensagem(mensagem);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	
}
