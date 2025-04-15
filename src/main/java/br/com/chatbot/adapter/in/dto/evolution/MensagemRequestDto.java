package br.com.chatbot.adapter.in.dto.evolution;

import lombok.Data;

@Data
public class MensagemRequestDto {
    private String event;
    private String instance;
    private DataPayload data;

    @lombok.Data
    public static class DataPayload {
        private Key key;
        private String pushName;
        private String status;
        private Message message;
        private String messageType;
        private Long messageTimestamp;
    }

    @lombok.Data
    public static class Key {
        private String remoteJid;
        private Boolean fromMe;
        private String id;
        private String participant;
    }

    @lombok.Data
    public static class Message {
        private String conversation;
    }
}
