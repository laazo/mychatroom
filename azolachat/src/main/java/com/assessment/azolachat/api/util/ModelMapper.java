package com.assessment.azolachat.api.util;

import com.assessment.azolachat.api.model.ChatMessageDto;
import com.assessment.azolachat.api.model.ChatRoomResponse;
import com.assessment.azolachat.api.model.SendMessageResponse;
import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelMapper {
    public static ChatRoomResponse buildResponse(Chatroom chatroom) {
        if(chatroom != null) {
            ChatRoomResponse response = new ChatRoomResponse();
            response.setChatRoomId(chatroom.getChatRoomId());
            response.setChatRoomName(chatroom.getChatRoomName());
            response.setDateCreated(chatroom.getDateCreated());
            response.setChatMessages(buildMessages(chatroom.getMessages()));
            return response;
        }
        return null;
    }

    private static List<ChatMessageDto> buildMessages(List<Message> messages) {
        if(messages != null && !messages.isEmpty()) {
            List<ChatMessageDto> resultMessages = new ArrayList<>();
            for(Message message : messages) {
                ChatMessageDto chatMessage = new ChatMessageDto();
                BeanUtils.copyProperties(message, chatMessage);
                resultMessages.add(chatMessage);
            }
            return resultMessages;
        }

        return Collections.emptyList();
    }

    public static SendMessageResponse buildResponse(Message message) {
        if(message != null) {
            SendMessageResponse response = new SendMessageResponse();
            response.setChatRoomId(message.getChatRoomId());
            response.setUserId(message.getUserId());
            response.setDatePosted(message.getDatePosted());
            response.setMessageContent(message.getMessageContent());
            return response;
        }
        return null;
    }
}
