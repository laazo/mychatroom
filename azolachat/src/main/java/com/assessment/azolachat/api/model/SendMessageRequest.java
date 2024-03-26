package com.assessment.azolachat.api.model;

import lombok.Data;

@Data
public class SendMessageRequest {
    private long userId;
    private long chatRoomId;
    private String messageContent;
}
