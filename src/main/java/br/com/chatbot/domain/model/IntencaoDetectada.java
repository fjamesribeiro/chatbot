package br.com.chatbot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntencaoDetectada {
    private String tipo;
    private String servico;
}
