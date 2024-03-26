package com.assessment.azolachat.api.util;

import com.assessment.azolachat.api.model.CreateChatRoomResponse;
import com.assessment.azolachat.api.model.SendMessageResponse;
import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;

public class ModelMapper {
    public static CreateChatRoomResponse buildResponse(Chatroom chatroom) {
        if(chatroom != null) {
            CreateChatRoomResponse response = new CreateChatRoomResponse();
            response.setChatRoomId(chatroom.getChatRoomId());
            response.setDateCreated(chatroom.getDateCreated());
            return response;
        }
        return null;
    }

    public static SendMessageResponse buildResponse(Message message) {
        if(message != null) {
            SendMessageResponse response = new SendMessageResponse();
            response.setChatRoomId(response.getChatRoomId());
            response.setUserId(message.getUserId());
            response.setDatePosted(message.getDatePosted());
            response.setMessageContent(message.getMessageContent());
            return response;
        }
        return null;
    }
}
