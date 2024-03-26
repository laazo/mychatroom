package com.assessment.azolachat.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {
    private long messageId;
    private long userId;
    private long chatRoomId;
    private String messageContent;
    private LocalDateTime datePosted;
}
