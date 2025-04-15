package br.com.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.chatbot.adapter.out.ia.OpenRouterIaAdapter;
import br.com.chatbot.domain.gateway.InteligenciaArtificial;

@Configuration
public class Config {

	@Value("${ia.tipo}")
	private String tipo;

	@Bean
	InteligenciaArtificial ia(OpenRouterIaAdapter openRouter) {
		return openRouter; // futuramente, condição para múltiplas IAs
	}

	@Bean
	RestTemplate rest() {
		return new RestTemplate();
	}

}
