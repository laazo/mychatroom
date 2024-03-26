package com.assessment.azolachat.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendMessageResponse {
    private long userId;
    private long chatRoomId;
    private String messageContent;
    private LocalDateTime datePosted;
}
