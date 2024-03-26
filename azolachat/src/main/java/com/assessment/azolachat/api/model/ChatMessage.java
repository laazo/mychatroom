package com.assessment.azolachat.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private long messageId;
    private long chatRoomId;
    private String messageContent;
    private LocalDateTime datePosted;
}
