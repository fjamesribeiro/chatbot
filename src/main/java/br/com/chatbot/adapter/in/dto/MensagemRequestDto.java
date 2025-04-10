package br.com.chatbot.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemRequestDto {
    private String numero;
    private String texto;

}
