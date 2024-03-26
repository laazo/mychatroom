package com.assessment.azolachat.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatRoomResponse {
    private long chatRoomId;
    private String chatRoomName;
    private LocalDateTime dateCreated;
    private List<ChatMessageDto> chatMessages;
}
